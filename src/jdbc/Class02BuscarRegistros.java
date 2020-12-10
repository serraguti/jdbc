package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class02BuscarRegistros {

    public static void main(String[] args) throws SQLException {
        //VAMOS A MOSTRAR LOS DOCTORES DE UN HOSPITAL
        //APELLIDO Y ESPECIALIDAD
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca código de hospital: ");
        String dato = teclado.nextLine();
        int hospitalcod = Integer.parseInt(dato);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        Statement st = cn.createStatement();
        String consulta = "select apellido, especialidad from doctor where hospital_cod=" + hospitalcod;
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String espe = rs.getString("ESPECIALIDAD");
            System.out.println(apellido + " --- " + espe);
        }
        rs.close();
        cn.close();
    }
}
