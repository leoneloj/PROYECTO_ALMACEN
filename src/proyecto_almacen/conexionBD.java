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

    //                      MANTENIMIENTO A LA TABLA AREA
    
    // 1. Listar todas las áreas activas desde la vista view_area
    public ResultSet listarAreas() throws SQLException {
        String sql = "SELECT id_area, nombre_area FROM view_area";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    // 2. Buscar áreas por coincidencia de nombre
    public ResultSet buscarAreas(String nombre) throws SQLException {
        String sql = "SELECT id_area, nombre_area FROM view_area WHERE nombre_area LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + nombre + "%");
        rs = pst.executeQuery();
        return rs;
    }

    // 3. Insertar una nueva área
    public void insertarArea(String nombreArea) throws SQLException {
        String sql = "INSERT INTO area (nombre_area) VALUES (?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombreArea);
            pst.executeUpdate();
            System.out.println("Área insertada correctamente...!");
        }
    }

    // 4. Modificar una área existente
    public void modificarArea(int codigo, String nuevoNombre) throws SQLException {
        String sql = "UPDATE area SET nombre_area = ? WHERE id_area = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nuevoNombre);
            pst.setInt(2, codigo);
            pst.executeUpdate();
            System.out.println("Área modificada correctamente...!");
        }
    }

    // 5. Dar de baja / eliminar área
    public void desactivarArea(int codigo) throws SQLException {
        String sql = "DELETE FROM area WHERE id_area = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            System.out.println("Área eliminada/desactivada correctamente...!");
        }
    }

    //                      MANTENIMIENTO A LA TABLA CARGO
    
    // 1. Listar todos los cargos desde la vista view_cargo
    public ResultSet listarCargos() throws SQLException {
        String sql = "SELECT id_cargo, nombre_cargo FROM view_cargo";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    // 2. Buscar cargos por coincidencia de nombre
    public ResultSet buscarCargos(String nombre) throws SQLException {
        String sql = "SELECT id_cargo, nombre_cargo FROM view_cargo WHERE nombre_cargo LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + nombre + "%");
        rs = pst.executeQuery();
        return rs;
    }

    // 3. Insertar un nuevo cargo
    public void insertarCargo(String nombreCargo) throws SQLException {
        String sql = "INSERT INTO cargo (nombre_cargo) VALUES (?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombreCargo);
            pst.executeUpdate();
            System.out.println("Cargo insertado correctamente...!");
        }
    }

    // 4. Modificar un cargo existente
    public void modificarCargo(int codigo, String nuevoNombre) throws SQLException {
        String sql = "UPDATE cargo SET nombre_cargo = ? WHERE id_cargo = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nuevoNombre);
            pst.setInt(2, codigo);
            pst.executeUpdate();
            System.out.println("Cargo modificado correctamente...!");
        }
    }

    // 5. Dar de baja / eliminar cargo
    public void desactivarCargo(int codigo) throws SQLException {
        String sql = "DELETE FROM cargo WHERE id_cargo = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            System.out.println("Cargo eliminado/desactivado correctamente...!");
        }
    }

    // Método para cerrar recursos
    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("Conexion cerrada exitosamente.");
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
