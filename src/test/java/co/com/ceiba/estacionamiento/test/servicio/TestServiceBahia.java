package co.com.ceiba.estacionamiento.test.servicio;

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
	public void listarTodasLasBahias() {
		System.out.println();

		// Act
		List<Bahia> bahias = serviceBahia.listar();

		int contadorBahias = 0;
		for (int i = 0; i < bahias.size(); i++) {
			contadorBahias++;
		}

		// Assert
		assertEquals(contadorBahias, serviceBahia.contar());
	}

}
