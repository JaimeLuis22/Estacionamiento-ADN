package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoBahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;

@Service
public class ServiceBahiaContrato implements ServiceBahia{

	@Autowired
	private DaoBahia daoBahia;
	
	@Override
	public void insertarBahia(Bahia bahia) {
		daoBahia.insertBahia(bahia);		
	}

	@Override
	public void actualizarBahia(Bahia bahia) {
		daoBahia.updateBahia(bahia);		
	}

	@Override
	public void eliminarBahia(Bahia bahia) {
		daoBahia.deleteBahia(bahia);		
	}

	@Override
	public Bahia recuperarBahiaPorNumero(Bahia bahia) {
		return daoBahia.findBahiaByNumero(bahia);
	}

	@Override
	public List<Bahia> listarTodasLasBahias() {
		return daoBahia.findAllBahias();
	}

	@Override
	public int contarBahias() {
		return daoBahia.countBahias();
	}

}
