package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.commons.CodesApp;
import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dto.DTOBuilder;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.servicio.ServiceBahia;

@RestController
public class BahiaRestController {

	/**
	 * Inyeccion del bean
	 */
	private final ServiceBahia serviceBahia;

	BahiaRestController(ServiceBahia serviceBahia) {
		this.serviceBahia = serviceBahia;
	}

	/**
	 * Metodo que repesenta el listado de bahias
	 * 
	 * @return
	 */
	@GetMapping("/bahias")
	public ResponseEntity<DTOResponseContainer> obtenerBahias() {
		List<Bahia> lista = serviceBahia.listar();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(
					DTOBuilder.toDTOResponseContainer(CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(lista), HttpStatus.OK);
	}

	/**
	 * Metodo que representa el registro de bahia
	 * 
	 * @param bahia
	 * @return
	 */
	@PostMapping(path = "/bahias")
	public ResponseEntity<DTOResponseContainer> registroBahia(@RequestBody Bahia bahia) {
		serviceBahia.insertar(bahia);
		return new ResponseEntity<>(
				DTOBuilder.toDTOResponseContainer(CodesApp.INFO_REGISTRO_EXITOSO.getMensaje(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	/**
	 * Metodo que representa la busqueda de una bahia por su numero
	 * 
	 * @param numero
	 * @return
	 */
	@GetMapping("/bahia/{numero}")
	public ResponseEntity<DTOResponseContainer> bahiaPorNumero(@PathVariable("numero") int numero) {
		Bahia bahia = new Bahia();
		bahia.setNumero(numero);
		bahia = serviceBahia.recuperarPorNumero(bahia);
		if (bahia == null) {
			return new ResponseEntity<>(
					DTOBuilder.toDTOResponseContainer(CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(bahia), HttpStatus.OK);
	}
}
