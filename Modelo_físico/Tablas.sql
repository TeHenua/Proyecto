DROP TABLE ADMINISTRACION CASCADE CONSTRAINTS;
DROP TABLE CENTROS CASCADE CONSTRAINTS;
DROP TABLE LOGISTICA CASCADE CONSTRAINTS;
DROP TABLE PARTES CASCADE CONSTRAINTS;
DROP TABLE USUARIOS CASCADE CONSTRAINTS;
DROP TABLE VIAJES CASCADE CONSTRAINTS;
DROP TABLE PARTESXML CASCADE CONSTRAINTS;

CREATE
  TABLE Centros
  (
    id        NUMBER GENERATED ALWAYS AS IDENTITY 
                        MINVALUE 1 
                        MAXVALUE 99
                        INCREMENT BY 1 
                        START WITH 1  
                        NOORDER  
                        NOCYCLE  NOT NULL ENABLE
      CONSTRAINT centros_id_pk PRIMARY KEY ,
    nombre    VARCHAR2 (20) NOT NULL ,
    telefono  VARCHAR2 (9) NOT NULL ,
    calle     VARCHAR2 (50) NOT NULL ,
    numero    VARCHAR2 (3) NOT NULL ,
    c_postal  VARCHAR2 (5) NOT NULL ,
    ciudad    VARCHAR2 (50) NOT NULL ,
    provincia VARCHAR2 (30) NOT NULL
  ) ;

CREATE
  TABLE Administracion
  (
    dni             VARCHAR2 (9) PRIMARY KEY ,
    nombre          VARCHAR2 (20) NOT NULL ,
    ape1            VARCHAR2 (20) NOT NULL ,
    ape2            VARCHAR2 (20) NOT NULL ,
    calle           VARCHAR2 (50) NOT NULL ,
    portal          VARCHAR2 (3) NOT NULL ,
    piso            VARCHAR2 (3) NOT NULL ,
    mano            VARCHAR2 (3) NOT NULL ,
    telpersonal     VARCHAR2 (9) ,
    telempresa      VARCHAR2 (9) NOT NULL ,
    fechanac        DATE ,
    salario         NUMBER ,
    Centro_id       NUMBER NOT NULL,
    CONSTRAINT adm_cen_fk FOREIGN KEY(Centro_id) REFERENCES centros(id)    
  ) ;

CREATE
  TABLE Logistica
  (
    dni             VARCHAR2 (9) PRIMARY KEY ,
    nombre          VARCHAR2 (20) NOT NULL ,
    ape1            VARCHAR2 (20) NOT NULL ,
    ape2            VARCHAR2 (20) NOT NULL ,
    calle           VARCHAR2 (50) NOT NULL ,
    portal          VARCHAR2 (3) NOT NULL ,
    piso            VARCHAR2 (3) NOT NULL ,
    mano            VARCHAR2 (3) NOT NULL ,
    telpersonal     VARCHAR2 (9) ,
    telempresa      VARCHAR2 (9) NOT NULL ,
    fechanac        DATE ,
    salario         NUMBER ,
    Centro_id       NUMBER NOT NULL,
    CONSTRAINT log_cen_fk FOREIGN KEY(Centro_id) REFERENCES centros(id)
  ) ;




CREATE
  TABLE Partes
  (
    id            NUMBER (2)
      GENERATED ALWAYS AS IDENTITY 
                        MINVALUE 1 
                        MAXVALUE 99
                        INCREMENT BY 1 
                        START WITH 1  
                        NOORDER  
                        NOCYCLE  NOT NULL ENABLE
      CONSTRAINT parte_id_pk PRIMARY KEY ,
    km_principio       NUMBER(6) NOT NULL ,
    km_llegada         NUMBER(6) NOT NULL ,
    cerrado            NUMBER(1) NOT NULL ,
    validado           NUMBER(1) NOT NULL ,
    fecha              DATE NOT NULL ,
    gasoil             NUMBER(6) ,
    autopista          NUMBER(6) ,
    dietas             NUMBER(6) ,
    otros              VARCHAR2 (1000) ,
    incidencias        VARCHAR2 (1000) ,
    Logistica_dni      VARCHAR2 (9) NOT NULL ,
    Administracion_dni VARCHAR2 (9),
    CONSTRAINT par_log_fk FOREIGN KEY ( Logistica_dni) REFERENCES Logistica ( dni ),
    CONSTRAINT par_adm_fk FOREIGN KEY ( Administracion_dni) REFERENCES Administracion ( dni )
  ) ;



CREATE
  TABLE Usuarios
  (
    usuario        VARCHAR2 (25) PRIMARY KEY,
    password    VARCHAR2 (25) NOT NULL ,
    dniA           VARCHAR2 (25) ,
    dniL           VARCHAR2 (25),
    CONSTRAINT usu_dniA_fk FOREIGN KEY(dniA) REFERENCES administracion(dni),
    CONSTRAINT usu_dniL_fk FOREIGN KEY(dniL) REFERENCES logistica(dni)
  ) ;



CREATE
  TABLE Viajes
  (
    num_albaran VARCHAR2 (10) PRIMARY KEY ,
    h_salida    VARCHAR2(5) NOT NULL ,
    h_llegada   VARCHAR2(5) NOT NULL ,
    matricula   VARCHAR2 (8) NOT NULL ,
    Parte_id    NUMBER(2) NOT NULL,
    CONSTRAINT via_par_fk FOREIGN KEY(Parte_id) REFERENCES partes(id)
  ) ;

CREATE TABLE partesxml (
      id            NUMBER (2)
      GENERATED ALWAYS AS IDENTITY 
                        MINVALUE 1 
                        MAXVALUE 99
                        INCREMENT BY 1 
                        START WITH 1  
                        NOORDER  
                        NOCYCLE  NOT NULL ENABLE
      CONSTRAINT partexml_id_pk PRIMARY KEY ,
      parte   xmltype NOT NULL,
      mes     VARCHAR(7) NOT NULL
);
