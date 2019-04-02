package co.com.ceiba.estacionamiento.dominio;

import java.io.Serializable;

public class Tipo implements Serializable{
	
	/**
	 * Serial del bean
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que representa el id del tipo
	 */
	private long id_tipo;
	
	/**
	 * Atributo que representa el nombre del tipo
	 */
	private String nombre;

	/**
	 * Constructor
	 */
	public Tipo(){
		// Constructor vacio para que cumpla con las indicaciones de un bean
	}

	/**
	 * Metodo que obtiene el id del tipo
	 * @return
	 */
	public long getId_tipo() {
		return id_tipo;
	}

	/**
	 * Metodo que modifica el id del tipo
	 * @param id_tipo
	 */
	public void setId_tipo(long id_tipo) {
		this.id_tipo = id_tipo;
	}

	/**
	 * Metodo que obtiene el nombre del tipo
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que modifica el nombre del tipo
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que imprime los datos del bean
	 */
	@Override
	public String toString() {
		return "Tipo [id_tipo=" + id_tipo + ", nombre=" + nombre + "]";
	}
	
}
