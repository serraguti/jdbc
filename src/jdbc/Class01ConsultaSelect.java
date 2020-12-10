package jdbc;

//PAQUETE DE ACCESO A DATOS
import java.sql.*;
import oracle.jdbc.OracleDriver;

public class Class01ConsultaSelect {

    public static void main(String[] args) throws SQLException {
        //REGISTRAR EL DRIVER DE ACCESO A ORACLE
        DriverManager.registerDriver(new OracleDriver());
        //NECESITAMOS LA CADENA DE CONEXION
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        //A PARTIR DEL DRIVER, SE RECUPERA LA CONEXION CON
        //EL METODO getConnection()
        //DEBEMOS INDICAR LA CADENA, USUARIO Y PASSWORD
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        //UNA VEZ QUE TENEMOS UNA CONEXION, NECESITAMOS UNA CONSULTA
        //LAS CONSULTAS EN JAVA NO LLEVAN PUNTO Y COMA AL FINAL
        String consulta = "select * from emp";
        //PARA EJECUTAR SENTENCIAS SE UTILIZAN OBJETOS Statement
        //UN Statement SE CREA A PARTIR DE UNA CONEXION
        Statement st = cn.createStatement();
        //DEPENDIENDO DE LA CONSULTA, NOS DEVUELVE DATOS O NO
        //SI LA CONSULTA ES UN SELECT UTILIZAMOS EL METODO
        //executeQuery() QUE NOS DEVUELVE UN ResultSet
        ResultSet rs = st.executeQuery(consulta);
        //EL METODO next() SE MUEVE FILA A FILA
        //Y DEVUELVE UN boolean false CUANDO NO HAY MAS FILAS
        //EXISTE ALGUNA FORMA DE QUE JAVA HAGA ALGO MIENTRAS TRUE???
        //LOS CURSORES SE LEEN CON BUCLE WHILE
        while (rs.next() == true) {
            //PARA RECUPERAR UN REGISTRO DEBEMOS INDICAR
            //EL METODO getTipoDato()
            String apellido = rs.getString("APELLIDO");
            String fecha = rs.getString("FECHA_ALT");
            System.out.println(apellido + " " + fecha);
        }
        //UNA VEZ QUE HEMOS TERMINADO DE EJECUTAR TODO
        //DEBEMOS LIBERAR LOS RECURSOS CERRANDO
        //EL LECTOR Y LA CONEXION
        rs.close();
        cn.close();
        System.out.println("Fin de programa");
    }
}
