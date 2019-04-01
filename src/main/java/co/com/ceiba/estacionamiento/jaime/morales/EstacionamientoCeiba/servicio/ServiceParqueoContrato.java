package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;

@Service
public class ServiceParqueoContrato implements ServiceParqueo{
	
	@Autowired
	private DaoParqueo daoParqueo;

	@Override
	public void insertarParqueo(Parqueo parqueo) {
		daoParqueo.insertParqueo(parqueo);
	}

	@Override
	public void actualizarParqueo(Parqueo parqueo) {
		daoParqueo.updateParqueo(parqueo);
	}

	@Override
	public void eliminarParqueo(Parqueo parqueo) {
		daoParqueo.deleteParqueo(parqueo);
	}

	@Override
	public Parqueo encontrarParqueoPorId(long idParqueo) {
		return daoParqueo.findParqueoById(idParqueo);
	}

	@Override
	public List<Parqueo> listarTodosLosParqueos() {// TODO Auto-generated method stub
		return daoParqueo.findAllParqueos();
	}

	@Override
	public int contarParqueos() {
		return daoParqueo.countParqueos();
	}

}
