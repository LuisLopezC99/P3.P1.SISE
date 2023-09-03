package SISE.Presentation.MainView;//Paquete Presentation.SurcusalView
/*
* Clase: SISE.Presentation.MainView.Control
* Fecha:8/09/2022
* Autor: Luis Ignacio López Castro Id: 402420889
* Organización:Universidad Nacional de Costa Rica
* Correo: luis.lopez.castro@est.una.ac.cr
* Git: LuisLopezC99 derechos reservados
* */
import SISE.Logic.Empleado;//Importa librerias nesesarias
import SISE.Logic.Service;
import SISE.Logic.Sucursal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.Date;



public class Control { //Inicio de la clase control mainView
    //Atributos
    private static Model model;//Static llamados o modificados en metodos cambiantes
    private static View view;
    private static Service service;
    private static JTable tableEmpleados;
    private static JTable tableSucursales;
    public static SISE.Presentation.EmpleadoView.Control EMPLEADOS;//tenemos a los controladores de empleadoview y sucursal view
    public static SISE.Presentation.SurcursalView.Control SUCURSALES;
//----------------------------------------------------------------------------------------------------------------------//
   //Constructor
    public Control(Model model, View view) throws Exception { //Metemos la vista y el modelo en control ademas de tener tablas para update
        service=new Service();
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
        tableSucursales=new JTable();
        tableEmpleados=new JTable();
        service.cargarxml();
        //DataDefault();
        TablaSucursales();
        TablaEmpleado();
        show();
        //crearpuntos();
        this.view.setVisible(true);
    }
    //----------------------------------------------------------------------------------------------------------------------//
    public  static  void show() {//Observable update dates, is it possible to update the map or the others
        model.setJtableDepa(tableSucursales);
        model.setJtableEmple(tableEmpleados);
        model.commit();//Encargado de update
    }
    //----------------------------------------------------------------------------------------------------------------------//
    public void exit() { //Funcion Final guardar aqui solamente
        service.GurdarXml();
    }
    //----------------------------------------------------------------------------------------------------------------------//
    public void DataDefault() throws Exception { //Datos quemados
        Sucursal sucursal;
        Empleado empleado;
        sucursal=new Sucursal("001", "Golfito", "Puntarenas, 50m E Centro", 1.4,0,0);
        agregarDepartamento(sucursal);
        sucursal=new Sucursal("002", "Liberia", "Guanacaste, 130m O Iglesia", 2.0,0,0);
        agregarDepartamento(sucursal);
        sucursal=new Sucursal("003", "Sabana", "San José, 25m N Musmanni", 3.2,0,0);
        agregarDepartamento(sucursal);
        empleado=new Empleado("402420889", "Luis Lopez", "88154579", "Sabana", 206400.0);
        agregarEmpleado(empleado);
        empleado=new Empleado("118090020", "Isaac Méndez", "70561756", "Sabana", 180600.0);
        agregarEmpleado(empleado);
        empleado=new Empleado("603790746", "Manfred Fernandez", "70351816", "Liberia", 196860.0);
        agregarEmpleado(empleado);
    }
    //----------------------------------------------------------------------------------------------------------------------//
    //Crea una tabla nueva para luego hacerle update
    //Importante resaltar que no se puede agregar de uno en uno ya que la instancia de default model o el modelo no deja setiar la cantidad de filas por tanto no deja agregar obligando el update
    private static void TablaEmpleado() throws Exception//Tratar de pasar a metodo agregar y desaparecer este metodo agregando ala tabla uno por uno los datos echos
    {
        tableEmpleados.setModel(new DefaultTableModel(new String[]{"Cédula", "Nombre", "Teléfono", "Salario", "Departamento", "% Zonaje", "Sal. Total"}, service.AllEmpleados().size()));
        for (int i = 0; i < service.AllEmpleados().size(); i++) {
                Empleado emple = (Empleado) service.EmpleadoGetnumero(i);
                tableEmpleados.setValueAt(emple.getCedula(), i, 0);
                tableEmpleados.setValueAt(emple.getNombre(), i, 1);
                tableEmpleados.setValueAt(emple.getTelefono(), i, 2);
                tableEmpleados.setValueAt(emple.getSalarioBase(), i, 3);
                tableEmpleados.setValueAt(emple.getDepartamento(), i, 4);
                tableEmpleados.setValueAt(emple.getZonaje(), i, 5);
                tableEmpleados.setValueAt(emple.getSalarioTotal(), i, 6);
            }
    }
    private static void BusquedaTablaEmpleado(String name) throws Exception//Tratar de pasar a metodo agregar y desaparecer este metodo agregando ala tabla uno por uno los datos echos
    {
        tableEmpleados.setModel(new DefaultTableModel(new String[]{"Cédula", "Nombre", "Teléfono", "Salario", "Departamento", "% Zonaje", "Sal. Total"}, 1));
            Empleado emple = service.EmpleadoGetname(name);
            if(emple==null)
                TablaEmpleado();
            else{
            tableEmpleados.setValueAt(emple.getCedula(), 0, 0);
            tableEmpleados.setValueAt(emple.getNombre(), 0, 1);
            tableEmpleados.setValueAt(emple.getTelefono(), 0, 2);
            tableEmpleados.setValueAt(emple.getSalarioBase(), 0, 3);
            tableEmpleados.setValueAt(emple.getDepartamento(), 0, 4);
            tableEmpleados.setValueAt(emple.getZonaje(), 0, 5);
            tableEmpleados.setValueAt(emple.getSalarioTotal(), 0, 6);
            }
    }
     public static void TablaSucursales() throws Exception {
        tableSucursales.setModel(new DefaultTableModel(new String[]{"Código", "Referencia", "Dirección", "Zonaje"}, service.AllSucursales().size()));
        for (int i = 0; i < service.AllSucursales().size(); i++) {
            Sucursal depe = service.SucursalGetnumero(i);
            tableSucursales.setValueAt(depe.getCodigo(), i, 0);
            tableSucursales.setValueAt(depe.getReferencia(), i, 1);
            tableSucursales.setValueAt(depe.getDireccion(), i, 2);
            tableSucursales.setValueAt(depe.getZonaje(), i, 3);
        }
    }
    public static void busquedaTablaSucursales(String refe) throws Exception {
        tableSucursales.setModel(new DefaultTableModel(new String[]{"Código", "Referencia", "Dirección", "Zonaje"}, 1));
            Sucursal depe = service.SucursalGet(refe);
            if(depe==null)
                TablaSucursales();
            else{
            tableSucursales.setValueAt(depe.getCodigo(), 0, 0);
            tableSucursales.setValueAt(depe.getReferencia(), 0, 1);
            tableSucursales.setValueAt(depe.getDireccion(), 0, 2);
            tableSucursales.setValueAt(depe.getZonaje(), 0, 3);
            }

    }
//----------------------------------------------------------------------------------------------------------------------//
    //Agrega empleados y Departamentos atravez de servicio agregando data a las otras capas
    public static void agregarEmpleado(Empleado emp) throws Exception {
        double zonaje = 0;
        zonaje = service.SucursalGet(emp.getDepartamento()).getZonaje();
        emp.setZonaje(zonaje);
        emp.setSalarioTotal();
        service.EmpleadoAdd(emp);
        TablaEmpleado();//Actualizamos la tabla
        show();//llamamos el update
    }
    public static void agregarDepartamento(Sucursal su) throws Exception {
        service.SucursalAdd(su);
        //AgregarPuntos((int) su.getUbicacionX(), (int) su.getUbicacionY());
        TablaSucursales();
        show();
    }
    public static void EditarEmpleado(String cedula, String nombre, String telefono, String departamento, String salarioBase)throws Exception {
        service.setEmpleado(cedula,nombre,telefono,departamento,salarioBase);
        TablaEmpleado();
        show();
    }
    public static void EditarSucursal(String codigo, String Referencia, String Direccion, double zonaje,int posx,int posy) throws Exception {
        service.setSucursal( codigo, Referencia,  Direccion, zonaje, posx, posy);
        TablaSucursales();
        TablaEmpleado();
        show();
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Corremos cuando sea nesesario las ventanas empleadoview y sucursalview creando un control respectivo en cada una que encapsule sus modelos y view
    public void RunEmpleadoView() throws Exception {
        SISE.Presentation.EmpleadoView.Model modelEmpleados = new SISE.Presentation.EmpleadoView.Model();
        SISE.Presentation.EmpleadoView.View viewEmpleados = new SISE.Presentation.EmpleadoView.View(view);
        SISE.Presentation.EmpleadoView.Control controllerEmpleados = new SISE.Presentation.EmpleadoView.Control(modelEmpleados, viewEmpleados,service);
        EMPLEADOS = controllerEmpleados;
    }
    public void RunEmpleadoEditView(String cedu) throws Exception {
        SISE.Presentation.EmpleadoView.Model modelEmpleados = new SISE.Presentation.EmpleadoView.Model();
        SISE.Presentation.EmpleadoView.View viewEmpleados = new SISE.Presentation.EmpleadoView.View(view);
        SISE.Presentation.EmpleadoView.Control controllerEmpleados = new SISE.Presentation.EmpleadoView.Control(modelEmpleados, viewEmpleados,service,cedu);
        EMPLEADOS = controllerEmpleados;
    }
    public void RunSucursalView() throws Exception {
        SISE.Presentation.SurcursalView.Model modelSucursales=new SISE.Presentation.SurcursalView.Model() ;
        SISE.Presentation.SurcursalView.View viewSucursales = new SISE.Presentation.SurcursalView.View(view);
        SISE.Presentation.SurcursalView.Control controllerSucursales = new SISE.Presentation.SurcursalView.Control(modelSucursales,viewSucursales,service);
        SUCURSALES = controllerSucursales;
    }
    public void RunSucursalEdidView(String refe) throws Exception {
        SISE.Presentation.SurcursalView.Model modelSucursales=new SISE.Presentation.SurcursalView.Model() ;
        SISE.Presentation.SurcursalView.View viewSucursales = new SISE.Presentation.SurcursalView.View(view);
        SISE.Presentation.SurcursalView.Control controllerSucursales = new SISE.Presentation.SurcursalView.Control(modelSucursales,viewSucursales,service,service.SucursalGet(refe));
        SUCURSALES = controllerSucursales;
    }
//----------------------------------------------------------------------------------------------------------------------//
    public void borrarEmpleado(int fila) throws Exception {
        String nameEmpleado= (String) view.getTablaEmpleados().getValueAt(fila,0);
        service.eliminarEmpleado(nameEmpleado);
        TablaEmpleado();
        show();
    }
    public void borrarSucursal(int fila) throws Exception {
        String codigo= (String) view.getTablaSucursales().getValueAt(fila,0);
        service.eliminarSucursal(codigo);
        TablaSucursales();
        show();
    }
    public void BuscarEmpleado(String nombre) throws Exception {
        BusquedaTablaEmpleado(nombre);
        show();
    }
    public void BuscarSucursal(String refe) throws Exception {
        busquedaTablaSucursales(refe);
        show();
    }
//----------------------------------------------------------------------------------------------------------------------//
    public  void createPdfEmpleados() throws DocumentException, FileNotFoundException {
        FileOutputStream archivo = new FileOutputStream("Reporte Empleados.pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Fecha: " + new Date()));
        documento.add(Chunk.NEWLINE);
        Paragraph titulo = new Paragraph("Reporte de Empleados");
        titulo.setAlignment(1);
        documento.add(titulo);
        documento.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(110);
        PdfPCell cedula = new PdfPCell(new Phrase("Cédula"));
        cedula.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
        nombre.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell telefono = new PdfPCell(new Phrase("Teléfono"));
        telefono.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell salario = new PdfPCell(new Phrase("Salario"));
        salario.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell departamento = new PdfPCell(new Phrase("Departamento"));
        departamento.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell zonaje = new PdfPCell(new Phrase("% Zonaje"));
        zonaje.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell salarioTotal = new PdfPCell(new Phrase("Sal. Total"));
        salarioTotal.setBackgroundColor(BaseColor.ORANGE);

        tabla.addCell(cedula);
        tabla.addCell(nombre);
        tabla.addCell(telefono);
        tabla.addCell(salario);
        tabla.addCell(departamento);
        tabla.addCell(zonaje);
        tabla.addCell(salarioTotal);

        for (int i=0;i<tableEmpleados.getRowCount();i++){
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,0)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,1)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,2)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,3)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,4)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,5)));
            tabla.addCell(String.valueOf(tableEmpleados.getValueAt(i,6)));
        }
        documento.add(tabla);

        documento.close();
    }
    public  void createPdfSucursales() throws DocumentException, FileNotFoundException {
        FileOutputStream archivo = new FileOutputStream("Reporte Sucursales.pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Fecha: " + new Date()));
        documento.add(Chunk.NEWLINE);
        Paragraph titulo = new Paragraph("Reporte de Sucursales");
        titulo.setAlignment(1);
        documento.add(titulo);
        documento.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(110);
        PdfPCell codigo = new PdfPCell(new Phrase("Código"));
        codigo.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell referencia = new PdfPCell(new Phrase("Referencia"));
        referencia.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell direccion = new PdfPCell(new Phrase("Dirección"));
        direccion.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell zonaje = new PdfPCell(new Phrase("Zonaje"));
        zonaje.setBackgroundColor(BaseColor.ORANGE);

        tabla.addCell(codigo);
        tabla.addCell(referencia);
        tabla.addCell(direccion);
        tabla.addCell(zonaje);

        for (int i=0;i<tableSucursales.getRowCount();i++){
            tabla.addCell(String.valueOf(tableSucursales.getValueAt(i,0)));
            tabla.addCell(String.valueOf(tableSucursales.getValueAt(i,1)));
            tabla.addCell(String.valueOf(tableSucursales.getValueAt(i,2)));
            tabla.addCell(String.valueOf(tableSucursales.getValueAt(i,3)));
        }
        documento.add(tabla);

        documento.close();
    }
    public int getPosx(int i) throws Exception {
        int X =  (int)service.SucursalGetnumero(i).getUbicacionX();
        return X;
    }
    public int getPosY(int i) throws Exception {
        int y=(int)service.SucursalGetnumero(i).getUbicacionY();
        return y;
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase control