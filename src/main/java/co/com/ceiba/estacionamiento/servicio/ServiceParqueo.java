package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Service
public class ServiceParqueo{
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoParqueo daoParqueo;

	/**
	 * Metodo que registra un parqueo
	 * @param parqueo
	 */
	public void insertar(Parqueo parqueo) {
		daoParqueo.insertParqueo(parqueo);
	}

	/**
	 * Metodo que actualiza un parqueo
	 * @param parqueo
	 */
	public void actualizar(Parqueo parqueo) {
		daoParqueo.updateParqueo(parqueo);
	}

	/**
     * Metodo que elimina un parqueo
     * @param parqueo
     */
	public String eliminar(Parqueo parqueo) {
		return daoParqueo.deleteParqueo(parqueo);
	}

	 /**
     * Metodo que encuentra un parqueo por su id
     * @param idParqueo
     * @return
     */
	public Parqueo encontrarPorId(long idParqueo) throws EstacionamientoException{
		return daoParqueo.findParqueoById(idParqueo);
	}

	/**
     * Metodo que lista todos los parqueos registrados
     * @return
     */
	public List<Parqueo> listar() {
		return daoParqueo.findAllParqueos();
	}

	/**
     * Metodo que cuenta los parqueos registrados
     */
	public int contar() {
		return daoParqueo.countParqueos();
	}
	
	/**
     * Metodo que obtiene un parqueo por el id del vehiculo
     */
	public Parqueo encontrarParqueoPorIdVehiculo(int idVehiculo) {
		return daoParqueo.findParqueoByIdVehiculo(idVehiculo);
	}
}
