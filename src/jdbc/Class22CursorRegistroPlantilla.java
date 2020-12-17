package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.internal.OracleTypes;

/*
create or replace package pqtipos
is
    --declaramos el registro para la consulta
    --personalizada de la plantilla
    type registroplantilla is record (
         apellido plantilla.apellido%type
         , nombre hospital.nombre%type
         , funcion plantilla.funcion%type
         , turno varchar2(30)
    );
    type cursorplantillahosp is ref cursor return registroplantilla;
    --TENEMOS QUE DECLARAR UN CURSOR CON
    --EL TIPO DE CONSULTA QUE DEVOLVERA
    type cursorempleados is ref cursor return emp%rowtype;
    type cursordepartamentos is ref cursor return dept%rowtype;
end pqtipos;

create or replace procedure datosplantillahospital
(consultaplantilla OUT pqtipos.cursorplantillahosp)
as
begin
  open consultaplantilla for
  select plantilla.apellido, hospital.nombre
  , plantilla.funcion
  , decode(turno, 'M', 'MAÑANA', 'T', 'TARDE', 'NOCHE')
  as turno
  from plantilla
  inner join hospital
  on plantilla.hospital_cod = hospital.hospital_cod;
end;
 */
public class Class22CursorRegistroPlantilla {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc
                = "{ call datosplantillahospital (?) }";
        CallableStatement cst
                = cn.prepareCall(consultaproc);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ape = rs.getString("APELLIDO");
            String nom = rs.getString("NOMBRE");
            String fun = rs.getString("FUNCION");
            String turno = rs.getString("TURNO");
            System.out.println(ape + " -- " + nom + " -- " + fun + " -- " + turno);
        }
        rs.close();
        cn.close();
        System.out.println("Fin de programa");
    }
}
