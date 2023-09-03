package SISE.Logic;//Capa logica
/*
 * Clase: SISE.Logic.Empleado
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
public class Empleado { //Inicio de clase Empleado
    //----------------------------------------------------------------------------------------------------------------------//
    //Atributos privados
    private String cedula, nombre, telefono, departamento;
    private double zonaje, salarioBase, salarioTotal;
    //----------------------------------------------------------------------------------------------------------------------//
    //Costructores de la clase
    public Empleado(String cedula, String nombre, String telefono, String departamento,double zonaje, double salarioBase, double salarioTotal)
    {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.departamento = departamento;
        this.zonaje=zonaje;
        this.salarioBase = salarioBase;
        this.salarioTotal = salarioTotal;
    }
    public Empleado(String cedula, String nombre, String telefono, String departamento, double salarioBase)
    {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.departamento = departamento;
        zonaje=0;
        this.salarioBase = salarioBase;
        salarioTotal = 0;
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //SetsAndGets
    public String getCedula() {
        return cedula;
    } public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    } public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    } public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDepartamento() {
        return departamento;
    } public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public double getZonaje(){ return zonaje; } public void setZonaje(double zonaje){ this.zonaje=zonaje;}
    public double getSalarioBase() {
        return salarioBase;
    } public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    public double getSalarioTotal() {
        return salarioTotal;
    }
    public void setSalarioTotal() {
        double BONOZona=salarioBase*(zonaje/100);
        salarioTotal = salarioBase+BONOZona;
    }
    //----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase Empleado
