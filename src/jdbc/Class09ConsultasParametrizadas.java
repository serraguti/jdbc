package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class09ConsultasParametrizadas {

    public static void main(String[] args) throws SQLException {
        //VAMOS A REALIZAR UNA APLICACION
        //DONDE BUSCAREMOS POR OFICIO Y DEPARTAMENTO
        Scanner teclado = new Scanner(System.in);
        System.out.println("CONSULTAS PARAMETRIZADAS");
        System.out.println("Introduzca oficio");
        String oficio = teclado.nextLine();
        System.out.println("Introduzca número departamento");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        //CONSULTA CON PARAMETROS
        String consulta
                = "select * from emp where upper(oficio) = upper(?) and dept_no=?";
        PreparedStatement pst = cn.prepareStatement(consulta);
        //ESTABLECEMOS LOS PARAMETROS DE IZQUIERDA A DERECHA
        //EMPEZANDO EN 1
        pst.setString(1, oficio);
        pst.setInt(2, deptno);
        //EJECUTAMOS LA CONSULTA SIN PASAR NADA AL METODO
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String datooficio = rs.getString("OFICIO");
            String dept = rs.getString("DEPT_NO");
            System.out.println(apellido + " -- " + oficio + " -- " + dept);
        }
        rs.close();
        cn.close();
    }
}
