package co.com.ceiba.estacionamiento.test.servicio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.servicio.ServiceVehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestServiceVehiculo {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private ServiceVehiculo serviceVehiculo;

	/**
	 * Test que lista todos los vehiculos
	 */
	@Test
	@Transactional
	public void listar() {
		System.out.println();

		// Act
		List<Vehiculo> vehiculos = serviceVehiculo.listar();

		int contadorVehiculos = 0;
		for (int i = 0; i < vehiculos.size(); i++) {
			contadorVehiculos++;
		}

		// Assert
		assertEquals(contadorVehiculos, serviceVehiculo.contar());
	}

	/**
	 * Test que inserta un vehiculo
	 */
	@Test
	@Transactional
	public void insertar() {
		System.out.println();
		// Arrange
		// El script de datos tiene 1 registro
		assertEquals(1, serviceVehiculo.contar());

		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("ndj840");
		vehiculo.setIdTipo(1);
		vehiculo.setIdBahia(1);
		serviceVehiculo.insertar(vehiculo);

		// Recuperamos el vehiculo recien insertado por su placa
		vehiculo = serviceVehiculo.encontrarPorPlaca(vehiculo);

		// Assert
		// Deberia existir 2 vehiculos
		assertEquals(2, serviceVehiculo.contar());
	}

	/**
	 * Test que cuenta vehiculos, inserta, encuentra por su placa y actualiza
	 */
	@Test
	@Transactional
	public void operacionA_Vehiculo() {
		System.out.println();
		// Arrange
		// El script de datos tiene 1 registro
		assertEquals(1, serviceVehiculo.contar());

		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("ndj840");
		vehiculo.setIdTipo(1);
		vehiculo.setIdBahia(1);
		serviceVehiculo.insertar(vehiculo);

		// Recuperamos el vehiculo recien insertado por su placa
		vehiculo = serviceVehiculo.encontrarPorPlaca(vehiculo);

		// Assert
		// Deberia existir 2 vehiculos
		assertEquals(2, serviceVehiculo.contar());

		// Modificamos el vehiculo ingresado
		vehiculo.setPlaca("mmm333");
		serviceVehiculo.actualizar(vehiculo);

		desplegarVehiculos();
	}

	/**
	 * Test que valida la regla de la placa que inicia con A (Domingos - Lunes)
	 * Tener en cuenta el estado de la bahia
	 */
	@Test
	@Transactional
	public void reglaPlacaInicioA() {
		System.out.println();
		// Arrange
		String mensajeEsperado = "Autorizacion negada";

		try {
			// Act
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca("AAA123");
			vehiculo.setIdTipo(1);
			vehiculo.setIdBahia(1);
			serviceVehiculo.insertar(vehiculo);

		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(mensajeEsperado, e.getMensaje());
		}
	}

	/**
	 * Test que valida si el vehiculo es una moto y no cuente con el cilindraje
	 */
	@Test
	@Transactional
	public void validarVehiculoMoto() {
		System.out.println();
		// Arrange
		String mensajeEsperado = "Cilindraje vacio";

		try {
			// Act
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca("qqq123");
			vehiculo.setIdTipo(2);
			vehiculo.setIdBahia(1);
			serviceVehiculo.insertar(vehiculo);

		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(mensajeEsperado, e.getMensaje());
		}
	}

	/**
	 * Test que valida si la bahia esta disponible para ingresar el vehiculo
	 */
	@Test
	@Transactional
	public void validarBahiaDisponible() {
		System.out.println();
		// Arrange
		String mensajeEsperado = "No hay bahias disponibles";

		try {
			// Act
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca("qqq123");
			vehiculo.setIdTipo(1);
			vehiculo.setIdBahia(1);
			serviceVehiculo.insertar(vehiculo);

		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(mensajeEsperado, e.getMensaje());
		}
	}

	/**
	 * Test que busca un vehiculo por su placa
	 */
	@Test
	@Transactional
	public void obtenerVehiculoPorPlaca() {
		System.out.println();
		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("QWE213");

		Vehiculo vehiculoR = serviceVehiculo.encontrarPorPlaca(vehiculo);
		
		// Assert
		assertNotNull(vehiculoR);
	}

	/**
	 * Test que da salida a un vehiculo
	 */
	@Test
	@Transactional
	public void salidaVehiculo() {
		System.out.println();
		// Act
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo((long) 1);
		vehiculo.setPlaca("QWE213");
		vehiculo.setIdTipo(1);
		vehiculo.setIdBahia(1);

		double costo = serviceVehiculo.salida(vehiculo);

		// Assert
		assertNotNull(costo);
	}

	/**
	 * Metodo que ayuda a la impresion de los vehiculos
	 */
	private void desplegarVehiculos() {
		List<Vehiculo> vehiculos = serviceVehiculo.listar();
		
		assertNotNull(vehiculos);
	}
}
