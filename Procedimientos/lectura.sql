SET SERVEROUTPUT ON;

CREATE OR REPLACE PACKAGE paquete 
IS TYPE vCursor IS REF CURSOR;
  
  PROCEDURE mostrarAdmin(
    vDniA IN administracion.dni%TYPE,
    vUserCursor OUT vCursor
  );
  
  PROCEDURE mostrarLogis(
    vDniL IN logistica.dni%TYPE,
    vUserCursor OUT vCursor
  );
END;


CREATE OR REPLACE PACKAGE BODY paquete
IS 
  PROCEDURE mostrarCentro(
    vId IN centros.ID%TYPE,
    vNombre IN centros.nombre%TYPE,
    vUserCursor OUT vCursor
  )IS
  BEGIN
    OPEN vUserCursor FOR
    SELECT id, nombre
    FROM centros
    WHERE ID = vId;
  END mostrarCentro;
  
  PROCEDURE mostrarAdmin(
    vDniA IN administracion.dni%TYPE,
    vUserCursor OUT vCursor
  )IS
  BEGIN 
    OPEN vUserCursor FOR
      SELECT *
      FROM administracion
      WHERE dni = vDniA;
    END mostrarAdmin;
    
  PROCEDURE mostrarLogis(
    vDniL IN logistica.dni%TYPE,
    vUserCursor OUT vCursor
  )IS
    BEGIN
    OPEN vUserCursor FOR
      SELECT *
      FROM logistica
      WHERE dni = vDniL;
    END mostrarLogis;
END;    
    
    
    
    