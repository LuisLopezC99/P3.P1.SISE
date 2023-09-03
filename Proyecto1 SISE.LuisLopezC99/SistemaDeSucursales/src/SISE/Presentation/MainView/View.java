package SISE.Presentation.MainView;//Paquete Presentation.SurcusalView
/*
 * Clase: SISE.Presentation.MainView.View
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import javax.swing.*;//Importa librerias nesesarias
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

public class View extends JFrame implements java.util.Observer { //Inicio de la clase view hereda JDialog implementa observer
    //Atributos privados
    private Control controller; private Model model;
    private JPanel panelPrincipal; private JPanel panelMapamain;
    private JTextField textFieldCedula;  private JTextField textFieldReferencia;
    private JButton buscarButton1; private JButton buscarButton;
    private JButton agregarButton1; private JButton agregarButton;

    private JButton borrarButton; private JButton borrarButton1;
    private JButton reporteButton; private JButton reporteButton1;
    private JTable tablaDepartamentos;private JTable tablaEmpleados;
    private JTabbedPane tabbedPane1;
    private int selex,seley;
//----------------------------------------------------------------------------------------------------------------------//
    public void setController(Control controller){
        this.controller=controller;
    }

    public Control getController() {
        return controller;
    }

    public void setModel(Model model){ this.model=model; model.addObserver(this); }

    public Model getModel() {
        return model;
    }
//----------------------------------------------------------------------------------------------------------------------//
    @Override
    public void update(Observable o, Object arg) {
        tablaEmpleados.setModel(model.getTablaEmpleados().getModel());
        tablaEmpleados.setColumnModel(model.getTablaEmpleados().getColumnModel());
        tablaDepartamentos.setModel(model.getTablaDepartamentos().getModel());
        tablaDepartamentos.setColumnModel(model.getTablaDepartamentos().getColumnModel());
        redimensionarAnchoColumna(tablaDepartamentos);
        redimensionarAnchoColumna(tablaEmpleados);
    }
//----------------------------------------------------------------------------------------------------------------------//
    //CustomCreate
    private void createUIComponents() {
    panelMapamain=new FondoPanel();
}
    public View() throws Exception { //Constructor
        initComponents();
    }
    private void initComponents() throws Exception {
        selex = 0;
        seley = 0;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISE: Sistema de Sucursales y Empleados");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        this.setContentPane(panelPrincipal); // Seteo contenido del form al JFrame que se acaba de crear
        this.pack();
        Actions();
    }
//----------------------------------------------------------------------------------------------------------------------//
    public void Actions(){
        agregarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaEmpleados.getSelectedRowCount() == 0) {
                    try {
                        controller.RunEmpleadoView();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                    }
                } else {
                    String cedu = String.valueOf(tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 0));
                    try {
                        controller.RunEmpleadoEditView(cedu);
                        tablaEmpleados.clearSelection();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                    }
                }
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaDepartamentos.getSelectedRowCount() == 0) {
                    try {
                        controller.RunSucursalView();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                    }
                    panelMapamain.repaint();
                } else {
                    String refe = String.valueOf(tablaDepartamentos.getValueAt(tablaDepartamentos.getSelectedRow(), 1));
                    try {
                        controller.RunSucursalEdidView(refe);
                        tablaDepartamentos.clearSelection();
                        selex=0;
                        seley=0;
                        panelMapamain.repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                    }

                }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.borrarSucursal(tablaDepartamentos.getSelectedRow());
                    panelMapamain.repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        borrarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.borrarEmpleado(tablaEmpleados.getSelectedRow());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.BuscarEmpleado(textFieldCedula.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.BuscarSucursal(textFieldReferencia.getText());
                    if(tablaDepartamentos.getRowCount()==1) {
                        tablaDepartamentos.setRowSelectionInterval(0,0);
                        selex=controller.getPosx(tablaDepartamentos.getSelectedRow());
                        seley=controller.getPosY(tablaDepartamentos.getSelectedRow());
                    }
                    else {
                        selex=0;
                        seley=0;
                    }
                    panelMapamain.repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        tablaDepartamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    selex=controller.getPosx(tablaDepartamentos.getSelectedRow());
                    seley=controller.getPosY(tablaDepartamentos.getSelectedRow());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                panelMapamain.repaint();
                super.mouseClicked(e);
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.createPdfSucursales();
                    JOptionPane.showMessageDialog(null,"PDF creado revisar carpeta pdf");
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        reporteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.createPdfEmpleados();
                    JOptionPane.showMessageDialog(null,"PDF creado revisar carpeta pdf");
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Error,"+ex.getMessage());
                }
            }
        });
    }
//----------------------------------------------------------------------------------------------------------------------//
    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_  formWindowClosing
        controller.exit();
    }
//----------------------------------------------------------------------------------------------------------------------//
    public void redimensionarAnchoColumna(JTable tabla) {
        final TableColumnModel columnModel = tabla.getColumnModel();
        for (int column = 0; column < tabla.getColumnCount(); column++) {
            int width = 70; // Ancho Mínimo
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer renderer = tabla.getCellRenderer(row, column);
                Component comp = tabla.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
//----------------------------------------------------------------------------------------------------------------------//
    //Parte mas importante subClass FondoPanel
    class FondoPanel extends JPanel { //Inicio de la clase FondoPanel hereda de JPanel
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
            int x=0;
            int y=0;
            JLabel M;
            super.paintComponent(g);
            for (int i = 0; i < tablaDepartamentos.getRowCount(); i++) {
                try {
                    x=controller.getPosx(i);
                    y=controller.getPosY(i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if(x==selex&&y==seley) {
                    g.drawImage(image4.getImage(), x, y, 25, 25, this);
                    M=new JLabel();
                    M.setBounds(x,y,25,25);
                    M.setToolTipText(String.valueOf(tablaDepartamentos.getValueAt(i,2)));
                    super.add(M);
                }else {
                    g.drawImage(image2.getImage(), x, y, 25, 25, this);
                    M=new JLabel();
                    M.setBounds(x,y,25,25);
                    M.setToolTipText( String.valueOf(tablaDepartamentos.getValueAt(i,2)));
                    super.add(M);
                }
            }
        }
    }//Fin de la clase FondoPanel
//----------------------------------------------------------------------------------------------------------------------//
    public JTable getTablaEmpleados(){
        return tablaEmpleados;
    }
    public JTable getTablaSucursales(){
        return tablaDepartamentos;
    }
    public JPanel getPanelMapamain(){
        return panelMapamain;
    }
//----------------------------------------------------------------------------------------------------------------------//
}//Fin de la clase View