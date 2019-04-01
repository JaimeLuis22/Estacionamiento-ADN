package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

public interface DaoVehiculo {

	void insertVehiculo(Vehiculo vehiculo);

    void updateVehiculo(Vehiculo vehiculo);

    void deleteVehiculo(Vehiculo vehiculo);

    Vehiculo findVehiculoByPlaca(Vehiculo vehiculo);

    List<Vehiculo> findAllVehiculos();

    int countVehiculos();
    
}
