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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao.DaoVehiculo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDaoVehiculo {

	private final Logger logger = LogManager.getRootLogger();
	
	@Autowired
    private DaoVehiculo daoVehiculo;

	@Test
    public void mostrarVehiculos() {
        try {
            System.out.println();
            logger.info("Inicio del test mostrarVehiculos");

            List<Vehiculo> vehiculos = daoVehiculo.findAllVehiculos();

            int contadorVehiculos = 0;
            for (Vehiculo vehiculo : vehiculos) {
                logger.info("Vehiculo: " + vehiculo);
                contadorVehiculos++;
            }

            assertEquals(contadorVehiculos, daoVehiculo.countVehiculos());

            logger.info("Fin del test mostrarVehiculos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
    public void insertarVehiculo() {
		try {
            System.out.println();
            logger.info("Inicio del test insertarVehiculo");
            
            // El script de datos tiene 1 registro
            assertEquals(1, daoVehiculo.countVehiculos());
            
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("uyt122");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            daoVehiculo.insertVehiculo(vehiculo);

            //Recuperamos el vehiculo recien insertado por su placa 
            vehiculo = daoVehiculo.findVehiculoByPlaca(vehiculo);
            logger.info("Vehiculo insertado (recuperado por placa): \n" + vehiculo);
            
            // Deberia existir 2 vehiculos
            assertEquals(2, daoVehiculo.countVehiculos());
            logger.info("Fin del test insertarVehiculo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }

}
