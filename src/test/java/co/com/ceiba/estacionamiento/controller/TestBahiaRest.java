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
public class TestBahiaRest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void bahias() throws Exception {
        mockMvc.perform(get("/bahias"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': [{'idBahia': 1,'numero': 1,'estado': 'Disponible','idTipo': 1}]}"));
    }
    
    @Test
    public void bahiaPorNumero() throws Exception {
        mockMvc.perform(get("/bahia/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'payload': {'idBahia': 1,'numero': 1,'estado': 'Disponible','idTipo': 1}}"));
    }
}
