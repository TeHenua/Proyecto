/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Modelo.Albaran;
import Modelo.Parte;
import com.sun.jndi.toolkit.url.Uri;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import proyectofinal.ProyectoFinal;

/**
 *
 * @author UsueUrruela
 */
public class Parser {

    private static Document document = null;
    public Parser(String mes) {
        try {
            ArrayList partes = ProyectoFinal.getPartes(mes);
            
            document = createDocumentXML(document,partes);
            ParserBD.guardarBD(document,mes);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public static Document obtenerInforme(String mes) throws SQLException{
        try {
            String xml = ParserBD.getXMLBD(mes);
            document = getDocumentFromXMLString(xml);
            
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    
    public static void generarArchivo(String mes) throws SQLException{
        document = obtenerInforme(mes);
        guardarArchivo(document);
    }
    
    public static Document createDocumentXML(Document document,ArrayList<Parte> partes){

     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     try{
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        document = implementation.createDocument(null, "xml", null);

        /*Creación de elementos*/
        Text vMes=null;
        Text vTrabajador=null;
        Text vFecha=null;
        Text vId=null;
        Text vKmI=null;
        Text vKmF=null;
        Text vCerrado=null;
        Text vValidado=null;
        Text vGasoil=null;
        Text vAuto=null;
        Text vDietas=null;
        Text vOtros=null;
        Text vIncidencias=null;
        Text vNumAlb=null;
        Text vHoraS=null;
        Text vHoraL=null;
        Text vVehiculo=null;
        //Creamos el elemento raiz Informe
        Element informe = document.createElement("Informe"); 
        document.setXmlVersion("1.0"); 
        //Creamos el resto de elmentos
        for (Parte p : partes) {
            Element mes = document.createElement("Mes"); 
            Element parte = document.createElement("Parte"); 
            Element trabajador = document.createElement("Trabajador");
            Element fecha = document.createElement("Fecha"); 
            Element id = document.createElement("Id"); 
            Element kmI = document.createElement("KmInicio"); 
            Element kmF = document.createElement("KmFin"); 
            Element cerrado = document.createElement("Cerrado"); 
            Element validado = document.createElement("Validado"); 
            Element gasoil = document.createElement("Gasoil"); 
            Element autopista = document.createElement("Autopista"); 
            Element dietas = document.createElement("Dietas"); 
            Element otros = document.createElement("Otros"); 
            Element incidencias = document.createElement("Incidencias"); 
            
            //Añadimos el informe al Document creado
            document.getDocumentElement().appendChild(informe);
            //Añadimos el resto de elementos
            informe.appendChild(mes);
            informe.appendChild(parte);
            parte.appendChild(trabajador);
            parte.appendChild(fecha);
            parte.appendChild(id);
            parte.appendChild(kmI);
            parte.appendChild(kmF);
            parte.appendChild(cerrado);
            parte.appendChild(validado);
            parte.appendChild(gasoil);
            parte.appendChild(autopista);
            parte.appendChild(dietas);
            parte.appendChild(otros);
            parte.appendChild(incidencias);
           
            
            java.sql.Date fechaSql = p.getFecha();
            Calendar cal = new GregorianCalendar();
            cal.setTime(fechaSql);
            int mesInt = cal.get(Calendar.MONTH)+1;
            vMes = document.createTextNode(String.valueOf(mesInt));
            vTrabajador = document.createTextNode(p.getDniLog());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
            String textFecha = df.format(p.getFecha());  
            vFecha = document.createTextNode(textFecha);
            vId = document.createTextNode(p.getId());
            vKmI = document.createTextNode(p.getKmPrin());
            vKmF = document.createTextNode(p.getKmLleg());
            boolean cerr = p.isCerrado();
            String cerra;
            if(cerr){
                cerra = "si";
            }else{
                cerra = "no";
            }
            vCerrado = document.createTextNode(cerra);
            boolean val = p.isValidado();
            String vali;
            if(val){
                vali = "si";
            }else{
                vali ="no";
            }
            vValidado = document.createTextNode(vali);
            vGasoil = document.createTextNode(p.getGasoil());
            vAuto = document.createTextNode(p.getAutopista());
            vDietas = document.createTextNode(p.getDietas());
            vOtros = document.createTextNode(p.getOtros());
            vIncidencias = document.createTextNode(p.getIncidencias());
            
            mes.appendChild(vMes);
            trabajador.appendChild(vTrabajador);
            fecha.appendChild(vFecha);
            id.appendChild(vId);
            kmI.appendChild(vKmI);
            kmF.appendChild(vKmF);
            cerrado.appendChild(vCerrado);
            validado.appendChild(vValidado);
            gasoil.appendChild(vGasoil);
            autopista.appendChild(vAuto);
            dietas.appendChild(vDietas);
            otros.appendChild(vOtros);
            incidencias.appendChild(vIncidencias);
            
            
            ArrayList<Albaran> albaranes = p.getAlbaranes();
            for(Albaran a : albaranes){
                Element albaran = document.createElement("Albaran"); 
                Element numAlbaran = document.createElement("NumeroAlbaran"); 
                Element horaS = document.createElement("HoraSalida"); 
                Element horaL = document.createElement("HoraLlegada"); 
                Element vehiculo = document.createElement("Vehiculo"); 
                
                parte.appendChild(albaran);
                albaran.appendChild(numAlbaran);
                albaran.appendChild(horaS);
                albaran.appendChild(horaL);
                albaran.appendChild(vehiculo);
                
                vNumAlb = document.createTextNode(a.getId());
                vHoraS = document.createTextNode(a.getHoraSalida());
                vHoraL = document.createTextNode(a.getHoraLlegada());
                vVehiculo = document.createTextNode(a.getVehiculo());
                
                numAlbaran.appendChild(vNumAlb);
                horaS.appendChild(vHoraS);
                horaL.appendChild(vHoraL);
                vehiculo.appendChild(vVehiculo);
            }
        }       

     }catch(Exception e){
         System.err.println("Error");
     }

        return document;
    }
    
    public static String getStringFromDocument(Document document){
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            //Decidimos no omitir la declaración XML ("yes" para omitirla)
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            String output = writer.getBuffer().toString();

            //Devolvemos el String
            return output;
        }catch(Exception e){
        
        }
        return "";
    }
    
    public static Document getDocumentFromXMLString(String xml) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        return doc;
    }
    
    public static void guardarArchivo(Document document){
        try {
            TransformerFactory transFact = TransformerFactory.newInstance();

            //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
            transFact.setAttribute("indent-number", new Integer(3));
            Transformer trans = transFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            //Hacemos la transformación
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            DOMSource domSource = new DOMSource(document);
            trans.transform(domSource, sr);

            //Mostrar información a guardar por consola (opcional)
            //Result console= new StreamResult(System.out);
            //trans.transform(domSource, console);
            try {
                //Creamos fichero para escribir en modo texto
                SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
                Calendar cal = Calendar.getInstance();
                String fecha =  sdf.format(cal.getTime());
                //Cambiar "C:\\......." a la ruta correspondiente a informes
                File file= new File ("C:\\Users\\TeHenua\\Desktop\\ProyectoFinal\\informes\\"+fecha+".xml");
                FileWriter fw;
                if (file.exists()){
                   fw = new FileWriter(file,true);//if file exists append to file. Works fine.
                }else{
                   file.createNewFile();
                   fw = new FileWriter(file);
                }
                //Escribimos todo el árbol en el fichero
                try (PrintWriter writer = new PrintWriter(file)) {
                    //Escribimos todo el árbol en el fichero
                    writer.println(sw.toString());
                    
                    //Cerramos el fichero
                }
            } catch (IOException e) {
            }
        } catch(IllegalArgumentException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
