package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.commons.CodesApp;
import co.com.ceiba.estacionamiento.dao.DaoBahia;
import co.com.ceiba.estacionamiento.dao.DaoTipo;
import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Service
public class ServiceBahia{

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
	public void insertar(Bahia bahia) throws EstacionamientoException{
		Tipo tipo = daoTipo.findTipoById((long)bahia.getIdTipo());
		int numeroBahias = daoBahia.countBahiasByIdTipo(bahia.getIdTipo());
		
		if("Carro".equals(tipo.getNombre()) && numeroBahias > 20) {
			throw new EstacionamientoException("No se puede registrar. Ha superado el limite de inserciones", CodesApp.ERROR_NEG_401.getCodigo());
		}
		
		if("Moto".equals(tipo.getNombre()) && numeroBahias > 10) {
			throw new EstacionamientoException("No se puede registrar. Ha superado el limite de inserciones", CodesApp.ERROR_NEG_401.getCodigo());
		}
		
		daoBahia.insertBahia(bahia);		
	}

	/**
	 * Metodo que actualiza una bahia
	 * @param bahia
	 */
	public void actualizar(Bahia bahia) {
		daoBahia.updateBahia(bahia);		
	}

	/**
     * Metodo que elimina una bahia
     * @param bahia
     */
	public String eliminar(Bahia bahia) {
		return daoBahia.deleteBahia(bahia);		
	}

	/**
     * Metodo que recupera una bahia por su numero
     * @param bahia
     * @return
     */
	public Bahia recuperarPorNumero(Bahia bahia) throws EstacionamientoException{
		return daoBahia.findBahiaByNumero(bahia);
	}

	/**
     * Metodo que lista todas las bahias
     * @return
     */
	public List<Bahia> listar() {
		return daoBahia.findAllBahias();
	}

	/**
     * Metodo que cuenta las bahias registradas en la base de datos
     * @return
     */
	public int contar() {
		return daoBahia.countBahias();
	}

}
