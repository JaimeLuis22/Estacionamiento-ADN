package co.com.ceiba.estacionamiento.dominio;

import java.io.Serializable;

public class Bahia implements Serializable {

    /**
     * Serial del bean
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que representa el id
     */
    private long idBahia;

    /**
     * Atributo que representa el numero de la bahia
     */
    private int numero;

    /**
     * Atributo que representa el estado de la bahia
     */
    private String estado;

    /**
     * Atributo que representa el idTipo de la bahia (foranea)
     */
    private int idTipo;

    /**
     * Constructor
     */
    public Bahia() {
        // Constructor vacio para que cumpla con las indicaciones de un bean
    }

    /**
     * Metodo que obtiene el id de la bahia
     *
     * @return
     */
    public long getIdBahia() {
        return idBahia;
    }

    /**
     * Metodo que modifica el id de la bahia
     *
     * @param idBahia
     */
    public void setIdBahia(long idBahia) {
        this.idBahia = idBahia;
    }

    /**
     * Metodo que obtiene el numero de la bahia
     *
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Metodo que modifica el numero de la bahia
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Metodo que obtiene el estado de la bahia
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Metodo que modifica el estado de la bahia
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Metodo que obtiene el idTipo de la bahia
     *
     * @return
     */
    public int getIdTipo() {
        return idTipo;
    }

    /**
     * Metodo que modifica el idTipo de la bahia
     *
     * @param idTipo
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * Metodo que imprime los datos del bean
     */
    @Override
    public String toString() {
        return "Bahia [idBahia=" + idBahia + ", numero=" + numero + ", estado=" + estado + ", idTipo=" + idTipo + "]";
    }

}
