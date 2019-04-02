package co.com.ceiba.estacionamiento.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dao.DaoBahia;
import co.com.ceiba.estacionamiento.dominio.Bahia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDaoBahia {

	private final Logger logger = LogManager.getRootLogger();
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
    private DaoBahia daoBahia;
	
	@Test
    public void mostrarBahias() {
        try {
            System.out.println();
            logger.info("Inicio del test mostrarBahias");
            
            // Act
            List<Bahia> bahias = daoBahia.findAllBahias();

            int contadorBahias = 0;
            for (Bahia bahia : bahias) {
                logger.info("Bahia: " + bahia);
                contadorBahias++;
            }

            // Assert
            assertEquals(contadorBahias, daoBahia.countBahias());

            logger.info("Fin del test mostrarBahias");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
	@Transactional
    public void insertarBahia() {
		try {
            System.out.println();
            logger.info("Inicio del test insertarBahia");
            
            // Arrange
            // El script de datos tiene 1 registro
            assertEquals(1, daoBahia.countBahias());
            
            // Act
            Bahia bahia = new Bahia();
            bahia.setNumero(2);
            bahia.setEstado("Disponible");
            bahia.setIdTipo(1);
            daoBahia.insertBahia(bahia);

            //Recuperamos la bahia recien insertada por su numero 
            bahia = daoBahia.findBahiaByNumero(bahia);
            logger.info("Bahia insertada (recuperada por numero): \n" + bahia);
            
            // Assert
            // Deberia existir 2 bahias
            assertEquals(2, daoBahia.countBahias());
            logger.info("Fin del test insertarBahia");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void contarBahiaPorIdTipo() {
		try {
            System.out.println();
            logger.info("Inicio del test contarBahiaPorIdTipo");
            
            // Arrange
            int idTipo = 1;
            
            // Act
            List<Bahia> bahias = daoBahia.findAllBahias();
            int contadorBahias = 0;
            for (Bahia bahia : bahias) {
            	if(bahia.getIdTipo() == idTipo) {
            		logger.info("Bahia: " + bahia);               
                	contadorBahias++;
                }                
            }
            
            // Assert
            assertEquals(contadorBahias, daoBahia.countBahiasByIdTipo(idTipo));
            logger.info("Fin del test contarBahiaPorIdTipo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void contarBahiaPorIdTipoEstado() {
		try {
            System.out.println();
            logger.info("Inicio del test contarBahiaPorIdTipoEstado");
            
            // Arrange
            int idTipo = 1;
            
            // Act
            List<Bahia> bahias = daoBahia.findAllBahias();
            int contadorBahias = 0;
            for (Bahia bahia : bahias) {
            	if(bahia.getIdTipo() == idTipo && bahia.getEstado().equals("Disponible")) {
            		logger.info("Bahia: " + bahia);               
                	contadorBahias++;
                }                
            }
            
            // Assert
            assertEquals(contadorBahias, daoBahia.countBahiasByIdTipoState(idTipo));
            logger.info("Fin del test contarBahiaPorIdTipoEstado");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void buscarBahiaPorId() {
		try {
            System.out.println();
            logger.info("Inicio del test buscarBahiaPorId");
            
            // Arrange
            int idBahia = 1;
            
            // Act
            Bahia bahia = daoBahia.findBahiaById((long)idBahia);
            logger.info(bahia);
            
            // Assert
            assertNotNull(bahia);
            logger.info("Fin del test buscarBahiaPorId");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
}
