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
		assertEquals(1, daoVehiculo.countVehiculos());

		// Act
		Vehiculo vehiculo = TestBuilder.toVehiculo();
		daoVehiculo.insertVehiculo(vehiculo);

		// Recuperamos el vehiculo recien insertado por su placa
		vehiculo = daoVehiculo.findVehiculoByPlaca(vehiculo);

		// Assert
		assertEquals(2, daoVehiculo.countVehiculos());
	}

	@Test
	@Transactional
	public void buscarVehiculoNoExistentePorPlaca() {
		System.out.println();

		// Act
		Vehiculo vehiculo = TestBuilder.toVehiculo();

		// Assert
		assertNull(daoVehiculo.findVehiculoByPlaca(vehiculo));
	}
	
	@Test
    public void eliminar() {
    	// Arrange
    	String mensaje = "eliminado";
    	
        // Act
        String respuesta = daoVehiculo.deleteVehiculo(TestBuilder.toVehiculo());

        // Assert
        assertEquals(mensaje, respuesta);
    }
}
