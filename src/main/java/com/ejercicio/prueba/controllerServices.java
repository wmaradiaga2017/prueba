/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.dao.Persona;
import com.ejercicio.interfaces.conexionBaseDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author walter.maradiaga
 */
@RestController
@RequestMapping("/api")
public class controllerServices {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String listadoPersonal() throws Exception {
        JSONObject json = new JSONObject();
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Listado que manejara el personal 
        List<Persona> personal = new ArrayList<Persona>();
        try {
            if (conn != null) {
                //Consulta del personal
                String sql = "SELECT id, nombre, apellidos, telefono, sitio, pais, ciudad, to_char(fechanac, 'DD/MM/YYYY') as fechanac, edad FROM public.tbl_personal;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    Persona pr = new Persona(
                            result.getString("nombre"),
                            result.getString("apellidos"),
                            result.getString("telefono"),
                            result.getString("sitio"),
                            result.getString("pais"),
                            result.getString("ciudad"),
                            result.getString("fechanac"),
                            result.getInt("edad"),
                            result.getInt("id"));
                    personal.add(pr);
                }
                statement.close();
            }
            json.put("response", 200);
            //retorna listado del personal
            json.put("personal", personal);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/paises", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String listadoPais() throws Exception {
        JSONObject json = new JSONObject();
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Listado generico para consultar los paises
        JSONArray jsonArray = new JSONArray();
        try {
            if (conn != null) {
                //Consulta paises
                String sql = "SELECT id, nombre FROM public.tbl_pais;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    int total_rows = result.getMetaData().getColumnCount();
                    JSONObject obj = new JSONObject();
                    for (int i = 0; i < total_rows; i++) {
                        obj.put(result.getMetaData().getColumnLabel(i + 1)
                                .toLowerCase(), result.getObject(i + 1));

                    }
                    jsonArray.put(obj);
                }
                statement.close();
            }
            json.put("response", 200);
            //retorna listado del personal
            json.put("paises", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/guardarempleado", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String guardarempleado(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("telefono") String telefono, @RequestParam("sitiotrabajo") String sitiotrabajo, @RequestParam("pais") String pais,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("fecha") String fecha) throws Exception {
        JSONObject json = new JSONObject();
        nombre = new String(nombre.getBytes("ISO-8859-1"), "UTF-8");
        apellidos = new String(apellidos.getBytes("ISO-8859-1"), "UTF-8");
        telefono = new String(telefono.getBytes("ISO-8859-1"), "UTF-8");
        sitiotrabajo = new String(sitiotrabajo.getBytes("ISO-8859-1"), "UTF-8");
        pais = new String(pais.getBytes("ISO-8859-1"), "UTF-8");
        ciudad = new String(ciudad.getBytes("ISO-8859-1"), "UTF-8");
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;

        //Inserta la persona nueva
        JSONArray jsonArray = new JSONArray();
        try {
            if (conn != null) {
                //Consulta paises
                String sql = "select fn_insertpersona('" + nombre + "','" + apellidos + "','" + telefono + "','" + sitiotrabajo + "','" + pais + "','" + ciudad + "','" + fecha + "') as id;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    json.put("idempleado", result.getString("id"));
                }
                statement.close();
            }
            json.put("response", 200);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
            json.put("idempleado", 0);
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/consultaempleado", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String consultaempleado(@RequestParam("id") String id) throws Exception {
        JSONObject json = new JSONObject();
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Listado que manejara el personal 
        List<Persona> personal = new ArrayList<Persona>();
        try {
            if (conn != null) {
                //Consulta del personal
                String sql = "SELECT id, nombre, apellidos, telefono, sitio, pais, ciudad, to_char(fechanac, 'DD/MM/YYYY') as fechanac, edad FROM public.tbl_personal where id = '" + id + "';";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    Persona pr = new Persona(
                            result.getString("nombre"),
                            result.getString("apellidos"),
                            result.getString("telefono"),
                            result.getString("sitio"),
                            result.getString("pais"),
                            result.getString("ciudad"),
                            result.getString("fechanac"),
                            result.getInt("edad"),
                            result.getInt("id"));
                    personal.add(pr);
                }
                statement.close();
            }
            json.put("response", 200);
            //retorna persona consultada
            json.put("personal", personal);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/actualizaempleado", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String actualizaempleado(@RequestParam("id") String id, @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("telefono") String telefono, @RequestParam("sitiotrabajo") String sitiotrabajo, @RequestParam("pais") String pais,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("fecha") String fecha) throws Exception {
        JSONObject json = new JSONObject();
        nombre = new String(nombre.getBytes("ISO-8859-1"), "UTF-8");
        apellidos = new String(apellidos.getBytes("ISO-8859-1"), "UTF-8");
        telefono = new String(telefono.getBytes("ISO-8859-1"), "UTF-8");
        sitiotrabajo = new String(sitiotrabajo.getBytes("ISO-8859-1"), "UTF-8");
        pais = new String(pais.getBytes("ISO-8859-1"), "UTF-8");
        ciudad = new String(ciudad.getBytes("ISO-8859-1"), "UTF-8");
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Actualizar empleado
        try {
            if (conn != null) {
                //Consulta paises
                String sql = "select fn_updatepersona('" + id + "','" + nombre + "','" + apellidos + "','" + telefono + "','" + sitiotrabajo + "','" + pais + "','" + ciudad + "','" + fecha + "') as id;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    json.put("estado", result.getString("id"));
                }
                statement.close();
            }
            json.put("response", 200);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
            json.put("estado", "");
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/eliminarempleado", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String eliminarempleado(@RequestParam("id") String id) throws Exception {
        JSONObject json = new JSONObject();
        //instancia nueva de la conexion de la base de datos     
        Connection conn = conexionBaseDatos.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = null;
        //Eliminar persona
        try {
            if (conn != null) {
                //Consulta paises
                String sql = "select fn_deletepersona('" + id + "') as id;";
                statement = conn.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    json.put("estado", result.getString("id"));
                }
                statement.close();
            }
            json.put("response", 200);
        } catch (Exception e) {
            e.printStackTrace();
            //en el caso que ocurra un problema el json campo response retorna valor 100
            json.put("response", 100);
            json.put("estado", "");
        } finally {
            //cerrar conexion de base de datos
            conn.close();
        }
        return json.toString();
    }
}
