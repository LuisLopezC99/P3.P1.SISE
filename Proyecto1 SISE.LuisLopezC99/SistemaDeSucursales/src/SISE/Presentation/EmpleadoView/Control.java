package SISE.Presentation.EmpleadoView;//Paquete Presentation.EmpleadoView
/*
 * Clase: SISE.Presentation.EmpleadoView.Control
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import SISE.Logic.Empleado; //Importa librerias nesesarias
import SISE.Logic.Service;

import static SISE.Presentation.MainView.Control.agregarEmpleado; //Tenemos que llamar a este metodo que pertenese a mainview
import static SISE.Presentation.MainView.Control.EditarEmpleado;
public class Control { //Inicio de la clase control EmpleadoView

    //Atributos
    // Igual los static permiten llamarlos cuando hay metodos dinamicos o modificables dentro de ellos mismos
    private Model model; private static View view; private static Service service;

//----------------------------------------------------------------------------------------------------------------------//
    //Construye el control de la vista empleado almacenando, tratando y modificando su vista y modelo
    public Control(Model model, View view, Service service) throws Exception {
        this.model = model;
        this.view = view;
        this.service=service;
        view.setModel(model);
        view.setController(this);
        SetComboboX();
        view.setCantidadSucursales(service.AllSucursales().size());
        view.setEditable();
        view.setTextCedulaEditable(true);
        view.setVisible(true);
    }
    public Control(Model model, View view, Service service,String cedu) throws Exception {
        this.model = model;
        this.view = view;
        this.service=service;
        view.setModel(model);
        view.setController(this);
        SetComboboX();
        view.setCantidadSucursales(service.AllSucursales().size());
        show(cedu);
        view.setVisible(true);
    }
//----------------------------------------------------------------------------------------------------------------------//
    public void show(String cedu) throws Exception {
        Empleado e=service.EmpleadoGetcedula(cedu);
        model.setCedula(cedu);
        model.setNombre(e.getNombre());
        model.setTelefono(e.getTelefono());
        model.setDepartamento(e.getDepartamento());
        model.setSalarioBase(e.getSalarioBase());
        model.commit();
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Importante crea un empleado y llama al metodo agregar de control mainview para lograr esto variables y metodos deven ser static
    public static void agregarEmple(String cedula, String nombre, String telefono, String departamento, String salarioBase) throws Exception {
        Empleado empleado=new Empleado(cedula,nombre,telefono,departamento,Double.valueOf(salarioBase));
        agregarEmpleado(empleado);
    }
    public static void editarEpmple(String cedula, String nombre, String telefono, String departamento, String salarioBase)throws Exception {
        EditarEmpleado(cedula,nombre,telefono,departamento,salarioBase);
    }
//----------------------------------------------------------------------------------------------------------------------//
    public int getPosx(int i) throws Exception {
        int X =  (int)service.SucursalGetnumero(i).getUbicacionX();
        return X;
    }
    public int getPosY(int i) throws Exception {
        int y=(int)service.SucursalGetnumero(i).getUbicacionY();
        return y;
    }
    public String getDireccion(int i) throws Exception {
        return service.SucursalGetnumero(i).getDireccion();
    }
    public String getReferecia(int posx,int posy){
        return service.getReferencia(posx,posy);
    }
    public static void  SetComboboX() throws Exception { //Agrega las referencias de las sucursales existentes al combobox
        for(int i=0;i<service.AllSucursales().size();i++){
            view.annadirComboBox(service.SucursalGetnumero(i).getReferencia());
        }
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase control