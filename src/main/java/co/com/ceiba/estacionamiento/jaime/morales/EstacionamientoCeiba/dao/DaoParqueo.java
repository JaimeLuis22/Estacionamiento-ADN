package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;

public interface DaoParqueo {

	void insertParqueo(Parqueo parqueo);

    void updateParqueo(Parqueo parqueo);

    void deleteParqueo(Parqueo parqueo);

    Parqueo findParqueoById(long idParqueo);

    List<Parqueo> findAllParqueos();

    int countParqueos();
    
    Parqueo findParqueoByIdVehiculo(int idVehiculo);
}
