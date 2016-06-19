
package Modelo;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author UsueUrruela
 */
public class TrabajadorBD {
    
    private static String nombre ;
    private static String ape1;
    private static String ape2 ;
    private static String calle;
    private static String portal;
    private static String piso;
    private static String mano;
    private static String telMovil;
    private static String telPersonal;
    private static java.sql.Date fecha;
    private static int salario;
    private static Calendar cal=Calendar.getInstance();

    
    /**
     * Consulta en la base de datos todos los parametros del trabajador que ha hecho login si es de Administracion
     * @param dni
     * @return adm Administracion
     * @throws java.sql.SQLException 
     */
    public static Administracion pedirDatosAdmBD(String dni) throws SQLException{
        GenericBD.conectar();
        
        String llamada = "{call consultas.getAdmin(?,?)}";
        CallableStatement cs = GenericBD.getCon().prepareCall(llamada);
        cs.setString(1, dni);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.execute();
        
        ResultSet rs =(ResultSet)cs.getObject(2);
            while(rs.next()){
            dni = rs.getString("dni");
            nombre = rs.getString("nombre");
            ape1 = rs.getString("ape1");
            ape2 = rs.getString("ape2");
            calle = rs.getString("calle");
            portal = rs.getString("portal");
            piso = rs.getString("piso");
            mano = rs.getString("mano");
            telMovil = rs.getString("telempresa");
            telPersonal = rs.getString("telpersonal"); 
            salario = rs.getInt("salario");
            fecha = rs.getDate("fechanac");  
        }
        
        
        GenericBD.desconectar();
        Administracion adm = new Administracion(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
        return adm;
    }
    
    
    /**
     * Consulta en la base de datos todos los parametros del trabajador que ha hecho login si es de Logistica
     * @param dni
     * @return log Logistica
     * @throws java.sql.SQLException 
     */
    public static Logistica pedirDatosLogBD(String dni) throws SQLException{
        GenericBD.conectar();
        String llamada = "{call consultas.getLog(?,?)}";
        CallableStatement cs = GenericBD.getCon().prepareCall(llamada);
        cs.setString(1, dni);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.execute();
        ResultSet rs =(ResultSet)cs.getObject(2);
        while(rs.next()){
            nombre = rs.getString("nombre");
            ape1 = rs.getString("ape1");
            ape2 = rs.getString("ape2");
            calle = rs.getString("calle");
            portal = rs.getString("portal");
            piso = rs.getString("piso");
            mano = rs.getString("mano");
            telMovil = rs.getString("telempresa");
            telPersonal = rs.getString("telpersonal"); 
            fecha = rs.getDate("fechanac");  
            salario = rs.getInt("salario");
        }
        GenericBD.desconectar();
        Logistica log = new Logistica(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
        return log;
    }
    
    
    /**
     * Devuelve una lista con todos los trabajadores de Logistica
     * @return lista ArrayList
     * @throws java.sql.SQLException 
     */
    public static ArrayList<String> getListaLog() throws SQLException{
        
        ArrayList<String> lista = new ArrayList();
        GenericBD.conectar();
        Statement st = GenericBD.getCon().createStatement();
        ResultSet rs = st.executeQuery("select dni from logistica");
        while(rs.next()){
            lista.add(rs.getString("dni"));
        }
        GenericBD.desconectar();
        return lista;
    }
     
    
    /**
     * Guarda en la base de datos los cambio del trabajador de Administracion
     * @param a Administracion
     */
    public static void guardarAdmBD(Administracion a){
        String dni=a.getDni();
        nombre=a.getNombre();
        ape1=a.getApe1();
        ape2=a.getApe2();
        calle=a.getCalle();
        portal=a.getPortal();
        piso=a.getPiso();
        mano=a.getMano();
        telMovil=a.getTelMovil();
        telPersonal=a.getTelPers();
        fecha=a.getFechaN();
        salario=a.getSalario();
        try {
            GenericBD.conectar();
            PreparedStatement ps= GenericBD.getCon().prepareStatement("update administracion set nombre=?,ape1=?,ape2=?,calle=?,"
                    + "portal=?,piso=?,mano=?,telempresa=?,telpersonal=?,fechanac=?,salario=? where dni=?");
            ps.setString(1, nombre);
            ps.setString(2,ape1 );
            ps.setString(3,ape2 );
            ps.setString(4,calle );
            ps.setString(5,portal );
            ps.setString(6,piso );
            ps.setString(7, mano);
            ps.setString(8, telMovil);
            ps.setString(9, telPersonal);
            ps.setDate(10,fecha );
            ps.setInt(11, salario);
            ps.setString(12, dni);
            ps.executeUpdate();
            GenericBD.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(TrabajadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Guarda en la base de datos los cambio del trabajador de Logistica
     * @param l
     */
    public static void guardarLogBD(Logistica l){
        String dni=l.getDni();
        nombre=l.getNombre();
        ape1=l.getApe1();
        ape2=l.getApe2();
        calle=l.getCalle();
        portal=l.getPortal();
        piso=l.getPiso();
        mano=l.getMano();
        telMovil=l.getTelMovil();
        telPersonal=l.getTelPers();
        fecha=l.getFechaN();
        salario=l.getSalario();
        try {
            GenericBD.conectar();
            PreparedStatement ps= GenericBD.getCon().prepareStatement("update logistica set nombre=?,ape1=?,ape2=?,calle=?,"
                    + "portal=?,piso=?,mano=?,telempresa=?,telpersonal=?,fechanac=?,salario=? where dni=?" );
            ps.setString(1, nombre);
            ps.setString(2,ape1 );
            ps.setString(3,ape2 );
            ps.setString(4,calle );
            ps.setString(5,portal );
            ps.setString(6,piso );
            ps.setString(7, mano);
            ps.setString(8, telMovil);
            ps.setString(9, telPersonal);
            ps.setDate(10,fecha );
            ps.setInt(11, salario);
            ps.setString(12, dni);
            ps.executeUpdate();
            GenericBD.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(TrabajadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
