package ar.org.centro8.curso.java.connector;  // Importar este

// Esto es lo que se cambia en las distintas compus para que ande.

import java.sql.Connection;
import java.sql.DriverManager;


public class Connector {
    
    // Vamos a usar el Patrón de Diseño SINGLETON
    
    private static String driver="com.mysql.jdbc.Driver";
    private static String vendor="mysql";  // vendor es fabricante ("vendedor").
    private static String server="localhost";   // Esto se suele cambiar
    private static String port="3306";   // Este es por defecto
    private static String db="colegio";  // La Base de Datos que se usa
    private static String user="root";
    private static String pass="root";   // Varía siempre obviamente
    
    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+db;
    
    /*
    Lo de arriba es el String de conexión.
    
    Vos modificás los atributos de más arriba, y la url se modifica sola.
    */
    
    private static Connection conn=null;
    
    private Connector(){} // Constructor privado
    
    public static synchronized Connection getConnection(){ // Se crea solo un objeto a la vez
        
        if(conn==null){   // Aseguramos un solo objeto de conexión (Esto es Singleton)
            try {
                Class.forName(driver);   // Registra el driver
                conn=DriverManager.getConnection(url, user, pass); 
                // Arriba devuelve el objeto conexión (del driver registrado anteriormente)
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return conn;
    }
    
}
