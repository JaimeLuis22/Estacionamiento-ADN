package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.dto.DTOResponseGeneric;
import co.com.ceiba.estacionamiento.servicio.ServiceParqueo;

@RestController
public class ParqueoRestController {

	/**
	 * Inyecion del bean
	 */
	@Autowired
	ServiceParqueo serviceParqueo;
	
	/**
	 * Metodo que representa el listado de parqueos
	 * @return
	 */
	@RequestMapping(value = "/parqueos", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> obtenerParqueos() {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
    	try {
    		List<Parqueo> lista = serviceParqueo.listarTodosLosParqueos();
    		
    		if(lista.isEmpty()) {
    			respuestaGenerica.setMensaje("No hay parqueos");
    			respuestaGenerica.setCodigo(HttpStatus.OK.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    		}
    		contenedor.setPayload(lista);
		} catch (Exception e) {
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
	
	/**
	 * Metodo que representa la busqueda de un parqueo por su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/parqueo-por-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseContainer> parqueoPorId(@PathVariable("id") long id) {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
		Parqueo parqueo = new Parqueo();
		
    	try {
    		parqueo = serviceParqueo.encontrarParqueoPorId(id);
    		
    		if(parqueo == null) {
    			respuestaGenerica.setMensaje("No existe el parqueo");
    			respuestaGenerica.setCodigo(HttpStatus.OK.value());
    			contenedor.setPayload(respuestaGenerica);
    			
    			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    		}
    		contenedor.setPayload(parqueo);
		} catch (Exception e) {
			respuestaGenerica.setMensaje("Error Interno");
			respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			contenedor.setPayload(respuestaGenerica);
			
			return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponseContainer>(contenedor, HttpStatus.OK);
    }
}
