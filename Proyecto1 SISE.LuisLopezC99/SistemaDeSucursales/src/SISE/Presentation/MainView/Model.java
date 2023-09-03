package SISE.Presentation.MainView;//Paquete Presentation.SurcusalView
/*
 * Clase: SISE.Presentation.MainView.Model
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import javax.swing.*;//Importa librerias nesesarias
import javax.swing.table.DefaultTableModel;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable { //Heredamos Observable patron observador inicio de la clase modelo
    //Atributos privados
    private JTable tablaDepartamentos=new JTable();//Atributos a setear
    private  JTable tablaEmpleados=new JTable();
    private DefaultTableModel d=new DefaultTableModel();

    //SetsAndGets
//----------------------------------------------------------------------------------------------------------------------//
    public JTable getTablaDepartamentos() { return  tablaDepartamentos; }

    public JTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public void setJtableDepa(JTable tablaDepartamentos){ this.tablaDepartamentos=tablaDepartamentos; }
    public void setJtableEmple(JTable tablaEmpleados){ this.tablaEmpleados=tablaEmpleados; }

//----------------------------------------------------------------------------------------------------------------------//
    //Metodos del patron observer
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
}//Fin de la clase modelo