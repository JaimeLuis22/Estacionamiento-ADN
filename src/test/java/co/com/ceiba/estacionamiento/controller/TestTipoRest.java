/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ceiba.estacionamiento.controller;

import co.com.ceiba.estacionamiento.builder.TestBuilder;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author jaime.morales
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTipoRest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void tipos() throws Exception {
        mockMvc.perform(get("/tipos"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': [{'idTipo': 1,'nombre': 'Carro'},{'idTipo': 2,'nombre': 'Moto'}]}"));
    }
    
    @Test
    public void ingresar() throws Exception {        
        mockMvc.perform(post("/tipos").contentType(MediaType.APPLICATION_JSON).content(TestBuilder.objectToJson(TestBuilder.toTipo())))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': {'mensaje': 'Registro exitoso','codigo': 200}}"));
    }
}
