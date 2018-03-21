package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connector.Connector;
import java.sql.Connection;
import java.sql.Statement;

public class TestConnector {
    
    public static void main(String[] args) throws Exception {
        
        Connection conn=Connector.getConnection();
        Statement st=conn.createStatement();
        String query="insert into alumnos (nombre,apellido,edad,idCurso) values "
                + "('Elliot','Alderson',30,1)";
        st.execute(query);
        //st.close(); // No se usa esto
        //conn.close(); // Esto tampoco
        
        //query="select * from alumnos";
        //System.out.println(st.execute(query)); 
        
        Connector.getConnection().createStatement().execute(
                "insert into alumnos (nombre,apellido,edad,idCurso) values "
                    + "('Héctor','Blanco',64,1)"
        );
        
        
        
    }
    
}
