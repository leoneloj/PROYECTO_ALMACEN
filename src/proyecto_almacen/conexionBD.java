package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexionBD {

    /* Variables de instancia para la conexión y consultas */
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    /* Parámetros de conexión a MySQL / XAMPP */
    private static final String URL = "jdbc:mysql://localhost:3306/bdalmacen?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /* Constructor: Conecta con la base de datos bdalmacen */
    public conexionBD() {
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


    //                   MANTENIMIENTO A LA TABLA EMPRESA

    // 1. Listar todas las empresas activas desde la vista view_empresa
    public ResultSet listarEmpresas() throws SQLException {
        String sql = "SELECT id_empresa, razon_social, ruc, telefono, correo FROM view_empresa";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    // 2. Buscar empresas por coincidencia de nombre / razón social
    public ResultSet buscarEmpresas(String nombre) throws SQLException {
        String sql = "SELECT id_empresa, razon_social, ruc, telefono, correo FROM view_empresa WHERE razon_social LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + nombre + "%");
        rs = pst.executeQuery();
        return rs;
    }

    // 3. Insertar una nueva empresa
    public void insertarEmpresa(String razonSocial) throws SQLException {
        String sql = "INSERT INTO empresa (razon_social) VALUES (?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, razonSocial);
            pst.executeUpdate();
            System.out.println("Empresa insertada correctamente...!");
        }
    }

    // 4. Modificar una empresa existente
    public void modificarEmpresa(int codigo, String nuevoNombre) throws SQLException {
        String sql = "UPDATE empresa SET razon_social = ? WHERE id_empresa = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nuevoNombre);
            pst.setInt(2, codigo);
            pst.executeUpdate();
            System.out.println("Empresa modificada correctamente...!");
        }
    }

    // 5. Dar de baja / eliminar empresa
    public void desactivarEmpresa(int codigo) throws SQLException {
        String sql = "DELETE FROM empresa WHERE id_empresa = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            System.out.println("Empresa eliminada/desactivada correctamente...!");
        }
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
        conexionBD prueba = new conexionBD();
        if (prueba.getConnection() != null) {
            System.out.println("¡Prueba exitosa! Conectado a bdalmacen.");
        }
    }
}