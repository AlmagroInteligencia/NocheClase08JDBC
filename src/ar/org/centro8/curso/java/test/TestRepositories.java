package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connector.Connector;
import ar.org.centro8.curso.java.entities.Alumno;
import ar.org.centro8.curso.java.repositories.AlumnoR;

public class TestRepositories {
    
    public static void main(String[] args) {
        
        AlumnoR ar=new AlumnoR(Connector.getConnection());
        Alumno alumno=new Alumno("Robin","Hood",33,1);
        ar.save(alumno); // El alumno creado se fue pa la base de datos
        System.out.println(alumno);
        
    }
    
}
