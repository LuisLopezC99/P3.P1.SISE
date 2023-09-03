package SISE.Presentation.EmpleadoView; //Paquete Presentation.EmpleadoView
/*
 * Clase: SISE.Presentation.EmpleadoView.View
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import javax.swing.*;//Importa librerias nesesarias
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class View extends JDialog implements Observer { //Inicio de la clase view hereda JDialog implementa observer
    //Atributos privados
    private Control controller; private Model model;
    private JTextField textFieldCedula; private JTextField textFieldNombre; private JTextField textFieldTelefono; private JTextField textFieldSalario;
    private JPanel panelEmpleado; private JPanel panelMapa;
    private JButton guardarButton; private JButton cancelarButton;
    private JComboBox comboBox1;
    private int cantidadSucursales; private int idexCombo;private int x,y;
    private Boolean editable;
//----------------------------------------------------------------------------------------------------------------------//
    public void setModel(Model model){
        this.model=model;
        model.addObserver(this);
    }
    public Model getModel() {
        return model;
    }
//----------------------------------------------------------------------------------------------------------------------//
    public void update(Observable o, Object arg) {
        textFieldCedula.setText(model.getCedula());
        setTextCedulaEditable(false);
        //textFieldCedula.setEditable(false);
        textFieldNombre.setText(model.getNombre());
        textFieldTelefono.setText(model.getTelefono());
        comboBox1.setSelectedItem(model.getDepartamento());
        textFieldSalario.setText(String.valueOf(model.getSalarioBase()));
        editable=true;
        repaint();
    }
//----------------------------------------------------------------------------------------------------------------------//
    //CustomCreate
    private void createUIComponents() {
    panelMapa=new FondoPanel();
    }
    //Constructor
    public View(JFrame padre) {
        super(padre, "Empleado", true);
        initComponents();
    }
//----------------------------------------------------------------------------------------------------------------------//
    private void initComponents(){
         // Invoca al constructor de JDialog
        guardarButton.setBackground(Color.green);
        cancelarButton.setBackground(Color.pink);
        textFieldCedula.setEditable(true);
        editable=false;
        cantidadSucursales=1;
        idexCombo=0;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panelEmpleado); // Seteo contenido del form al JFrame que se acaba de crear
        this.pack();
        Actions();
    }
//----------------------------------------------------------------------------------------------------------------------//
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
                    if(textFieldCedula.getText().isEmpty()||textFieldNombre.getText().isEmpty()||textFieldTelefono.getText().isEmpty()||textFieldSalario.getText().isEmpty()||validarDouble(textFieldSalario.getText())||Double.valueOf(textFieldSalario.getText())==0.0)
                        JOptionPane.showMessageDialog(null,"Datos invalidos, Porfavor vuelva a intentarlo");
                    else {
                        if (editable) {
                            controller.editarEpmple(textFieldCedula.getText(), textFieldNombre.getText(), textFieldTelefono.getText(), String.valueOf(comboBox1.getSelectedItem()), textFieldSalario.getText());
                            setEditable();
                        } else
                            controller.agregarEmple(textFieldCedula.getText(), textFieldNombre.getText(), textFieldTelefono.getText(), String.valueOf(comboBox1.getSelectedItem()), textFieldSalario.getText());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                dispose();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idexCombo!=comboBox1.getSelectedIndex()) {
                    idexCombo = comboBox1.getSelectedIndex();
                    panelMapa.repaint();
                }
            }
        });
        panelEmpleado.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                x=e.getX()-290;
                y=e.getY()-20;
                String Referencia=controller.getReferecia(x,y);
                comboBox1.setSelectedItem(Referencia);
                panelMapa.repaint();
            }
        });
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Parte mas importante subClass FondoPanel
    class FondoPanel extends JPanel { //Inicio de la Clase FondoPanel que hereda de JPanel
        ImageIcon image = new ImageIcon("PunteroAzul.png");
        ImageIcon image2 = new ImageIcon(image.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        ImageIcon image3 = new ImageIcon("PunteroMapaRed.png");
        ImageIcon image4 = new ImageIcon(image3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));

        public void paint(Graphics g) {
            ImageIcon imagenmap = new ImageIcon("CostaRica.jpg");
            g.drawImage(imagenmap.getImage(), 0, 0, 400, 400, this);
            setOpaque(false);
            super.paint(g);
        }
        public void paintComponent(Graphics g) {
            try {
                super.paintComponent(g);
                for (int i = 0; i < cantidadSucursales; i++) {
                    x = controller.getPosx(i);
                    y = controller.getPosY(i);
                    g.drawImage(image2.getImage(), x, y, 25, 25, this);
                }
                x =  controller.getPosx(comboBox1.getSelectedIndex());
                y =  controller.getPosY(comboBox1.getSelectedIndex());
                String dire = controller.getDireccion(comboBox1.getSelectedIndex());
                g.drawImage(image4.getImage(), x, y, 25, 25, this);
                g.drawString(dire,x,y-5);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }//Fin de la clase FondoPanel
//----------------------------------------------------------------------------------------------------------------------//
    public void setCantidadSucursales(int cs){
        cantidadSucursales=cs;
    }
    public void setController(Control control) { controller=control; }
    public void annadirComboBox(String referencia){
        comboBox1.addItem(referencia);
    }
    public void setEditable(){
        editable=false;
    }
    public void setTextCedulaEditable(Boolean x){
        textFieldCedula.setEditable(x);
    }
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