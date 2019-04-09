package co.com.ceiba.estacionamiento.test.servicio;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.servicio.ServiceParqueo;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceParqueo {

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
        System.out.println();

        // Act
        List<Parqueo> parqueos = serviceParqueo.listar();

        int contadorParqueos = 0;
        for (int i = 0; i < parqueos.size(); i++) {
            contadorParqueos++;
        }

        // Assert
        assertEquals(contadorParqueos, serviceParqueo.contar());
    }
    
    /**
     * Test que encuentra el parqueo por el id vehiculo
     */
    @Test
    public void encontrarParqueoPorIdVehiculo() {
        System.out.println();

        // Act
        int idVehiculo = 1;
        Parqueo parqueo = serviceParqueo.encontrarParqueoPorIdVehiculo(idVehiculo);

        // Assert
        assertNotNull(parqueo);
    }
    
    /**
     * Test que inserta un parqueo
     */
    @Test
    @Transactional
    public void insertar() {
        System.out.println();
        // Arrange
        assertEquals(1, serviceParqueo.contar());   
        
        // Act
        serviceParqueo.insertar(TestBuilder.toParqueo());

        // Assert
        assertEquals(2, serviceParqueo.contar());
    }
    
    /**
     * Test que encuentra un parqueo por su id
     */
    @Test
    public void encontrarParqueoPorId() {
        System.out.println();   
        
        // Act
        Parqueo parqueo = serviceParqueo.encontrarPorId(TestBuilder.toParqueoExistente().getIdParqueo());

        // Assert
        assertNotNull(parqueo);
    }
    
    /**
	 * Test que elimina una parqueo
	 */
	@Test
	public void eliminar() {
		// Arrange
		String mensaje = "eliminado";

		// Act
		String respuesta = serviceParqueo.eliminar(TestBuilder.toParqueo());

		// Assert
		assertEquals(mensaje, respuesta);
	}
}
