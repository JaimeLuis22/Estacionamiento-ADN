package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoBahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoTipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoVehiculo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

@Service
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
	@Override
	public void insertarVehiculo(Vehiculo vehiculo) throws Exception {
		Date fechaSistema = new Date();
		
		// Validaciones
		validarPlaca(vehiculo.getPlaca().toLowerCase(), fechaSistema);
		validarVehiculo(vehiculo);
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
	public Vehiculo encontrarVehiculoPorPlaca(Vehiculo vehiculo) {
		return daoVehiculo.findVehiculoByPlaca(vehiculo);
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
	public double salidaVehiculo(Vehiculo vehiculo) throws Exception{		
		Tipo tipo = daoTipo.findTipoById((long)vehiculo.getIdTipo());
		Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo((int)vehiculo.getIdVehiculo());
		Bahia bahia = daoBahia.findBahiaById((long)vehiculo.getIdBahia());
		
		return calcularCosto(vehiculo, tipo, parqueo, bahia);
	}
	
	/**
	 * Metodo que valida el vehiculo para su insercion
	 * @param vehiculo
	 * @throws Exception
	 */
	private void validarVehiculo(Vehiculo vehiculo) throws Exception{
		Tipo tipo = daoTipo.findTipoById((long) vehiculo.getIdTipo());
		
		if(tipo.getNombre().equals("Moto") && (vehiculo.getCilidranje() == null) ) {
			throw new Exception("Cilindraje vacio");
		}
	}
	
	/**
	 * Metodo que valida la placa del vehiculo para su insercion
	 * @param placa
	 * @param fechaSistema
	 * @throws Exception
	 */
	private void validarPlaca(String placa, Date fechaSistema) throws Exception {		
		if (String.valueOf(placa.charAt(0)).equals("a")) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE");
			String dia = formatoFecha.format(fechaSistema).toLowerCase();

			switch (dia) {
				case "sunday":
					throw new Exception("Autorizacion negada");
				case "monday":
					throw new Exception("Autorizacion negada");
			}
		}
	}

	/**
	 * Metodo que valida la bahia para la insercion del vehiculo
	 * @param idTipo
	 * @throws Exception
	 */
	private void validarBahia(int idTipo) throws Exception{
		int numeroBahiasDisponibles = daoBahia.countBahiasByIdTipoState(idTipo);
		
		if(numeroBahiasDisponibles <= 0) {
			throw new Exception("No hay bahias disponibles");
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
	private double calcularCosto(Vehiculo vehiculo, Tipo tipo, Parqueo parqueo, Bahia bahia) throws Exception {
		Date fechaSistema = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaFinalParqueo = formatoFecha.format(fechaSistema);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaInicial = LocalDateTime.parse(parqueo.getFechaInicial(), formatter);
        LocalDateTime fechaFinal = LocalDateTime.parse(fechaFinalParqueo, formatter);
        double numeroDeHoras = Duration.between(fechaInicial, fechaFinal).toHours();        
        double doubleResultado = (numeroDeHoras/24);
        int dias = (int) doubleResultado;
        double decimalPart = doubleResultado - dias;
        double horasObtener = decimalPart*24;
        int horasObtenidas = (int) horasObtener;
        
        boolean esCarro = true;
        double horaCarro = 1000;
        double horaMoto = 500;
        double diaCarro = 8000;
        double diaMoto = 4000;
        double motoAdicional = 2000;
        double valorPagar;
        String cilindraje = "0";
        
        if(tipo.getNombre().equals("Moto")) {
        	esCarro = false;
        	cilindraje = vehiculo.getCilidranje();
        }
        
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
        
        // Actualizacion de la fechaFinal y costo del parqueo
        parqueo.setCosto(String.valueOf(valorPagar));
        parqueo.setFechaFin(fechaFinalParqueo);
        daoParqueo.updateParqueo(parqueo);
        
        // Actualizacion del estado de la bahia
        bahia.setEstado("Disponible");
        daoBahia.updateBahia(bahia);
        
        return valorPagar;
	}
}
