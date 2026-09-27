package proyecto_almacen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionBD {

    private Connection conn;
    private Statement st;
    private ResultSet rs;

    // Ajusta la URL, puerto, nombre de tu base de datos, usuario y contraseña según tu configuración local
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/bd_almacen?useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String clave = ""; 

    public conexionBD() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // ==========================================
    // MÉTODOS PARA EL MANTENIMIENTO DE EMPRESA
    // ==========================================

    public ResultSet listarEmpresas() throws SQLException {
        st = conn.createStatement();
        rs = st.executeQuery("SELECT CodigoEmpresa, NombreEmpresa FROM empresa WHERE Estado = 1");
        return rs;
    }

    public void insertarEmpresa(String nombre) throws SQLException {
        String sql = "INSERT INTO empresa (NombreEmpresa, Estado) VALUES (?, 1)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
        ps.close();
    }

    public void modificarEmpresa(int codigo, String nombre) throws SQLException {
        String sql = "UPDATE empresa SET NombreEmpresa = ? WHERE CodigoEmpresa = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setInt(2, codigo);
        ps.executeUpdate();
        ps.close();
    }

    public void desactivarEmpresa(int codigo) throws SQLException {
        String sql = "UPDATE empresa SET Estado = 0 WHERE CodigoEmpresa = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet buscarEmpresas(String nombre) throws SQLException {
        String sql = "SELECT CodigoEmpresa, NombreEmpresa FROM empresa WHERE NombreEmpresa LIKE ? AND Estado = 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + nombre + "%");
        return ps.executeQuery();
    }

    // ==========================================
    // MÉTODOS PARA EL MANTENIMIENTO DE FACULTAD
    // ==========================================

    public ResultSet listarFacultades() throws SQLException {
        st = conn.createStatement();
        rs = st.executeQuery("SELECT CodigoFacultad, NombreFacultad FROM facultad WHERE Estado = 1");
        return rs;
    }

    public void insertarFacultad(String nombre) throws SQLException {
        String sql = "INSERT INTO facultad (NombreFacultad, Estado) VALUES (?, 1)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
        ps.close();
    }

    public void modificarFacultad(int codigo, String nombre) throws SQLException {
        String sql = "UPDATE facultad SET NombreFacultad = ? WHERE CodigoFacultad = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setInt(2, codigo);
        ps.executeUpdate();
        ps.close();
    }

    public void desactivarFacultad(int codigo) throws SQLException {
        String sql = "UPDATE facultad SET Estado = 0 WHERE CodigoFacultad = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet buscarFacultades(String nombre) throws SQLException {
        String sql = "SELECT CodigoFacultad, NombreFacultad FROM facultad WHERE NombreFacultad LIKE ? AND Estado = 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + nombre + "%");
        return ps.executeQuery();
    }

    // ==========================================
    // CIERRE DE CONEXIÓN
    // ==========================================

    public void cerrarConexion() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
            System.out.println("Conexión cerrada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}