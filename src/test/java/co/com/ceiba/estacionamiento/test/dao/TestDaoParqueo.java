package co.com.ceiba.estacionamiento.test.dao;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.dominio.Parqueo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestDaoParqueo {

	@Autowired
	private DaoParqueo daoParqueo;

	@Test
	public void mostrarParqueos() {
		System.out.println();

		List<Parqueo> parqueos = daoParqueo.findAllParqueos();

		int contadorParqueos = 0;
		for (int i = 0; i < parqueos.size(); i++) {
			contadorParqueos++;
		}

		assertEquals(contadorParqueos, daoParqueo.countParqueos());
	}

	@Test
	@Transactional
	public void insertarParqueo() {
		System.out.println();
		// Assert
		assertEquals(1, daoParqueo.countParqueos());

		// Act
		Parqueo parqueo = TestBuilder.toParqueo();
		daoParqueo.insertParqueo(parqueo);

		// Recuperamos el parqueo recien insertado por su id
		parqueo = daoParqueo.findParqueoById(2);

		// Assert
		assertEquals(2, daoParqueo.countParqueos());
	}

	@Test
	public void parqueoPorIdVehiculo() {
		System.out.println();

		// Act
		Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo(1);

		// Assert
		assertEquals(1, parqueo.getIdParqueo());
	}
}
