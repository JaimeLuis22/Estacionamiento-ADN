package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dao.DaoBahia;
import co.com.ceiba.estacionamiento.dao.DaoTipo;
import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.excepcion.error.ErrorCodes;

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
	public void insertarBahia(Bahia bahia) throws EstacionamientoException{
		Tipo tipo = daoTipo.findTipoById((long)bahia.getIdTipo());
		int numeroBahias = daoBahia.countBahiasByIdTipo(bahia.getIdTipo());
		
		if("Carro".equals(tipo.getNombre()) && numeroBahias > 20) {
			throw new EstacionamientoException("No se puede registrar. Ha superado el limite de inserciones", ErrorCodes.ERROR_NEG_401.getCodigo());
		}
		
		if("Moto".equals(tipo.getNombre()) && numeroBahias > 10) {
			throw new EstacionamientoException("No se puede registrar. Ha superado el limite de inserciones", ErrorCodes.ERROR_NEG_401.getCodigo());
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
	public Bahia recuperarBahiaPorNumero(Bahia bahia) throws EstacionamientoException{
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
