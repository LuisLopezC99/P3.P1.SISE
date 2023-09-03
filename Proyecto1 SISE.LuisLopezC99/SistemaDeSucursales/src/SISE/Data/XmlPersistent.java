package SISE.Data;
/*
 * Clase: SISE.Data.XmlPersistent
 * Fecha:8/09/2022
 * Autor: Luis Ignacio López Castro Id: 402420889
 * Organización:Universidad Nacional de Costa Rica
 * Correo: luis.lopez.castro@est.una.ac.cr
 * Git: LuisLopezC99 derechos reservados
 * */
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import SISE.Logic.Empleado;
import SISE.Logic.Sucursal;

public class XmlPersistent {
    public void guardarEmpleados(Data lists) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("listaempleados");
            doc.appendChild(rootElement);
            // Creo los elementos
            Element EmpleadoDoc;
            String dato;
            Attr attr;
            for (int i = 0; i < lists.getEmpleados().size(); i++) {

                EmpleadoDoc = doc.createElement("empleado");
                rootElement.appendChild(EmpleadoDoc);
                Empleado emple = (Empleado) lists.getEmpleados().get(i);


                dato = emple.getCedula();
                Element ID = doc.createElement("id");
                ID.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(ID);


                dato = emple.getNombre();
                Element Name = doc.createElement("name");
                Name.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(Name);


                dato = emple.getTelefono();
                Element Telefono = doc.createElement("telefono");
                Telefono.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(Telefono);


                dato = String.valueOf(emple.getDepartamento());
                Element Departamento = doc.createElement("departamento");
                Departamento.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(Departamento);

                dato=String.valueOf(emple.getZonaje());
                Element Zonaje =doc.createElement("zonaje");
                Zonaje.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(Zonaje);

                dato = String.valueOf(emple.getSalarioBase());
                Element SalarioBase = doc.createElement("salariobase");
                SalarioBase.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(SalarioBase);


                dato = String.valueOf(emple.getSalarioTotal());
                Element SalarioTotal = doc.createElement("salariototal");
                SalarioTotal.appendChild(doc.createTextNode(dato));
                EmpleadoDoc.appendChild(SalarioTotal);


            }

            ///Se escribe el contenido del XML en un archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new File("ListaEmpleados.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void guardarSucrusal (Data lists){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("listasucursales");
            doc.appendChild(rootElement);
            Element SucursalDoc;
            String dato;
            for (int i = 0; i < lists.getSucursales().size(); i++) {

                SucursalDoc = doc.createElement("sucursal");
                rootElement.appendChild(SucursalDoc);
                Sucursal suc = (Sucursal) lists.getSucursales().get(i);


                dato = suc.getCodigo();
                Element Codigo = doc.createElement("codigo");
                Codigo.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Codigo);


                dato = suc.getReferencia();
                Element Referencia = doc.createElement("referencia");
                Referencia.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Referencia);


                dato = suc.getDireccion();
                Element Direccion = doc.createElement("direccion");
                Direccion.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Direccion);


                dato = String.valueOf(suc.getZonaje());
                Element Zonaje = doc.createElement("zonaje");
                Zonaje.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Zonaje);


                dato = String.valueOf(suc.getUbicacionX());
                Element Ux = doc.createElement("ubicacionx");
                Ux.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Ux);


                dato = String.valueOf(suc.getUbicacionY());
                Element Uy = doc.createElement("ubicaciony");
                Uy.appendChild(doc.createTextNode(dato));
                SucursalDoc.appendChild(Uy);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new File("ListaDepartamentos.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List cargarEmpleados(){
        List<Empleado> l = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("ListaEmpleados.xml"));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("empleado");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    String id = element.getElementsByTagName("id").item(0).getTextContent();

                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String telefono = element.getElementsByTagName("telefono").item(0).getTextContent();
                    String departamento = element.getElementsByTagName("departamento").item(0).getTextContent();
                    double zonaje = Double.valueOf(element.getElementsByTagName("zonaje").item(0).getTextContent());
                    double salariobase = Double.valueOf(element.getElementsByTagName("salariobase").item(0).getTextContent());
                    double salariototal = Double.valueOf(element.getElementsByTagName("salariototal").item(0).getTextContent());
                    Empleado e=new Empleado(id,name,telefono,departamento,zonaje,salariobase,salariototal);
                    l.add(e);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return  l;

    }

    public List cargarSucrusal(){
        List<Sucursal> l = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("ListaDepartamentos.xml"));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("sucursal");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();

                    // get text
                    String referencia = element.getElementsByTagName("referencia").item(0).getTextContent();
                    String direccion = element.getElementsByTagName("direccion").item(0).getTextContent();
                    double zonaje = Double.valueOf(element.getElementsByTagName("zonaje").item(0).getTextContent());
                    double ubicacionx = Double.valueOf(element.getElementsByTagName("ubicacionx").item(0).getTextContent());
                    double ubicaciony = Double.valueOf(element.getElementsByTagName("ubicaciony").item(0).getTextContent());
                    Sucursal d=new Sucursal(codigo,referencia,direccion,zonaje,ubicacionx,ubicaciony);
                    l.add(d);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return  l;
    }

}