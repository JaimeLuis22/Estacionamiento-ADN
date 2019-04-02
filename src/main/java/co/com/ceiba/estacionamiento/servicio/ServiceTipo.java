package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio;

import java.util.List;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

public interface ServiceTipo {
	
	public List<Tipo> listarTipos();

    public Tipo recuperarTipoPorNombre(Tipo tipo);

    public void agregarTipo(Tipo tipo);

    public void modificarTipo(Tipo tipo);

    public void eliminarTipo(Tipo tipo);

}
