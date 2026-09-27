package proyecto_almacen;

import java.sql.*;

public class conexionBD {

    /*Variables de instancia para la conexion y ejecucion de consultas*/
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    /*Parametros de conexion (ajusta según tu entorno)*/
    private static final String URL = "jdbc:mysql://localhost:3306/universidad202620";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /*Contructor: se ejecuta al crear el objeto y conecta con la base de datos ...3 Lines*/
    public conexionBD() {
        try {
            /*Crgar el driver de MYSQL (opcional en versiones recientes)*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*Establecer conexion con la BD*/
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("X Error: Driver de MYSQL no encontrado. ");
        } catch (SQLException e) {
            System.out.println("X Error al conectar la base de datos. ");
        }
    }/*FINALIZAR*/
 /*Método: Obtiene el listado de escuelas profesionales (con JOIN a facultad y escuela profesional) */
    public ResultSet listarEscuelasProfesionales() throws SQLException {
        String sql = "select*from view_escuelasprofesionales";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*Metodo para listar los cursos*/
    public ResultSet listarCursos() throws SQLException {
        String sql = "select*from";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /*Metodo para listar los usuarios*/
    public ResultSet listarUsuarios() throws SQLException {
        String sql = "select*from";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    /* MANTENIMIENTO A LA TABLA FACULTAD */
    // 1. Método para listar todas las facultades activas usando la vista
    public ResultSet listarFacultades() throws SQLException {

        String sql = "SELECT * FROM vw_facultad_activa";
        /* Llamamos a la vista estandarizada */
        st = conn.createStatement();
        /* Creamos el statement */
        rs = st.executeQuery(sql);
        /* Ejecutamos la consulta */
        return rs;
        /* Devolvemos los resultados */
    }

    // 2. Método para buscar facultades por coincidencia de nombre
    public ResultSet buscarFacultades(String nombre) throws SQLException {
        String sql = "{CALL sp_facultad_buscar(?)}";
        /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);
        /* Usamos CallableStatement para SPs */
        cs.setString(1, nombre);
        /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();
        /* Ejecutamos y obtenemos resultados */
        return rs;
    }

    // 3. Método para insertar una nueva facultad
    public void insertarFacultad(String nombreFacultad) throws SQLException {
        String sql = "{CALL sp_facultad_insertar(?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, nombreFacultad);
            cs.execute();
            System.out.println("Facultad insertada correctamente...!");
        }
    }

    // 4. Método para modificar una facultad existente
    public void modificarFacultad(int codigo, String nuevoNombre) throws SQLException {
        String sql = "{CALL sp_facultad_actualizar(?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, codigo);
            cs.setString(2, nuevoNombre);
            cs.executeUpdate();
            System.out.println("Facultad modificada correctamente...!");
        }
    }

    // 5. Método para dar de baja (desactivar) una facultad lógicamente
    public void desactivarFacultad(int codigo) throws SQLException {
        String sql = "{CALL sp_facultad_desactivar(?)}";
        // Usamos CallableStatement para mantener la consistencia con los procedimientos
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, codigo);
            cs.executeUpdate();
            System.out.println("Facultad desactivada correctamente...!");
        }
    }

    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (rs != null) {
                st.close();
            }
            if (rs != null) {
                conn.close();
            }
            System.out.println("Conexion cerrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al cerrada la conexion.");
        }
    }

    public Connection getConnection() {
        return conn;
    }
    /*Finaliza*/
}
