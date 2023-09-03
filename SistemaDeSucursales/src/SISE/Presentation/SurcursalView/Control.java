package SISE.Presentation.SurcursalView;//Paquete Presentation.SurcusalView
/*
 * Clase: SISE.Presentation.SurcursalView.Control
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import SISE.Logic.Service; //Importa librerias nesesarias
import SISE.Logic.Sucursal;

import static SISE.Presentation.MainView.Control.agregarDepartamento; //Importa metodos
import static SISE.Presentation.MainView.Control.EditarSucursal;

public class Control { //Inicio de la clase Control
    //Atributos privados
    private Model model; private View view; private  Service service;
//----------------------------------------------------------------------------------------------------------------------//
    //Constructores
    public Control(Model model, View view, Service service) throws Exception {
        this.model = model;
        this.view = view;
        this.service=service;
        view.setModel(model);
        view.setController(this);
        view.setTextFielcod(LastCodeSucursal());
        view.setEditable();
        view.setVisible(true);
    }
    public Control(Model model, View view, Service service,Sucursal s) {
        this.model = model;
        this.view = view;
        this.service=service;
        view.setModel(model);
        view.setController(this);
        show(s);
        view.setVisible(true);
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Metodos de llamado a L
    public static void AgregarSucursal(String codigo, String Referencia, String Direccion, String zonaje,int posx,int posy) throws Exception {
        Sucursal sucursal=new Sucursal(codigo,Referencia,Direccion,Double.valueOf(zonaje),posx,posy);
        agregarDepartamento(sucursal);
    }
    public static void Editarsucursal(String codigo, String Referencia, String Direccion, String zonaje,int posx,int posy) throws Exception {
        EditarSucursal(codigo,Referencia,Direccion,Double.valueOf(zonaje),posx,posy);
    }
    public  String LastCodeSucursal() throws Exception {
        String code;
        int lastcode= Integer.valueOf(service.SucursalGetnumero(service.AllSucursales().size()-1).getCodigo());
        lastcode++;
        code=String.valueOf(lastcode);
        return "00"+code;
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Metodo show
    public void show(Sucursal s) {
        model.setCodigo(s.getCodigo());
        model.setDireccion(s.getDireccion());
        model.setReferencia(s.getReferencia());
        model.setZonaje(s.getZonaje());
        model.setUbicacionX(s.getUbicacionX());
        model.setUbicacionY(s.getUbicacionY());
        model.commit();
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase Control