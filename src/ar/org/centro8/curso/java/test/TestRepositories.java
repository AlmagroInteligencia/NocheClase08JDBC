package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connector.Connector;
import ar.org.centro8.curso.java.entities.Alumno;
import ar.org.centro8.curso.java.entities.Curso;
import ar.org.centro8.curso.java.repositories.AlumnoR;
import ar.org.centro8.curso.java.repositories.CursoR;

public class TestRepositories {
    
    public static void main(String[] args) {
        
        AlumnoR ar=new AlumnoR(Connector.getConnection());
        Alumno alumno=new Alumno("Robin","Hood",33,1);
        //ar.save(alumno); // El alumno creado se fue pa la base de datos
        //System.out.println(alumno);
        
        // Probamos un cambio
        System.out.println("----- A verrrr -----");
        
        // Desde acá es Clase08
        System.out.println("-------------------------------------------------");
        
        //ar.remove(ar.getById(19));
        
        alumno=ar.getById(27);
        alumno.setApellido("Jud");
        ar.update(alumno);
        
        ar.getAll().forEach(System.out::println);
        ar.getByApellido("Perez").forEach(System.out::println);
        ar.getByApellido("Alderson").forEach(System.out::println);
        ar.getLikeApellido("Bl").forEach(System.out::println);
        
        CursoR cr=new CursoR((Connector.getConnection()));
        Curso curso=new Curso("PHP","Gomez","Jueves","Mañana");
        //cr.save(curso);
        //System.out.println(curso);
        
        cr.remove(cr.getById(2));
        
        curso=cr.getById(1);
        curso.setProfesor("Lopez");
        cr.update(curso);
        
        //cr.update(curso.setProfesor("Jorge Lopez")); // Esto no anda

        cr.getAll().forEach(System.out::println);
        //cr.getByProfesor("Gomez").forEach(System.out::println);
        
        
        
        
        
    }
    
}
