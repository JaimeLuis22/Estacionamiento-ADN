package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;

public interface DaoBahia {

	void insertBahia(Bahia bahia);

    void updateBahia(Bahia bahia);

    void deleteBahia(Bahia bahia);

    Bahia findBahiaByNumero(Bahia bahia);

    List<Bahia> findAllBahias();

    int countBahias();
    
    Bahia findBahiaById(long idBahia);
}
