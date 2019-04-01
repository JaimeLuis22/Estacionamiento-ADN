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

	@Autowired
	private DaoVehiculo daoVehiculo;
	
	@Autowired
	private DaoBahia daoBahia;
	
	@Autowired
	private DaoParqueo daoParqueo;
	
	@Autowired
	private DaoTipo daoTipo;

	@Override
	public void insertarVehiculo(Vehiculo vehiculo) throws Exception {
		Date fechaActual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("");
				
		String placa = vehiculo.getPlaca().toLowerCase();
		
		if (String.valueOf(placa.charAt(0)).equals("a")) {
			formatoFecha = new SimpleDateFormat("EEEE");
			String dia = formatoFecha.format(fechaActual).toLowerCase();

			switch (dia) {
				case "sunday":
					throw new Exception("Autorizacion negada");
				case "monday":
					throw new Exception("Autorizacion negada");
			}
		}

		daoVehiculo.insertVehiculo(vehiculo);
		
		Bahia bahia = daoBahia.findBahiaById(vehiculo.getIdBahia());
		bahia.setEstado("Ocupado");
		daoBahia.updateBahia(bahia);
		
		formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Parqueo parqueo = new Parqueo();
		parqueo.setFechaInicial(formatoFecha.format(fechaActual));
		parqueo.setEstado("Activo");
		parqueo.setIdVehiculo((int)daoVehiculo.findVehiculoByPlaca(vehiculo).getIdVehiculo());
		daoParqueo.insertParqueo(parqueo);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		daoVehiculo.updateVehiculo(vehiculo);
	}

	@Override
	public void eliminarVehiculo(Vehiculo vehiculo) {
		daoVehiculo.deleteVehiculo(vehiculo);
	}

	@Override
	public Vehiculo encontrarVehiculoPorPlaca(Vehiculo vehiculo) {
		return daoVehiculo.findVehiculoByPlaca(vehiculo);
	}

	@Override
	public List<Vehiculo> listarTodosLosVehiculos() {
		return daoVehiculo.findAllVehiculos();
	}

	@Override
	public int contarVehiculos() {
		return daoVehiculo.countVehiculos();
	}

	@Override
	public void salidaVehiculo(Vehiculo vehiculo) {
		Date fechaSistema = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Tipo tipo = daoTipo.findTipoById(vehiculo.getIdTipo());
		Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo((int)vehiculo.getIdVehiculo());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaInicial = LocalDateTime.parse(parqueo.getFechaInicial(), formatter);
        LocalDateTime fechaFinal = LocalDateTime.parse(formatoFecha.format(fechaSistema), formatter);

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
        
        if(dias < 1){
            if(horasObtenidas < 9){
                if(esCarro){
                    valorPagar = horaCarro * horasObtenidas;
                }else{
                    valorPagar = horaMoto * horasObtenidas;
                }
            }else{
                if(esCarro){
                    valorPagar = diaCarro;
                }else{
                    valorPagar = diaMoto;                    
                }
            }
        }else{
            if(horasObtenidas < 9){
                if(esCarro){
                    valorPagar = (horaCarro * horasObtenidas) + (diaCarro * dias);
                }else{
                    valorPagar = horaMoto * horasObtenidas + (diaMoto * dias);
                }
            }else{
                if(esCarro){
                    valorPagar = diaCarro + (diaCarro * dias);
                }else{
                    valorPagar = diaMoto + (diaMoto * dias);
                }
            }
        }
        
        if(!esCarro){
            if(Integer.parseInt(cilindraje) > 500){
                valorPagar = valorPagar + motoAdicional;
            }
        }
	}

}
