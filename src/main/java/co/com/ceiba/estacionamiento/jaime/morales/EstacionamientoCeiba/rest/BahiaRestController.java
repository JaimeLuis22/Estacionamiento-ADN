package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceBahia;

@RestController
public class BahiaRestController {

	@Autowired
	ServiceBahia serviceBahia;
	
	@RequestMapping(value = "/bahias", method = RequestMethod.GET)
    public ResponseEntity<List<Bahia>> obtenerBahias() {
		List<Bahia> lista = null;
    	try {
    		lista = serviceBahia.listarTodasLasBahias();
    		
    		if(lista.isEmpty()) {
    			return new ResponseEntity<List<Bahia>>(HttpStatus.ACCEPTED);
    		}
		} catch (Exception e) {
			return new ResponseEntity<List<Bahia>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<List<Bahia>>(lista, HttpStatus.OK);
    }
}
