package co.com.ceiba.estacionamiento.servicio;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
		Date fechaSistema = new Date();
		
		// Validaciones
		validarPlaca(vehiculo.getPlaca().toLowerCase(), fechaSistema);
		validarVehiculoEntrada(vehiculo);
		validarBahia(vehiculo.getIdTipo());
		
		// Insertar vehiculo
		daoVehiculo.insertVehiculo(vehiculo);
		
		// Asignar Bahia
		Bahia bahia = daoBahia.findBahiaById(vehiculo.getIdBahia());
		bahia.setEstado("Ocupado");
		daoBahia.updateBahia(bahia);
		
		// Asignar parqueo		
		daoParqueo.insertParqueo(dominioParqueoParaInsertar(fechaSistema, (int) daoVehiculo.findVehiculoByPlaca(vehiculo).getIdVehiculo()));
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
		Tipo tipo = daoTipo.findTipoById((long)vehiculo.getIdTipo());
		Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo((int)vehiculo.getIdVehiculo());
		Bahia bahia = daoBahia.findBahiaById((long)vehiculo.getIdBahia());
		
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

			switch (dia) {
				case "sunday":
					throw new EstacionamientoException(ErrorCodes.ERROR_NEG_401.getMensaje(), ErrorCodes.ERROR_NEG_401.getCodigo());
				case "monday":
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
        
        boolean esCarro = true;        
        String cilindraje = "0";
        
        if(tipo.getNombre().equals("Moto")) {
        	esCarro = false;
        	cilindraje = vehiculo.getCilidranje();
        }
        
        double valorPagar = valorAPagar(esCarro, dias, horasObtenidas, cilindraje);
        
        // Actualizacion de la fechaFinal y costo del parqueo
        parqueo.setCosto(String.valueOf(valorPagar));
        parqueo.setFechaFin(fechaFinalParqueo);
        parqueo.setEstado("Inactivo");;
        daoParqueo.updateParqueo(parqueo);
        
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
            if (dias < 1) {
                if (horasObtenidas < 9) {
                    valorPagar = horaCarro * horasObtenidas;
                } else {
                    valorPagar = diaCarro;
                }
            } else {
                if (horasObtenidas < 9) {
                    valorPagar = (horaCarro * horasObtenidas) + (diaCarro * dias);
                } else {
                    valorPagar = diaCarro + (diaCarro * dias);
                }
            }
        } else {
            if (dias < 1) {
                if (horasObtenidas < 9) {
                    valorPagar = horaMoto * horasObtenidas;
                } else {
                    valorPagar = diaMoto;
                }
            } else {
                if (horasObtenidas < 9) {
                    valorPagar = horaMoto * horasObtenidas + (diaMoto * dias);                    
                } else {
                    valorPagar = diaMoto + (diaMoto * dias);                    
                }
            }
            
            if (Integer.parseInt(cilindraje) > 500) {
                valorPagar = valorPagar + motoAdicional;
            }
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
		
		if(daoVehiculo.findVehiculoByPlaca(vehiculo) != null) {
			if(!"Activo".equals(parqueo.getEstado())) {
				throw new EstacionamientoException("Vehiculo no genera cobro", ErrorCodes.ERROR_NEG_400.getCodigo());
			}
		}else {
			throw new EstacionamientoException("El vehiculo no se encuentra registrado", ErrorCodes.ERROR_NEG_400.getCodigo());
		}
	}
}
