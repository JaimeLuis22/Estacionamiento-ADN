package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Tipo {
	
	private long id_tipo;
	private String nombre;

	public Tipo(){
		
	}

	public long getId_tipo() {
		return id_tipo;
	}


	public void setId_tipo(long id_tipo) {
		this.id_tipo = id_tipo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Tipo [id_tipo=" + id_tipo + ", nombre=" + nombre + "]";
	}
	
}
