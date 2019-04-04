package co.com.ceiba.estacionamiento.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dao.DaoTipo;
import co.com.ceiba.estacionamiento.dominio.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestDaoTipo {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoTipo daoTipo;

	@Test
	public void mostrarTipos() {
		System.out.println();

		// Act
		List<Tipo> tipos = daoTipo.findAllTipos();

		int contadorTipos = 0;
		for (int i = 0; i < tipos.size(); i++) {
			contadorTipos++;
		}

		// Assert
		assertEquals(contadorTipos, daoTipo.countTipos());
	}

	@Test
	@Transactional
	public void insertarTipo() {
		System.out.println();

		// Arrange
		// El script de datos tiene 1 registro
		assertEquals(2, daoTipo.countTipos());

		// Act
		Tipo tipo = new Tipo();
		tipo.setNombre("Bicicleta");
		daoTipo.insertTipo(tipo);

		// Recuperamos el tipo recien insertado por su nombre
		tipo = daoTipo.findTipoByNombre(tipo);

		// Assert
		// Deberia existir 2 tipos
		assertEquals(3, daoTipo.countTipos());
	}

	@Test
	public void buscarTipoPorId() {
		System.out.println();

		// Act
		int idTipo = 1;
		String nombre = "Carro";

		// Assert
		// Deberia existir 2 tipos
		assertEquals(nombre, daoTipo.findTipoById(idTipo).getNombre());
	}

}
