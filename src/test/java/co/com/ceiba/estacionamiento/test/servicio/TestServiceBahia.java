package co.com.ceiba.estacionamiento.test.servicio;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.servicio.ServiceBahia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestServiceBahia {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private ServiceBahia serviceBahia;

	/**
	 * Test que lista todas las bahias
	 */
	@Test
	public void listar() {
		// Act
		List<Bahia> bahias = serviceBahia.listar();

		int contadorBahias = 0;
		for (int i = 0; i < bahias.size(); i++) {
			contadorBahias++;
		}

		// Assert
		assertEquals(contadorBahias, serviceBahia.contar());
	}

	/**
	 * Test que inserta una bahia
	 */
	@Test
	public void insertarActualizar() {
		// Arrange
		assertEquals(1, serviceBahia.contar());

		// Act
		Bahia bahia = TestBuilder.toBahia();
		serviceBahia.insertar(bahia);
		serviceBahia.actualizar(TestBuilder.toBahiaModificada(bahia));

		// Assert
		assertEquals(2, serviceBahia.contar());
	}
	
	/**
	 * Test que elimina una bahia
	 */
	@Test
	public void eliminar() {
		// Arrange
		String mensaje = "eliminado";

		// Act
		String respuesta = serviceBahia.eliminar(TestBuilder.toBahia());

		// Assert
		assertEquals(mensaje, respuesta);
	}
}
