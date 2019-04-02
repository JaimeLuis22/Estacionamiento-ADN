package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

public interface ServiceVehiculo {

	/**
	 * Metodo que inserta un vehiculo
	 * @param vehiculo
	 * @throws Exception
	 */
	void insertarVehiculo(Vehiculo vehiculo) throws Exception;

	/**
	 * Metodo que actualiza un vehiculo
	 * @param vehiculo
	 */
    void actualizarVehiculo(Vehiculo vehiculo);

    /**
     * Metodo que elimina un vehiculo
     * @param vehiculo
     */
    void eliminarVehiculo(Vehiculo vehiculo);

    /**
     * Metodo que encuentra un vehiculo por su placa
     * @param vehiculo
     * @return
     */
    Vehiculo encontrarVehiculoPorPlaca(Vehiculo vehiculo);

    /**
     * Metodo que lista todos los vehiculos
     * @return
     */
    List<Vehiculo> listarTodosLosVehiculos();

    /**
     * Metodo que cuenta los vehiculos registrados en la base de datos
     * @return
     */
    int contarVehiculos();
    
    /**
     * Metodo que representa la salida de un vehiculo
     * @param vehiculo
     * @return
     * @throws Exception
     */
    double salidaVehiculo(Vehiculo vehiculo) throws Exception;
}
