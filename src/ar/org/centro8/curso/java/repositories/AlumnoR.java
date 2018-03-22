package ar.org.centro8.curso.java.repositories;

import ar.org.centro8.curso.java.entities.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoR {
    
    private Connection conn;

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
    
    public void remove(Alumno alumno){  // para el metodo delete de sql
        if(alumno==null) return;
        try {
            conn.createStatement().execute("delete from alumnos where id="+alumno.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update(Alumno alumno){
        if(alumno==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "update alumnos set nombre=?, apellido=?, edad=?, idCurso=? where id=?"
            );
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.setInt(5, alumno.getId());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private List<Alumno> getByFiltro(String filtro){ // Esto rompería con el patrón DAO
        
        List<Alumno> lista=new ArrayList();
        try {
            ResultSet rs=conn.createStatement().executeQuery(
                    "select * from alumnos where "+filtro
            );
            while(rs.next()){ // si hay un próximo registro te devuelve True
                lista.add(new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("edad"),
                    rs.getInt("idCurso")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
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
