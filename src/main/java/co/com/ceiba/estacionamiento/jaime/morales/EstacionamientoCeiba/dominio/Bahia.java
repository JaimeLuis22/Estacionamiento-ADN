package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio;

public class Bahia {

	private long idBahia;
    private int numero;
    private String estado;
    private int idTipo;
    
    public Bahia() {
    	
    }

	public long getIdBahia() {
		return idBahia;
	}

	public void setIdBahia(long idBahia) {
		this.idBahia = idBahia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "Bahia [idBahia=" + idBahia + ", numero=" + numero + ", estado=" + estado + ", idTipo=" + idTipo + "]";
	}
    
}
