
package Modelo;



/**
 *
 * @author UsueUrruela
 */
public abstract class Trabajador {
    private String dni;
    private String nombre;
    private String ape1;
    private String ape2;
    private String calle;
    private String portal;
    private String piso;
    private String mano;
    private String telPersonal;
    private String telMovil;
    private int salario;
    private java.sql.Date fechaN;

    public Trabajador(String dni, String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String telPersonal, String telMovil, int salario, java.sql.Date fechaN) {
        this.dni = dni;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.calle = calle;
        this.portal = portal;
        this.piso = piso;
        this.mano = mano;
        this.telPersonal = telPersonal;
        this.telMovil = telMovil;
        this.salario = salario;
        this.fechaN = fechaN;
    }

    public String getTelPersonal() {
        return telPersonal;
    }

    public void setTelPersonal(String telPersonal) {
        this.telPersonal = telPersonal;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    
    
    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getMano() {
        return mano;
    }

    public void setMano(String mano) {
        this.mano = mano;
    }

    public String getTelPers() {
        return telPersonal;
    }

    public void setTelPers(String telPers) {
        this.telPersonal = telPers;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    

    public java.sql.Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(java.sql.Date fechaN) {
        this.fechaN = fechaN;
    }

    
    
    
}
