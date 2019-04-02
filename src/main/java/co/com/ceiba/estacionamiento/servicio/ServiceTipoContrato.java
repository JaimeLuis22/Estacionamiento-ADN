package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dao.DaoTipo;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Service
public class ServiceTipoContrato implements ServiceTipo{
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoTipo daoTipo;

	/**
	 * Metodo que lista los tipos
	 * @return
	 */
	@Override
	public List<Tipo> listarTipos() {
		return daoTipo.findAllTipos();
	}

	/**
	 * Metodo que obtiene el tipo por su nombre
	 * @param tipo
	 * @return
	 */
	@Override
	public Tipo recuperarTipoPorNombre(Tipo tipo) throws EstacionamientoException{
		return daoTipo.findTipoByNombre(tipo);
	}

	/**
     * Metodo que registra un tipo
     * @param tipo
     */
	@Override
	public void agregarTipo(Tipo tipo) {
		daoTipo.insertTipo(tipo);		
	}

	/**
     * Metodo que actualiza un tipo
     * @param tipo
     */
	@Override
	public void modificarTipo(Tipo tipo) {
		daoTipo.updateTipo(tipo);		
	}

	/**
     * Metodo que elimina un tipo
     */
	@Override
	public void eliminarTipo(Tipo tipo) {
		daoTipo.deleteTipo(tipo);		
	}
	
}
