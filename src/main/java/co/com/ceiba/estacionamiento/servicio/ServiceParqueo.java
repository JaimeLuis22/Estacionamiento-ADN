package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;

public interface ServiceParqueo {

	void insertarParqueo(Parqueo parqueo);

    void actualizarParqueo(Parqueo parqueo);

    void eliminarParqueo(Parqueo parqueo);

    Parqueo encontrarParqueoPorId(long idParqueo);

    List<Parqueo> listarTodosLosParqueos();

    int contarParqueos();
    
}
