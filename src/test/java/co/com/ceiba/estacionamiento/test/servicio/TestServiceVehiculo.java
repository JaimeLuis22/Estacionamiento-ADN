package co.com.ceiba.estacionamiento.test.servicio;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import co.com.ceiba.estacionamiento.dao.DaoBahia;
import static org.junit.Assert.*;

import java.util.List;

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

    /**
     * Inyeccion del bean
     */
    @Autowired
    private ServiceVehiculo serviceVehiculo;
    
    /**
     * Inyeccion del bean
     */
    @Autowired
    private DaoBahia daoBahia;

    /**
     * Test que lista todos los vehiculos
     */
    @Test
    @Transactional
    public void listar() {
        System.out.println();

        // Act
        List<Vehiculo> vehiculos = serviceVehiculo.listar();

        int contadorVehiculos = 0;
        for (int i = 0; i < vehiculos.size(); i++) {
            contadorVehiculos++;
        }

        // Assert
        assertEquals(contadorVehiculos, serviceVehiculo.contar());
    }

    /**
     * Test que inserta un vehiculo
     */
    @Test
    @Transactional
    public void insertar() {
        // Arrange
        // El script de datos tiene 1 registro
        assertEquals(1, serviceVehiculo.contar());

        // Act
        Vehiculo vehiculo = TestBuilder.toVehiculo();
        serviceVehiculo.insertar(vehiculo);

        // Recuperamos el vehiculo recien insertado por su placa
        serviceVehiculo.encontrarPorPlaca(vehiculo);

        // Assert
        // Deberia existir 2 vehiculos
        assertEquals(2, serviceVehiculo.contar());
    }

    /**
     * Test que cuenta vehiculos, inserta, encuentra por su placa y actualiza
     */
    @Test
    @Transactional
    public void operacionA_Vehiculo() {
        // Arrange
        // El script de datos tiene 1 registro
        assertEquals(1, serviceVehiculo.contar());

        // Act
        Vehiculo vehiculo = TestBuilder.toVehiculo();
        serviceVehiculo.insertar(vehiculo);

        // Recuperamos el vehiculo recien insertado por su placa
        vehiculo = serviceVehiculo.encontrarPorPlaca(vehiculo);

        // Assert
        // Deberia existir 2 vehiculos
        assertEquals(2, serviceVehiculo.contar());
        vehiculo.setPlaca("mmm333");
        serviceVehiculo.actualizar(vehiculo);
        assertEquals(2, serviceVehiculo.contar());
    }

    /**
     * Test que valida la regla de la placa que inicia con A (Domingos - Lunes)
     * Tener en cuenta el estado de la bahia
     */
    @Test
    @Transactional
    public void reglaPlacaInicioA() {
        // Arrange
        String mensajeEsperado = "Autorizacion negada";

        try {
            // Act
            serviceVehiculo.insertar(TestBuilder.toVehiculoPlacaA());

        } catch (EstacionamientoException e) {
            // Assert
            assertEquals(mensajeEsperado, e.getMensaje());
        }
    }

    /**
     * Test que valida si el vehiculo es una moto y no cuente con el cilindraje
     */
    @Test
    @Transactional
    public void validarVehiculoMoto() {
        // Arrange
        String mensajeEsperado = "Cilindraje vacio";

        try {
            // Act
            serviceVehiculo.insertar(TestBuilder.toVehiculoMotoSinCilindraje());

        } catch (EstacionamientoException e) {
            // Assert
            assertEquals(mensajeEsperado, e.getMensaje());
        }
    }

    /**
     * Test que valida si la bahia esta disponible para ingresar el vehiculo
     */
    @Test
    @Transactional
    public void validarBahiaDisponible() {
        // Arrange
        daoBahia.updateBahia(TestBuilder.toBahiaExistenteNoDisponible());
        String mensajeEsperado = "No hay bahias disponibles";

        try {
            // Act
            serviceVehiculo.insertar(TestBuilder.toVehiculo());

        } catch (EstacionamientoException e) {
            // Assert
            assertEquals(mensajeEsperado, e.getMensaje());
        }
    }

    /**
     * Test que busca un vehiculo por su placa
     */
    @Test
    @Transactional
    public void obtenerVehiculoPorPlaca() {
        // Act
        Vehiculo vehiculoR = serviceVehiculo.encontrarPorPlaca(TestBuilder.toVehiculoExistente());

        // Assert
        assertNotNull(vehiculoR);
    }

    /**
     * Test que da salida a un vehiculo
     */
    @Test
    @Transactional
    public void salidaVehiculo() {
        // Act
        double costo = serviceVehiculo.salida(TestBuilder.toVehiculoExistente());

        // Assert
        assertNotNull(costo);
    }

    /**
     * Test que da salida a un vehiculo
     */
    @Test
    @Transactional
    public void vehiculoExiste() {
        String mensajeEsperado = "Existe el registro";
        try {
            // Act
            serviceVehiculo.insertar(TestBuilder.toVehiculoExistente());
        } catch (EstacionamientoException e) {
            // Assert
            assertEquals(mensajeEsperado, e.getMensaje());
        }
        
    }
}
