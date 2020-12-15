package jdbc;
//control + shift + c
//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTO ALMACENADO">

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

//fcom + tab
//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTOS ALMACENADO">
//create or replace procedure borrarempleado
//(p_num emp.emp_no%type)
//as
//begin
//  delete from emp where emp_no = p_num;
//end;
//</editor-fold>
public class Class13ProcedimientosAlmacenados {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP PROCEDIMIENTOS ALMACENADOS");
        System.out.println("------------------------------");
        System.out.println("Id empleado a eliminar");
        String datoempno = teclado.nextLine();
        int empno = Integer.parseInt(datoempno);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc
                = "{ call borrarempleado(?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, empno);
        int eliminados = cst.executeUpdate();
        System.out.println("Empleados eliminados " + eliminados);
        cn.close();
    }
}
