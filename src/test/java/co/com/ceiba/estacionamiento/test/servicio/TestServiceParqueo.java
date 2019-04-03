package co.com.ceiba.estacionamiento.test.servicio;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.servicio.ServiceParqueo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceParqueo {

	private final Logger logger = LogManager.getRootLogger();
	
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
        try {
            System.out.println();
            logger.info("Inicio del test listarTodosLosParqueos");
            
            // Act
            List<Parqueo> parqueos = serviceParqueo.listarTodosLosParqueos();

            int contadorParqueos = 0;
            for (Parqueo parqueo : parqueos) {
                logger.info("Parqueo: " + parqueo);
                contadorParqueos++;
            }
            
            // Assert
            assertEquals(contadorParqueos, serviceParqueo.contarParqueos());

            logger.info("Fin del test listarTodosLosParqueos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

}
