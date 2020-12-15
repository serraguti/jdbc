package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTOS ALMACENADOS">
/*create or replace procedure insertardepartamento
(p_num dept.dept_no%type
, p_nom dept.dnombre%type
, p_loc dept.loc%type)
as
begin
  insert into dept values
  (p_num, p_nom, p_loc);
  commit;
end;*/
//</editor-fold>
public class Class14ProcedimientoInsertarDepartamento {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP INSERTAR DEPT PROCEDIMIENTOS");
        System.out.println("--------------------------------");
        System.out.println("Número de departamento");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        System.out.println("Nombre de departamento");
        String nombre = teclado.nextLine();
        System.out.println("Localidad");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc
                = "{ call insertardepartamento (?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, deptno);
        cst.setString(2, nombre);
        cst.setString(3, localidad);
        cst.executeUpdate();
        String consultaselect = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consultaselect);
        while (rs.next()) {
            String num = rs.getString("DEPT_NO");
            String nom = rs.getString("DNOMBRE");
            String loc = rs.getString("LOC");
            System.out.println(num + " -- " + nom + " -- " + loc);
        }
        rs.close();
        cn.close();
    }
}
