package co.com.ceiba.estacionamiento.servicio;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dao.DaoBahia;
import co.com.ceiba.estacionamiento.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.dao.DaoTipo;
import co.com.ceiba.estacionamiento.dao.DaoVehiculo;
import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.excepcion.error.ErrorCodes;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true) 
public class ServiceVehiculoContrato implements ServiceVehiculo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceVehiculoContrato.class);
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoVehiculo daoVehiculo;
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoBahia daoBahia;
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoParqueo daoParqueo;
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoTipo daoTipo;

	/**
	 * Metodo que inserta un vehiculo
	 * @param vehiculo
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void insertarVehiculo(Vehiculo vehiculo) throws EstacionamientoException {
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Inicio del metodo");
		Date fechaSistema = new Date();
		
		// Validaciones
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Validar placa");
		validarPlaca(vehiculo.getPlaca().toLowerCase(), fechaSistema);
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Validar vehiculo");
		validarVehiculoEntrada(vehiculo);
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Validar bahia");
		validarBahia(vehiculo.getIdTipo());
		
		// Insertar vehiculo
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Insertar vehiculo");
		daoVehiculo.insertVehiculo(vehiculo);
		
		// Asignar Bahia
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Encontrar bahia por su id");
		Bahia bahia = daoBahia.findBahiaById(vehiculo.getIdBahia());
		bahia.setEstado("Ocupado");
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Bahia encontrada y modificada: " +bahia);
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Actualizar bahia");
		daoBahia.updateBahia(bahia);
		
		// Asignar parqueo
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Asignacion de parqueo");
		daoParqueo.insertParqueo(dominioParqueoParaInsertar(fechaSistema, (int) daoVehiculo.findVehiculoByPlaca(vehiculo).getIdVehiculo()));
		LOGGER.info("[ServiceVehiculoContrato][insertarVehiculo] Fin del metodo");
	}

	/**
	 * Metodo que actualiza un vehiculo
	 * @param vehiculo
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		daoVehiculo.updateVehiculo(vehiculo);
	}

	/**
     * Metodo que elimina un vehiculo
     * @param vehiculo
     */
	@Override
	public void eliminarVehiculo(Vehiculo vehiculo) {
		daoVehiculo.deleteVehiculo(vehiculo);
	}

	/**
     * Metodo que encuentra un vehiculo por su placa
     * @param vehiculo
     * @return
     */
	@Override
	public Vehiculo encontrarVehiculoPorPlaca(Vehiculo vehiculo) throws EstacionamientoException{
		Vehiculo vehiculoR = daoVehiculo.findVehiculoByPlaca(vehiculo);
		if( vehiculoR == null) {
			throw new EstacionamientoException("El vehiculo no esta registrado", ErrorCodes.ERROR_NEG_400.getCodigo());
		}
		
		return vehiculoR;
	}

	/**
     * Metodo que lista todos los vehiculos
     * @return
     */
	@Override
	public List<Vehiculo> listarTodosLosVehiculos() {
		return daoVehiculo.findAllVehiculos();
	}

	/**
     * Metodo que cuenta los vehiculos registrados en la base de datos
     * @return
     */
	@Override
	public int contarVehiculos() {
		return daoVehiculo.countVehiculos();
	}

	/**
     * Metodo que representa la salida de un vehiculo
     * @param vehiculo
     * @return
     * @throws Exception
     */
	@Override
	public double salidaVehiculo(Vehiculo vehiculo) throws EstacionamientoException {
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] Inicio del metodo");
		
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] Encontrar tipo por su id");
		Tipo tipo = daoTipo.findTipoById((long)vehiculo.getIdTipo());
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] tipo encontrado: " +tipo);
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] Encontrar parqueo por id vehiculo");
		Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo((int)vehiculo.getIdVehiculo());
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] parqueo encontrado :" +parqueo);
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] Encontrar bahia por su id");
		Bahia bahia = daoBahia.findBahiaById((long)vehiculo.getIdBahia());
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] bahia encontrada :" +bahia);
		
		LOGGER.info("[ServiceVehiculoContrato][salidaVehiculo] Validar vehiculo para su salida");
		validarVahiculoSalida(vehiculo, parqueo);
		
		return calcularCosto(vehiculo, tipo, parqueo, bahia);
	}
	
	/**
	 * Metodo que valida el vehiculo para su insercion
	 * @param vehiculo
	 * @throws Exception
	 */
	private void validarVehiculoEntrada(Vehiculo vehiculo) throws EstacionamientoException{
				
		if(daoVehiculo.findVehiculoByPlaca(vehiculo) == null) {
			Tipo tipo = daoTipo.findTipoById((long) vehiculo.getIdTipo());
			
			if("Moto".equals(tipo.getNombre()) && (vehiculo.getCilidranje() == null) ) {
				throw new EstacionamientoException("Cilindraje vacio", ErrorCodes.ERROR_NEG_400.getCodigo());
			}
		}else {
			throw new EstacionamientoException("El vehiculo ya se encuentra registrado", ErrorCodes.ERROR_NEG_400.getCodigo());
		}		
	}
	
	/**
	 * Metodo que valida la placa del vehiculo para su insercion
	 * @param placa
	 * @param fechaSistema
	 * @throws Exception
	 */
	private void validarPlaca(String placa, Date fechaSistema) throws EstacionamientoException {		
		if ( "a".equals(String.valueOf(placa.charAt(0))) ) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE");
			String dia = formatoFecha.format(fechaSistema).toLowerCase();

			if ("sunday".equals(dia)) {
				throw new EstacionamientoException(ErrorCodes.ERROR_NEG_401.getMensaje(), ErrorCodes.ERROR_NEG_401.getCodigo());
			}
			if("monday".equals(dia)) {
				throw new EstacionamientoException(ErrorCodes.ERROR_NEG_401.getMensaje(), ErrorCodes.ERROR_NEG_401.getCodigo());
			}
		}
	}

	/**
	 * Metodo que valida la bahia para la insercion del vehiculo
	 * @param idTipo
	 * @throws Exception
	 */
	private void validarBahia(int idTipo) throws EstacionamientoException{
		int numeroBahiasDisponibles = daoBahia.countBahiasByIdTipoState(idTipo);
		
		if(numeroBahiasDisponibles <= 0) {
			throw new EstacionamientoException("No hay bahias disponibles", ErrorCodes.ERROR_NEG_401.getCodigo());
		}
	}
	
	/**
	 * Metodo que retorna el Parqueo para su insercion
	 * @param fechaSistema
	 * @param idVehiculo
	 * @return
	 */
	private Parqueo dominioParqueoParaInsertar(Date fechaSistema, int idVehiculo) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Parqueo parqueo = new Parqueo();
		parqueo.setFechaInicial(formatoFecha.format(fechaSistema));
		parqueo.setEstado("Activo");
		parqueo.setIdVehiculo(idVehiculo);
		return parqueo;
	}
	
	/**
	 * Metodo que calcula el costo del vehiculo para su salida
	 * @param vehiculo
	 * @param tipo
	 * @param parqueo
	 * @return
	 * @throws Exception
	 */
	private double calcularCosto(Vehiculo vehiculo, Tipo tipo, Parqueo parqueo, Bahia bahia) throws EstacionamientoException {
		LOGGER.info("[ServiceVehiculoContrato][calcularCosto] Inicio metodo");
		Date fechaSistema = new Date();
		String formato = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
		String fechaFinalParqueo = formatoFecha.format(fechaSistema);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        LocalDateTime fechaInicial = LocalDateTime.parse(parqueo.getFechaInicial(), formatter);
        LocalDateTime fechaFinal = LocalDateTime.parse(fechaFinalParqueo, formatter);
        double numeroDeHoras = Duration.between(fechaInicial, fechaFinal).toHours();        
        double doubleResultado = (numeroDeHoras/24);
        int dias = (int) doubleResultado;
        double decimalPart = doubleResultado - dias;
        double horasObtener = decimalPart*24;
        int horasObtenidas = (int) horasObtener;
        LOGGER.info("[ServiceVehiculoContrato][calcularCosto] dias a cobrar: " +dias+ ". Horas a cobrar: " +horasObtenidas);
        
        boolean esCarro = true;        
        String cilindraje = "0";
        
        if("Moto".equals(tipo.getNombre())) {
        	esCarro = false;
        	cilindraje = vehiculo.getCilidranje();
        }
        
        LOGGER.info("[ServiceVehiculoContrato][calcularCosto] El vehiculo esCarro?: " +esCarro);
        double valorPagar = valorAPagar(esCarro, dias, horasObtenidas, cilindraje);
        LOGGER.info("[ServiceVehiculoContrato][calcularCosto] valor a pagar: " +valorPagar);
        
        LOGGER.info("[ServiceVehiculoContrato][calcularCosto] actualizacion del parqueo");
        // Actualizacion de la fechaFinal y costo del parqueo
        parqueo.setCosto(String.valueOf(valorPagar));
        parqueo.setFechaFin(fechaFinalParqueo);
        parqueo.setEstado("Inactivo");
        daoParqueo.updateParqueo(parqueo);
        
        LOGGER.info("[ServiceVehiculoContrato][calcularCosto] actualizacion de la bahia");
        // Actualizacion del estado de la bahia
        bahia.setEstado("Disponible");
        daoBahia.updateBahia(bahia);
        
        return valorPagar;
	}
	
	/**
	 * Metodo que tiene la funcionalidad de hacer el calculo del valor total a pagar
	 * @param esCarro
	 * @param dias
	 * @param horasObtenidas
	 * @param cilindraje
	 * @return valorAPagar
	 */
	private double valorAPagar(boolean esCarro, int dias, int horasObtenidas, String cilindraje) {
		double horaCarro = 1000;
        double horaMoto = 500;
        double diaCarro = 8000;
        double diaMoto = 4000;
        double motoAdicional = 2000;
		double valorPagar;
		
		if (esCarro) {
			valorPagar = valorTotalCarro(dias, horasObtenidas, horaCarro, diaCarro);
        } else {
        	valorPagar = valorTotalMoto(dias, horasObtenidas, horaMoto, diaMoto, motoAdicional, cilindraje);
        }
		
		return valorPagar;
	}
	
	/**
	 * Metodo que calcular el valor a pagar por parte del carro
	 * @param dias
	 * @param horasObtenidas
	 * @param horaCarro
	 * @param diaCarro
	 * @return
	 */
	private double valorTotalCarro(int dias, int horasObtenidas, double horaCarro, double diaCarro) {
		double valorPagar;		
		if (dias < 1) {
            if (horasObtenidas < 9) {
            	if(horasObtenidas < 1) {
            		horasObtenidas = 1;
            	}
                valorPagar = horaCarro * horasObtenidas;
            } else {
                valorPagar = diaCarro;
            }
        } else {
            if (horasObtenidas < 9) {
            	if(horasObtenidas < 1) {
            		horasObtenidas = 1;
            	}
                valorPagar = (horaCarro * horasObtenidas) + (diaCarro * dias);
            } else {
                valorPagar = diaCarro + (diaCarro * dias);
            }
        }
		return valorPagar;
	}
	
	/**
	 * Metodo que calcular el valor a pagar por parte de la moto
	 * @param dias
	 * @param horasObtenidas
	 * @param horaMoto
	 * @param diaMoto
	 * @param motoAdicional
	 * @param cilindraje
	 * @return
	 */
	private double valorTotalMoto(int dias, int horasObtenidas, double horaMoto, double diaMoto, double motoAdicional, String cilindraje) {
		double valorPagar;
		if (dias < 1) {
            if (horasObtenidas < 9) {
            	if(horasObtenidas < 1) {
            		horasObtenidas = 1;
            	}
                valorPagar = horaMoto * horasObtenidas;
            } else {
                valorPagar = diaMoto;
            }
        } else {
            if (horasObtenidas < 9) {
            	if(horasObtenidas < 1) {
            		horasObtenidas = 1;
            	}
                valorPagar = horaMoto * horasObtenidas + (diaMoto * dias);                    
            } else {
                valorPagar = diaMoto + (diaMoto * dias);                    
            }
        }
        
        if (Integer.parseInt(cilindraje) > 500) {
            valorPagar = valorPagar + motoAdicional;
        }
        return valorPagar;
	}

	/**
	 * Metodo que tiene la funcionalidad de validar si ell vehiculo esta registrado y al dia
	 * @param vehiculo
	 * @param parqueo
	 * @throws EstacionamientoException
	 */
	private void validarVahiculoSalida(Vehiculo vehiculo, Parqueo parqueo) throws EstacionamientoException{
		LOGGER.info("[ServiceVehiculoContrato][validarVahiculoSalida] Inicio metodo");
		if(daoVehiculo.findVehiculoByPlaca(vehiculo) != null) {
			if(!"Activo".equals(parqueo.getEstado())) {
				LOGGER.info("[ServiceVehiculoContrato][validarVahiculoSalida] Vehiculo :" +vehiculo+ " no genera cobro");
				throw new EstacionamientoException("Vehiculo no genera cobro", ErrorCodes.ERROR_NEG_400.getCodigo());
			}
		}else {
			LOGGER.info("[ServiceVehiculoContrato][validarVahiculoSalida] Vehiculo :" +vehiculo+ " no se encuentra registrado");
			throw new EstacionamientoException("El vehiculo no se encuentra registrado", ErrorCodes.ERROR_NEG_400.getCodigo());
		}
		LOGGER.info("[ServiceVehiculoContrato][validarVahiculoSalida] Fin metodo");
	}
}
