package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.dto.DTOResponseGeneric;
import co.com.ceiba.estacionamiento.servicio.ServiceTipo;

@RestController
public class TipoRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoRestController.class);
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	ServiceTipo serviceTipo;
	
	/**
	 * Metodo que representa el listado de tipos
	 * @return
	 */
	@RequestMapping(value = "/tipos", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> obtenerTipos() {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
    	try {
    		List<Tipo> lista = serviceTipo.listarTipos();
    		
    		if(lista.isEmpty()) {
    			respuestaGenerica.setMensaje("No hay tipos");
    			respuestaGenerica.setCodigo(HttpStatus.OK.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    		}
    		contenedor.setPayload(lista);
		} catch (Exception e) {
			LOGGER.error("[TipoRestController][obtenerTipos] Excepcion: "+e.getMessage(), e);
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
}
