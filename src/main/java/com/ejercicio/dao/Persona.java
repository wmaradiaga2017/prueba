package com.ejercicio.dao;

/**
 *
 * @author walter.maradiaga
 */
public class Persona {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    private String nombre;// Nombre de cada objeto persona
    private String apellidos;// Apellidos de cada objeto persona
    private String telefono;// Telefono de cada objeto persona
    private String sitio;// sitio de trabajo de cada objeto persona
    private String pais;// pais de residencia de cada objeto persona
    private String ciudad;// ciudad de residencia de cada objeto persona
    private String fechanac;// fechanac de cada objeto persona
    private int edad;// Edad de cada objeto persona
    private int id;//Idnetificador

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Constructor
    public Persona(String nombre, String apellidos, String telefono, String sitio, String pais, String ciudad, String fechanac, int edad, int id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sitio = sitio;
        this.pais = pais;
        this.ciudad = ciudad;
        this.fechanac = fechanac;
        this.edad = edad;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", apellido=" + apellidos
                + ", telefono=" + telefono + ", sitio de trabajo=" + sitio + "]";
    }
}
