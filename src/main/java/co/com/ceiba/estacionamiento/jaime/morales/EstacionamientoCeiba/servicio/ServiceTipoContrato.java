package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoTipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

@Service
public class ServiceTipoContrato implements ServiceTipo{
	
	@Autowired
	private DaoTipo daoTipo;

	@Override
	public List<Tipo> listarTipos() {
		return daoTipo.findAllTipos();
	}

	@Override
	public Tipo recuperarTipoPorNombre(Tipo tipo) {
		return daoTipo.findTipoByNombre(tipo);
	}

	@Override
	public void agregarTipo(Tipo tipo) {
		daoTipo.insertTipo(tipo);		
	}

	@Override
	public void modificarTipo(Tipo tipo) {
		daoTipo.updateTipo(tipo);		
	}

	@Override
	public void eliminarTipo(Tipo tipo) {
		daoTipo.deleteTipo(tipo);		
	}
	
}
