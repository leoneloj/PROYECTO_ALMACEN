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

   // =========================================================================
    //                     MANTENIMIENTO A LA TABLA EMPRESA
    // =========================================================================

    public ResultSet listarEmpresas() throws SQLException {
        String sql = "SELECT id_empresa, ruc, razon_social FROM view_empresa ORDER BY id_empresa ASC";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet buscarEmpresas(String criterio) throws SQLException {
        String sql = "SELECT id_empresa, ruc, razon_social FROM view_empresa WHERE razon_social LIKE ? OR ruc LIKE ? ORDER BY id_empresa ASC";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + criterio + "%");
        pst.setString(2, "%" + criterio + "%");
        rs = pst.executeQuery();
        return rs;
    }

    
   // Insertar empresa recibiendo solo el nombre/razón social desde el formulario
    public void insertarEmpresa(String razonSocial) throws SQLException {
        String sql = "INSERT INTO empresa (ruc, razon_social) VALUES (?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "00000000000"); // RUC temporal por defecto
            pst.setString(2, razonSocial);
            pst.executeUpdate();
            System.out.println("Empresa registrada correctamente...!");
        }
    }

    // Modificar empresa recibiendo el código y el nuevo nombre
    public void modificarEmpresa(int codigo, String razonSocial) throws SQLException {
        String sql = "UPDATE empresa SET razon_social = ? WHERE id_empresa = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, razonSocial);
            pst.setInt(2, codigo);
            pst.executeUpdate();
            System.out.println("Empresa modificada correctamente...!");
        }
    }

    public void desactivarEmpresa(int codigo) throws SQLException {
        String sql = "DELETE FROM empresa WHERE id_empresa = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            System.out.println("Empresa eliminada/desactivada correctamente...!");
        }
    }
    
    
    //                      MANTENIMIENTO A LA TABLA AREA
    
    
    
    /* 1. Método para listar todos los clientes activos usando la vista */
public ResultSet listarArea() throws SQLException {
    String sql = "SELECT * FROM vw_area_activa";
    st = conn.createStatement();
    rs = st.executeQuery(sql);
    return rs;
}

/* 2. Método para buscar clientes por coincidencia de nombres */
public ResultSet buscarArea(String nombre) throws SQLException {
    String sql = "{CALL sp_area_buscar(?)}";
    CallableStatement cs = conn.prepareCall(sql);
    cs.setString(1, nombre);
    rs = cs.executeQuery();
    return rs;
}

    /* 3. Método para insertar un nuevo cliente */
public void insertarArea(
        String nombreArea,
        String descripcion) throws SQLException {

    String sql = "{CALL sp_area_insertar(?, ?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, nombreArea);
        cs.setString(2, descripcion);
        cs.execute();

        System.out.println("Area insertado correctamente...!");
    }
}

    /* 4. Método para modificar un cliente existente */
public void modificarArea(
        int idArea,
        String nuevoNombre,
        String nuevaDescripcion) throws SQLException {

    String sql = "{CALL sp_area_actualizar(?,?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idArea);
        cs.setString(2, nuevoNombre);
        cs.setString(3, nuevaDescripcion);
        cs.executeUpdate();

        System.out.println("Area modificado correctamente...!");
    }
}

    /* 5. Dar de baja / eliminar área
    public void desactivarArea(int codigo) throws SQLException {
        String sql = "DELETE FROM area WHERE id_area = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            System.out.println("Área eliminada/desactivada correctamente...!");
        }
    }*/

    // =========================================================================
    //                      MANTENIMIENTO A LA TABLA CARGO
    // =========================================================================

    // 1. Listar todos los cargos garantizando el orden por id_cargo ASC
    public ResultSet listarCargos() throws SQLException {
        String sql = "SELECT id_cargo, nombre_cargo FROM view_cargo ORDER BY id_cargo ASC";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    // 2. Buscar cargos por coincidencia de nombre ordenados por id_cargo ASC
    public ResultSet buscarCargos(String nombre) throws SQLException {
        String sql = "SELECT id_cargo, nombre_cargo FROM view_cargo WHERE nombre_cargo LIKE ? ORDER BY id_cargo ASC";
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
