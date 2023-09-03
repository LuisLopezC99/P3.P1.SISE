package SISE.Presentation.SurcursalView; //Paquete Presentation.SucursalView
/*
 * Clase: SISE.Presentation.SurcursalView.View
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import javax.swing.*;//Incluye las librerias nesesarias
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;


public class View extends JDialog  implements Observer {//Inicio de la clase view hereda de JDialogo siendo su hijo e implementa obserber para lograr un update
    //Atributos privados de view
    private Model model; private Control control;
    private JTextField textFieldReferencia; private JTextField textFieldDireccion; private JTextField textFieldZonaje; private JTextField textFieldCodigo;
    private JButton guardarButton; private JButton cancelarButton;
    private JPanel panelDepartamento; private  JPanel panelMapa;
    private boolean editable;
    private int posx,posy;
//----------------------------------------------------------------------------------------------------------------------//
    //Los sets mas importantes donde se tiene el modelo y el control
    public void setModel(Model model) { this.model=model; model.addObserver(this); }public void setController(Control control) {this.control=control;}
//----------------------------------------------------------------------------------------------------------------------//
    //Update
    @Override
    public void update(Observable o, Object arg) {
        textFieldCodigo.setText(model.getCodigo());
        textFieldCodigo.setEditable(false);
        textFieldReferencia.setText(model.getReferencia());
        textFieldDireccion.setText(model.getDireccion());
        textFieldZonaje.setText(String.valueOf(model.getZonaje()));
        setPos((int)model.getUbicacionX(),(int)model.getUbicacionY());
        repaint();
        editable=true;
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Custom create
    private void createUIComponents() { panelMapa=new FondoPanel(); }
    //Constructor
    public View(JFrame padre) throws Exception // Constructor
    {
        super(padre, "Sucursal", true); // Invoca al constructor de JDialog
        posx=40;posy=40;
        editable=false;
        createUIComponents();
        guardarButton.setBackground(Color.green);
        cancelarButton.setBackground(Color.pink);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panelDepartamento); // Seteo contenido del form al JFrame que se acaba de crear
        this.pack();
        Actions();
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Actions
    public void Actions(){
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textFieldReferencia.getText().isEmpty()||textFieldDireccion.getText().isEmpty()||textFieldZonaje.getText().isEmpty()||validarDouble(textFieldZonaje.getText())||Double.valueOf(textFieldZonaje.getText())==0.0||posx<1||posy<1)
                        JOptionPane.showMessageDialog(null,"Datos invalidos, intentelo de nuevo");
                    else{
                        if(editable) {
                            Control.Editarsucursal(textFieldCodigo.getText(), textFieldReferencia.getText(), textFieldDireccion.getText(), textFieldZonaje.getText(), posx, posy);
                            editable=false;
                        }
                        else {
                            Control.AgregarSucursal(textFieldCodigo.getText(), textFieldReferencia.getText(), textFieldDireccion.getText(), textFieldZonaje.getText(), posx, posy);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                dispose();
            }
        });
        panelDepartamento.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x=e.getX()-280;
                int y=e.getY()-20;
                setPos(x,y);
                repaint();
                panelMapa.repaint();
                panelMapa.revalidate();
            }
        });
    }
//----------------------------------------------------------------------------------------------------------------------//
    //La Subclase Fondo panel la mas importante
    class FondoPanel extends JPanel{ //Inicio de la clase FondoPanel padre que hereda de Janel
        ImageIcon image=new ImageIcon("PunteroMapaRed.png");
        ImageIcon image2=new ImageIcon(image.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH));
        public void paint(Graphics g){
            ImageIcon imagenmap=new ImageIcon("CostaRica.jpg");
            g.drawImage(imagenmap.getImage(),0,0, 400,400,this);
            setOpaque(false);
            super.paint(g);
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image2.getImage(),posx,posy,25,25,this);
        }
    }
//----------------------------------------------------------------------------------------------------------------------//
    //set y validar
    public void setPos(int x,int y) { posx=x; posy=y; }
    public void setTextFielcod(String s){ textFieldCodigo.setText(s); }
    public void setEditable(){ editable=false; }
    private boolean validarDouble(String x){
        double z=0;
        try{
            z=Double.parseDouble(x);
            return false;
        }
        catch (Exception E){
            return true;
        }
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase view