package jdbc;

import java.sql.*;
import oracle.jdbc.OracleDriver;

public class Class04SelectDepartamentos {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        String consulta = "select * from dept";
        ResultSet rs = st.executeQuery(consulta);
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
