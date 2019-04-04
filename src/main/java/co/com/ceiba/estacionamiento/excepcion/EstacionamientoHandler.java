package co.com.ceiba.estacionamiento.excepcion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import co.com.ceiba.estacionamiento.dto.DTOResponseContainer;
import co.com.ceiba.estacionamiento.dto.DTOResponseGeneric;

@ControllerAdvice
@CrossOrigin("*")
public class EstacionamientoHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EstacionamientoHandler.class);
	
	@ExceptionHandler(EstacionamientoException.class)
	public ResponseEntity<DTOResponseContainer> respuestaEstacionamientoException(EstacionamientoException ex, WebRequest request) {
		LOGGER.error("[EstacionamientoHandler][respuestaEstacionamientoException] EstacionamientoException: "+ex.getMensaje(), ex);
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		
		if(ex.getCodigo() == HttpStatus.BAD_REQUEST.value()) {
			respuestaGenerica.setMensaje(ex.getMensaje());
			respuestaGenerica.setCodigo(ex.getCodigo());
			contenedor.setPayload(respuestaGenerica);
			return new ResponseEntity<>(contenedor, HttpStatus.BAD_REQUEST);
		}
		
		if(ex.getCodigo() == HttpStatus.UNAUTHORIZED.value()) {
			respuestaGenerica.setMensaje(ex.getMensaje());
			respuestaGenerica.setCodigo(ex.getCodigo());
			contenedor.setPayload(respuestaGenerica);
			return new ResponseEntity<>(contenedor, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(obtenerErrorInterno(contenedor, respuestaGenerica), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DTOResponseContainer> respuestaException(Exception ex, WebRequest request) {
		LOGGER.error("[EstacionamientoHandler][respuestaException] Exception: "+ex.getMessage(), ex);
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();		
		return new ResponseEntity<>(obtenerErrorInterno(contenedor, respuestaGenerica), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Metodo que genera el objeto de respuesta para los casos errores internos
	 * @return
	 */
	private DTOResponseContainer obtenerErrorInterno(DTOResponseContainer contenedor, DTOResponseGeneric respuestaGenerica) {		
		respuestaGenerica.setMensaje("Error interno");
		respuestaGenerica.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		contenedor.setPayload(respuestaGenerica);
		return contenedor;
	}
}
