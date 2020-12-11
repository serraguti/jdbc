package jdbc;

import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class03ConsultaAccion {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca inscripción de enfermo a eliminar");
        String dato = teclado.nextLine();
        int inscripcion = Integer.parseInt(dato);
        //REGISTRAR DRIVER ORACLE
        DriverManager.registerDriver(new OracleDriver());
        //CADENA CONEXION
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        //CONEXION
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        //CREAMOS LA SENTENCIA
        Statement st = cn.createStatement();
        //CONSULTA A REALIZAR
        String consulta = "delete from enfermo where inscripcion=" + inscripcion;
        //LAS CONSULTAS DE ACCION DEVUELVEN EL NUMERO DE REGISTROS
        //QUE HAN MODIFICADO
        //SE EJECUTAN CON EL METODO executeUpdate()
        int eliminados = st.executeUpdate(consulta);
        System.out.println("Enfermos eliminados: " + eliminados);
        cn.close();
        System.out.println("Fin de programa");
    }
}
