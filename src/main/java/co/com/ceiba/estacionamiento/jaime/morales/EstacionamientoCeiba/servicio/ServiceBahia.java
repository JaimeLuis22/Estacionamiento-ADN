package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;

public interface ServiceBahia {

	void insertarBahia(Bahia bahia);

    void actualizarBahia(Bahia bahia);

    void eliminarBahia(Bahia bahia);

    Bahia recuperarBahiaPorNumero(Bahia bahia);

    List<Bahia> listarTodasLasBahias();

    int contarBahias();
}
