/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.dao;

import com.ejercicio.interfaces.conexionBaseDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author walter.maradiaga
 */
public class DataServicio {

    public List<String> paises() throws Exception {
        List<String> options = new ArrayList<String>();
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Listado generico para consultar los paises
        try {
            if (conn != null) {
                //Consulta paises
                String sql = "SELECT id, nombre FROM public.tbl_pais;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    options.add(result.getString("nombre"));                    
                }
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return options;
    }

}
