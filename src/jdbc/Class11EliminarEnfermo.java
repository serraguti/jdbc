package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class11EliminarEnfermo {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP ELIMINAR ENFERMOS");
        System.out.println("---------------------");

        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String respuesta = "s";
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Introduzca inscripción a eliminar");
            String datoinscripcion = teclado.nextLine();
            int inscripcion = Integer.parseInt(datoinscripcion);
            String consultadelete
                    = "delete from enfermo2 where inscripcion=?";
            PreparedStatement pst = cn.prepareStatement(consultadelete);
            pst.setInt(1, inscripcion);
            int eliminados = pst.executeUpdate();
            System.out.println("Enfermos eliminados: " + eliminados);
            String consultaselect = "select * from enfermo2";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultaselect);
            while (rs.next()) {
                String apellido = rs.getString("APELLIDO");
                String ins = rs.getString("INSCRIPCION");
                System.out.println(ins + " -- " + apellido);
            }
            rs.close();
            System.out.println("¿Desea eliminar más registros? (s/n)");
            respuesta = teclado.nextLine();
        }
        cn.close();
        System.out.println("Fin de programa");
    }
}
