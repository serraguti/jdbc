package jdbc;

//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTO VERSION 1">
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

/*
create or replace package pqpracticaempleados
is
       type registroempleado is record (
            apellido emp.apellido%type
            , total int
            , nombre dept.dnombre%type
            , localidad dept.loc%type
       );
       type cursorempleados is ref cursor return registroempleado;
end pqpracticaempleados;


create or replace procedure empleadosdepartamentoout
(p_nombre dept.dnombre%type
, consultaempleados OUT pqpracticaempleados.cursorempleados)
as
begin
  open consultaempleados for
  select emp.apellido
  , nvl(emp.salario, 0) + nvl(emp.comision, 0) as total
  , dept.dnombre, dept.loc
  from emp
  inner join dept
  on emp.dept_no = dept.dept_no
  where lower(dept.dnombre)=lower(p_nombre);
end;
 */
//</editor-fold>
public class Class23CursorRegistroEmpleados {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP PROCEDIMIENTO EMPLEADOS CURSOR");
        System.out.println("Introduzca un nombre de departamento");
        String nombre = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc
                = "{ call empleadosdepartamentoout (?,?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);
        cst.registerOutParameter(2, OracleTypes.CURSOR);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        cst.execute();
        int personas = cst.getInt(3);
        int sumasalarial = cst.getInt(4);
        ResultSet rs = (ResultSet) cst.getObject(2);
        while (rs.next()) {
            String ape = rs.getString("APELLIDO");
            String total = rs.getString("TOTAL");
            String nom = rs.getString("DNOMBRE");
            String loc = rs.getString("LOC");
            System.out.println(ape + " -- " + total + " -- " + nom + " -- " + loc);
        }
        System.out.println(personas
                + " personas de " + nombre + ", Suma Salarial: " + sumasalarial);
        rs.close();
        cn.close();
    }
}
