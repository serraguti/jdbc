package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class07SalarioDepartamentos {

    //INCREMENTAMOS EL SALARIO POR DEPARTAMENTO
    //MOSTRAREMOS LOS DATOS DE LOS EMPLEADOS INCREMENTADOS
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca número departamento");
        String datodept = teclado.nextLine();
        int deptno = Integer.parseInt(datodept);
        System.out.println("Introduzca incremento");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //update emp set salario = salario + 1 where dept_no = 10
        String consulta
                = "update emp set salario = salario + " + incremento
                + " where dept_no = " + deptno;
        int afectados = st.executeUpdate(consulta);
        System.out.println("Empleados modificados: " + afectados);
        System.out.println("-------------------------------");
        //LEEMOS LOS REGISTROS
        //select apellido, salario from emp where dept_no=10
        String consultaselect
                = "select apellido, salario from emp where dept_no=" + deptno;
        ResultSet rs = st.executeQuery(consultaselect);
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String salario = rs.getString("SALARIO");
            System.out.println(apellido + " -- " + salario);
        }
        rs.close();
        cn.close();
        System.out.println("Fin de programa");
    }
}
