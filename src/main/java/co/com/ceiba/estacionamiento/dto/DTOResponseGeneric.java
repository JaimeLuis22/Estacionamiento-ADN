package co.com.ceiba.estacionamiento.dto;

import java.io.Serializable;

public class DTOResponseGeneric implements Serializable{
	
	/**
	 * Serial del bean
	 */
	private static final long serialVersionUID = 1L;

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
	public DTOResponseGeneric() {
		
	}
	
	/**
	 * Constructor con parametro mensaje
	 * @param mensaje
	 */
	public DTOResponseGeneric(String mensaje) {
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
