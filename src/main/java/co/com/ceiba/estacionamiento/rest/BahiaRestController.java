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

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.dto.DTOResponseGeneric;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.servicio.ServiceBahia;

@RestController
public class BahiaRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BahiaRestController.class);
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	ServiceBahia serviceBahia;
	
	/**
	 * Metodo que repesenta el listado de bahias
	 * @return
	 */
	@RequestMapping(value = "/bahias", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> obtenerBahias() {
		ResponseEntity<DTOResponseContainer> response;
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
    	try {
    		List<Bahia> lista = serviceBahia.listarTodasLasBahias();
    		
    		if(lista.isEmpty()) {
    			respuestaGenerica.setMensaje("No hay bahias");
    			respuestaGenerica.setCodigo(HttpStatus.ACCEPTED.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			response = new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.ACCEPTED);
    			return response;
    		}
    		contenedor.setPayload(lista);
    		response = new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("[BahiaRestController][obtenerBahias] Excepcion: "+e.getMessage(), e);	
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			response = new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}    	
        return response;
    }
	
	/**
	 * Metodo que representa el registro de bahia
	 * @param bahia
	 * @return
	 */
	@RequestMapping(value = "/registro-bahia", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseContainer> registroBahia(@RequestBody Bahia bahia) {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
    	try {
    		serviceBahia.insertarBahia(bahia);
    		
    		respuestaGenerica.setMensaje("Registro exitoso");
    		respuestaGenerica.setCodigo(HttpStatus.OK.value());
    		contenedor.setPayload(respuestaGenerica);
		} catch (EstacionamientoException ex) {
			LOGGER.error("[BahiaRestController][registroBahia] Excepcion: "+ex.getMessage(), ex);
			if(ex.getCodigo() == HttpStatus.UNAUTHORIZED.value()) {
				respuestaGenerica.setMensaje(ex.getMensaje());
				respuestaGenerica.setCodigo(ex.getCodigo());
				contenedor.setPayload(respuestaGenerica);
				
				return new ResponseEntity<DTOResponseContainer>(HttpStatus.UNAUTHORIZED);
			}			
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
	
	/**
	 * Metodo que representa la busqueda de una bahia por su numero
	 * @param numero
	 * @return
	 */
	@RequestMapping(value = "/bahia-por-numero/{numero}", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> bahiaPorNumero(@PathVariable("numero") int numero) {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();		
		
    	try {
    		Bahia bahia = new Bahia();
    		bahia.setNumero(numero);
    		bahia = serviceBahia.recuperarBahiaPorNumero(bahia);
    		
    		if(bahia == null) {
    			respuestaGenerica.setMensaje("No existe la bahia");
        		respuestaGenerica.setCodigo(HttpStatus.OK.value());
        		contenedor.setPayload(respuestaGenerica);
        		
        		return new ResponseEntity<DTOResponseContainer>(HttpStatus.OK);
    		}
    		contenedor.setPayload(bahia);
		} catch (Exception e) {
			LOGGER.error("[BahiaRestController][bahiaPorNumero] Excepcion: "+e.getMessage(), e);
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
}
