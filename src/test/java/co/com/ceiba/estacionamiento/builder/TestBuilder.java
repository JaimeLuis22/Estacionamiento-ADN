/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ceiba.estacionamiento.builder;

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author jaime.morales
 */
public class TestBuilder {

    /**
     * Constructor
     */
    public TestBuilder() {
        // Constructor vacio
    }

    /**
     * Metodo que construye un objeto bahia para pruebas
     *
     * @return
     */
    public static Bahia toBahia() {
        Bahia bahia = new Bahia();
        bahia.setNumero(2);
        bahia.setEstado("Disponible");
        bahia.setIdTipo(1);
        return bahia;
    }

    /**
     * Metodo que construye un objeto bahia existente en la base de datos
     *
     * @return
     */
    public static Bahia toBahiaExistenteNoDisponible() {
        Bahia bahia = new Bahia();
        bahia.setIdBahia((long) 1);
        bahia.setNumero(1);
        bahia.setEstado("No Disponible");
        bahia.setIdTipo(1);
        return bahia;
    }

    /**
     * Metodo que devuelve un objeto bahia modificado
     *
     * @param bahia
     * @return
     */
    public static Bahia toBahiaModificada(Bahia bahia) {
        bahia.setNumero(12);
        bahia.setEstado("Disponible");
        bahia.setIdTipo(1);
        return bahia;
    }

    /**
     * Metodo que construye un objeto vehiculo
     *
     * @return
     */
    public static Vehiculo toVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("QQQ123");
        vehiculo.setIdTipo(1);
        vehiculo.setIdBahia(1);
        return vehiculo;
    }

    /**
     * Metodo que construye un objeto vehiculo
     *
     * @return
     */
    public static Vehiculo toVehiculoMotoSinCilindraje() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("qqq123");
        vehiculo.setIdTipo(2);
        vehiculo.setIdBahia(1);
        return vehiculo;
    }
    
    /**
     * Metodo que construye un objeto vehiculo
     *
     * @return
     */
    public static Vehiculo toVehiculoPlacaA() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("AAA123");
        vehiculo.setIdTipo(1);
        vehiculo.setIdBahia(1);
        return vehiculo;
    }

    /**
     * Metodo que construye un objeto vehiculo existente
     *
     * @return
     */
    public static Vehiculo toVehiculoExistente() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo((long) 1);
        vehiculo.setPlaca("QWE213");
        vehiculo.setIdTipo(1);
        vehiculo.setIdBahia(1);
        return vehiculo;
    }
    
    /**
     * Metodo que construye un objeto tipo para pruebas
     *
     * @return
     */
    public static Tipo toTipo() {
        Tipo tipo = new Tipo();
        tipo.setNombre("Bicicleta");
        return tipo;
    }
    
    /**
     * Metodo que construye un objeto parqueo para pruebas
     *
     * @return
     */
    public static Parqueo toParqueo() {
        Parqueo parqueo = new Parqueo();
        parqueo.setFechaInicial("2019-04-01 19:40:37");
        parqueo.setEstado("Activo");
        parqueo.setIdVehiculo(1);
        return parqueo;
    }
    
    /**
     * Metodo que construye un objeto parqueo existente en base de datos
     *
     * @return
     */
    public static Parqueo toParqueoExistente() {
        Parqueo parqueo = new Parqueo();
        parqueo.setIdParqueo((long)1);
        parqueo.setFechaInicial("2019-04-01 19:40:37");
        parqueo.setEstado("Activo");
        parqueo.setIdVehiculo(1);
        return parqueo;
    }
    
     /**
     * Metodo que convierte en json un objeto
     *
     * @param object
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public static String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String objectString = mapper.writeValueAsString(object);
        return objectString;
    }

}
