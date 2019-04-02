package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Vehiculo {
	
	/**
	 * Atributo que representa el id del vehiculo
	 */
	private long idVehiculo;
	
	/**
	 * Atributo que representa la placa del vehiculo
	 */
	private String placa;
	
	/**
	 * Atributo que representa el cilindraje del vehiculo (Si lo amerita)
	 */
	private String cilidranje;
	
	/**
	 * Atributo que representa el idTipo (Foranea)
	 */
	private int idTipo;
	
	/**
	 * Atributo que representa el idBahia (Foranea)
	 */
	private int idBahia;
	
	/**
	 * Constructor
	 */
	public Vehiculo() {
		
	}
	
	/**
	 * Metodo que obtiene el idVehiculo
	 * @return
	 */
	public long getIdVehiculo() {
		return idVehiculo;
	}

	/**
	 * Metodo que modifica el idVehiculo
	 * @param idVehiculo
	 */
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	/**
	 * Metodo que obtiene la placa
	 * @return
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Metodo que modifica la placa
	 * @param placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Metodo que Obtiene el idTipo
	 * @return
	 */
	public int getIdTipo() {
		return idTipo;
	}

	/**
	 * Metodo que modifica el idTipo
	 * @param idTipo
	 */
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	/**
	 * Metodo que obtiene el cilindraje
	 * @return
	 */
	public String getCilidranje() {
		return cilidranje;
	}

	/**
	 * Metodo que modifica el cilindraje
	 * @param cilidranje
	 */
	public void setCilidranje(String cilidranje) {
		this.cilidranje = cilidranje;
	}
	
	/**
	 * Metodo que obtiene el idBahia
	 * @return
	 */
	public int getIdBahia() {
		return idBahia;
	}

	/**
	 * Metodo que modifica el idBahia
	 * @param idBahia
	 */
	public void setIdBahia(int idBahia) {
		this.idBahia = idBahia;
	}

	/**
	 * Metodo que imprime los datos del bean
	 */
	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", placa=" + placa + ", cilidranje=" + cilidranje + ", idTipo="
				+ idTipo + ", idBahia=" + idBahia + "]";
	}

}
