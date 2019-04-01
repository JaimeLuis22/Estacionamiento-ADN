package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoBahia;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDaoBahia {

	private final Logger logger = LogManager.getRootLogger();
	
	@Autowired
    private DaoBahia daoBahia;
	
	@Test
    public void mostrarBahias() {
        try {
            System.out.println();
            logger.info("Inicio del test mostrarBahias");

            List<Bahia> bahias = daoBahia.findAllBahias();

            int contadorBahias = 0;
            for (Bahia bahia : bahias) {
                logger.info("Bahia: " + bahia);
                contadorBahias++;
            }

            assertEquals(contadorBahias, daoBahia.countBahias());

            logger.info("Fin del test mostrarBahias");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void insertarBahia() {
		try {
            System.out.println();
            logger.info("Inicio del test insertarBahia");
            
            // El script de datos tiene 1 registro
            assertEquals(1, daoBahia.countBahias());
            
            Bahia bahia = new Bahia();
            bahia.setNumero(2);
            bahia.setEstado("Disponible");
            bahia.setIdTipo(1);
            daoBahia.insertBahia(bahia);

            //Recuperamos la bahia recien insertada por su numero 
            bahia = daoBahia.findBahiaByNumero(bahia);
            logger.info("Bahia insertada (recuperada por numero): \n" + bahia);
            
            // Deberia existir 2 bahias
            assertEquals(2, daoBahia.countBahias());
            logger.info("Fin del test insertarBahia");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

}
