package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
//<editor-fold defaultstate="collapsed" desc="PROCEDURE OUT">


/*
create or replace procedure updatesalariodoctores
(p_nombre hospital.nombre%type
, p_incremento int
, p_suma out int
, p_media out int)
as
  v_codigo int;
begin
  --debemos guardar el codigo del hospital
  --para el update de los doctores
  select hospital_cod into v_codigo
  from hospital
  where lower(nombre)=lower(p_nombre);
  update doctor set salario = salario + p_incremento
  where hospital_cod=v_codigo;
  commit;
  select sum(salario), avg(salario) into p_suma, p_media
  from doctor
  where hospital_cod=v_codigo;
end;
 */
//</editor-fold>
public class Class19UpdateDoctores {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca nombre de hospital");
        String nombre = teclado.nextLine();
        System.out.println("Incremento salarial");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc = "{ call updatesalariodoctores (?,?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);
        cst.setInt(2, incremento);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        cst.executeUpdate();
        int suma = cst.getInt(3);
        int media = cst.getInt(4);
        System.out.println("La media del hospital " + nombre + " es "
                + media + " y su suma salarial es " + suma);
        cn.close();
    }
}
