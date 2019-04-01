package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.test.servicio;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.servicio.ServiceVehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceVehiculo {
	
	private final Logger logger = LogManager.getRootLogger();
	
	@Autowired
    private ServiceVehiculo serviceVehiculo;
	
	@Test
	@Ignore
    public void listarTodosLosVehiculos() {
        try {
            System.out.println();
            logger.info("Inicio del test listarTodosLosVehiculos");

            List<Vehiculo> vehiculos = serviceVehiculo.listarTodosLosVehiculos();

            int contadorVehiculos = 0;
            for (Vehiculo vehiculo : vehiculos) {
                logger.info("Vehiculo: " + vehiculo);
                contadorVehiculos++;
            }

            assertEquals(contadorVehiculos, serviceVehiculo.contarVehiculos());

            logger.info("Fin del test listarTodosLosVehiculos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
	@Ignore
    public void insertarVehiculo() {
        try {
            System.out.println();
            logger.info("Inicio del test insertarVehiculo");

            // El script de datos tiene 1 registro
            assertEquals(1, serviceVehiculo.contarVehiculos());
            
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("ndj840");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo);

            //Recuperamos el vehiculo recien insertado por su placa 
            vehiculo = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
            logger.info("Vehiculo insertado (recuperado por placa): \n" + vehiculo);
            
            // Deberia existir 2 vehiculos
            assertEquals(2, serviceVehiculo.contarVehiculos());

            logger.info("Fin del test insertarVehiculo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	@Test
	@Ignore
    public void operacionA_Vehiculo() {
        try {
            System.out.println();
            logger.info("Inicio del test operacionA_Vehiculo");

            // El script de datos tiene 1 registro
            assertEquals(1, serviceVehiculo.contarVehiculos());
            
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("ndj840");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo);

            //Recuperamos el vehiculo recien insertado por su placa 
            vehiculo = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
            logger.info("Vehiculo insertado (recuperado por placa): \n" + vehiculo);
            
            // Deberia existir 2 vehiculos
            assertEquals(2, serviceVehiculo.contarVehiculos());
            
            // Modificamos el vehiculo ingresado
            vehiculo.setPlaca("mmm333");
            serviceVehiculo.actualizarVehiculo(vehiculo);
            
            desplegarVehiculos();            

            logger.info("Fin del test operacionA_Vehiculo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	private void desplegarVehiculos() {
		List<Vehiculo> vehiculos = serviceVehiculo.listarTodosLosVehiculos();
		
        for (Vehiculo vehiculo : vehiculos) {
            logger.info("Vehiculo: " + vehiculo);
        }
    }
	
	@Test
    public void reglaPlacaInicioA() {
		System.out.println();
		logger.info("Inicio del test reglaPlacaInicioA");
		
		String mensajeEsperado = "Autorizacion negada";
		
        try {            
            
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("AAA123");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo); 
            
        } catch (Exception e) {
        	logger.error("Excepcion: " +e.getMessage());
        	assertEquals(mensajeEsperado, e.getMessage());
        }
        
        logger.info("Fin del test reglaPlacaInicioA");
    }
}
