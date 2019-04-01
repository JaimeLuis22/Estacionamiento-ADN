package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

public interface ServiceVehiculo {

	void insertarVehiculo(Vehiculo vehiculo) throws Exception;

    void actualizarVehiculo(Vehiculo vehiculo);

    void eliminarVehiculo(Vehiculo vehiculo);

    Vehiculo encontrarVehiculoPorPlaca(Vehiculo vehiculo);

    List<Vehiculo> listarTodosLosVehiculos();

    int contarVehiculos();
    
    void salidaVehiculo(Vehiculo vehiculo);
}
