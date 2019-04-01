package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dto;

public class DTOResponse {
	private String mensaje;

	public DTOResponse() {
		
	}
	
	public DTOResponse(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
		
}
