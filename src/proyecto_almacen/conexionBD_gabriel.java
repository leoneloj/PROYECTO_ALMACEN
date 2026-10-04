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
    /*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA CATEGORIA :::::::::::::::::::*/

    // 1. Método para listar todas las categorías usando la vista estandarizada
    public ResultSet listarCategorias() throws SQLException {
        String sql = "SELECT * FROM vw_categoria_activa"; /* Llamamos a la vista estandarizada de categoria */
        st = conn.createStatement();                      /* Creamos el statement */
        rs = st.executeQuery(sql);                        /* Ejecutamos la consulta */
        return rs;                                        /* Devolvemos los resultados */
    }


    // 2. Método para buscar categorías por coincidencia de nombre
    public ResultSet buscarCategorias(String nombre) throws SQLException {
        String sql = "{CALL sp_categoria_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, nombre);                         /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar una nueva categoría
    public void insertarCategoria(String nombre, String descripcion) throws SQLException {
    String sql = "{CALL sp_categoria_insertar(?, ?)}";
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, nombre);
        cs.setString(2, descripcion);
        cs.execute();
        System.out.println("Categoría insertada correctamente...!");
    }
}


    // 4. Método para modificar una categoría existente
    public void modificarCategoria(int idCategoria, String nuevoNombre, String nuevaDescripcion) throws SQLException {
        String sql = "{CALL sp_categoria_actualizar(?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCategoria);
            cs.setString(2, nuevoNombre);
            cs.setString(3, nuevaDescripcion);
            cs.executeUpdate();
            System.out.println("Categoría modificada correctamente...!");
        } 
    }
/*FINALIZA*/
    
    /*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA CLIENTE :::::::::::::::::::*/

    /* 1. Método para listar todos los clientes usando la vista */
    public ResultSet listarClientes() throws SQLException {
    String sql = "SELECT * FROM vw_cliente_mostrar"; // <--- Cambiar 'vw_cliente_activo' por 'vw_cliente_mostrar'
    st = conn.createStatement();
    rs = st.executeQuery(sql);
    return rs;
    }


    // 2. Método para buscar clientes por coincidencia de nombre o correo
    public ResultSet buscarClientes(String nombre) throws SQLException {
        String sql = "{CALL sp_cliente_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, nombre);                         /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo cliente
    public void insertarCliente(String nombreCliente, String direccion, String telefono, String correo) throws SQLException {
        String sql = "{CALL sp_cliente_insertar(?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, nombreCliente);
            cs.setString(2, direccion);
            cs.setString(3, telefono);
            cs.setString(4, correo);
            cs.execute();
            System.out.println("Cliente insertado correctamente...!");
        }
    }


    // 4. Método para modificar un cliente existente
    public void modificarCliente(int idCliente, String nuevoNombre, String nuevaDireccion, String nuevoTelefono, String nuevoCorreo) throws SQLException {
        String sql = "{CALL sp_cliente_actualizar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCliente);
            cs.setString(2, nuevoNombre);
            cs.setString(3, nuevaDireccion);
            cs.setString(4, nuevoTelefono);
            cs.setString(5, nuevoCorreo);
            cs.executeUpdate();
            System.out.println("Cliente modificado correctamente...!");
        } 
    }
/*FINALIZA*/
    
    /*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA PROVEEDOR :::::::::::::::::::*/

    // 1. Método para listar todos los proveedores usando la vista estandarizada
    public ResultSet listarProveedores() throws SQLException {
        String sql = "SELECT * FROM vw_proveedor_activo"; /* Llamamos a la vista estandarizada de proveedor */
        st = conn.createStatement();                      /* Creamos el statement */
        rs = st.executeQuery(sql);                        /* Ejecutamos la consulta */
        return rs;                                        /* Devolvemos los resultados */
    }


    // 2. Método para buscar proveedores por coincidencia de RUC o nombre
    public ResultSet buscarProveedores(String criterio) throws SQLException {
        String sql = "{CALL sp_proveedor_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, criterio);                       /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo proveedor
    public void insertarProveedor(String ruc, String nombre, String telefono, String correo, String direccion) throws SQLException {
        String sql = "{CALL sp_proveedor_insertar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, ruc);
            cs.setString(2, nombre);
            cs.setString(3, telefono);
            cs.setString(4, correo);
            cs.setString(5, direccion);
            cs.execute();
            System.out.println("Proveedor insertado correctamente...!");
        }
    }


    // 4. Método para modificar un proveedor existente
    public void modificarProveedor(int idProveedor, String nuevoRuc, String nuevoNombre, String nuevoTelefono, String nuevoCorreo, String nuevaDireccion) throws SQLException {
        String sql = "{CALL sp_proveedor_actualizar(?, ?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setString(2, nuevoRuc);
            cs.setString(3, nuevoNombre);
            cs.setString(4, nuevoTelefono);
            cs.setString(5, nuevoCorreo);
            cs.setString(6, nuevaDireccion);
            cs.executeUpdate();
            System.out.println("Proveedor modificado correctamente...!");
        } 
    }
    /*FINALIZA*/
    /*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA TIPO_PERSONAL :::::::::::::::::::*/

    // 1. Método para listar todos los tipos de personal usando la vista estandarizada
    public ResultSet listarTiposPersonal() throws SQLException {
        String sql = "SELECT * FROM vw_tipo_personal_activo"; /* Llamamos a la vista estandarizada de tipo_personal */
        st = conn.createStatement();                          /* Creamos el statement */
        rs = st.executeQuery(sql);                            /* Ejecutamos la consulta */
        return rs;                                            /* Devolvemos los resultados */
    }


    // 2. Método para buscar tipos de personal por coincidencia de nombre
    public ResultSet buscarTiposPersonal(String nombre) throws SQLException {
        String sql = "{CALL sp_tipo_personal_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);        /* Usamos CallableStatement para SPs */
        cs.setString(1, nombre);                             /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                              /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo tipo de personal
    public void insertarTipoPersonal(String nombreTipoPersonal, String observaciones) throws SQLException {
        String sql = "{CALL sp_tipo_personal_insertar(?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, nombreTipoPersonal);
            cs.setString(2, observaciones);
            cs.execute();
            System.out.println("Tipo de personal insertado correctamente...!");
        }
    }


    // 4. Método para modificar un tipo de personal existente
    public void modificarTipoPersonal(int idTipoPersonal, String nuevoNombre, String nuevasObservaciones) throws SQLException {
        String sql = "{CALL sp_tipo_personal_actualizar(?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idTipoPersonal);
            cs.setString(2, nuevoNombre);
            cs.setString(3, nuevasObservaciones);
            cs.executeUpdate();
            System.out.println("Tipo de personal modificado correctamente...!");
        } 
    }
/*FINALIZA*/
    
    /*:::::::::::::::: MANTENIMIENTO A LA TABLA ALMACEN :::::::::::::::::*/

// 1. Método para listar todos los almacenes activos usando la vista
public ResultSet listarAlmacenes() throws SQLException {
    String sql = "SELECT * FROM vw_almacen_activo"; /* Llamamos a la vista estandarizada */
    st = conn.createStatement();                     /* Creamos el statement */
    rs = st.executeQuery(sql);                       /* Ejecutamos la consulta */
    return rs;                                       /* Devolvemos los resultados */
}


// 2. Método para buscar almacenes por coincidencia de nombre
public ResultSet buscarAlmacenes(String nombre) throws SQLException {
    String sql = "{CALL sp_almacen_buscar(?)}";      /* Llamada al procedimiento almacenado */
    CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
    cs.setString(1, nombre);                         /* Asignamos el parámetro de búsqueda */
    rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
    return rs;
}


// 3. Método para insertar un nuevo almacén
public void insertarAlmacen(int idSucursal, String nombreAlmacen, String ubicacion) throws SQLException {
    String sql = "{CALL sp_almacen_insertar(?, ?, ?)}";
    // Usamos CallableStatement para procedimientos almacenados
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idSucursal);
        cs.setString(2, nombreAlmacen);
        cs.setString(3, ubicacion);
        cs.execute();
        System.out.println("Almacén insertado correctamente...!");
    }
}


// 4. Método para modificar un almacén existente
public void modificarAlmacen(int idAlmacen, int idSucursal, String nuevoNombre, String nuevaUbicacion) throws SQLException {
    String sql = "{CALL sp_almacen_actualizar(?, ?, ?, ?)}";
    // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idAlmacen);
        cs.setInt(2, idSucursal);
        cs.setString(3, nuevoNombre);
        cs.setString(4, nuevaUbicacion);
        cs.executeUpdate();
        System.out.println("Almacén modificado correctamente...!");
    } 
}


// 5. Método para dar de baja (desactivar) un almacén lógicamente
public void desactivarAlmacen(int idAlmacen) throws SQLException {
    String sql = "{CALL sp_almacen_desactivar(?)}";
    // Usamos CallableStatement para mantener la consistencia con los procedimientos
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idAlmacen);
        cs.executeUpdate();
        System.out.println("Almacén desactivado correctamente...!");
    }
}

/*::::::::::::::::::::: MANTENIMIENTO A LA TABLA PRODUCTO ::::::::::::::::::::*/

    // 1. Método para listar todos los productos activos usando la vista
    public ResultSet listarProductos() throws SQLException {
        String sql = "SELECT * FROM vw_producto_activo"; /* Llamamos a la vista estandarizada */
        st = conn.createStatement();                     /* Creamos el statement */
        rs = st.executeQuery(sql);                       /* Ejecutamos la consulta */
        return rs;                                       /* Devolvemos los resultados */
    }


    // 2. Método para buscar productos por coincidencia (nombre, código o subcategoría)
    public ResultSet buscarProductos(String criterio) throws SQLException {
        String sql = "{CALL sp_producto_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, criterio);                       /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo producto
    public void insertarProducto(int idSubcategoria, String codigo, String nombreProducto, double precioVenta, String unidadMedida) throws SQLException {
        String sql = "{CALL sp_producto_insertar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idSubcategoria);
            cs.setString(2, codigo);
            cs.setString(3, nombreProducto);
            cs.setDouble(4, precioVenta);
            cs.setString(5, unidadMedida);
            cs.execute();
            System.out.println("Producto insertado correctamente...!");
        }
    }


    // 4. Método para modificar un producto existente
    public void modificarProducto(int idProducto, int idSubcategoria, String codigo, String nombreProducto, double precioVenta, String unidadMedida) throws SQLException {
        String sql = "{CALL sp_producto_actualizar(?, ?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProducto);
            cs.setInt(2, idSubcategoria);
            cs.setString(3, codigo);
            cs.setString(4, nombreProducto);
            cs.setDouble(5, precioVenta);
            cs.setString(6, unidadMedida);
            cs.executeUpdate();
            System.out.println("Producto modificado correctamente...!");
        }
    }


    // 5. Método para dar de baja (desactivar) un producto lógicamente
    public void desactivarProducto(int idProducto) throws SQLException {
        String sql = "{CALL sp_producto_desactivar(?)}";
        // Usamos CallableStatement para mantener la consistencia con los procedimientos
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProducto);
            cs.executeUpdate();
            System.out.println("Producto desactivado correctamente...!");
        }
    }
/*FINALIZA*/
    
    /*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA COMPRA :::::::::::::::::::*/

    // 1. Método para listar todas las compras usando la vista estandarizada
    public ResultSet listarCompras() throws SQLException {
        String sql = "SELECT * FROM vw_compra_listar"; /* Llamamos a la vista estandarizada de compra */
        st = conn.createStatement();                   /* Creamos el statement */
        rs = st.executeQuery(sql);                        /* Ejecutamos la consulta */
        return rs;                                        /* Devolvemos los resultados */
    }


    // 2. Método para buscar compras por criterio de búsqueda (ID o parámetro)
    public ResultSet buscarCompras(String buscar) throws SQLException {
        String sql = "{CALL sp_compra_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, buscar);                         /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar una nueva compra
    public void insertarCompra(int idProveedor, int idAlmacen, int idPersonal, double total) throws SQLException {
        String sql = "{CALL sp_compra_insertar(?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setInt(2, idAlmacen);
            cs.setInt(3, idPersonal);
            cs.setDouble(4, total);
            cs.execute();
            System.out.println("Compra insertada correctamente...!");
        }
    }


    // 4. Método para modificar una compra existente
    public void modificarCompra(int idCompra, int idProveedor, int idAlmacen, int idPersonal, double total) throws SQLException {
        String sql = "{CALL sp_compra_actualizar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCompra);
            cs.setInt(2, idProveedor);
            cs.setInt(3, idAlmacen);
            cs.setInt(4, idPersonal);
            cs.setDouble(5, total);
            cs.executeUpdate();
            System.out.println("Compra modificada correctamente...!");
        } 
    }
/*FINALIZA*/
    
    /*:::::::::::::::::: MANTENIMIENTO A LA TABLA VENTA :::::::::::::::: */

    // 1. Método para listar todas las ventas usando la vista
    public ResultSet listarVentas() throws SQLException {
        String sql = "SELECT * FROM vw_venta_activa"; /* Llamamos a la vista estandarizada */
        st = conn.createStatement();                   /* Creamos el statement */
        rs = st.executeQuery(sql);                     /* Ejecutamos la consulta */
        return rs;                                     /* Devolvemos los resultados */
    }


    // 2. Método para buscar ventas por coincidencia de texto o ID
    public ResultSet buscarVentas(String filtro) throws SQLException {
        String sql = "{CALL sp_venta_buscar(?)}";      /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, filtro);                         /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                          /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar una nueva venta
    public void insertarVenta(int idCliente, int idAlmacen, int idPersonal, double total) throws SQLException {
        String sql = "{CALL sp_venta_insertar(?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idCliente);
            cs.setInt(2, idAlmacen);
            cs.setInt(3, idPersonal);
            cs.setDouble(4, total);
            cs.execute();
            System.out.println("Venta insertada correctamente...!");
        }
    }


    // 4. Método para modificar una venta existente
    public void modificarVenta(int idVenta, int idCliente, int idAlmacen, int idPersonal, double total) throws SQLException {
        String sql = "{CALL sp_venta_actualizar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idVenta);
            cs.setInt(2, idCliente);
            cs.setInt(3, idAlmacen);
            cs.setInt(4, idPersonal);
            cs.setDouble(5, total);
            cs.executeUpdate();
            System.out.println("Venta modificada correctamente...!");
        } 
    }
/*FINALIZA*/
    
    /*::::::::::::::::: MANTENIMIENTO A LA TABLA MOVIMIENTO_INVENTARIO :::::::::::::::*/

    // 1. Método para listar todos los movimientos usando la vista
    public ResultSet listarMovimientosInventario() throws SQLException {
        String sql = "SELECT * FROM vw_movimiento_inventario_activo"; /* Llamamos a la vista estandarizada */
        st = conn.createStatement();                                   /* Creamos el statement */
        rs = st.executeQuery(sql);                                     /* Ejecutamos la consulta */
        return rs;                                                     /* Devolvemos los resultados */
    }


    // 2. Método para buscar movimientos por criterio o filtro
    public ResultSet buscarMovimientoInventario(String filtro) throws SQLException {
        String sql = "{CALL sp_movimiento_inventario_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);                  /* Usamos CallableStatement para SPs */
        cs.setString(1, filtro);                                       /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                                        /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo movimiento de inventario
    public void insertarMovimientoInventario(int idProducto, int idAlmacen, int idPersonal, String tipoMovimiento, int cantidad) throws SQLException {
        String sql = "{CALL sp_movimiento_inventario_insertar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProducto);
            cs.setInt(2, idAlmacen);
            cs.setInt(3, idPersonal);
            cs.setString(4, tipoMovimiento);
            cs.setInt(5, cantidad);
            cs.execute();
            System.out.println("Movimiento de inventario insertado correctamente...!");
        }
    }


    // 4. Método para modificar un movimiento de inventario existente
    public void modificarMovimientoInventario(int idMovimiento, int idProducto, int idAlmacen, int idPersonal, String tipoMovimiento, int cantidad) throws SQLException {
        String sql = "{CALL sp_movimiento_inventario_actualizar(?, ?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idMovimiento);
            cs.setInt(2, idProducto);
            cs.setInt(3, idAlmacen);
            cs.setInt(4, idPersonal);
            cs.setString(5, tipoMovimiento);
            cs.setInt(6, cantidad);
            cs.executeUpdate();
            System.out.println("Movimiento de inventario modificado correctamente...!");
        } 
    }
    
    /*:::::::::::::::::: MANTENIMIENTO A LA TABLA INVENTARIO :::::::::::::::::::*/

    // 1. Método para listar todos los registros de inventario usando la vista
    public ResultSet listarInventarios() throws SQLException {
        String sql = "SELECT * FROM vw_inventario_activo"; /* Llamamos a la vista estandarizada */
        st = conn.createStatement();                       /* Creamos el statement */
        rs = st.executeQuery(sql);                         /* Ejecutamos la consulta */
        return rs;                                         /* Devolvemos los resultados */
    }


    // 2. Método para buscar en el inventario por criterio o filtro
    public ResultSet buscarInventario(String filtro) throws SQLException {
        String sql = "{CALL sp_inventario_buscar(?)}";     /* Llamada al procedimiento almacenado */
        CallableStatement cs = conn.prepareCall(sql);    /* Usamos CallableStatement para SPs */
        cs.setString(1, filtro);                           /* Asignamos el parámetro de búsqueda */
        rs = cs.executeQuery();                            /* Ejecutamos y obtenemos resultados */
        return rs;
    }


    // 3. Método para insertar un nuevo registro de inventario
    public void insertarInventario(int idProducto, int idAlmacen, int stockActual, int stockMinimo) throws SQLException {
        String sql = "{CALL sp_inventario_insertar(?, ?, ?, ?)}";
        // Usamos CallableStatement para procedimientos almacenados
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProducto);
            cs.setInt(2, idAlmacen);
            cs.setInt(3, stockActual);
            cs.setInt(4, stockMinimo);
            cs.execute();
            System.out.println("Registro de inventario insertado correctamente...!");
        }
    }


    // 4. Método para modificar un registro de inventario existente
    public void modificarInventario(int idInventario, int idProducto, int idAlmacen, int stockActual, int stockMinimo) throws SQLException {
        String sql = "{CALL sp_inventario_actualizar(?, ?, ?, ?, ?)}";
        // Usamos CallableStatement en lugar de PreparedStatement para llamadas a SPs
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idInventario);
            cs.setInt(2, idProducto);
            cs.setInt(3, idAlmacen);
            cs.setInt(4, stockActual);
            cs.setInt(5, stockMinimo);
            cs.executeUpdate();
            System.out.println("Registro de inventario modificado correctamente...!");
        } 
    }
    
    /*:::::::::::::::::: MANTENIMIENTO A LA TABLA PERSONAL :::::::::::::::::::*/

    // 1. Método para listar todo el personal desde la vista
    public ResultSet listarPersonal() throws SQLException {
        String sql = "SELECT * FROM vw_personal_activo";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }

    // 2. Método para buscar personal por filtro (nombres, apellidos o documento)
    public ResultSet buscarPersonal(String filtro) throws SQLException {
        String sql = "{CALL sp_personal_buscar(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, filtro);
        rs = cs.executeQuery();
        return rs;
    }

    // 3. Método para insertar un nuevo personal
    public void insertarPersonal(int idTipoPersonal, int idCargo, int idArea, int idSucursal, String nombres, String apellidos, String documento) throws SQLException {
        String sql = "{CALL sp_personal_insertar(?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idTipoPersonal);
            cs.setInt(2, idCargo);
            cs.setInt(3, idArea);
            cs.setInt(4, idSucursal);
            cs.setString(5, nombres);
            cs.setString(6, apellidos);
            cs.setString(7, documento);
            cs.execute();
            System.out.println("Personal registrado correctamente...!");
        }
    }

    // 4. Método para modificar un registro de personal
    public void modificarPersonal(int idPersonal, int idTipoPersonal, int idCargo, int idArea, int idSucursal, String nombres, String apellidos, String documento) throws SQLException {
        String sql = "{CALL sp_personal_actualizar(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idPersonal);
            cs.setInt(2, idTipoPersonal);
            cs.setInt(3, idCargo);
            cs.setInt(4, idArea);
            cs.setInt(5, idSucursal);
            cs.setString(6, nombres);
            cs.setString(7, apellidos);
            cs.setString(8, documento);
            cs.executeUpdate();
            System.out.println("Personal modificado correctamente...!");
        }
    }

    // Cargar ComboBoxes auxiliares
    public ResultSet cargarComboTipoPersonal() throws SQLException {
        String sql = "SELECT id_tipo_personal, nombre_tipo_personal FROM tipo_personal ORDER BY nombre_tipo_personal ASC";
        st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public ResultSet cargarComboCargo() throws SQLException {
        String sql = "SELECT id_cargo, nombre_cargo FROM cargo ORDER BY nombre_cargo ASC";
        st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public ResultSet cargarComboArea() throws SQLException {
        String sql = "SELECT id_area, nombre_area FROM area ORDER BY nombre_area ASC";
        st = conn.createStatement();
        return st.executeQuery(sql);
    }

    public ResultSet cargarComboSucursal() throws SQLException {
        String sql = "SELECT id_sucursal, nombre FROM sucursal ORDER BY nombre ASC";
        st = conn.createStatement();
        return st.executeQuery(sql);
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
