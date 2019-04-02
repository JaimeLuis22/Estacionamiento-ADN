package co.com.ceiba.estacionamiento.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.dominio.Parqueo;

@Service
public class ServiceParqueoContrato implements ServiceParqueo{
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoParqueo daoParqueo;

	/**
	 * Metodo que registra un parqueo
	 * @param parqueo
	 */
	@Override
	public void insertarParqueo(Parqueo parqueo) {
		daoParqueo.insertParqueo(parqueo);
	}

	/**
	 * Metodo que actualiza un parqueo
	 * @param parqueo
	 */
	@Override
	public void actualizarParqueo(Parqueo parqueo) {
		daoParqueo.updateParqueo(parqueo);
	}

	/**
     * Metodo que elimina un parqueo
     * @param parqueo
     */
	@Override
	public void eliminarParqueo(Parqueo parqueo) {
		daoParqueo.deleteParqueo(parqueo);
	}

	 /**
     * Metodo que encuentra un parqueo por su id
     * @param idParqueo
     * @return
     */
	@Override
	public Parqueo encontrarParqueoPorId(long idParqueo) {
		return daoParqueo.findParqueoById(idParqueo);
	}

	/**
     * Metodo que lista todos los parqueos registrados
     * @return
     */
	@Override
	public List<Parqueo> listarTodosLosParqueos() {// TODO Auto-generated method stub
		return daoParqueo.findAllParqueos();
	}

	/**
     * Metodo que cuenta los parqueos registrados
     */
	@Override
	public int contarParqueos() {
		return daoParqueo.countParqueos();
	}

}