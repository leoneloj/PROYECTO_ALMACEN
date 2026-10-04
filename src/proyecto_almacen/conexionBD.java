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
    //                 MANTENIMIENTO A LA TABLA EMPRESA
    // =========================================================================

    /* 1. Método para listar todas las empresas activas usando la vista */
    public ResultSet listarEmpresa() throws SQLException {
        String sql = "SELECT * FROM vw_empresa_activa";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /* 2. Método para buscar empresas por coincidencia de nombre o RUC */
    public ResultSet buscarEmpresa(String criterio) throws SQLException {
        String sql = "{CALL sp_empresa_buscar(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, criterio);
        rs = cs.executeQuery();
        return rs;
    }

    /* 3. Método para insertar una nueva empresa */
    public void insertarEmpresa(
            String razonSocial,
            String ruc,
            String telefono,
            String correo) throws SQLException {

        String sql = "{CALL sp_empresa_insertar(?, ?, ?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, razonSocial);
            cs.setString(2, ruc);
            cs.setString(3, telefono);
            cs.setString(4, correo);
            cs.execute();

            System.out.println("Empresa insertada correctamente...!");
        }
    }

    /* 4. Método para modificar una empresa existente */
    public void modificarEmpresa(
            int idEmpresa,
            String nuevaRazonSocial,
            String nuevoRuc,
            String nuevoTelefono,
            String nuevoCorreo) throws SQLException {

        String sql = "{CALL sp_empresa_actualizar(?,?,?,?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idEmpresa);
            cs.setString(2, nuevaRazonSocial);
            cs.setString(3, nuevoRuc);
            cs.setString(4, nuevoTelefono);
            cs.setString(5, nuevoCorreo);
            cs.executeUpdate();

            System.out.println("Empresa modificada correctamente...!");
        }
    }
    
    
    // =========================================================================
    //                      MANTENIMIENTO A LA TABLA AREA
    // =========================================================================

    
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

    String sql = "{CALL sp_area_actualizar(?,?,?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idArea);
        cs.setString(2, nuevoNombre);
        cs.setString(3, nuevaDescripcion);
        cs.executeUpdate();

        System.out.println("Area modificado correctamente...!");
    }
}

  

    // =========================================================================
    //                    MANTENIMIENTO A LA TABLA CARGO
    // =========================================================================

    /* 1. Método para listar todos los cargos activos usando la vista */
    public ResultSet listarCargo() throws SQLException {
        String sql = "SELECT * FROM vw_cargo_activo";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /* 2. Método para buscar cargos por coincidencia de nombres */
    public ResultSet buscarCargo(String nombre) throws SQLException {
        String sql = "{CALL sp_cargo_buscar(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, nombre);
        rs = cs.executeQuery();
        return rs;
    }

    /* 3. Método para insertar un nuevo cargo */
    public void insertarCargo(String nombreCargo) throws SQLException {
        String sql = "{CALL sp_cargo_insertar(?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, nombreCargo);
            cs.execute();
            System.out.println("Cargo insertado correctamente...!");
        }
    }

    /* 4. Método para modificar un cargo existente */
    public void modificarCargo(int idCargo, String nuevoNombre) throws SQLException {
        String sql = "{CALL sp_cargo_actualizar(?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCargo);
            cs.setString(2, nuevoNombre);
            cs.executeUpdate();
            System.out.println("Cargo modificado correctamente...!");
        }
    }

    /* 5. Método para dar de baja / desactivar un cargo */
    public void darDeBajaCargo(int idCargo) throws SQLException {
        String sql = "{CALL sp_cargo_desactivar(?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCargo);
            cs.executeUpdate();
            System.out.println("Cargo dado de baja correctamente...!");
        }
    }
   /* 6. Método para listar cargos de baja (Usando número para inactivo/baja) */
    public ResultSet listarCargosDeBaja() throws SQLException {
        // Cambia el '0' por el número que use tu base de datos para indicar baja o inactivo
        String sql = "SELECT * FROM vw_cargo_inactivos"; 
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /* 7. Método para reactivar un cargo (Usando número para activo) */
    public void reactivarCargo(int idCargo) throws SQLException {
        // Cambia el '1' por el número que use tu base de datos para indicar activo
        String sql = "UPDATE cargo SET estado_cargo = 1 WHERE id_cargo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCargo);
            ps.executeUpdate();
            System.out.println("Cargo reactivado correctamente...!");
        }
    }
    
     // =========================================================================
    //                    MANTENIMIENTO A LA TABLA SUCURSAL
    // =========================================================================
   /*1. Para cargar las empresas al JComboBox */
    public ResultSet combobox_ListarEmpresas() throws SQLException {
        String sql = "SELECT razon_social FROM empresa";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*2. Obtiene el ID dado el nombre o razón social de la empresa */
    public int obtenerCodigoEmpresa(String razonSocial) throws SQLException {
        String sql = "SELECT id_empresa FROM empresa WHERE razon_social = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, razonSocial);
        ResultSet rsAux = ps.executeQuery();
        if (rsAux.next()) {
            return rsAux.getInt("id_empresa");
        } else {
            return -1;
        }
    }

    /*3. Listar sucursales en la tabla: Activas con su respectiva empresa usando la vista estándar */
    public ResultSet verSucursales() throws SQLException {
        String sql = "SELECT * FROM vw_sucursal_activa";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*4. Buscar sucursal por nombre usando el procedimiento almacenado */
    public ResultSet buscarSucursal(String criterio) throws SQLException {
        String sql = "{CALL sp_sucursal_buscar(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, criterio);
        rs = cs.executeQuery();
        return rs;
    }

    /*5. Insertar una nueva sucursal llamando al procedimiento almacenado */
    public void insertarSucursal(int idEmpresa, String nombre, String direccion, String telefono) throws SQLException {
        String sql = "{CALL sp_sucursal_insertar(?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idEmpresa);
            cs.setString(2, nombre);
            cs.setString(3, direccion);
            cs.setString(4, telefono);
            cs.execute();
        }
    }

    /*6. Modificar o actualizar los datos de una sucursal */
    public void modificarSucursal(int idSucursal, int idEmpresa, String nuevoNombre, String nuevaDireccion, String nuevoTelefono) throws SQLException {
        String sql = "{CALL sp_sucursal_actualizar(?, ?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idSucursal);
            cs.setInt(2, idEmpresa);
            cs.setString(3, nuevoNombre);
            cs.setString(4, nuevaDireccion);
            cs.setString(5, nuevoTelefono);
            cs.executeUpdate();
        }
    }
    
    // =========================================================================
    //                    MANTENIMIENTO A LA TABLA SUBCATEGORIA
    // =========================================================================
   /*1. Para cargar las categorías al JComboBox */
    public ResultSet combobox_ListarCategorias() throws SQLException {
        String sql = "SELECT nombre FROM categoria";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*2. Obtiene el ID dado el nombre de la categoría */
    public int obtenerCodigoCategoria(String nombreCategoria) throws SQLException {
        String sql = "SELECT id_categoria FROM categoria WHERE nombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombreCategoria);
        ResultSet rsAux = ps.executeQuery();
        if (rsAux.next()) {
            return rsAux.getInt("id_categoria");
        } else {
            return -1;
        }
    }

    /*3. Listar subcategorías en la tabla: Activas con su respectiva categoría usando la vista estándar */
    public ResultSet verSubcategorias() throws SQLException {
        String sql = "SELECT * FROM vw_subcategoria_activa";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*4. Buscar subcategoría por nombre usando el procedimiento almacenado */
    public ResultSet buscarSubcategoria(String criterio) throws SQLException {
        String sql = "{CALL sp_subcategoria_buscar(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, criterio);
        rs = cs.executeQuery();
        return rs;
    }

    /*5. Insertar una nueva subcategoría llamando al procedimiento almacenado */
    public void insertarSubcategoria(int idCategoria, String nombreSubcategoria, String descripcion) throws SQLException {
        String sql = "{CALL sp_subcategoria_insertar(?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCategoria);
            cs.setString(2, nombreSubcategoria);
            cs.setString(3, descripcion);
            cs.execute();
        }
    }

    /*6. Modificar o actualizar los datos de una subcategoría */
    public void modificarSubcategoria(int idSubcategoria, int idCategoria, String nuevoNombre, String nuevaDescripcion) throws SQLException {
        String sql = "{CALL sp_subcategoria_actualizar(?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idSubcategoria);
            cs.setInt(2, idCategoria);
            cs.setString(3, nuevoNombre);
            cs.setString(4, nuevaDescripcion);
            cs.executeUpdate();
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
