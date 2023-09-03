package SISE.Presentation.SurcursalView;//Paquete Presentation.SurcusalView
/*
 * Clase: SISE.Presentation.SurcursalView.Model
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import java.util.Observable;//Importa librerias nesesarias
import java.util.Observer;

public class Model extends Observable { //Inicio de la clase Model hereda de la clase Observable
    //Atributos privados
    private String codigo, referencia,direccion;
    private double zonaje,ubicacionX,ubicacionY;
//----------------------------------------------------------------------------------------------------------------------//
    //SetAndGets
    public String getCodigo() {
        return codigo;
    } public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    } public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

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
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }
    public void commit(){//commit notifica a la clase observada y realiza el update deseado
        this.setChanged();
        this.notifyObservers();
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase Model