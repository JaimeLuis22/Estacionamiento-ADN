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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoTipo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDaoTipo {
	
	private final Logger logger = LogManager.getRootLogger();
	
	@Autowired
    private DaoTipo daoTipo;

	@Test
    public void mostrarTipos() {
        try {
            System.out.println();
            logger.info("Inicio del test mostrarTipos");

            List<Tipo> tipos = daoTipo.findAllTipos();

            int contadorTipos = 0;
            for (Tipo tipo : tipos) {
                logger.info("Tipo: " + tipo);
                contadorTipos++;
            }

            assertEquals(contadorTipos, daoTipo.countTipos());

            logger.info("Fin del test mostrarTipos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void insertarTipo() {
		try {
            System.out.println();
            logger.info("Inicio del test insertarTipo");
            
            // El script de datos tiene 1 registro
            assertEquals(1, daoTipo.countTipos());
            
            Tipo tipo = new Tipo();
            tipo.setNombre("Moto");
            daoTipo.insertTipo(tipo);

            //Recuperamos el tipo recien insertado por su nombre 
            tipo = daoTipo.findTipoByNombre(tipo);
            logger.info("Tipo insertado (recuperado por nombre): \n" + tipo);
            
            // Deberia existir 2 tipos
            assertEquals(2, daoTipo.countTipos());
            logger.info("Fin del test insertarTipo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

}
