package jdbc;

//<editor-fold defaultstate="collapsed" desc="PAQUETE">
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

/*create or replace package paquetemartes
as
      procedure incrementarsalarios
        (p_oficio emp.oficio%type
        , p_salario emp.salario%type);
end paquetemartes;


create or replace package body paquetemartes
as
      procedure incrementarsalarios
        (p_oficio emp.oficio%type
        , p_salario emp.salario%type)
        as
      begin
        update emp set salario = salario + p_salario
        where lower(oficio) = lower(p_oficio);
        commit;
      end;
end paquetemartes; */
//</editor-fold>
public class Class16SubirPaqueteSalarios {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un oficio");
        String oficio = teclado.nextLine();
        System.out.println("Incremento salarial");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultapaquete = "{ call paquetemartes.incrementarsalarios(?,?) }";
        CallableStatement cst = cn.prepareCall(consultapaquete);
        cst.setString(1, oficio);
        cst.setInt(2, incremento);
        cst.executeUpdate();
        String consultaselect
                = "select * from emp where lower(oficio) = lower(?)";
        PreparedStatement pst = cn.prepareStatement(consultaselect);
        pst.setString(1, oficio);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String ape = rs.getString("APELLIDO");
            String sal = rs.getString("SALARIO");
            System.out.println(ape + " -- " + sal);
        }
        rs.close();
        cn.close();
    }
}
