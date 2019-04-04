/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ceiba.estacionamiento.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author jaime.morales
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParqueoRest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void parqueos() throws Exception {
        System.out.println("");
        mockMvc.perform(get("/parqueos"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': [{'idParqueo': 1,'fechaInicial': '2019-04-01 19:40:37','fechaFin': null,'costo': null,'estado': 'Activo','idVehiculo': 1}]}"));
    }
    
    @Test
    public void parqueoPorId() throws Exception {
        mockMvc.perform(get("/parqueo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': {'idParqueo': 1,'fechaInicial': '2019-04-01 19:40:37','fechaFin': null,'costo': null,'estado': 'Activo','idVehiculo': 1}}"));
    }
}
