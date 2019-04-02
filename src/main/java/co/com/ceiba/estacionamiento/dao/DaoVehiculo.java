package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

public interface DaoVehiculo {

	/**
	 * Metodo que inserta un vehiculo en la base de datos
	 * @param vehiculo
	 */
	void insertVehiculo(Vehiculo vehiculo);

	/**
	 * Metodo que actualiza un vehiculo en la base de datos
	 * @param vehiculo
	 */
    void updateVehiculo(Vehiculo vehiculo);

    /**
     * Metodo que elimina un vehiculo en la base de datos
     * @param vehiculo
     */
    void deleteVehiculo(Vehiculo vehiculo);

    /**
     * Metodo que encuentra un vehiculo por la placa
     * @param vehiculo
     * @return
     */
    Vehiculo findVehiculoByPlaca(Vehiculo vehiculo) throws EstacionamientoException;

    /**
     * Metodo que retorna los vehiculos registrados en la base de datos
     * @return
     */
    List<Vehiculo> findAllVehiculos();

    /**
     * Metodo que cuenta los vehiculos registrados en la base de datos
     * @return
     */
    int countVehiculos();
    
}
