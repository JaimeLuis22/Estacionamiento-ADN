package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

public interface ServiceParqueo {

	/**
	 * Metodo que registra un parqueo
	 * @param parqueo
	 */
	void insertarParqueo(Parqueo parqueo);

	/**
	 * Metodo que actualiza un parqueo
	 * @param parqueo
	 */
    void actualizarParqueo(Parqueo parqueo);

    /**
     * Metodo que elimina un parqueo
     * @param parqueo
     */
    void eliminarParqueo(Parqueo parqueo);

    /**
     * Metodo que encuentra un parqueo por su id
     * @param idParqueo
     * @return
     */
    Parqueo encontrarParqueoPorId(long idParqueo) throws EstacionamientoException;

    /**
     * Metodo que lista todos los parqueos registrados
     * @return
     */
    List<Parqueo> listarTodosLosParqueos();

    /**
     * Metodo que cuenta los parqueos registrados
     */
    int contarParqueos();
    
}
