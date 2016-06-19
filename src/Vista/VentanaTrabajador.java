/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import Modelo.*;
import proyectofinal.ProyectoFinal;
import datechooser.model.exeptions.IncompatibleDataExeption;
import Excepciones.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TeHenua
 */
public class VentanaTrabajador extends javax.swing.JFrame {

    /**
     * Creates new form VentanaTrabajador
     */
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
    private static char tipo;
    private static int salario;
    
    
    
    private static String vacio ="Uno o varios campos obligatorios vacíos";
    private static String incorrecto ="Alguno de los valores es incorrecto";
    private static Administracion adm;
    private static Logistica log;
    private static String dni;
    
    public VentanaTrabajador() {
        initComponents();
        jrAdministracion.setEnabled(false);
        jrLogistica.setEnabled(false);
        tipo = UsuarioBD.getTipo();
        dni = UsuarioBD.getDni();
        llenarVentana();
        
        
    }
    
    public void llenarVentana(){
        try {
            if(tipo=='a'){
                adm = ProyectoFinal.pedirDatosAdm(dni);
                llenarCamposAdm(adm);
            }
            if(tipo=='l'){
                log = ProyectoFinal.pedirDatosLog(dni);
                llenarCamposLog(log);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(VentanaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarCamposAdm(Administracion adm){
        jrAdministracion.setSelected(true);
        jtDni.setText(adm.getDni());
        jtNombre.setText(adm.getNombre());
        jtApe1.setText(adm.getApe1());
        jtApe2.setText(adm.getApe2());
        jtCalle.setText(adm.getCalle());
        jtPortal.setText(adm.getPortal());
        jtPiso.setText(adm.getPiso());
        jtMano.setText(adm.getMano());
        jtPersonal.setText(adm.getTelPers());
        jtMovil.setText(adm.getTelMovil());
        jtSalario.setText(String.valueOf(adm.getSalario()));
        fecha = adm.getFechaN();
        Calendar cal = Calendar.getInstance();
        if(fecha!=null)
            cal.setTime(fecha);
        dcFecha.setSelectedDate(cal);
    }
    
    public void llenarCamposLog(Logistica log){
        jrLogistica.setSelected(true);
        jtDni.setText(log.getDni());
        jtNombre.setText(log.getNombre());
        jtApe1.setText(log.getApe1());
        jtApe2.setText(log.getApe2());
        jtCalle.setText(log.getCalle());
        jtPortal.setText(log.getPortal());
        jtPiso.setText(log.getPiso());
        jtMano.setText(log.getMano());
        jtPersonal.setText(log.getTelPers());
        jtMovil.setText(log.getTelMovil());
        jtSalario.setText(String.valueOf(log.getSalario()));
        fecha = log.getFechaN();
        Calendar cal = Calendar.getInstance();
        if(fecha!=null)
            cal.setTime(fecha);
        dcFecha.setSelectedDate(cal);
    }
    
    public void limpiar(){
        jtDni.setText("");
        jtNombre.setText("");
        jtApe1.setText("");
        jtApe2.setText("");
        jtCalle.setText("");
        jtPortal.setText("");
        jtPiso.setText("");
        jtMano.setText("");
        jtMovil.setText("");
        jtPersonal.setText("");
        jtSalario.setText("");
        try {
            dcFecha.setDefaultPeriods(null);
        } catch (IncompatibleDataExeption ex) {
            Logger.getLogger(VentanaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void editarTrabajadorAdm(){
        
        Administracion a = ProyectoFinal.getAdm();
        llenarCamposAdm(a);  
    }    
    
    
    public void validarCampos() throws Vacio{
        if(jtNombre.getText()==""||jtApe1.getText()==""||jtApe2.getText()==""||jtCalle.getText()==""||jtPortal.getText()==""||jtPiso.getText()==""||
            jtMano.getText()==""||jtMovil.getText()==""|| (jrAdministracion.isSelected()==false&&jrLogistica.isSelected()==false)){
             throw new Vacio(vacio);
        }
        
            String dni=jtDni.getText();
            nombre=jtNombre.getText();
            ape1=jtApe1.getText();
            ape2=jtApe2.getText();
            calle= jtCalle.getText();
            portal= jtPortal.getText();
            piso = jtPiso.getText();
            mano= jtMano.getText();
            telMovil= jtMovil.getText();
            
            
            if(jrAdministracion.isSelected()){
                adm = new Administracion(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
            }else{
                log= new Logistica(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
            }
            
            if(jtSalario.getText()!=""){
               salario =Integer.parseInt(jtSalario.getText());
                if(tipo=='a'){
                   adm.setSalario(salario);
                }else{
                    log.setSalario(salario);
                }
            }
            if(jtMovil.getText()!=""){
                telMovil = jtMovil.getText();
                if(tipo=='a'){
                    adm.setTelMovil(telMovil);
                }else{
                    log.setTelMovil(telMovil);
                }
            }
            if(dcFecha.getSelectedDate()!=null){
                fecha = new java.sql.Date(0,0,0);
                if(tipo=='a'){
                    adm.setFechaN(fecha);
                }else{
                    log.setFechaN(fecha);
                }
            }
         
         if(tipo=='a'){
             ProyectoFinal.guardarAdministracion(adm);
         }else{
             ProyectoFinal.guardarLogistica(log);
         }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtCalle = new javax.swing.JTextField();
        jtPortal = new javax.swing.JTextField();
        jtPiso = new javax.swing.JTextField();
        jtMano = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtPersonal = new javax.swing.JTextField();
        jtMovil = new javax.swing.JTextField();
        jtDni = new javax.swing.JTextField();
        jtNombre = new javax.swing.JTextField();
        jtApe1 = new javax.swing.JTextField();
        jtApe2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jrAdministracion = new javax.swing.JRadioButton();
        jrLogistica = new javax.swing.JRadioButton();
        jtSalario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jbEditar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        dcFecha = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DATOS DEL TRABAJADOR");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("DNI:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NOMBRE:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("PRIMER APELLIDO:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("SEGUNDO APELLIDO:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("DIRECCIÓN"));

        jLabel6.setText("CALLE:");

        jLabel7.setText("PORTAL:");

        jLabel8.setText("PISO:");

        jLabel9.setText("MANO:");

        jtCalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCalleMouseClicked(evt);
            }
        });
        jtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCalleActionPerformed(evt);
            }
        });

        jtPortal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPortalMouseClicked(evt);
            }
        });
        jtPortal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPortalActionPerformed(evt);
            }
        });

        jtPiso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPisoMouseClicked(evt);
            }
        });
        jtPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPisoActionPerformed(evt);
            }
        });

        jtMano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtManoMouseClicked(evt);
            }
        });
        jtMano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtManoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jtPortal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jtMano))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32)
                        .addComponent(jtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jtPortal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtMano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TELEFONOS"));

        jLabel10.setText("PERSONAL:");

        jLabel11.setText("MOVIL:");

        jtPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPersonalMouseClicked(evt);
            }
        });
        jtPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPersonalActionPerformed(evt);
            }
        });

        jtMovil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMovilMouseClicked(evt);
            }
        });
        jtMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMovilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jtMovil))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDniActionPerformed(evt);
            }
        });

        jtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNombreMouseClicked(evt);
            }
        });
        jtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNombreActionPerformed(evt);
            }
        });

        jtApe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtApe1MouseClicked(evt);
            }
        });
        jtApe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtApe1ActionPerformed(evt);
            }
        });

        jtApe2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtApe2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtApe2MouseEntered(evt);
            }
        });
        jtApe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtApe2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("TIPO"));

        buttonGroup1.add(jrAdministracion);
        jrAdministracion.setText("ADMINISTRACIÓN");
        jrAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAdministracionActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrLogistica);
        jrLogistica.setText("LOGÍSTICA");
        jrLogistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrLogisticaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrAdministracion)
                    .addComponent(jrLogistica))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrAdministracion)
                .addGap(18, 18, 18)
                .addComponent(jrLogistica)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jtSalario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtSalarioMouseClicked(evt);
            }
        });
        jtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtSalarioActionPerformed(evt);
            }
        });

        jLabel12.setText("SALARIO:");

        jLabel13.setText("FECHA DE NACIMIENTO:");

        jbEditar.setText("Guardar cambios");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbSalir.setText("SALIR");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        dcFecha.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcFecha.setCurrentNavigateIndex(0);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(307, 307, 307)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtApe1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtApe2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(56, 56, 56)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel13)
                .addComponent(jLabel12))
            .addGap(18, 18, 18)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(259, 259, 259))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addComponent(jbSalir)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbEditar)
                    .addGap(377, 377, 377)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel4)
                    .addGap(16, 16, 16)
                    .addComponent(jLabel5))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtApe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addComponent(jtApe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEditar)
                        .addComponent(jbSalir))
                    .addGap(28, 28, 28))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtApe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtApe1ActionPerformed
    }//GEN-LAST:event_jtApe1ActionPerformed

    private void jtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNombreActionPerformed
    }//GEN-LAST:event_jtNombreActionPerformed

    private void jtApe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtApe2ActionPerformed
    }//GEN-LAST:event_jtApe2ActionPerformed

    private void jtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtSalarioActionPerformed
    }//GEN-LAST:event_jtSalarioActionPerformed

    private void jtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDniActionPerformed
        
      
        
    }//GEN-LAST:event_jtDniActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        
        nombre=jtNombre.getText();
        ape1=jtApe1.getText();
        ape2=jtApe2.getText();
        calle=jtCalle.getText();
        portal=jtPortal.getText();
        piso=jtPiso.getText();
        mano=jtMano.getText();
        telMovil=jtMovil.getText();
        telPersonal=jtPersonal.getText();
        salario=Integer.parseInt(jtSalario.getText());
        Calendar c = dcFecha.getSelectedDate();
        fecha = new java.sql.Date(c.getTime().getTime());
        
        if(jrAdministracion.isSelected())
            tipo='a';
        if(jrLogistica.isSelected())
             tipo='l';
        if(tipo=='a'){
            Administracion adm = new Administracion(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
            ProyectoFinal.guardarAdministracion(adm);
        }
        if(tipo=='l'){
            Logistica log = new Logistica(dni,nombre,ape1,ape2,calle,portal,piso,mano,telMovil,telPersonal,salario,fecha);
            ProyectoFinal.guardarLogistica(log);
        }
        JOptionPane.showMessageDialog(rootPane, "Cambiado!!");
        
        
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jrAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAdministracionActionPerformed
      
    }//GEN-LAST:event_jrAdministracionActionPerformed

    private void jrLogisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrLogisticaActionPerformed
       
    }//GEN-LAST:event_jrLogisticaActionPerformed

    private void jtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCalleActionPerformed
        
    }//GEN-LAST:event_jtCalleActionPerformed

    private void jtPortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPortalActionPerformed
   
    }//GEN-LAST:event_jtPortalActionPerformed

    private void jtPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPisoActionPerformed
        
    }//GEN-LAST:event_jtPisoActionPerformed

    private void jtManoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtManoActionPerformed
    }//GEN-LAST:event_jtManoActionPerformed

    private void jtPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPersonalActionPerformed
    }//GEN-LAST:event_jtPersonalActionPerformed

    private void jtMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMovilActionPerformed
    }//GEN-LAST:event_jtMovilActionPerformed

    private void dcFechaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dcFechaOnSelectionChange
    }//GEN-LAST:event_dcFechaOnSelectionChange

    private void jtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNombreMouseClicked
    }//GEN-LAST:event_jtNombreMouseClicked

    private void jtApe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtApe1MouseClicked
    }//GEN-LAST:event_jtApe1MouseClicked

    private void jtApe2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtApe2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtApe2MouseEntered

    private void jtApe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtApe2MouseClicked
 
    }//GEN-LAST:event_jtApe2MouseClicked

    private void jtCalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCalleMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jtCalleMouseClicked

    private void jtPortalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPortalMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtPortalMouseClicked

    private void jtPisoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPisoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPisoMouseClicked

    private void jtManoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtManoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtManoMouseClicked

    private void jtPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPersonalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPersonalMouseClicked

    private void jtMovilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMovilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtMovilMouseClicked

    private void jtSalarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSalarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtSalarioMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaTrabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JRadioButton jrAdministracion;
    private javax.swing.JRadioButton jrLogistica;
    private javax.swing.JTextField jtApe1;
    private javax.swing.JTextField jtApe2;
    private javax.swing.JTextField jtCalle;
    private javax.swing.JTextField jtDni;
    private javax.swing.JTextField jtMano;
    private javax.swing.JTextField jtMovil;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JTextField jtPersonal;
    private javax.swing.JTextField jtPiso;
    private javax.swing.JTextField jtPortal;
    private javax.swing.JTextField jtSalario;
    // End of variables declaration//GEN-END:variables
}
