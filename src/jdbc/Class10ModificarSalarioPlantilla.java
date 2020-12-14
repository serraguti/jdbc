package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class10ModificarSalarioPlantilla {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP MODIFICAR DATOS PLANTILLA");
        System.out.println("---------------------------");
        System.out.println("Introduzca función");
        String funcion = teclado.nextLine();
        System.out.println("Incremento salarial: ");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaupdate
                = "update plantilla set salario = salario + ? where upper(funcion)=upper(?)";
        PreparedStatement pst = cn.prepareStatement(consultaupdate);
        pst.setInt(1, incremento);
        pst.setString(2, funcion);
        int modificados = pst.executeUpdate();
        System.out.println("Empleados modificados: " + modificados);
        String consultaselect
                = "select * from plantilla where upper(funcion)=upper(?)";
        //REUTILIZAMOS EL PREPAREDSTATEMENT
        pst = cn.prepareStatement(consultaselect);
        pst.setString(1, funcion);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String ape = rs.getString("APELLIDO");
            String fun = rs.getString("FUNCION");
            String sal = rs.getString("SALARIO");
            System.out.println(ape + " -- " + fun + " -- " + sal);
        }
        rs.close();
        cn.close();
        System.out.println("Fin de programa");
    }
}
