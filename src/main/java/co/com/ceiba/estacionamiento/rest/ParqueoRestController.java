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
    public ResponseEntity<List<Parqueo>> obtenerParqueos() {
		List<Parqueo> lista = null;
    	try {
    		lista = serviceParqueo.listarTodosLosParqueos();
    		
    		if(lista.isEmpty()) {
    			return new ResponseEntity<List<Parqueo>>(HttpStatus.ACCEPTED);
    		}
		} catch (Exception e) {
			return new ResponseEntity<List<Parqueo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<List<Parqueo>>(lista, HttpStatus.OK);
    }
	
	/**
	 * Metodo que representa la busqueda de un parqueo por su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/parqueo-por-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Parqueo> parqueoPorId(@PathVariable("id") long id) {
		Parqueo parqueo = new Parqueo();
		
    	try {
    		parqueo = serviceParqueo.encontrarParqueoPorId(id);
		} catch (Exception e) {
			return new ResponseEntity<Parqueo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<Parqueo>(parqueo, HttpStatus.OK);
    }
}
