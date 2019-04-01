package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceParqueo;

@RestController
public class ParqueoRestController {

	@Autowired
	ServiceParqueo serviceParqueo;
	
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
}
