package SISE.Presentation.EmpleadoView; //Paquete Presentation.EmpleadoView
/*
 * Clase: SISE.Presentation.EmpleadoView.Model
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import java.util.Observable; //Importa librerias nesesarias
import java.util.Observer;

public class Model extends Observable { //Inicio de la clase modelo hereda observable para el uso del patron observador
    //Atributos privados
    private String cedula, nombre,  telefono,  departamento; double   salarioBase;
//----------------------------------------------------------------------------------------------------------------------//
    //Metodos SetAndGet
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
    public double getSalarioBase() {
        return salarioBase;
    } public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
//----------------------------------------------------------------------------------------------------------------------//
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        this.setChanged();
        this.notifyObservers();//Notifica a las clases observadas para el update
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase model