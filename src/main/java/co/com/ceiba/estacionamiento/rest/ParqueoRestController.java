package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.dto.DTOBuilder;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.servicio.ServiceParqueo;
import co.com.ceiba.estacionamiento.commons.CodesApp;

@RestController
public class ParqueoRestController {

	/**
	 * Inyecion del bean
	 */
	private final ServiceParqueo serviceParqueo;

	ParqueoRestController(ServiceParqueo serviceParqueo) {
		this.serviceParqueo = serviceParqueo;
	}

	/**
	 * Metodo que representa el listado de parqueos
	 * 
	 * @return
	 */
	@GetMapping("/parqueos")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> obtenerParqueos() {
		List<Parqueo> lista = serviceParqueo.listar();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(
					CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()), HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(lista), HttpStatus.OK);
	}

	/**
	 * Metodo que representa la busqueda de un parqueo por su id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/parqueos/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> parqueoPorId(@PathVariable("id") long id) {	
		Parqueo parqueo = serviceParqueo.encontrarPorId(id);
		if (parqueo == null) {
			return new ResponseEntity<>(
					DTOBuilder.toDTOResponseContainer(CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(parqueo), HttpStatus.OK);
	}
	
	/**
	 * Metodo que representa la busqueda de un parqueo por el id del vehiculo
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/parqueos/vehiculo/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> parqueoPorIdVehiculo(@PathVariable("id") int idVehiculo) {	
		Parqueo parqueo = serviceParqueo.encontrarParqueoPorIdVehiculo(idVehiculo);
		if (parqueo == null) {
			return new ResponseEntity<>(
					DTOBuilder.toDTOResponseContainer(CodesApp.INFO_NO_REGISTRO.getMensaje(), HttpStatus.OK.value()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(parqueo), HttpStatus.OK);
	}
}
