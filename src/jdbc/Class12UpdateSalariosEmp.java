package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

/*
	Vamos a incrementar el salario de los empleados
por su departamento.
	Pediremos el NOMBRE del departamento
	Actualizaremos los datos de los empleados del departamento.
Empleados modificados: 6
	Preguntaremos de nuevo si queremos modificar.
NO ES NECESARIO MOSTRAR LOS REGISTROS (opcional)
Versión 2: Mostramos los empleados modificados
 */
public class Class12UpdateSalariosEmp {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP INCREMENTAR SALARIO DEPT");
        System.out.println("----------------------------");
        System.out.println("Introduzca nombre de departamento");
        String nombre = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaselect
                = "select dept_no from dept where lower(dnombre)=lower(?)";
        PreparedStatement pst = cn.prepareStatement(consultaselect);
        pst.setString(1, nombre);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == true) {
            //EXISTE DEPARTAMENTO
            int deptno = Integer.parseInt(rs.getString("DEPT_NO"));
            //YA HEMOS ACABADO DE LEER
            rs.close();
            System.out.println("Introduzca un incremento");
            String datoincremento = teclado.nextLine();
            int incremento = Integer.parseInt(datoincremento);
            //HACEMOS EL UPDATE
            String consultaupdate
                    = "update emp set salario = salario + ? where dept_no=?";
            pst = cn.prepareStatement(consultaupdate);
            pst.setInt(1, incremento);
            pst.setInt(2, deptno);
            int modificados = pst.executeUpdate();
            System.out.println("Empleados modificados " + modificados);
        } else {
            //NO EXISTE DEPARTAMENTO
            System.out.println("El departamento " + nombre + " NO EXISTE");
            rs.close();
        }
        cn.close();
    }
}
