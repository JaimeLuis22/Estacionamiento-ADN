package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.dto.DTOResponseGeneric;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.servicio.ServiceVehiculo;

@RestController
public class VehiculoRestController {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	ServiceVehiculo serviceVehiculo;
	
	/**
	 * Metodo que representa el listado de vehiclulos
	 * @return
	 */
	@RequestMapping(value = "/vehiculos", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> obtenerVehiculos() {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
    	try {
    		List<Vehiculo> lista = serviceVehiculo.listarTodosLosVehiculos();
    		
    		if(lista.isEmpty()) {
    			respuestaGenerica.setMensaje("No hay vehiculos");
    			respuestaGenerica.setCodigo(HttpStatus.OK.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    		}
    		contenedor.setPayload(lista);
		} catch (Exception ex) {
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
     
	/**
	 * Metodo que representa el registro de un vehiculo
	 * @param vehiculo
	 * @return
	 */
    @RequestMapping(value = "/registro-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseContainer> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
    	DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
    	
    	try {
			serviceVehiculo.insertarVehiculo(vehiculo);
			
			respuestaGenerica.setMensaje("Registro Exitoso");
			respuestaGenerica.setCodigo(HttpStatus.OK.value());
			contenedor.setPayload(respuestaGenerica);
		} catch (EstacionamientoException ex) {
			if(ex.getCodigo() == HttpStatus.BAD_REQUEST.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.BAD_REQUEST);
			}
			
			if(ex.getCodigo() == HttpStatus.UNAUTHORIZED.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.UNAUTHORIZED);
			} else {
				respuestaGenerica.setMensaje("Error Interno");
				respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
				contenedor.setPayload(respuestaGenerica);
				
				return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
			}		
		}
    	return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
    
    /**
     * Metodo que representa la salida de un vehiculo
     * @param vehiculo
     * @return
     */
    @RequestMapping(value = "/salida-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseContainer> salidaVehiculo(@RequestBody Vehiculo vehiculo) {
    	DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
    	
    	try {
    		double costo = serviceVehiculo.salidaVehiculo(vehiculo);
    		
    		respuestaGenerica.setMensaje(String.valueOf(costo));
    		respuestaGenerica.setCodigo(HttpStatus.OK.value());
    		contenedor.setPayload(respuestaGenerica);
		} catch (Exception e) {
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
}
