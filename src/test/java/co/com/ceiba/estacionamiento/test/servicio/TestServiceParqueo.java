package co.com.ceiba.estacionamiento.test.servicio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.servicio.ServiceParqueo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestServiceParqueo {

	/**
	 * Inyeccion del bean
	 */
	@Autowired
	private ServiceParqueo serviceParqueo;

	/**
	 * Test que lista todos los parqueos
	 */
	@Test
	public void listarTodosLosParqueos() {
		System.out.println();

		// Act
		List<Parqueo> parqueos = serviceParqueo.listar();

		int contadorParqueos = 0;
		for (int i = 0; i < parqueos.size(); i++) {
			contadorParqueos++;
		}

		// Assert
		assertEquals(contadorParqueos, serviceParqueo.contar());
	}

}
