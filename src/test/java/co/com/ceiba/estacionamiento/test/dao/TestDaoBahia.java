package co.com.ceiba.estacionamiento.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dao.DaoBahia;
import co.com.ceiba.estacionamiento.dominio.Bahia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestDaoBahia {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoBahia daoBahia;

	@Test
	public void mostrarBahias() {
		System.out.println();

		// Act
		List<Bahia> bahias = daoBahia.findAllBahias();

		int contadorBahias = 0;
		for (int i = 0; i < bahias.size(); i++) {
			contadorBahias++;
		}

		// Assert
		assertEquals(contadorBahias, daoBahia.countBahias());
	}

	@Test
	@Transactional
	public void insertarBahia() {
		System.out.println();

		// Arrange
		// El script de datos tiene 1 registro
		assertEquals(1, daoBahia.countBahias());

		// Act
		Bahia bahia = new Bahia();
		bahia.setNumero(2);
		bahia.setEstado("Disponible");
		bahia.setIdTipo(1);
		daoBahia.insertBahia(bahia);

		// Recuperamos la bahia recien insertada por su numero
		bahia = daoBahia.findBahiaByNumero(bahia);

		// Assert
		// Deberia existir 2 bahias
		assertEquals(2, daoBahia.countBahias());
	}

	@Test
	public void contarBahiaPorIdTipo() {
		System.out.println();

		// Arrange
		int idTipo = 1;

		// Act
		List<Bahia> bahias = daoBahia.findAllBahias();
		int contadorBahias = 0;
		for (Bahia bahia : bahias) {
			if (bahia.getIdTipo() == idTipo) {
				contadorBahias++;
			}
		}

		// Assert
		assertEquals(contadorBahias, daoBahia.countBahiasByIdTipo(idTipo));
	}

	@Test
	public void contarBahiaPorIdTipoEstado() {
		System.out.println();

		// Arrange
		int idTipo = 1;

		// Act
		List<Bahia> bahias = daoBahia.findAllBahias();
		int contadorBahias = 0;
		for (Bahia bahia : bahias) {
			if (bahia.getIdTipo() == idTipo && bahia.getEstado().equals("Disponible")) {
				contadorBahias++;
			}
		}

		// Assert
		assertEquals(contadorBahias, daoBahia.countBahiasByIdTipoState(idTipo));
	}

	@Test
	public void buscarBahiaPorId() {
		System.out.println();

		// Arrange
		int idBahia = 1;

		// Act
		Bahia bahia = daoBahia.findBahiaById((long) idBahia);

		// Assert
		assertNotNull(bahia);
	}
}
