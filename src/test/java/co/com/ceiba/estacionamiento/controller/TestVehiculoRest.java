package co.com.ceiba.estacionamiento.controller;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVehiculoRest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void vehiculos() throws Exception {
        mockMvc.perform(get("/vehiculos"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': [{'idVehiculo': 1,'placa': 'QWE213','cilidranje': null,'idTipo': 1,'idBahia': 1}]}"));
    }
    
    @Test
    public void vehiculoPorPlaca() throws Exception {
        mockMvc.perform(get("/vehiculos/QWE213"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': {'idVehiculo': 1,'placa': 'QWE213','cilidranje': null,'idTipo': 1,'idBahia': 1}}"));
    }
    
    @Test
    public void validacionPlacaA() throws Exception {        
        mockMvc.perform(post("/vehiculos").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toVehiculoPlacaA())))
        		.andExpect(content().json("{'payload': {'mensaje': 'Autorizacion negada','codigo': 401}}"));
    }
    
    @Test
    public void ingresar() throws Exception {        
        mockMvc.perform(post("/vehiculos").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toVehiculo())))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': {'mensaje': 'Registro exitoso','codigo': 200}}"));
    }
    
    @Test
    public void salida() throws Exception {        
        mockMvc.perform(put("/vehiculos/salida").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toVehiculoExistente())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void vehiculoConCilindrajeVacio() throws Exception {        
        mockMvc.perform(post("/vehiculos").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toVehiculoMotoSinCilindraje())))
        		.andExpect(content().json("{'payload': {'mensaje': 'Cilindraje vacio','codigo': 400}}"));
    }
    
    @Test
    public void errorInterno() throws Exception {        
        mockMvc.perform(post("/vehiculos/1").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toVehiculoMotoSinCilindraje())))
        		.andExpect(content().json("{'payload': {'mensaje': 'Error interno','codigo': 500}}"));
    }
}
