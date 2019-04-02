package co.com.ceiba.estacionamiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dto.DTOResponse;
import co.com.ceiba.estacionamiento.servicio.ServiceVehiculo;

@RestController
public class VehiculoRestController {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	ServiceVehiculo serviceVehiculo;
	
	/**
	 * Metodo que representa el listado de vehiclulos
	 * @return
	 */
	@RequestMapping(value = "/vehiculos", method = RequestMethod.GET)
    public ResponseEntity<List<Vehiculo>> obtenerVehiculos() {
		List<Vehiculo> lista = null;
    	try {
    		lista = serviceVehiculo.listarTodosLosVehiculos();
    		
    		if(lista.isEmpty()) {
    			return new ResponseEntity<List<Vehiculo>>(HttpStatus.ACCEPTED);
    		}
		} catch (Exception e) {
			return new ResponseEntity<List<Vehiculo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<List<Vehiculo>>(lista, HttpStatus.OK);
    }
     
	/**
	 * Metodo que representa el registro de un vehiculo
	 * @param vehiculo
	 * @return
	 */
    @RequestMapping(value = "/registro-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponse> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
    	DTOResponse response = new DTOResponse();
    	
    	try {
			serviceVehiculo.insertarVehiculo(vehiculo);
			response.setMensaje("Registro Exitoso");
			response.setCodigo(HttpStatus.OK.value());
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<DTOResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponse>(response, HttpStatus.OK);
    }
    
    /**
     * Metodo que representa la salida de un vehiculo
     * @param vehiculo
     * @return
     */
    @RequestMapping(value = "/salida-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponse> salidaVehiculo(@RequestBody Vehiculo vehiculo) {
    	DTOResponse response = new DTOResponse();
    	
    	try {
    		double costo = serviceVehiculo.salidaVehiculo(vehiculo);
    		response.setMensaje(String.valueOf(costo));
			response.setCodigo(HttpStatus.OK.value());
		} catch (Exception e) {
			// TODO: handle exception
		}
        return new ResponseEntity<DTOResponse>(response, HttpStatus.OK);
    }
}
