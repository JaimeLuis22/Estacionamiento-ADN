package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dto.DTOResponse;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceVehiculo;

@RestController
public class VehiculoRestController {

	@Autowired
	ServiceVehiculo serviceVehiculo;
	
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
     
    @RequestMapping(value = "/registro-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponse> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
    	DTOResponse response = new DTOResponse();
    	
    	try {
			serviceVehiculo.insertarVehiculo(vehiculo);
			response.setMensaje("Registro Exitoso");
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<DTOResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}    	
    	
        return new ResponseEntity<DTOResponse>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/salida-vehiculo", method = RequestMethod.POST)
    public ResponseEntity<DTOResponse> salidaVehiculo(@RequestBody Vehiculo vehiculo) {
    	
        return new ResponseEntity<DTOResponse>(HttpStatus.OK);
    }
}
