package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoBahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoTipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

@Service
public class ServiceBahiaContrato implements ServiceBahia{

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoBahia daoBahia;
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoTipo daoTipo;
	
	/**
	 * Metodo que inserta una bahia
	 * @param bahia
	 * @throws Exception
	 */
	@Override
	public void insertarBahia(Bahia bahia) throws Exception{
		Tipo tipo = daoTipo.findTipoById((long)bahia.getIdTipo());
		int numeroBahias = daoBahia.countBahiasByIdTipo(bahia.getIdTipo());
		
		if(tipo.getNombre().equals("Carro") && numeroBahias > 20) {
			throw new Exception("No se puede registrar. Ha superado el limite de inserciones");
		}
		
		if(tipo.getNombre().equals("Moto") && numeroBahias > 10) {
			throw new Exception("No se puede registrar. Ha superado el limite de inserciones");
		}
		
		daoBahia.insertBahia(bahia);		
	}

	/**
	 * Metodo que actualiza una bahia
	 * @param bahia
	 */
	@Override
	public void actualizarBahia(Bahia bahia) {
		daoBahia.updateBahia(bahia);		
	}

	/**
     * Metodo que elimina una bahia
     * @param bahia
     */
	@Override
	public void eliminarBahia(Bahia bahia) {
		daoBahia.deleteBahia(bahia);		
	}

	/**
     * Metodo que recupera una bahia por su numero
     * @param bahia
     * @return
     */
	@Override
	public Bahia recuperarBahiaPorNumero(Bahia bahia) {
		return daoBahia.findBahiaByNumero(bahia);
	}

	/**
     * Metodo que lista todas las bahias
     * @return
     */
	@Override
	public List<Bahia> listarTodasLasBahias() {
		return daoBahia.findAllBahias();
	}

	/**
     * Metodo que cuenta las bahias registradas en la base de datos
     * @return
     */
	@Override
	public int contarBahias() {
		return daoBahia.countBahias();
	}

}
