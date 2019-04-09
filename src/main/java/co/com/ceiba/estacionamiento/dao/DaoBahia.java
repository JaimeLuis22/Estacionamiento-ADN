package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

public interface DaoBahia {

    /**
     * Metodo para insertar una bahia en la base de datos
     *
     * @param bahia
     */
    void insertBahia(Bahia bahia);

    /**
     * Metodo para actualizar una bahia en la base de datos
     *
     * @param bahia
     */
    void updateBahia(Bahia bahia);

    /**
     * Metodo que elimina una bahia en la base de datos
     *
     * @param bahia
     */
    String deleteBahia(Bahia bahia);

    /**
     * Metodo que encuentra una bahia por el numero
     *
     * @param bahia
     * @return
     */
    Bahia findBahiaByNumero(Bahia bahia) throws EstacionamientoException;

    /**
     * Metodo que retorna un listado de bahias
     *
     * @return
     */
    List<Bahia> findAllBahias();

    /**
     * Metodo que cuenta las bahias registradas en la base de datos
     *
     * @return
     */
    int countBahias();

    /**
     * Metodo que encuentra una bahia por su id
     *
     * @param idBahia
     * @return
     */
    Bahia findBahiaById(long idBahia);

    /**
     * Metodo que cuenta las bahias por el idTipo
     *
     * @param idTipo
     * @return
     */
    int countBahiasByIdTipo(int idTipo);

    /**
     * Metodo que cuenta las bahias por el idTipo y su estado
     *
     * @param idTipo
     * @return
     */
    int countBahiasByIdTipoState(int idTipo);
}
