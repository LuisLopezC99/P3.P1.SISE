package SISE.Data; //Capa Data
/*
 * Clase: SISE.Data.Data
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
//Importamos desde la capa Logic lo nesesario y la clases para una lista
import SISE.Logic.Empleado;
import SISE.Logic.Sucursal;
import java.util.ArrayList;
import java.util.List;


public class Data {//Inicio de la clase Data
    //----------------------------------------------------------------------------------------------------------------------//
    //Atributos privados
    private List<Empleado> Empleados;
    private List<Sucursal> Sucursales;
    //----------------------------------------------------------------------------------------------------------------------//
    public Data() //Constructor
    {
        Empleados=new ArrayList<>();
        Sucursales=new ArrayList<>();
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //SetsAndGets
    public List<Empleado> getEmpleados() { return Empleados; } public void setEmpleados(List<Empleado> Empleados) { this.Empleados = Empleados; }
    public List<Sucursal> getSucursales() { return Sucursales; } public void setSucursales(List<Sucursal> Sucursales) { this.Sucursales = Sucursales; }
    //----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase Data
