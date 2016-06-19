/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;
import Modelo.Administracion;
import Modelo.Centro;
import Modelo.CentroBD;
import Modelo.Logistica;
import Modelo.Parte;
import Modelo.ParteBD;
import Modelo.TrabajadorBD;
import Modelo.UsuarioBD;
import Modelo.Usuario;
import Parser.Parser;
import Vista.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author UsueUrruela
 */

    

public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    private static Principal bi;
    private static Login lo;
    private static Administracion adm;
    private static Logistica log;
    private static Logistica l;
    private static int idParte;
    private static ArrayList<Parte> partes;
    
    /**
     * MAIN la ejecución empieza aquí
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        crearBienvenida();
        crearLogin();
       
    }
    /**
     * Crea la ventana principal 
     */
    public static void crearBienvenida(){
        bi = new Principal();
        bi.setLocationRelativeTo(null);
        bi.setExtendedState(Principal.MAXIMIZED_BOTH);
        bi.setVisible(true);
    }
    /**
     * Crea la ventana de login y deshabilita la de bienvenida
     */
    public static void crearLogin(){
        lo = new Login();
        lo.setVisible(true);
        lo.setLocationRelativeTo(null);
        bi.setEnabled(false);
    }
    
    /**
     * Si el login es correcto envia la información a la ventana login para cerrarla y habilita la principal
     * @param nombre String (usuario)
     * @param passw String
     * @return correcto boolean
     * @throws java.sql.SQLException
     */
    public static boolean comprobarLogin(String nombre, String passw) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassw(passw);
        boolean correcto = UsuarioBD.comprobarLoginBD(usuario);
        if(correcto){
            bi.setEnabled(true);
        }  
        return correcto;
    }
    
    
    /**
     * Pide a CentroBD el listado de centros para llenar el combobox            NO USADA
     * @param centros ArrayList
     * @return centros ArrayList
     */
    public static ArrayList<Centro> llenarCombo(ArrayList<Centro> centros){
        CentroBD.recogerCentros(centros);
        return centros;
    }
    
    
    /**
     * Envía a AdministracionBD los cambios del trabajador de Administracion para la base de datos
     * @param a Administracion
     */
    public static void guardarAdministracion(Administracion a){
        
        TrabajadorBD.guardarAdmBD(a);
    }
    
    
    /**
     * Envía a LogisticaBD los cambios del trabajador de Logistica para la base de datos
     * @param l Logistica
     */
    public static void guardarLogistica(Logistica l){
        
        TrabajadorBD.guardarLogBD(l);
    }
    
    /**
     * Pide a la base de datos el tipo de trabajador
     * @return char tipo
     */
    public static char pedirTipo(){
        char tipo = UsuarioBD.getTipo();
        return tipo;
    }
    
    /**
     * Devuelve el objeto Administracion
     * @return adm Administracion
     */
    public static Administracion getAdm(){
        return adm;
    }
    
     /**
     * Devuelve el objeto Logistica
     * @return l Logistica
     */
    public static Logistica getLog(){
        return l;
    }
    
    
    
     /**
     * Pide a TrabajadorBD los datos del trabajador
     * @param dni
     * @return adm Administracion
     * @throws java.sql.SQLException
     */
    public static Administracion pedirDatosAdm(String dni) throws SQLException{
        adm = TrabajadorBD.pedirDatosAdmBD(dni);
        return adm;
    }
        
    /**
     * Pide a TrabajadorBD los datos del trabajador
     * @param dni
     * @return log Logistica
     * @throws java.sql.SQLException
     */
    public static Logistica pedirDatosLog(String dni) throws SQLException{
        log = TrabajadorBD.pedirDatosLogBD(dni);
        return log;
    }
    
    
    /**
     * Pide a UsuarioBD el dni del trabajador que ha iniciado sesión
     * @return dni String
     */
    public static String getTrabajador(){
        String dni = UsuarioBD.getDni();
        return dni;
    }
    
    /**
     * Envia a ParteBD un nuevo objeto parte para guardarlo en la base de datos
     * @param p Parte
     * @throws java.sql.SQLException
     */
    public static void guardarParte(Parte p) throws SQLException{
        ParteBD.guardarParteBD(p);
    }
    
    /**
     * Pide a ParteBD que realice una búsqueda de los partes seleccionados
     * @param desde java.sql.Date
     * @param hasta java.sql.Date
     * @param trabajador String
     * @return r String
     * @throws java.sql.SQLException
     */
    public static String buscarPartes(java.sql.Date desde, java.sql.Date hasta, String trabajador) throws SQLException{
        String r = ParteBD.buscarPartesBD(desde, hasta, trabajador);
        return r;
    }
    
    
    /**
     * Pide a TrabajadoreBD una lista de todos los trabajadores de Logistica para llenar el combobox
     * @return lista ArrayList
     * @throws java.sql.SQLException
     */
    public static ArrayList<String> pedirListaLog() throws SQLException{
        ArrayList<String> lista = TrabajadorBD.getListaLog();
        return lista;
    }
    /**
     * Envía los cambios a ParteBD para marcar el parte seleccionado como "validado"
     * @param n dni
     * @param desde java.sql.Date
     * @param hasta java.sql.Date
     * @param trabajador String
     * @throws java.sql.SQLException
     */
    public static void validarPartes(int n,java.sql.Date desde, java.sql.Date hasta, String trabajador) throws SQLException{
        ParteBD.validarPartesBD(n,desde,hasta,trabajador);
        
    }
    
    /**
     * Crea la ventana Editar Partes
     * @param id int
     */
    public static void crearVentanaEditar(int id){
        EditarPartes ep = new EditarPartes();
        ep.setVisible(true);
        ep.setLocationRelativeTo(null);
        idParte = id;
    }
    
    /**
     * Recoge de la base de datos todos los campos de un determinado parte para despues editarlos
     * @return Parte
     * @throws java.sql.SQLException
     */
    public static Parte editarParte() throws SQLException{
        Parte p = ParteBD.editarParteBD(idParte);
        return p;
    }

    /**
     * Ejecuta el parser correspondiente a ese mes
     * @param mes String
     * @throws java.sql.SQLException
     */
    public static void guardarInforme(String mes) throws SQLException{
        Parser p = new Parser(mes);
    }
    
    /**
     * Recoge todos los partes del mes de la base de datos
     * @param mes String
     * @return ArrayList
     * @throws java.sql.SQLException
     */
    public static ArrayList getPartes(String mes) throws SQLException{
        partes = ParteBD.recogerPartesMes(mes);
        return partes;
    }
    
    /**
     * Recoge de la base de datos todos los meses para los que existe un parte y se los devuelve a  la ventana para llenar el combobox
     * @return meses ArrayList
     * @throws java.sql.SQLException
     */
    public static ArrayList<String> llenarComboMeses() throws SQLException{
        ArrayList meses = ParteBD.recogerMeses();
        return meses;
    }
    
    /**
     * Genera el archivo xml del mes correspondiente
     * @param mes String
     * @throws java.sql.SQLException
     */
    public static void generarArchivoInf(String mes) throws SQLException{
        Parser.generarArchivo(mes);
    }
}
