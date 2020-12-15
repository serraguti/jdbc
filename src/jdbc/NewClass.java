/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class NewClass {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DriverManager.registerDriver(new Driver());
        String cadenaconexion = "jdbc:mysql://localhost:3306/hospital";
        Connection cn = DriverManager.getConnection(
                cadenaconexion,
                "root", "mysql");
        Statement st = cn.createStatement();
        String consulta = "select * from dept";
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String nombre = rs.getString("DNOMBRE");
            System.out.println(nombre);
        }
        rs.close();
        cn.close();
    }
}
