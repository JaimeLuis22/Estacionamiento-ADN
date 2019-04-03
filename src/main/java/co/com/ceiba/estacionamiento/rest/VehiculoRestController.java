package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoRestController.class);
	private static final String MENSAJE_ERROR = "Error Interno";
	
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
		
    	try {
    		List<Vehiculo> lista = serviceVehiculo.listarTodosLosVehiculos();
    		
    		if(lista.isEmpty()) {
    			DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
    			respuestaGenerica.setMensaje("No hay vehiculos");
    			respuestaGenerica.setCodigo(HttpStatus.OK.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<>(contenedor, HttpStatus.OK);
    		}
    		contenedor.setPayload(lista);
		} catch (Exception ex) {
			LOGGER.error("[VehiculoRestController][obtenerVehiculos] Excepcion: "+ex.getMessage(), ex);			
			return new ResponseEntity<>(obtenerErrorInterno(), HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<>(contenedor, HttpStatus.OK);
    }
     
	/**
	 * Metodo que representa el registro de un vehiculo
	 * @param vehiculo
	 * @return
	 */
    @RequestMapping(value = "/vehiculos", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseContainer> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
    	LOGGER.info("[VehiculoRestController][registrarVehiculo] Inicio del metodo");
    	DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
    	
    	try {
			serviceVehiculo.insertarVehiculo(vehiculo);
			
			respuestaGenerica.setMensaje("Registro Exitoso");
			respuestaGenerica.setCodigo(HttpStatus.OK.value());
			contenedor.setPayload(respuestaGenerica);
		} catch (EstacionamientoException ex) {
			LOGGER.error("[VehiculoRestController][registrarVehiculo] Excepcion: "+ex.getMessage(), ex);
			if(ex.getCodigo() == HttpStatus.BAD_REQUEST.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			LOGGER.info("[VehiculoRestController][registrarVehiculo] Fin del metodo");
    			return new ResponseEntity<>(contenedor, HttpStatus.BAD_REQUEST);
			}
			
			if(ex.getCodigo() == HttpStatus.UNAUTHORIZED.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			LOGGER.info("[VehiculoRestController][registrarVehiculo] Fin del metodo");
    			return new ResponseEntity<>(contenedor, HttpStatus.UNAUTHORIZED);
			}
			LOGGER.info("[VehiculoRestController][registrarVehiculo] Fin del metodo");
			return new ResponseEntity<>(obtenerErrorInterno(), HttpStatus.INTERNAL_SERVER_ERROR);		
		}
    	LOGGER.info("[VehiculoRestController][registrarVehiculo] Fin del metodo");
    	return new ResponseEntity<>(contenedor, HttpStatus.OK);
    }
    
    /**
     * Metodo que representa la salida de un vehiculo
     * @param vehiculo
     * @return
     */
    @RequestMapping(value = "/salida-vehiculo", method = RequestMethod.PUT)
    public ResponseEntity<DTOResponseContainer> salidaVehiculo(@RequestBody Vehiculo vehiculo) {
    	LOGGER.info("[VehiculoRestController][salidaVehiculo] Inicio del metodo");
    	DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
    	
    	try {
    		double costo = serviceVehiculo.salidaVehiculo(vehiculo);
    		
    		respuestaGenerica.setMensaje(String.valueOf(costo));
    		respuestaGenerica.setCodigo(HttpStatus.OK.value());
    		contenedor.setPayload(respuestaGenerica);
		} catch (EstacionamientoException ex) {
			LOGGER.error("[VehiculoRestController][salidaVehiculo] Excepcion: "+ex.getMessage(), ex);
			if(ex.getCodigo() == HttpStatus.BAD_REQUEST.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			LOGGER.info("[VehiculoRestController][salidaVehiculo] Inicio del metodo");
    			return new ResponseEntity<>(contenedor, HttpStatus.BAD_REQUEST);
			}
			LOGGER.info("[VehiculoRestController][salidaVehiculo] Inicio del metodo");
			return new ResponseEntity<>(obtenerErrorInterno(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	LOGGER.info("[VehiculoRestController][salidaVehiculo] Fin del metodo");
        return new ResponseEntity<>(contenedor, HttpStatus.OK);
    }
    
    /**
     * Metodo que obtiene un vehiculo por su placa
     * @param placa
     * @return
     */
    @RequestMapping(value = "/vehiculo/{placa}", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> obtenerVehiculoPorPlaca(@PathVariable("placa") String placa) {
    	DTOResponseContainer contenedor = new DTOResponseContainer();
    	
    	try {
    		Vehiculo vehiculo = new Vehiculo();
    		vehiculo.setPlaca(placa);
    		vehiculo = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
    		
    		contenedor.setPayload(vehiculo);
		} catch (EstacionamientoException ex) {
			LOGGER.error("[VehiculoRestController][obtenerVehiculoPorPlaca] Excepcion: "+ex.getMessage(), ex);
			if(ex.getCodigo() == HttpStatus.BAD_REQUEST.value()) {
				DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
				respuestaGenerica.setMensaje(ex.getMensaje());
    			respuestaGenerica.setCodigo(ex.getCodigo());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<>(contenedor, HttpStatus.BAD_REQUEST);
			}			
			return new ResponseEntity<>(obtenerErrorInterno(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(contenedor, HttpStatus.OK);
    }
    
    /**
	 * Metodo que genera el objeto de respuesta para los casos errores internos
	 * @return
	 */
	private DTOResponseContainer obtenerErrorInterno() {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
		respuestaGenerica.setMensaje(MENSAJE_ERROR);
		respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		contenedor.setPayload(respuestaGenerica);
		return contenedor;
	}
}
