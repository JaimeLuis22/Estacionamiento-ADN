package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dto;

public class DTOResponse {
	
	/**
	 * Atributo que representa el mensaje de respuesta 
	 */
	private String mensaje;
	
	/**
	 * Metodo que representa el codigo de la respuesta
	 */
	private int codigo;

	/**
	 * Constructor
	 */
	public DTOResponse() {
		
	}
	
	/**
	 * Constructor con parametro mensaje
	 * @param mensaje
	 */
	public DTOResponse(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Metodo que obtiene el mensaje
	 * @return
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Metodo que modifica el mensaje
	 * @param mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Metodo que obtiene el codigo
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que modifica el codigo
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
