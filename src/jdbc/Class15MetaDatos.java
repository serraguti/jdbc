package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleDriver;

public class Class15MetaDatos {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select * from emp");
        ResultSetMetaData metadata = rs.getMetaData();
        int numerocolumnas = metadata.getColumnCount();
        System.out.println("TODAS LAS COLUMNAS DE EMP");
        System.out.println("-------------------------");
        for (int i = 1; i <= numerocolumnas; i++) {
            String nombrecolumna = metadata.getColumnName(i);
            String tipo = metadata.getColumnTypeName(i);
            System.out.println(i + ".- " + nombrecolumna + " --- " + tipo);
        }
        rs.close();
        cn.close();
    }
}
