package jdbc;

//<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTO OUT">
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

/*
create or replace procedure updatesalarioempleado
(p_empno emp.emp_no%type
, p_salario emp.salario%type
, p_comision emp.comision%type
, p_total out int)
as
begin
  update emp set salario = salario + p_salario
  , comision = comision + p_comision
  where emp_no = p_empno;
  commit;
  select salario + comision into p_total
  from emp
  where emp_no=p_empno;
end;
 */
//</editor-fold>
public class Class17ProcedimientoSalida {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP PROCEDIMIENTOS SALIDA");
        System.out.println("-------------------------");
        System.out.println("Número de empleado");
        String datoemp = teclado.nextLine();
        int empno = Integer.parseInt(datoemp);
        System.out.println("Incremento salarial");
        String datosalario = teclado.nextLine();
        int salario = Integer.parseInt(datosalario);
        System.out.println("Incremento comisión");
        String datocomision = teclado.nextLine();
        int comision = Integer.parseInt(datocomision);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc
                = "{ call updatesalarioempleado (?,?,?,?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, empno);
        cst.setInt(2, salario);
        cst.setInt(3, comision);
        //PARA INDICAR LOS TIPOS TENEMOS UNA ENUMERACION QUE
        //NOS DEVUELVE EL TIPO DE DATO POR NOMBRE
        //UNA ENUMERACION NOS OFRECE TEXTOS QUE SIGNIFICAN NUMEROS
        //EJEMPLO:
        //  Brujula.Norte  --> 1
        //  Brujula.Sur  --> 2
        //1.- Norte
        //2.- Sur
        //3.- Oeste
        //4.- Este
        //  .setDireccion( Brujula.Norte  )
        //LA ENUMERACION DE TIPOS ES java.sql.Types.TIPOS
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        //AHORA EJECUTAMOS EL PROCEDIMIENTO
        cst.executeUpdate();
        //RECUPERAMOS EL VALOR DEL PARAMETRO DE SALIDA
        int total = cst.getInt(4);
        System.out.println("La suma del salario y comisión de "
                + empno + " es " + total);
        cn.close();
        System.out.println("Fin de programa");
    }
}
