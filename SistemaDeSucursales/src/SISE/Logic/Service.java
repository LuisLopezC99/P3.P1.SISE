package SISE.Logic;//Capa logic
/*
 * Clase: SISE.Logic.Service
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
//Acedemos a la capa data para lo nesesario y importamos otras clases
import SISE.Data.Data;
import SISE.Data.XmlPersistent;

import java.util.List;

public class Service {//Inicio de la clase Service
    // ----------------------------------------------------------------------------------------------------------------------//
    //Atributos Privados
    private static Service theInstance;
    private Data data;
    private XmlPersistent xml;

    //----------------------------------------------------------------------------------------------------------------------//
    public Service()//Constructor
    {
        /*try{
            data=XmlPersister.instance().load();//Mejorar para el uso de xml
        }
        catch(Exception e){*/
            data =  new Data();
            xml=new XmlPersistent();
    }

    //Nuevo servicio en thInstance
    public static Service instance()
    {
        if (theInstance==null){
            theInstance=new Service();
        }
        return theInstance;
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //Metodos de busqueda gets
    public Empleado EmpleadoGetname(String name) throws Exception{ //Devuelve un empleado exp
        Empleado result=data.getEmpleados().stream().filter(c->c.getNombre().equals(name)).findFirst().orElse(null);
        return result;
    }
    public Empleado EmpleadoGetcedula(String ced) throws Exception{ //Devuelve un empleado exp
        Empleado result=data.getEmpleados().stream().filter(c->c.getCedula().equals(ced)).findFirst().orElse(null);
        return result;
    }
    public Sucursal SucursalGet(String referecia) throws Exception{ //Devuelve una sucursal exp
        Sucursal result=data.getSucursales().stream().filter(c->c.getReferencia().equals(referecia)).findFirst().orElse(null);
        return result;
    }
    public Sucursal SucursalGetCO(String codigo) throws Exception{ //Devuelve una sucursal exp
        Sucursal result=data.getSucursales().stream().filter(c->c.getCodigo().equals(codigo)).findFirst().orElse(null);
        if(result!=null)return result;
        else throw new Exception("Sucursal no existe");
    }
    public Empleado EmpleadoGetnumero(int pos) throws Exception{ //Devuelve un empleado exp
        Empleado result=data.getEmpleados().get(pos);
        if (result!=null) return result;
        else throw new Exception("Posicion erronea");
    }
    public Sucursal SucursalGetnumero(int pos) throws Exception{ //Devuelve una sucursal exp
        Sucursal result=data.getSucursales().get(pos);
        if (result!=null) return result;
        else throw new Exception("Posicion erronea");
    }
    public String getReferencia(int posX,int posY){
        Sucursal sucursal=null;
        String referencia="";
        double x=0;double y=0;
        for (int i=0;i<data.getSucursales().size();i++){
            sucursal=data.getSucursales().get(i);
            x=sucursal.getUbicacionX()-posX;
            y=sucursal.getUbicacionY()-posY;
            if((x<5)&&(y<15))
                return sucursal.getReferencia();
        }
        return referencia;
    }
    public void setEmpleado(String cedula, String nombre, String telefono, String departamento, String salarioBase) throws Exception{
        Empleado E=EmpleadoGetcedula(cedula);
        E.setCedula(cedula);
        E.setNombre(nombre);
        E.setDepartamento(departamento);
        double zonaje = 0;
        zonaje =SucursalGet(departamento).getZonaje();
        E.setZonaje(zonaje);
        E.setSalarioBase(Double.valueOf(salarioBase));
        E.setSalarioTotal();
    }
    public void setSucursal(String codigo, String Referencia, String Direccion, double zonaje,int posx,int posy) throws Exception {
        Sucursal s=SucursalGetCO(codigo);
        setSucursalesEmpleados(s.getReferencia(),zonaje,Referencia);
        s.setCodigo(codigo);
        s.setReferencia(Referencia);
        s.setDireccion(Direccion);
        s.setZonaje(zonaje);
        s.setUbicacionX(posx);
        s.setUbicacionY(posy);
    }
    public void setSucursalesEmpleados(String Referencia,double zonaje,String Referencia2){
        Empleado empleado=null;
        for(int i=0;i<data.getEmpleados().size();i++){
            empleado=data.getEmpleados().get(i);
            if(empleado.getDepartamento().equals(Referencia)) {
                empleado.setDepartamento(Referencia2);
                empleado.setZonaje(zonaje);
                empleado.setSalarioTotal();
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //Devuelve las listas
    public List<Empleado> AllEmpleados() { return data.getEmpleados(); }
    public List<Sucursal> AllSucursales() { return data.getSucursales(); }
    //----------------------------------------------------------------------------------------------------------------------//
    //Metodos de añadir
    public void EmpleadoAdd(Empleado empleado) throws Exception{ //Añade un empleado a la lista empleados
        Empleado old=data.getEmpleados().stream().filter(c->c.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (old==null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }
    public void SucursalAdd(Sucursal sucursal) throws Exception{ //Añade una sucursal a la lista sucursales
        data.getSucursales().add(sucursal);
    }
    //----------------------------------------------------------------------------------------------------------------------//
    public void GurdarXml(){
        xml.guardarSucrusal(data);
        xml.guardarEmpleados(data);
    }
    public void cargarxml(){
        List<Sucursal> S=xml.cargarSucrusal();
        data.setSucursales(S);
        data.setEmpleados(xml.cargarEmpleados());
    }
    //----------------------------------------------------------------------------------------------------------------------//
    public void eliminarEmpleado(String cedula) {
        Boolean X = false;
        for (int i = 0; i < data.getEmpleados().size(); i++) {
            if(data.getEmpleados().get(i).getCedula().equals(cedula))
                data.getEmpleados().remove(data.getEmpleados().get(i));
        }
    }
        public void eliminarSucursal(String codigo) {
            Boolean X = false;
            for (int i = 0; i < data.getSucursales().size(); i++) {
                if(data.getSucursales().get(i).getCodigo().equals(codigo))
                    data.getSucursales().remove(data.getSucursales().get(i));
            }
    }
    //----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase Service
