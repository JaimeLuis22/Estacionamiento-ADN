package co.com.ceiba.estacionamiento.test.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.servicio.ServiceTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceTipo {
	
	 /**
     * Inyeccion del bean
     */
    @Autowired
    private ServiceTipo serviceTipo;

    /**
     * Test que lista todos los parqueos
     */
    @Test
    public void listarTodosLosTipos() {
        System.out.println();

        // Act
        List<Tipo> tipos = serviceTipo.listar();

        int contadorTipos = 0;
        for (int i = 0; i < tipos.size(); i++) {
        	contadorTipos++;
        }

        // Assert
        assertEquals(2, contadorTipos);
    }
    
    /**
     * Test que inserta un parqueo
     */
    @Test
    @Transactional
    public void insertar() {
        System.out.println();        
        // Act
        serviceTipo.insertar(TestBuilder.toTipo());
        
        Tipo tipo = serviceTipo.recuperarPorNombre(TestBuilder.toTipo());

        // Assert
        assertNotNull(tipo);
    }
    
    /**
	 * Test que elimina un tipo
	 */
	@Test
	public void eliminar() {
		// Arrange
		String mensaje = "eliminado";

		// Act
		String respuesta = serviceTipo.eliminar(TestBuilder.toTipo());

		// Assert
		assertEquals(mensaje, respuesta);
	}

}
