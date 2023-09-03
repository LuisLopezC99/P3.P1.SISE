package SISE.Logic;//Capa logic
/*
 * Clase: SISE.Logic.Sucursal
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
public class Sucursal {//Inicio de la clase Sucursal
    //----------------------------------------------------------------------------------------------------------------------//
    //Atributos privados
    private String codigo,referencia,direccion;
    private double zonaje, ubicacionX, ubicacionY;
    //----------------------------------------------------------------------------------------------------------------------//
    //Constructor
    public Sucursal(String codigo, String referencia, String direccion, double zonaje, double ubicacionX, double ubicacionY)
    {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.zonaje = zonaje;
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //SetsAndGets
    public String getCodigo() {
        return codigo;
    } public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getReferencia() { return referencia; } public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getDireccion() {
        return direccion;
    } public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public double getZonaje() {
        return zonaje;
    } public void setZonaje(double zonaje) {
        this.zonaje = zonaje;
    }
    public double getUbicacionX() {
        return ubicacionX;
    } public void setUbicacionX(double ubicacionX) {
        this.ubicacionX = ubicacionX;
    }
    public double getUbicacionY() {
        return ubicacionY;
    } public void setUbicacionY(double ubicacionY) {
        this.ubicacionY = ubicacionY;
    }
    //----------------------------------------------------------------------------------------------------------------------//
}//Fin de la Clase Sucursal
