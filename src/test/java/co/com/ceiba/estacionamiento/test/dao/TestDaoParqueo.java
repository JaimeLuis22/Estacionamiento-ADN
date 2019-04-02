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

import co.com.ceiba.estacionamiento.dao.DaoParqueo;
import co.com.ceiba.estacionamiento.dominio.Parqueo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDaoParqueo {

	private final Logger logger = LogManager.getRootLogger();
	
	@Autowired
    private DaoParqueo daoParqueo;
	
	@Test
    public void mostrarParqueos() {
        try {
            System.out.println();
            logger.info("Inicio del test mostrarParqueos");

            List<Parqueo> parqueos = daoParqueo.findAllParqueos();

            int contadorParqueos = 0;
            for (Parqueo parqueo : parqueos) {
                logger.info("Parqueo: " + parqueo);
                contadorParqueos++;
            }

            assertEquals(contadorParqueos, daoParqueo.countParqueos());

            logger.info("Fin del test mostrarParqueos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
	@Transactional
    public void insertarParqueo() {
		try {
            System.out.println();
            logger.info("Inicio del test insertarParqueo");
            // Assert
            // El script de datos tiene 1 registro
            assertEquals(1, daoParqueo.countParqueos());
            
            // Act
            Parqueo parqueo = new Parqueo();
            parqueo.setFechaInicial("12-12-12");
            parqueo.setEstado("Disponible");
            parqueo.setIdVehiculo(1);
            daoParqueo.insertParqueo(parqueo);
            
            //Recuperamos el parqueo recien insertado por su id 
            parqueo = daoParqueo.findParqueoById(2);
            logger.info("Parqueo insertado (recuperado por id): \n" + parqueo);
            
            // Assert
            // Deberian existir 2 parqueos
            assertEquals(2, daoParqueo.countParqueos());
            logger.info("Fin del test insertarParqueo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void parqueoPorIdVehiculo() {
		try {
            System.out.println();
            logger.info("Inicio del test parqueoPorIdVehiculo");
            
            // Act
            Parqueo parqueo = daoParqueo.findParqueoByIdVehiculo(1);
            logger.info("Parqueo encontrado: \n" + parqueo);
            
            // Assert
            assertEquals(1, parqueo.getIdParqueo());
            logger.info("Fin del test parqueoPorIdVehiculo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
}
