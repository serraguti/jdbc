package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.internal.OracleTypes;

//<editor-fold defaultstate="collapsed" desc="PAQUETES Y PROCEDIMIENTOS">
/*
create or replace package pqtipos
is
    --TENEMOS QUE DECLARAR UN CURSOR CON
    --EL TIPO DE CONSULTA QUE DEVOLVERA
    type cursorempleados is ref cursor return emp%rowtype;
end pqtipos;

create or replace procedure selectempleados
(consulta OUT pqtipos.cursorempleados)
as
begin
  open consulta for
  select * from emp;
end;
 */
//</editor-fold>
public class Class20CursoresProcedimientos {

    public static void main(String[] args) throws SQLException {
        System.out.println("APP CURSORES DE SALIDA PROCEDURES");
        System.out.println("---------------------------------");
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{ call selectempleados (?) }";
        //LOS CURSORES DE SALIDA SON PROPIO DE ORACLE
        //POR LO QUE NO ESTAN DENTRO DE LOS TIPO DE SQL
        //PARA DEFINIR EL TIPO DEL CURSOR DE ORACLE
        //NECESITAMOS LA ENUMERACION OracleTypes.CURSOR
        CallableStatement cst = cn.prepareCall(consulta);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        //NUESTRO PROCEDIMIENTO NO EJECUTA NINGUNA CONSULTA
        //QUE NO SEA EL CURSOR (no tiene update, delete...)
        //SI SOLAMENTE QUEREMOS EJECUTAR EL CURSOR
        //SE UTILIZA EL METODO execute()
        cst.execute();
        //UNA VEZ EJECUTADO, TENEMOS EL CURSOR CARGADO DENTRO
        //DEL PARAMETRO 1.
        //NO EXISTE UN METODO PARA RECUPERAR EL CURSOR DE FORMA
        //EXPLICITA getCursor(1)
        //DEBEMOS UTILIZAR EL METODO GENERICO getObject(1)
        //Y CONVERTIR EL CURSOR A UN TIPO DE DATO PARA RECORRER
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ape = rs.getString("APELLIDO");
            String ofi = rs.getString("OFICIO");
            String dept = rs.getString("DEPT_NO");
            System.out.println(ape + " -- " + ofi + " -- " + dept);
        }
        rs.close();
        cn.close();
    }
}
