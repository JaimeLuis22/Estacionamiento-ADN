package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dto.DTOResponse;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceBahia;

@RestController
public class BahiaRestController {

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
	
	/**
	 * Metodo que representa el registro de bahia
	 * @param bahia
	 * @return
	 */
	@RequestMapping(value = "/registro-bahia", method = RequestMethod.POST)
    public ResponseEntity<DTOResponse> registroBahia(@RequestBody Bahia bahia) {
		DTOResponse response = new DTOResponse();
		
    	try {
    		serviceBahia.insertarBahia(bahia);
    		response.setMensaje("Registro exitoso");
    		response.setCodigo(HttpStatus.OK.value());
		} catch (Exception e) {
			return new ResponseEntity<DTOResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponse>(response, HttpStatus.OK);
    }
	
	/**
	 * Metodo que representa la busqueda de una bahia por su numero
	 * @param numero
	 * @return
	 */
	@RequestMapping(value = "/bahia-por-numero/{numero}", method = RequestMethod.GET)
    public ResponseEntity<Bahia> bahiaPorNumero(@PathVariable("numero") int numero) {
		Bahia bahia = new Bahia();
		bahia.setNumero(numero);
		
    	try {
    		bahia = serviceBahia.recuperarBahiaPorNumero(bahia);
		} catch (Exception e) {
			return new ResponseEntity<Bahia>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<Bahia>(bahia, HttpStatus.OK);
    }
}
