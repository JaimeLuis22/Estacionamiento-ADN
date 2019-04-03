package co.com.ceiba.estacionamiento.dto;

import java.io.Serializable;

public class DTOResponseContainer implements Serializable{

	/**
	 * Serial del bean
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo del contenedor
	 */
	private transient Object payload;
	
	/**
	 * Constructor
	 */
	public DTOResponseContainer() {
		// Constructor vacio para que cumpla con las indicaciones de un bean
	}
	
	/**
	 * Constructor que recibe el objeto
	 * @param payload
	 */
	public DTOResponseContainer(Object payload) {
		this.payload = payload;
	}

	/**
	 * Metodo que obtiene el objeto	
	 * @return
	 */
	public Object getPayload() {
		return payload;
	}

	/**
	 * Metodo que modifica el objeto
	 * @param payload
	 */
	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
