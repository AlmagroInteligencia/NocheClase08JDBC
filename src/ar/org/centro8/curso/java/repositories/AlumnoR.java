package ar.org.centro8.curso.java.repositories;

import ar.org.centro8.curso.java.entities.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AlumnoR {
    
    Connection conn;

    public AlumnoR(Connection conn) {
        this.conn = conn;
    }
    
    public void save(Alumno alumno){ // Recibe un objeto alumno, y lo guarda en la base
        try {
            PreparedStatement ps=conn.prepareStatement(
                    "insert into alumnos (nombre,apellido,edad,idCurso) values (?,?,?,?)",
                    // Arriba el preparedStatement asigna dinamicamente los valores
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys(); //  El select retorna en ResultSet
            if(rs.next()) alumno.setId(rs.getInt(1)); // El rs.next()pregunta si hay registro
            
        } catch (Exception e) {
            System.out.println(e);
        }
    } // end save
    
    private List<Alumno> getByFiltro(String filtro){
        return null;
    } // end getByFiltro
    
    public List<Alumno> getAll(){
        return getByFiltro("1=1"); // Muestra todos los registros
    } // end getAll
    
    public Alumno getById(int id){
        List<Alumno> lista = getByFiltro("id="+id);
        return lista.isEmpty()? null:lista.get(0);
    }
    
    public List<Alumno> getByApellido(String apellido){
        return getByFiltro("apellido='"+apellido+"'");
    }
    
    public List<Alumno> getLikeApellido(String apellido){
        return getByFiltro("apellido like '%"+apellido+"%'");
    }
    
    public List<Alumno> getByCurso(int curso){
        return getByFiltro("idCurso="+curso);
    }
    
} // end class
