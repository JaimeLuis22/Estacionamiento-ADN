package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

public interface DaoTipo {
	
	/**
	 * Metodo que inserta un tipo en la base de datos
	 * @param tipo
	 */
	void insertTipo(Tipo tipo);

	/**
	 * Metodo que actualiza un tipo en la base de datos
	 * @param tipo
	 */
    void updateTipo(Tipo tipo);

    /**
     * Metodo que elimina un tipo en la base de datos
     * @param tipo
     */
    void deleteTipo(Tipo tipo);

    /**
     * Metodo que encuentra un tipo por el nombre
     * @param tipo
     * @return
     */
    Tipo findTipoByNombre(Tipo tipo) throws EstacionamientoException;

    /**
     * Metodo que retorna un listado de tipos de la base de datos
     * @return
     */
    List<Tipo> findAllTipos();

    /**
     * Metodo que cuenta los tipos registrados en la base de datos
     * @return
     */
    int countTipos();
    
    /**
     * Metodo que encuentra un tipo por el id
     * @param idTipo
     * @return
     */
    Tipo findTipoById(long idTipo);

}
