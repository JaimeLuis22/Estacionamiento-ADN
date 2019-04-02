package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.dominio.Bahia;

public interface ServiceBahia {

	/**
	 * Metodo que inserta una bahia
	 * @param bahia
	 * @throws Exception
	 */
	void insertarBahia(Bahia bahia) throws Exception;

	/**
	 * Metodo que actualiza una bahia
	 * @param bahia
	 */
    void actualizarBahia(Bahia bahia);

    /**
     * Metodo que elimina una bahia
     * @param bahia
     */
    void eliminarBahia(Bahia bahia);

    /**
     * Metodo que recupera una bahia por su numero
     * @param bahia
     * @return
     */
    Bahia recuperarBahiaPorNumero(Bahia bahia);

    /**
     * Metodo que lista todas las bahias
     * @return
     */
    List<Bahia> listarTodasLasBahias();

    /**
     * Metodo que cuenta las bahias registradas en la base de datos
     * @return
     */
    int contarBahias();
}
