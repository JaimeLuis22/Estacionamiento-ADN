package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.commons.CodesApp;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dto.DTOBuilder;
import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.servicio.ServiceVehiculo;

@RestController
public class VehiculoRestController {

	/**
	 * Inyeccion del bean
	 */
	private final ServiceVehiculo serviceVehiculo;

	VehiculoRestController(ServiceVehiculo serviceVehiculo) {
		this.serviceVehiculo = serviceVehiculo;
	}

	/**
	 * Metodo que representa el listado de vehiclulos
	 * 
	 * @return
	 */
	@GetMapping("/vehiculos")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> obtenerVehiculos() {
		List<Vehiculo> lista = serviceVehiculo.listar();
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(lista), HttpStatus.OK);
	}

	/**
	 * Metodo que representa el registro de un vehiculo
	 * 
	 * @param vehiculo
	 * @return
	 */
	@PostMapping("/vehiculos")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
		serviceVehiculo.insertar(vehiculo);
		return new ResponseEntity<>(
				DTOBuilder.toDTOResponseContainer(CodesApp.INFO_REGISTRO_EXITOSO.getMensaje(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	/**
	 * Metodo que representa la salida de un vehiculo
	 * 
	 * @param vehiculo
	 * @return
	 */
	@PutMapping("/vehiculos/salida")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> salidaVehiculo(@RequestBody Vehiculo vehiculo) {
		double costo = serviceVehiculo.salida(vehiculo);
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(String.valueOf(costo), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	/**
	 * Metodo que obtiene un vehiculo por su placa
	 * 
	 * @param placa
	 * @return
	 */
	@GetMapping("/vehiculos/{placa}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DTOResponseContainer> obtenerVehiculoPorPlaca(@PathVariable("placa") String placa) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo = serviceVehiculo.encontrarPorPlaca(vehiculo);
		return new ResponseEntity<>(DTOBuilder.toDTOResponseContainer(vehiculo), HttpStatus.OK);
	}
}
