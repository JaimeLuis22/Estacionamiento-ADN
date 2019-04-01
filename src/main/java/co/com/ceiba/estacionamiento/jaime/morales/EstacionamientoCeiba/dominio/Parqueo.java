package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Parqueo {

	private long idParqueo;
	private String fechaInicial;
	private String fechaFin;
	private String costo;
	private String estado;
	private int idVehiculo;
	
	public Parqueo() {
		
	}

	public long getIdParqueo() {
		return idParqueo;
	}

	public void setIdParqueo(long idParqueo) {
		this.idParqueo = idParqueo;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	@Override
	public String toString() {
		return "Parqueo [idParqueo=" + idParqueo + ", fechaInicial=" + fechaInicial + ", fechaFin=" + fechaFin
				+ ", costo=" + costo + ", estado=" + estado + ", idVehiculo=" + idVehiculo + "]";
	}
	
}
