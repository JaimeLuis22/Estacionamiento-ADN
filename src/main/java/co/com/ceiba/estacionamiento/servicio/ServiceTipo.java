package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Tipo;

public interface ServiceTipo {
	
	/**
	 * Metodo que lista los tipos
	 * @return
	 */
	public List<Tipo> listarTipos();

	/**
	 * Metodo que obtiene el tipo por su nombre
	 * @param tipo
	 * @return
	 */
    public Tipo recuperarTipoPorNombre(Tipo tipo);

    /**
     * Metodo que registra un tipo
     * @param tipo
     */
    public void agregarTipo(Tipo tipo);

    /**
     * Metodo que actualiza un tipo
     * @param tipo
     */
    public void modificarTipo(Tipo tipo);

    /**
     * Metodo que elimina un tipo
     */
    public void eliminarTipo(Tipo tipo);

}
