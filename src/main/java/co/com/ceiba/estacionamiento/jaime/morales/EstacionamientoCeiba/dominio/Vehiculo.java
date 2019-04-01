package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Vehiculo {
	
	private long idVehiculo;
	private String placa;
	private String cilidranje;
	private int idTipo;
	private int idBahia;
	
	public Vehiculo() {
		
	}
	
	public long getIdVehiculo() {
		return idVehiculo;
	}


	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getCilidranje() {
		return cilidranje;
	}

	public void setCilidranje(String cilidranje) {
		this.cilidranje = cilidranje;
	}
	
	public int getIdBahia() {
		return idBahia;
	}

	public void setIdBahia(int idBahia) {
		this.idBahia = idBahia;
	}

	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", placa=" + placa + ", cilidranje=" + cilidranje + ", idTipo="
				+ idTipo + ", idBahia=" + idBahia + "]";
	}

}
