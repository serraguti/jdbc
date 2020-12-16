package jdbc;

//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTO OUT">
import com.mysql.cj.jdbc.Driver;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

/*
create or replace procedure eliminarplantilla
(p_apellido plantilla.apellido%type
, p_personas out int
, p_suma out int)
as
begin
  delete from plantilla
  where lower(apellido)=lower(p_apellido);
  commit;
  select count(empleado_no), sum(salario)
  into p_personas, p_suma
  from plantilla;
end;
 */
//</editor-fold>
public class Class18EliminarPlantilla {
//'amigo r.'

//DELIMITER $$
//CREATE PROCEDURE eliminarplantilla (IN p_apellido varchar(30)
//, OUT personas int, OUT suma int)
//BEGIN
//	delete from plantilla
//	where lower(apellido)=lower(p_apellido);
//	select count(empleado_no), sum(salario)
//	into personas, suma
//	from plantilla;
//END$$
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP PROCEDURE OUT PLANTILLA");
        System.out.println("---------------------------");
        System.out.println("Introduzca apellido");
        String apellido = teclado.nextLine();
        /*
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
         */
        DriverManager.registerDriver(new Driver());
        String cadenaconexion = "jdbc:mysql://localhost:3306/hospital";
        Connection cn = DriverManager.getConnection(
                cadenaconexion,
                "root", "mysql");
        String consultaproc = "{ call eliminarplantilla (?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, apellido);
        cst.registerOutParameter(2, java.sql.Types.INTEGER);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.executeUpdate();
        int personas = cst.getInt(2);
        int suma = cst.getInt(3);
        System.out.println("Quedan " + personas + " en la plantilla y todos cobra "
                + suma);
        cn.close();
        System.out.println("Fin de programa");
    }
}
