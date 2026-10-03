package proyecto_almacen;
import java.sql.*;
import javax.swing.JOptionPane;

public class conexionBD_gabriel {

    /* Variables de instancia para la conexión y consultas */
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    /* Parámetros de conexión a MySQL / XAMPP */
    private static final String URL = "jdbc:mysql://localhost:3306/bdalmacen?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /* Constructor: Conecta con la base de datos bdalmacen */
    public conexionBD_gabriel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida correctamente con bdalmacen.");
        } catch (ClassNotFoundException e) {
            System.out.println("X Error: Driver de MySQL no encontrado.");
        } catch (SQLException e) {
            System.out.println("X Error al conectar con bdalmacen: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }


  
    // Método para cerrar recursos
    public void cerrarConexion() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
            System.out.println("Conexión cerrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    /* Método main para probar la conexión directamente desde esta clase */
    public static void main(String[] args) {
    conexionBD_gabriel prueba = new conexionBD_gabriel();
    if (prueba.getConnection() != null) {
        System.out.println("¡Prueba exitosa! Conectado a bdalmacen.");
    }
}
}
