package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;

public interface DaoParqueo {

	/**
	 * Metodo que inserta un parqueo en la base de datos
	 * @param parqueo
	 */
	void insertParqueo(Parqueo parqueo);

	/**
	 * Metodo que actualiza un parqueo en la base de datos
	 * @param parqueo
	 */
    void updateParqueo(Parqueo parqueo);

    /**
     * Metodo que elimina un parqueo en la base de datos
     * @param parqueo
     */
    void deleteParqueo(Parqueo parqueo);

    /**
     * Metodo que encuentra un parqueo por el id
     * @param idParqueo
     * @return
     */
    Parqueo findParqueoById(long idParqueo);

    /**
     * Metodo que retorna un listado de parqueos
     * @return
     */
    List<Parqueo> findAllParqueos();

    /**
     * Metodo que cuenta los parqueos registrados en la base de datos
     * @return
     */
    int countParqueos();
    
    /**
     * Metodo que encuentra un parqueo por el idVehiculo
     * @param idVehiculo
     * @return
     */
    Parqueo findParqueoByIdVehiculo(int idVehiculo);
}
