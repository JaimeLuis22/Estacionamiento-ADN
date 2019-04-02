package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Parqueo {

	/**
	 * Atributo de que representa el id del parqueo
	 */
	private long idParqueo;
	
	/**
	 * Atributo que representa la fecha inicial del parqueo
	 */
	private String fechaInicial;
	
	/**
	 * Atributo que representa la fecha fin del parqueo
	 */
	private String fechaFin;
	
	/**
	 * Atributo que representa el costo del parqueo
	 */
	private String costo;
	
	/**
	 * Atributo que representa el estado del parqueo
	 */
	private String estado;
	
	/**
	 * Atributo que representa el idVehiculo del parqueo (Foranea)
	 */
	private int idVehiculo;
	
	/**
	 * Constructor
	 */
	public Parqueo() {
		
	}

	/**
	 * Metodo que obtiene el id del parqueo
	 * @return
	 */
	public long getIdParqueo() {
		return idParqueo;
	}

	/**
	 * Metodo que modifica el id del parqueo
	 * @param idParqueo
	 */
	public void setIdParqueo(long idParqueo) {
		this.idParqueo = idParqueo;
	}

	/**
	 * Metodo que obtiene la decha inicial del parqueo
	 * @return
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Metodo que modifica la fecha inicial del parqueo
	 * @param fechaInicial
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Metodo que obtiene la fecha final del parqueo
	 * @return
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Metodo que modifica la fecha final del parqueo
	 * @param fechaFin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Metodo que obtiene el costo del parqueo
	 * @return
	 */
	public String getCosto() {
		return costo;
	}

	/**
	 * Metodo que modifica el costo del parqueo
	 * @param costo
	 */
	public void setCosto(String costo) {
		this.costo = costo;
	}

	/**
	 * Metodo que obtiene el estado del parqueo
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo que modifica el estado del parqueo
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Metodo que obtiene el idVehiculo
	 * @return
	 */
	public int getIdVehiculo() {
		return idVehiculo;
	}

	/**
	 * Metodo que modifica el idVehiculo
	 * @param idVehiculo
	 */
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	/**
	 * Metodo que imprime los datos del bean
	 */
	@Override
	public String toString() {
		return "Parqueo [idParqueo=" + idParqueo + ", fechaInicial=" + fechaInicial + ", fechaFin=" + fechaFin
				+ ", costo=" + costo + ", estado=" + estado + ", idVehiculo=" + idVehiculo + "]";
	}
	
}
