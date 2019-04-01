package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

public interface DaoTipo {
	
	void insertTipo(Tipo tipo);

    void updateTipo(Tipo tipo);

    void deleteTipo(Tipo tipo);

    Tipo findTipoByNombre(Tipo tipo);

    List<Tipo> findAllTipos();

    int countTipos();
    
    Tipo findTipoById(long idTipo);

}
