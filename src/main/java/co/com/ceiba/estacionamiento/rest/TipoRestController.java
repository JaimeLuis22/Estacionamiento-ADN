package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.dto.DTOBuilder;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.servicio.ServiceTipo;
import co.com.ceiba.estacionamiento.commons.CodesApp;

@RestController
public class TipoRestController {

	/**
	 * Inyeccion del bean
	 */
	private final ServiceTipo serviceTipo;

	TipoRestController(ServiceTipo serviceTipo) {
		this.serviceTipo = serviceTipo;
	}

	/**
	 * Metodo que representa el listado de tipos
	 * 
	 * @return
	 */
	@GetMapping("/tipos")
	public ResponseEntity<DTOResponseContainer> obtenerTipos() {
		List<Tipo> lista = serviceTipo.listar();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(
					CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()), HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(lista), HttpStatus.OK);
	}
}
