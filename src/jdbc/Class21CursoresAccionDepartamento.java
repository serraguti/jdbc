package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

//<editor-fold defaultstate="collapsed" desc="PROCEDURE Y PACKAGE">
/*
create or replace package pqtipos
is
    --TENEMOS QUE DECLARAR UN CURSOR CON
    --EL TIPO DE CONSULTA QUE DEVOLVERA
    type cursorempleados is ref cursor return emp%rowtype;
    type cursordepartamentos is ref cursor return dept%rowtype;
end pqtipos;

create or replace procedure insertselectdepartamento
(p_nombre dept.dnombre%type
, p_loc dept.loc%type
, consultadept out pqtipos.cursordepartamentos)
as
  v_deptno int;
begin
  --BUSCAMOS EL MAXIMO DEPTNO
  select max(dept_no) + 1 into v_deptno
  from dept;
  --INSERTAMOS EL NUEVO DEPARTAMENTO
  insert into dept values (v_deptno, p_nombre, p_loc);
  commit;
  open consultadept for select * from dept;
end;
 */
//</editor-fold>
public class Class21CursoresAccionDepartamento {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP PROCEDIMIENTOS OUT INSERT DEPT");
        System.out.println("----------------------------------");
        System.out.println("Inserte un nombre de departamento");
        String nombre = teclado.nextLine();
        System.out.println("Localidad del departamento");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc = "{ call insertselectdepartamento (?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);
        cst.setString(2, localidad);
        cst.registerOutParameter(3, OracleTypes.CURSOR);
        cst.executeUpdate();
        ResultSet rs = (ResultSet) cst.getObject(3);
        while (rs.next()) {
            String deptno = rs.getString("DEPT_NO");
            String nom = rs.getString("DNOMBRE");
            String loc = rs.getString("LOC");
            System.out.println(deptno + " -- " + nom + " -- " + loc);
        }
        rs.close();
        cn.close();
    }
}
