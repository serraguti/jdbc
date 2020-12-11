package jdbc;

import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class05InsertDepartamentos {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP INSERTAR DEPARTAMENTO");
        System.out.println("-------------------------");
        System.out.println("Introduzca número departamento: ");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        System.out.println("Nombre departamento");
        String nombre = teclado.nextLine();
        System.out.println("Introduzca localidad");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //insert into dept values (15, 'RRHH', 'CADIZ')
        String consulta
                = "insert into dept values (" + deptno + ", '" + nombre + "', '" + localidad + "')";
        int insertados = st.executeUpdate(consulta);
        System.out.println("Departamentos insertados " + insertados);
        cn.close();
    }
}
