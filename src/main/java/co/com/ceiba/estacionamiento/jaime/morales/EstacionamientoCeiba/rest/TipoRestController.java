package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceTipo;

@RestController
public class TipoRestController {
	
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
    public ResponseEntity<List<Tipo>> obtenerTipos() {
		List<Tipo> lista = null;
    	try {
    		lista = serviceTipo.listarTipos();
    		
    		if(lista.isEmpty()) {
    			return new ResponseEntity<List<Tipo>>(HttpStatus.ACCEPTED);
    		}
		} catch (Exception e) {
			return new ResponseEntity<List<Tipo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<List<Tipo>>(lista, HttpStatus.OK);
    }
}
