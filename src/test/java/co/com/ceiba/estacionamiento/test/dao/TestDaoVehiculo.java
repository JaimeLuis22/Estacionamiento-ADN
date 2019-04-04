package co.com.ceiba.estacionamiento.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dao.DaoVehiculo;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestDaoVehiculo {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private DaoVehiculo daoVehiculo;

	@Test
	public void mostrarVehiculos() {
		System.out.println();

		// Arrange
		List<Vehiculo> vehiculos = daoVehiculo.findAllVehiculos();

		// Act
		int contadorVehiculos = 0;
		for (int i = 0; i < vehiculos.size(); i++) {
			contadorVehiculos++;
		}

		// Assert
		assertEquals(contadorVehiculos, daoVehiculo.countVehiculos());
	}

	@Test
	@Transactional
	public void insertarVehiculo() {
		System.out.println();

		// Arrange
		// El script de datos tiene 1 registro
		assertEquals(1, daoVehiculo.countVehiculos());

		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("uyt122");
		vehiculo.setIdTipo(1);
		vehiculo.setIdBahia(1);
		daoVehiculo.insertVehiculo(vehiculo);

		// Recuperamos el vehiculo recien insertado por su placa
		vehiculo = daoVehiculo.findVehiculoByPlaca(vehiculo);

		// Assert
		// Deberia existir 2 vehiculos
		assertEquals(2, daoVehiculo.countVehiculos());
	}

	@Test
	@Transactional
	public void buscarVehiculoNoExistentePorPlaca() {
		System.out.println();

		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("uyt122");
		vehiculo.setIdTipo(1);
		vehiculo.setIdBahia(1);

		// Assert
		assertNull(daoVehiculo.findVehiculoByPlaca(vehiculo));
	}
}
