package com.ejercicio.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author walter.maradiaga
 */
public abstract class conexionBaseDatos {   
    public conexionBaseDatos() {
    }
    public static Connection getConnection() throws Exception
    {
        String dbDriver = "org.postgresql.Driver";       
        String dbConnString = "jdbc:postgresql://45.77.118.162:5432/postgres";       
        String dbUser = "postgres";       
        String dbPassword = "@2dminhn#$lnk";       
        Class.forName(dbDriver).newInstance();       
        return DriverManager.getConnection(dbConnString, dbUser, dbPassword);
    }
    
} 
