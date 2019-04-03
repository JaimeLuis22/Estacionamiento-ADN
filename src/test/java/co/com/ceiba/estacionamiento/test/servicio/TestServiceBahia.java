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

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.servicio.ServiceBahia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceBahia {

	private final Logger logger = LogManager.getRootLogger();
	
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
        try {
            System.out.println();
            logger.info("Inicio del test listarTodasLasBahias");
            
            // Act
            List<Bahia> bahias = serviceBahia.listarTodasLasBahias();

            int contadorBahias = 0;
            for (Bahia bahia : bahias) {
                logger.info("Bahia: " + bahia);
                contadorBahias++;
            }
            
            // Assert
            assertEquals(contadorBahias, serviceBahia.contarBahias());

            logger.info("Fin del test listarTodasLasBahias");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

}
