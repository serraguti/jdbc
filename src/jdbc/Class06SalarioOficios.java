package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class06SalarioOficios {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un oficio");
        String oficio = teclado.nextLine();
        System.out.println("Introduzca incremento");
        String dato = teclado.nextLine();
        int incremento = Integer.parseInt(dato);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //update emp set salario = salario + 1 where upper(oficio) = upper('vendedor')
        String consulta
                = "update emp set salario = salario + " + incremento
                + " where upper(oficio) = upper('" + oficio + "')";
        int modificados = st.executeUpdate(consulta);
        System.out.println("Empleados modificados: " + modificados);
        cn.close();
    }
}
