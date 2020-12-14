package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class08InsertarHospital {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP INSERTAR HOSPITAL");
        System.out.println("---------------------");
        System.out.println("Introduzca código hospital");
        String datocodigo = teclado.nextLine();
        int hospitalcod = Integer.parseInt(datocodigo);
        System.out.println("Introduzca nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Introduzca dirección ");
        String direccion = teclado.nextLine();
        System.out.println("Introduzca teléfono");
        String telefono = teclado.nextLine();
        System.out.println("Número de camas");
        String datocamas = teclado.nextLine();
        int numcamas = Integer.parseInt(datocamas);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //insert into hospital values (23, 'nombre', 'direccion', 'tlf', 55)
        String consulta
                = "insert into hospital values (" + hospitalcod + ", '"
                + nombre + "', '" + direccion + "', '" + telefono
                + "', " + numcamas + ")";
        int insertados = st.executeUpdate(consulta);
        System.out.println("Hospitales insertados: " + insertados);
        String consultaselect = "select * from hospital";
        ResultSet rs = st.executeQuery(consultaselect);

        System.out.println("HOSPITALES");
        while (rs.next()) {
            String name = rs.getString("NOMBRE");
            String dir = rs.getString("DIRECCION");
            System.out.println(name + " -- " + dir);
        }
        rs.close();
        cn.close();
    }
}
