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
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.servicio.ServiceVehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceVehiculo {
	
	private final Logger logger = LogManager.getRootLogger();
	
	/**
	 * Inyeccion del bean
	 */
	@Autowired
    private ServiceVehiculo serviceVehiculo;
	
	/**
	 * Test que lista todos los vehiculos
	 */
	@Test
	@Transactional
    public void listarTodosLosVehiculos() {
        try {
            System.out.println();
            logger.info("Inicio del test listarTodosLosVehiculos");
            
            // Act
            List<Vehiculo> vehiculos = serviceVehiculo.listarTodosLosVehiculos();

            int contadorVehiculos = 0;
            for (Vehiculo vehiculo : vehiculos) {
                logger.info("Vehiculo: " + vehiculo);
                contadorVehiculos++;
            }
            
            // Assert
            assertEquals(contadorVehiculos, serviceVehiculo.contarVehiculos());

            logger.info("Fin del test listarTodosLosVehiculos");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	/**
	 * Test que inserta un vehiculo
	 */
	@Test
	@Transactional
    public void insertarVehiculo() {
        try {
            System.out.println();
            logger.info("Inicio del test insertarVehiculo");
            
            // Arrange
            // El script de datos tiene 1 registro
            assertEquals(1, serviceVehiculo.contarVehiculos());
            
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("ndj840");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo);
            
            //Recuperamos el vehiculo recien insertado por su placa 
            vehiculo = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
            logger.info("Vehiculo insertado (recuperado por placa): \n" + vehiculo);
            
            // Assert
            // Deberia existir 2 vehiculos
            assertEquals(2, serviceVehiculo.contarVehiculos());

            logger.info("Fin del test insertarVehiculo");
        } catch (Exception e) {
            logger.error("Error JBDC", e);
        }
    }
	
	/**
	 * Test que cuenta vehiculos, inserta, encuentra por su placa y actualiza
	 */
	@Test
	@Transactional
    public void operacionA_Vehiculo() {
        try {
            System.out.println();
            logger.info("Inicio del test operacionA_Vehiculo");
            
            // Arrange
            // El script de datos tiene 1 registro
            assertEquals(1, serviceVehiculo.contarVehiculos());
            
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("ndj840");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo);

            //Recuperamos el vehiculo recien insertado por su placa 
            vehiculo = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
            logger.info("Vehiculo insertado (recuperado por placa): \n" + vehiculo);
            
            // Assert
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
	
	/**
	 * Test que valida la regla de la placa que inicia con A (Domingos - Lunes)
	 * Tener en cuenta el estado de la bahia
	 */
	@Test
	@Transactional
    public void reglaPlacaInicioA() {
		System.out.println();
		logger.info("Inicio del test reglaPlacaInicioA");
		
		// Arrange
		String mensajeEsperado = "Autorizacion negada";
		
        try {            
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("AAA123");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo); 
            
        } catch (EstacionamientoException e) {
        	logger.info("Excepcion: " +e.getMensaje());
        	
        	// Assert
        	assertEquals(mensajeEsperado, e.getMensaje());
        }
        
        logger.info("Fin del test reglaPlacaInicioA");
    }
	
	/**
	 * Test que valida si el vehiculo es una moto y no cuente con el cilindraje
	 */
	@Test
	@Transactional
    public void validarVehiculoMoto() {
		System.out.println();
		logger.info("Inicio del test validarVehiculoMoto");
		
		// Arrange
		String mensajeEsperado = "Cilindraje vacio";
		
        try {            
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("qqq123");
            vehiculo.setIdTipo(2);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo); 
            
        } catch (EstacionamientoException e) {
        	logger.info("Excepcion: " +e.getMensaje());
        	
        	// Assert
        	assertEquals(mensajeEsperado, e.getMensaje());
        }
        
        logger.info("Fin del test validarVehiculoMoto");
    }
	
	/**
	 * Test que valida si la bahia esta disponible para ingresar el vehiculo
	 */
	@Test
	@Transactional
    public void validarBahiaDisponible() {
		System.out.println();
		logger.info("Inicio del test validarBahiaDisponible");
		
		// Arrange
		String mensajeEsperado = "No hay bahias disponibles";
		
        try {            
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("qqq123");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            serviceVehiculo.insertarVehiculo(vehiculo); 
            
        } catch (EstacionamientoException e) {
        	logger.info("Excepcion: " +e.getMensaje());
        	
        	// Assert
        	assertEquals(mensajeEsperado, e.getMensaje());
        }
        
        logger.info("Fin del test validarBahiaDisponible");
    }
	
	/**
	 * Test que busca un vehiculo por su placa
	 */
	@Test
	@Transactional
    public void obtenerVehiculoPorPlaca() {
		System.out.println();
		logger.info("Inicio del test obtenerVehiculoPorPlaca");
		
        try {    		
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca("QWE213");
            
            Vehiculo vehiculoR = serviceVehiculo.encontrarVehiculoPorPlaca(vehiculo);
            logger.info(vehiculoR);
            
            // Assert
            assertNotNull(vehiculoR);
        } catch (EstacionamientoException e) {

        }
        
        logger.info("Fin del test obtenerVehiculoPorPlaca");
    }
	
	/**
	 * Test que da salida a un vehiculo
	 */
	@Test
	@Transactional
    public void salidaVehiculo() {
		System.out.println();
		logger.info("Inicio del test salidaVehiculo");
		
        try {    		
            // Act
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setIdVehiculo((long)1);
            vehiculo.setPlaca("QWE213");
            vehiculo.setIdTipo(1);
            vehiculo.setIdBahia(1);
            logger.info(vehiculo);
            
            double costo = serviceVehiculo.salidaVehiculo(vehiculo);
            logger.info(costo);
            
            // Assert
            assertNotNull(costo);
        } catch (EstacionamientoException e) {
        	logger.error("Excepcion :" +e);
        }
        
        logger.info("Fin del test salidaVehiculo");
    }
	
	/**
	 * Metodo que ayuda a la impresion de los vehiculos
	 */
	private void desplegarVehiculos() {
		List<Vehiculo> vehiculos = serviceVehiculo.listarTodosLosVehiculos();
		
        for (Vehiculo vehiculo : vehiculos) {
            logger.info("Vehiculo: " + vehiculo);
        }
    }
}
