
/*::::::::::::::::::MANTENIMIENTO CRUD A LA TABLA CATEGORIA:::::::::::::::::::*/
-- 1. Vista mostrar categorias
CREATE OR REPLACE VIEW vw_categoria_activa AS
SELECT 
    C.id_categoria, 
    C.nombre,
    C.descripcion
FROM categoria C;

-- Prueba de la vista
SELECT * FROM vw_categoria_activa;
-- ============================================================
-- 1. Procedure para buscar categorias
-- ============================================================
DROP PROCEDURE IF EXISTS sp_categoria_buscar;
DELIMITER //
CREATE PROCEDURE sp_categoria_buscar(IN p_nomCategoria VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(52);
    SET v_filtro = TRIM(p_nomCategoria);

    SELECT 
        id_categoria, 
        nombre,
        descripcion
    FROM vw_categoria_activa
    WHERE (v_filtro = '' OR nombre LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_categoria_buscar('Electrónica');
CALL sp_categoria_buscar(''); -- Devuelve todas las categorías si se envía vacío

-- ============================================================
-- 2. Procedure para insertar en la tabla categoria
-- ============================================================
DROP PROCEDURE IF EXISTS sp_categoria_insertar;
DELIMITER $$
CREATE PROCEDURE sp_categoria_insertar(
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(100)
)
BEGIN
    -- Declaramos las variables limpias correctamente
    DECLARE v_nombreLim VARCHAR(50); 
    DECLARE v_descripcionLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nombre);
    SET v_descripcionLim = TRIM(p_descripcion);

    -- Validamos que el nombre no venga vacío
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la categoría no puede estar vacío.';
    END IF;

    -- Verificar si ya existe una categoría con ese nombre
    IF NOT EXISTS (
        SELECT 1 
        FROM categoria 
        WHERE nombre = v_nombreLim
    ) THEN
        -- Si no existe, insertamos con los valores limpios
        INSERT INTO categoria (nombre, descripcion) 
        VALUES (v_nombreLim, v_descripcionLim);
    ELSE
        -- Si ya existe, lanzamos un error controlado
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La categoría ya existe, no se puede insertar un duplicado.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_categoria_insertar('Impresión y Escaneo', 'Equipos e insumos para impresión');

-- ============================================================
-- 3. Procedure para modificar una Categoria
-- ============================================================
DROP PROCEDURE IF EXISTS sp_categoria_actualizar;
DELIMITER //

CREATE PROCEDURE sp_categoria_actualizar (
    IN p_idCategoria INT,
    IN p_nuevoNombre VARCHAR(50),
    IN p_nuevaDescripcion VARCHAR(100)
)
BEGIN
    -- Declaramos variables para limpiar espacios en blanco
    DECLARE v_nombreLim VARCHAR(50);
    DECLARE v_descripcionLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nuevoNombre);
    SET v_descripcionLim = TRIM(p_nuevaDescripcion);

    -- 1. Validar que el ID sea válido
    IF p_idCategoria <= 0 OR p_idCategoria IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la categoría no es válido.';
    END IF;

    -- 2. Validar que el nuevo nombre no esté vacío
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la categoría no puede estar vacío.';
    END IF;

    -- 3. Validar que la categoría a modificar realmente exista
    IF NOT EXISTS (SELECT 1 FROM categoria WHERE id_categoria = p_idCategoria) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La categoría que intenta modificar no existe.';
    END IF;

    -- 4. Validar que no exista OTRA categoría con el mismo nombre (excluyendo la actual)
    IF EXISTS (
        SELECT 1 FROM categoria 
        WHERE nombre = v_nombreLim AND id_categoria != p_idCategoria
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otra categoría registrada con ese mismo nombre.';
    ELSE
        -- Si pasa todas las validaciones, procedemos a actualizar
        UPDATE categoria
        SET nombre = v_nombreLim,
            descripcion = v_descripcionLim
        WHERE id_categoria = p_idCategoria;
    END IF;
END //

DELIMITER ;

-- Ejemplo de uso:
CALL sp_categoria_actualizar(1, 'Electrónica Avanzada', 'Dispositivos y componentes electrónicos de alta gama');


/*FINALIZA*/

/*::::::::::::::::::MANTENIMIENTO CRUD A LA TABLA CLIENTE:::::::::::::::::::*/
-- 1. Vista mostrar clientes
CREATE OR REPLACE VIEW vw_cliente_mostrar AS
SELECT 
    C.id_cliente,
    C.nombre_cliente,
    C.direccion,
    C.telefono,
    C.correo
FROM cliente C;

-- Prueba de la vista
SELECT * FROM vw_cliente_mostrar;
-- ============================================================
-- 1. Procedure para buscar clientes (utilizando la vista)
-- ============================================================
DROP PROCEDURE IF EXISTS sp_cliente_buscar;
DELIMITER //
CREATE PROCEDURE sp_cliente_buscar(IN p_nombreCliente VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_nombreCliente);

    SELECT 
        id_cliente,
        nombre_cliente,
        direccion,
        telefono,
        correo
    FROM vw_cliente_mostrar
    WHERE (v_filtro = '' OR nombre_cliente LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplos de uso:
CALL sp_cliente_buscar('Corporación');
CALL sp_cliente_buscar(''); -- Devuelve todos los clientes registrados si se envía vacío

-- ============================================================
-- 2. Procedure para insertar un cliente
-- ============================================================

DROP PROCEDURE IF EXISTS sp_cliente_insertar;
DELIMITER //
CREATE PROCEDURE sp_cliente_insertar(
    IN p_nombreCliente VARCHAR(100),
    IN p_direccion VARCHAR(100),
    IN p_telefono VARCHAR(12),
    IN p_correo VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_direccionLim VARCHAR(100);
    DECLARE v_telefonoLim VARCHAR(12);
    DECLARE v_correoLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nombreCliente);
    SET v_direccionLim = TRIM(p_direccion);
    SET v_telefonoLim = TRIM(p_telefono);
    SET v_correoLim = TRIM(p_correo);

    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del cliente no puede estar vacío.';
    END IF;

    IF v_correoLim IS NOT NULL AND v_correoLim != '' THEN
        IF EXISTS (
            SELECT 1 
            FROM cliente 
            WHERE correo = v_correoLim
        ) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El correo ingresado ya se encuentra registrado con otro cliente.';
        END IF;
    ELSE
        SET v_correoLim = NULL;
    END IF;

    IF v_direccionLim = '' THEN SET v_direccionLim = NULL; END IF;
    IF v_telefonoLim = '' THEN SET v_telefonoLim = NULL; END IF;

    INSERT INTO cliente (nombre_cliente, direccion, telefono, correo) 
    VALUES (v_nombreLim, v_direccionLim, v_telefonoLim, v_correoLim);
END //
DELIMITER ;
-- Ejemplo de uso:
CALL sp_cliente_insertar('Inversiones San Juan S.A.C.', 'Av. Los Próceres 456', '987123456', 'contacto@sanjuan.pe');

-- ============================================================
-- 3. Procedure para modificar un cliente
-- ============================================================
DROP PROCEDURE IF EXISTS sp_cliente_actualizar;
DELIMITER //

CREATE PROCEDURE sp_cliente_actualizar (
    IN p_idCliente INT,
    IN p_nuevoNombre VARCHAR(100),
    IN p_nuevaDireccion VARCHAR(100),
    IN p_nuevoTelefono VARCHAR(12),
    IN p_nuevoCorreo VARCHAR(100)
)
BEGIN
    -- Declaramos variables para limpiar espacios
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_direccionLim VARCHAR(100);
    DECLARE v_telefonoLim VARCHAR(12);
    DECLARE v_correoLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nuevoNombre);
    SET v_direccionLim = TRIM(p_nuevaDireccion);
    SET v_telefonoLim = TRIM(p_nuevoTelefono);
    SET v_correoLim = TRIM(p_nuevoCorreo);

    -- 1. Validar que el ID sea válido
    IF p_idCliente <= 0 OR p_idCliente IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del cliente no es válido.';
    END IF;

    -- 2. Validar que el nuevo nombre no esté vacío
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del cliente no puede estar vacío.';
    END IF;

    -- 3. Validar que el cliente a modificar realmente exista
    IF NOT EXISTS (SELECT 1 FROM cliente WHERE id_cliente = p_idCliente) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente que intenta modificar no existe.';
    END IF;

    -- 4. Validar que no exista OTRO cliente con el mismo correo (excluyendo al actual)
    IF v_correoLim IS NOT NULL AND v_correoLim != '' THEN
        IF EXISTS (
            SELECT 1 FROM cliente 
            WHERE correo = v_correoLim AND id_cliente != p_idCliente
        ) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe otro cliente registrado con ese mismo correo electrónico.';
        END IF;
    ELSE
        SET v_correoLim = NULL;
    END IF;

    IF v_direccionLim = '' THEN SET v_direccionLim = NULL; END IF;
    IF v_telefonoLim = '' THEN SET v_telefonoLim = NULL; END IF;

    -- 5. Proceder a actualizar los datos del cliente
    UPDATE cliente
    SET nombre_cliente = v_nombreLim,
        direccion      = v_direccionLim,
        telefono       = v_telefonoLim,
        correo         = v_correoLim
    WHERE id_cliente = p_idCliente;
END //

DELIMITER ;

-- Ejemplo de uso:
CALL sp_cliente_actualizar(1, 'Corporación ABC S.A.C. (Actualizado)', 'Av. Los Olivos 105', '987654321', 'compras_actualizadas@abc.com');


/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA PROVEEDOR :::::::::::::::::::*/

-- 1. VISTA MOSTRAR PROVEEDORES

CREATE OR REPLACE VIEW vw_proveedor_activo AS
SELECT 
    p.id_proveedor, 
    p.ruc,
    p.nombre,
    p.telefono,
    p.correo,
    p.direccion
FROM proveedor p;

-- Prueba de la vista
SELECT * FROM vw_proveedor_activo;
-- ============================================================
-- 1. PROCEDURE PARA BUSCAR PROVEEDORES
-- ============================================================
DROP PROCEDURE IF EXISTS sp_proveedor_buscar;
DELIMITER //
CREATE PROCEDURE sp_proveedor_buscar(IN p_criterio VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_criterio);

    SELECT 
        id_proveedor, 
        ruc,
        nombre,
        telefono,
        correo,
        direccion
    FROM vw_proveedor_activo
    WHERE (v_filtro = '' OR v_filtro IS NULL)
       OR nombre LIKE CONCAT('%', v_filtro, '%')
       OR ruc LIKE CONCAT('%', v_filtro, '%');
END //
DELIMITER ;

-- Ejemplo de uso:
 CALL sp_proveedor_buscar('Tech');
 CALL sp_proveedor_buscar('20500000001');
 CALL sp_proveedor_buscar(''); -- Muestra todos los registros


-- ============================================================
-- 2. PROCEDURE PARA INSERTAR EN LA TABLA PROVEEDOR
-- ============================================================
DROP PROCEDURE IF EXISTS sp_proveedor_insertar;
DELIMITER $$
CREATE PROCEDURE sp_proveedor_insertar(
    IN p_ruc VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(12),
    IN p_correo VARCHAR(100),
    IN p_direccion VARCHAR(100)
)
BEGIN
    -- Variables para limpiar datos en blanco
    DECLARE v_rucLim VARCHAR(20);
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_telefonoLim VARCHAR(12);
    DECLARE v_correoLim VARCHAR(100);
    DECLARE v_direccionLim VARCHAR(100);

    SET v_rucLim = TRIM(p_ruc);
    SET v_nombreLim = TRIM(p_nombre);
    SET v_telefonoLim = TRIM(p_telefono);
    SET v_correoLim = TRIM(p_correo);
    SET v_direccionLim = TRIM(p_direccion);

    -- 1. Validaciones de campos obligatorios
    IF v_rucLim = '' OR v_rucLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El RUC del proveedor no puede estar vacío.';
    END IF;

    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre o razón social del proveedor no puede estar vacío.';
    END IF;

    -- 2. Verificar duplicidad de RUC
    IF EXISTS (
        SELECT 1 
        FROM proveedor 
        WHERE ruc = v_rucLim
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El RUC ingresado ya se encuentra registrado para otro proveedor.';
    ELSE
        -- 3. Insertar registro
        INSERT INTO proveedor (ruc, nombre, telefono, correo, direccion) 
        VALUES (v_rucLim, v_nombreLim, v_telefonoLim, v_correoLim, v_direccionLim);
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_proveedor_insertar('20600000099', 'Proveedor Demo S.A.C.', '987654321', 'contacto@demo.pe', 'Av. Central 123');


-- ============================================================
-- 3. PROCEDURE PARA MODIFICAR UN PROVEEDOR
-- ============================================================
DROP PROCEDURE IF EXISTS sp_proveedor_actualizar;
DELIMITER //

CREATE PROCEDURE sp_proveedor_actualizar (
    IN p_idProveedor INT,
    IN p_ruc VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(12),
    IN p_correo VARCHAR(100),
    IN p_direccion VARCHAR(100)
)
BEGIN
    -- Variables de limpieza
    DECLARE v_rucLim VARCHAR(20);
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_telefonoLim VARCHAR(12);
    DECLARE v_correoLim VARCHAR(100);
    DECLARE v_direccionLim VARCHAR(100);

    SET v_rucLim = TRIM(p_ruc);
    SET v_nombreLim = TRIM(p_nombre);
    SET v_telefonoLim = TRIM(p_telefono);
    SET v_correoLim = TRIM(p_correo);
    SET v_direccionLim = TRIM(p_direccion);

    -- 1. Validar ID de proveedor
    IF p_idProveedor <= 0 OR p_idProveedor IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del proveedor no es válido.';
    END IF;

    -- 2. Validar campos obligatorios
    IF v_rucLim = '' OR v_rucLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El RUC del proveedor no puede estar vacío.';
    END IF;

    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del proveedor no puede estar vacío.';
    END IF;

    -- 3. Validar existencia del registro a modificar
    IF NOT EXISTS (SELECT 1 FROM proveedor WHERE id_proveedor = p_idProveedor) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El proveedor que intenta modificar no existe.';
    END IF;

    -- 4. Validar que no exista OTRO proveedor con el mismo RUC (excluyendo el actual)
    IF EXISTS (
        SELECT 1 FROM proveedor 
        WHERE ruc = v_rucLim AND id_proveedor != p_idProveedor
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro proveedor registrado con el mismo RUC.';
    ELSE
        -- 5. Proceder a actualizar
        UPDATE proveedor
        SET ruc = v_rucLim,
            nombre = v_nombreLim,
            telefono = v_telefonoLim,
            correo = v_correoLim,
            direccion = v_direccionLim
        WHERE id_proveedor = p_idProveedor;
    END IF;
END //

DELIMITER ;

-- Ejemplo de uso:
 CALL sp_proveedor_actualizar(1, '20500000001', 'Importadora Tech Supply Perú S.A.C.', '955000001', 'ventas@techsupply.pe', 'Av. Argentina 890, Lima');

/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA TIPO_PERSONAL :::::::::::::::::::*/
-- 1. Vista correspondiente a la tabla
CREATE OR REPLACE VIEW vw_tipo_personal_activo AS
SELECT 
    TP.id_tipo_personal,
    TP.nombre_tipo_personal,
    TP.observaciones
FROM tipo_personal TP;

-- Prueba de la vista
SELECT * FROM vw_tipo_personal_activo;
-- ============================================================
-- 1. Procedure para buscar tipos de personal 
-- ============================================================
DROP PROCEDURE IF EXISTS sp_tipo_personal_buscar;
DELIMITER //
CREATE PROCEDURE sp_tipo_personal_buscar(IN p_nombre VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(50);
    SET v_filtro = TRIM(p_nombre);

    SELECT 
        id_tipo_personal,
        nombre_tipo_personal,
        observaciones
    FROM vw_tipo_personal_activo
    WHERE (v_filtro = '' OR nombre_tipo_personal LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplos de uso:
CALL sp_tipo_personal_buscar('Contratado');
CALL sp_tipo_personal_buscar(''); -- Devuelve todos los registros si se envía vacío

-- ============================================================
-- 2. Procedure para guardar registros en tipo_personal
-- ============================================================

DROP PROCEDURE IF EXISTS sp_tipo_personal_insertar;
DELIMITER $$
CREATE PROCEDURE sp_tipo_personal_insertar(
    IN p_nombreTipoPersonal VARCHAR(50),
    IN p_observaciones VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    DECLARE v_obsLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nombreTipoPersonal);
    SET v_obsLim = TRIM(p_observaciones);

    -- Validar que el nombre no esté vacío
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del tipo de personal no puede estar vacío.';
    END IF;

    -- Verificar que no exista un duplicado por nombre (CONSTRAINT uq_tipo_personal_nombre)
    IF NOT EXISTS (
        SELECT 1 
        FROM tipo_personal 
        WHERE nombre_tipo_personal = v_nombreLim
    ) THEN
        INSERT INTO tipo_personal (nombre_tipo_personal, observaciones) 
        VALUES (v_nombreLim, IF(v_obsLim = '', NULL, v_obsLim));
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de personal ya existe, no se puede insertar un duplicado.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_tipo_personal_insertar('Practicante Senati', 'Modalidad de aprendizaje dual');

-- ============================================================
-- 3. Procedure para modificar un tipo de personal
-- ============================================================
DROP PROCEDURE IF EXISTS sp_tipo_personal_actualizar;
DELIMITER //
CREATE PROCEDURE sp_tipo_personal_actualizar (
    IN p_idTipoPersonal INT,
    IN p_nuevoNombre VARCHAR(50),
    IN p_nuevasObservaciones VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    DECLARE v_obsLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nuevoNombre);
    SET v_obsLim = TRIM(p_nuevasObservaciones);

    -- 1. Validar que el ID sea válido
    IF p_idTipoPersonal <= 0 OR p_idTipoPersonal IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del tipo de personal no es válido.';
    END IF;

    -- 2. Validar que el nombre no esté vacío
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del tipo de personal no puede estar vacío.';
    END IF;

    -- 3. Validar que el registro realmente exista
    IF NOT EXISTS (SELECT 1 FROM tipo_personal WHERE id_tipo_personal = p_idTipoPersonal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de personal que intenta modificar no existe.';
    END IF;

    -- 4. Validar que no exista OTRO registro con el mismo nombre
    IF EXISTS (
        SELECT 1 FROM tipo_personal 
        WHERE nombre_tipo_personal = v_nombreLim AND id_tipo_personal != p_idTipoPersonal
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro tipo de personal registrado con ese mismo nombre.';
    ELSE
        UPDATE tipo_personal
        SET nombre_tipo_personal = v_nombreLim,
            observaciones = IF(v_obsLim = '', NULL, v_obsLim)
        WHERE id_tipo_personal = p_idTipoPersonal;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_tipo_personal_actualizar(1, 'Contratado Fijo / Indeterminado', 'Personal en planilla permanente con todos los beneficios');
/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA TIPO_PERSONAL :::::::::::::::::::*/
--1.VISTA PARA LISTAR ALMACENES ACTIVOS
CREATE OR REPLACE VIEW vw_almacen_activo AS
SELECT 
    A.id_almacen,
    A.id_sucursal,
    S.nombre AS nombre_sucursal,
    A.nombre_almacen,
    A.ubicacion,
    A.estado
FROM almacen A
INNER JOIN sucursal S ON A.id_sucursal = S.id_sucursal
WHERE A.estado = 1;

-- Prueba de la vista
SELECT * FROM vw_almacen_activo;

--2.PROCEDURE PARA BUSCAR ALMACEN
DROP PROCEDURE IF EXISTS sp_almacen_buscar;
DELIMITER //
CREATE PROCEDURE sp_almacen_buscar(IN p_nomAlmacen VARCHAR(60))
BEGIN
    DECLARE v_filtro VARCHAR(62);
    SET v_filtro = TRIM(p_nomAlmacen);

    SELECT 
        id_almacen,
        id_sucursal,
        nombre_sucursal,
        nombre_almacen,
        ubicacion
    FROM vw_almacen_activo
    WHERE (v_filtro = '' OR nombre_almacen LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_almacen_buscar('Principal');
CALL sp_almacen_buscar(''); -- Devuelve todos los almacenes activos si se envía vacío

--3.PROCEDURE PARA INSERTAR ALMACEN
DROP PROCEDURE IF EXISTS sp_almacen_insertar;
DELIMITER $$
CREATE PROCEDURE sp_almacen_insertar(
    IN p_idSucursal INT,
    IN p_nombreAlmacen VARCHAR(60),
    IN p_ubicacion VARCHAR(100)
)
BEGIN
    -- Declaramos variables limpias para el texto
    DECLARE v_nombreLim VARCHAR(60);
    DECLARE v_ubicacionLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nombreAlmacen);
    SET v_ubicacionLim = TRIM(p_ubicacion);

    -- 1. Validar id_sucursal
    IF p_idSucursal <= 0 OR p_idSucursal IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de la sucursal no es válido.';
    END IF;

    -- 2. Validar que la sucursal exista
    IF NOT EXISTS (SELECT 1 FROM sucursal WHERE id_sucursal = p_idSucursal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La sucursal especificada no existe.';
    END IF;

    -- 3. Validar que los campos obligatorios no vengan vacíos
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del almacén no puede estar vacío.';
    END IF;

    IF v_ubicacionLim = '' OR v_ubicacionLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La ubicación del almacén no puede estar vacía.';
    END IF;

    -- 4. Verificar duplicados (mismo nombre dentro de la misma sucursal)
    IF NOT EXISTS (
        SELECT 1 
        FROM almacen 
        WHERE id_sucursal = p_idSucursal AND nombre_almacen = v_nombreLim
    ) THEN
        -- Si no existe, se inserta con el estado activo por defecto (1)
        INSERT INTO almacen (id_sucursal, nombre_almacen, ubicacion, estado)
        VALUES (p_idSucursal, v_nombreLim, v_ubicacionLim, 1);
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe un almacén registrado con ese nombre en la sucursal indicada.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_almacen_insertar(1, 'Almacén Auxiliar Lima', 'Nave C - Sector 3');

--4.PROCEDURE PARA MODIFICAR ALMACEN
DROP PROCEDURE IF EXISTS sp_almacen_actualizar;
DELIMITER //

CREATE PROCEDURE sp_almacen_actualizar(
    IN p_idAlmacen INT,
    IN p_idSucursal INT,
    IN p_nuevoNombre VARCHAR(60),
    IN p_nuevaUbicacion VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(60);
    DECLARE v_ubicacionLim VARCHAR(100);

    SET v_nombreLim = TRIM(p_nuevoNombre);
    SET v_ubicacionLim = TRIM(p_nuevaUbicacion);

    -- 1. Validar identificadores
    IF p_idAlmacen <= 0 OR p_idAlmacen IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del almacén no es válido.';
    END IF;

    IF p_idSucursal <= 0 OR p_idSucursal IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de la sucursal no es válido.';
    END IF;

    -- 2. Validar que la sucursal exista
    IF NOT EXISTS (SELECT 1 FROM sucursal WHERE id_sucursal = p_idSucursal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La sucursal especificada no existe.';
    END IF;

    -- 3. Validar cadenas vacías
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del almacén no puede estar vacío.';
    END IF;

    IF v_ubicacionLim = '' OR v_ubicacionLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La ubicación del almacén no puede estar vacía.';
    END IF;

    -- 4. Validar existencia del almacén a modificar
    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_idAlmacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén que intenta modificar no existe.';
    END IF;

    -- 5. Validar que no exista otro almacén con el mismo nombre en esa sucursal
    IF EXISTS (
        SELECT 1 FROM almacen 
        WHERE id_sucursal = p_idSucursal 
          AND nombre_almacen = v_nombreLim 
          AND id_almacen != p_idAlmacen
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro almacén registrado con ese mismo nombre en la sucursal.';
    ELSE
        -- Actualización del registro
        UPDATE almacen
        SET id_sucursal = p_idSucursal,
            nombre_almacen = v_nombreLim,
            ubicacion = v_ubicacionLim
        WHERE id_almacen = p_idAlmacen;
    END IF;
END //

DELIMITER ;

-- Ejemplo de uso:
CALL sp_almacen_actualizar(1, 1, 'Almacén Principal Lima Central', 'Nave A - Sector 1 Renovado');

--5.PROCEDURE PARA DAR DE BAJA 
DROP PROCEDURE IF EXISTS sp_almacen_desactivar;
DELIMITER //

CREATE PROCEDURE sp_almacen_desactivar(IN p_idAlmacen INT)
BEGIN
    -- Validar id_almacen
    IF p_idAlmacen <= 0 OR p_idAlmacen IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del almacén no es válido.';
    END IF;

    -- 1. Verificar si el almacén existe
    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_idAlmacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El almacén especificado no existe.';
    END IF;

    -- 2. Verificar si ya se encuentra desactivado
    IF EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_idAlmacen AND estado = 0) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén ya se encuentra desactivado.';
    END IF;

    -- 3. Proceder con la desactivación lógica
    UPDATE almacen
    SET estado = 0
    WHERE id_almacen = p_idAlmacen;
END //

DELIMITER ;

-- Ejemplo de uso:
CALL sp_almacen_desactivar(11);
/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA PRODUCTO :::::::::::::::::::*/

-- ============================================================
-- 1. VISTA MOSTRAR PRODUCTOS ACTIVOS
-- ============================================================
CREATE OR REPLACE VIEW vw_producto_activo AS
SELECT 
    P.id_producto,
    P.id_subcategoria,
    S.nombre_subcategoria,
    P.codigo,
    P.nombre_producto,
    P.precio_venta,
    P.unidad_medida,
    P.estado
FROM producto P
INNER JOIN subcategoria S ON P.id_subcategoria = S.id_subcategoria
WHERE P.estado = 1;

-- Prueba de la vista
SELECT * FROM vw_producto_activo;


-- ============================================================
-- 2. PROCEDURE PARA BUSCAR PRODUCTOS
-- ============================================================
DROP PROCEDURE IF EXISTS sp_producto_buscar;
DELIMITER //

CREATE PROCEDURE sp_producto_buscar(IN p_criterio VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_criterio);

    SELECT 
        id_producto,
        id_subcategoria,
        nombre_subcategoria,
        codigo,
        nombre_producto,
        precio_venta,
        unidad_medida
    FROM vw_producto_activo
    WHERE (v_filtro = '' OR v_filtro IS NULL
           OR nombre_producto LIKE CONCAT('%', v_filtro, '%')
           OR codigo LIKE CONCAT('%', v_filtro, '%')
           OR nombre_subcategoria LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
 CALL sp_producto_buscar('Laptop');
 CALL sp_producto_buscar('PROD-001');
 CALL sp_producto_buscar(''); -- Devuelve todos los activos si se envía vacío


-- ============================================================
-- 3. PROCEDURE PARA INSERTAR PRODUCTO
-- ============================================================
DROP PROCEDURE IF EXISTS sp_producto_insertar;
DELIMITER $$

CREATE PROCEDURE sp_producto_insertar(
    IN p_idSubcategoria INT,
    IN p_codigo VARCHAR(45),
    IN p_nombreProducto VARCHAR(100),
    IN p_precioVenta DECIMAL(10,2),
    IN p_unidadMedida VARCHAR(20)
)
BEGIN
    -- Declaramos variables limpias
    DECLARE v_codigoLim VARCHAR(45);
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_unidadLim VARCHAR(20);

    SET v_codigoLim = TRIM(p_codigo);
    SET v_nombreLim = TRIM(p_nombreProducto);
    SET v_unidadLim = TRIM(p_unidadMedida);

    -- Validar FK de subcategoría
    IF p_idSubcategoria IS NULL OR p_idSubcategoria <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La subcategoría especificada no es válida.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM subcategoria WHERE id_subcategoria = p_idSubcategoria) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La subcategoría seleccionada no existe.';
    END IF;

    -- Validar código de producto
    IF v_codigoLim = '' OR v_codigoLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del producto no puede estar vacío.';
    END IF;

    -- Validar nombre de producto
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del producto no puede estar vacío.';
    END IF;

    -- Validar unidad de medida
    IF v_unidadLim = '' OR v_unidadLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La unidad de medida no puede estar vacía.';
    END IF;

    -- Validar precio de venta
    IF p_precioVenta IS NULL OR p_precioVenta < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de venta no puede ser negativo o nulo.';
    END IF;

    -- Verificar que no exista un producto con el mismo código
    IF EXISTS (
        SELECT 1 
        FROM producto 
        WHERE codigo = v_codigoLim
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del producto ya existe, no se permite duplicados.';
    END IF;

    -- Inserción del producto
    INSERT INTO producto (
        id_subcategoria, 
        codigo, 
        nombre_producto, 
        precio_venta, 
        unidad_medida, 
        estado
    ) 
    VALUES (
        p_idSubcategoria, 
        v_codigoLim, 
        v_nombreLim, 
        p_precioVenta, 
        v_unidadLim, 
        1
    );
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_producto_insertar(3, 'PROD-011', 'Teclado Mecánico RGB', 180.00, 'Unidad');


-- ============================================================
-- 4. PROCEDURE PARA MODIFICAR PRODUCTO
-- ============================================================
DROP PROCEDURE IF EXISTS sp_producto_actualizar;
DELIMITER //

CREATE PROCEDURE sp_producto_actualizar(
    IN p_idProducto INT,
    IN p_idSubcategoria INT,
    IN p_codigo VARCHAR(45),
    IN p_nombreProducto VARCHAR(100),
    IN p_precioVenta DECIMAL(10,2),
    IN p_unidadMedida VARCHAR(20)
)
BEGIN
    DECLARE v_codigoLim VARCHAR(45);
    DECLARE v_nombreLim VARCHAR(100);
    DECLARE v_unidadLim VARCHAR(20);

    SET v_codigoLim = TRIM(p_codigo);
    SET v_nombreLim = TRIM(p_nombreProducto);
    SET v_unidadLim = TRIM(p_unidadMedida);

    -- 1. Validar que el ID del producto sea válido
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;

    -- 2. Validar que el producto exista
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto que intenta modificar no existe.';
    END IF;

    -- 3. Validar la subcategoría
    IF p_idSubcategoria IS NULL OR p_idSubcategoria <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La subcategoría especificada no es válida.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM subcategoria WHERE id_subcategoria = p_idSubcategoria) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La subcategoría seleccionada no existe.';
    END IF;

    -- 4. Validar campos requeridos
    IF v_codigoLim = '' OR v_codigoLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del producto no puede estar vacío.';
    END IF;

    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del producto no puede estar vacío.';
    END IF;

    IF v_unidadLim = '' OR v_unidadLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La unidad de medida no puede estar vacía.';
    END IF;

    IF p_precioVenta IS NULL OR p_precioVenta < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de venta no puede ser negativo o nulo.';
    END IF;

    -- 5. Validar que no exista OTRO producto con el mismo código (excluyendo el actual)
    IF EXISTS (
        SELECT 1 FROM producto 
        WHERE codigo = v_codigoLim AND id_producto != p_idProducto
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro producto registrado con ese mismo código.';
    ELSE
        -- Actualización del registro
        UPDATE producto
        SET id_subcategoria = p_idSubcategoria,
            codigo          = v_codigoLim,
            nombre_producto = v_nombreLim,
            precio_venta    = p_precioVenta,
            unidad_medida   = v_unidadLim
        WHERE id_producto   = p_idProducto;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
 CALL sp_producto_actualizar(1, 1, 'PROD-001', 'Audífonos Bluetooth X1 Pro', 135.00, 'Unidad');


-- ============================================================
-- 5. PROCEDURE PARA DAR DE BAJA (DESACTIVAR ESTADO LÓGICO)
-- ============================================================
DROP PROCEDURE IF EXISTS sp_producto_desactivar;
DELIMITER //

CREATE PROCEDURE sp_producto_desactivar(IN p_idProducto INT)
BEGIN
    -- 1. Validar que el código sea válido
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;

    -- 2. Verificar si el producto existe
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El producto especificado no existe.';
    END IF;

    -- 3. Verificar si ya está desactivado
    IF EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto AND estado = 0) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto ya se encuentra desactivado.';
    END IF;

    -- 4. Proceder con la desactivación lógica
    UPDATE producto
    SET estado = 0
    WHERE id_producto = p_idProducto;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_producto_desactivar(10);
/*FINALIZA*/
/*::::::::::::::::::MANTENIMIENTO CRUD A LA TABLA COMPRA:::::::::::::::::::*/

-- ============================================================
-- 1. VISTA: vw_compra_listar
-- Muestra el detalle informativo de las compras uniendo los datos 
-- del proveedor, almacén y personal responsable.
-- ============================================================
CREATE OR REPLACE VIEW vw_compra_listar AS
SELECT 
    C.id_compra,
    C.fecha,
    C.total,
    C.id_proveedor,
    P.nombre AS nombre_proveedor,
    P.ruc AS ruc_proveedor,
    C.id_almacen,
    A.nombre_almacen,
    C.id_personal,
    CONCAT(PERS.nombres, ' ', PERS.apellidos) AS nombre_personal
FROM compra C
INNER JOIN proveedor P ON C.id_proveedor = P.id_proveedor
INNER JOIN almacen A ON C.id_almacen = A.id_almacen
INNER JOIN personal PERS ON C.id_personal = PERS.id_personal;

-- Prueba de la vista
SELECT * FROM vw_compra_listar;


-- ============================================================
-- 2. STORED PROCEDURE: sp_compra_buscar
-- Permite buscar compras por nombre/RUC de proveedor o por ID de compra.
-- Utiliza obligatoriamente la vista vw_compra_listar.
-- ============================================================
DROP PROCEDURE IF EXISTS sp_compra_buscar;

DELIMITER //

CREATE PROCEDURE sp_compra_buscar(IN p_criterio VARCHAR(100))
BEGIN

    DECLARE v_filtro VARCHAR(100);

    SET v_filtro = TRIM(p_criterio);

    -- Si está vacío, mostrar todas las compras
    IF v_filtro = '' OR v_filtro IS NULL THEN

        SELECT 
            id_compra,
            fecha,
            total,
            id_proveedor,
            nombre_proveedor,
            ruc_proveedor,
            id_almacen,
            nombre_almacen,
            id_personal,
            nombre_personal
        FROM vw_compra_listar;

    -- Si el criterio contiene solamente números,
    -- buscar únicamente por ID de compra
    ELSEIF v_filtro REGEXP '^[0-9]+$' THEN

        SELECT 
            id_compra,
            fecha,
            total,
            id_proveedor,
            nombre_proveedor,
            ruc_proveedor,
            id_almacen,
            nombre_almacen,
            id_personal,
            nombre_personal
        FROM vw_compra_listar
        WHERE id_compra = CAST(v_filtro AS UNSIGNED);

    -- Si no es número, buscar por proveedor o RUC
    ELSE

        SELECT 
            id_compra,
            fecha,
            total,
            id_proveedor,
            nombre_proveedor,
            ruc_proveedor,
            id_almacen,
            nombre_almacen,
            id_personal,
            nombre_personal
        FROM vw_compra_listar
        WHERE nombre_proveedor LIKE CONCAT('%', v_filtro, '%')
           OR ruc_proveedor LIKE CONCAT('%', v_filtro, '%');

    END IF;

END //

DELIMITER ;

-- Ejemplos de uso:
CALL sp_compra_buscar('Tech Supply'); -- Búsqueda por proveedor
CALL sp_compra_buscar('1');           -- Búsqueda por id_compra
CALL sp_compra_buscar('');            -- Devuelve todas las compras


-- ============================================================
-- 3. STORED PROCEDURE: sp_compra_insertar
-- Inserta una nueva compra realizando las validaciones de claves
-- foráneas existentes y montos válidos.
-- ============================================================
DROP PROCEDURE IF EXISTS sp_compra_insertar;

DELIMITER $$

CREATE PROCEDURE sp_compra_insertar(
    IN p_idProveedor INT,
    IN p_idAlmacen INT,
    IN p_idPersonal INT,
    IN p_total DECIMAL(10,2)
)
BEGIN
    -- Validar FK de proveedor
    IF p_idProveedor IS NULL OR p_idProveedor <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de proveedor no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM proveedor WHERE id_proveedor = p_idProveedor) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El proveedor seleccionado no existe.';
    END IF;

    -- Validar FK de almacén
    IF p_idAlmacen IS NULL OR p_idAlmacen <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de almacén no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_idAlmacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén seleccionado no existe.';
    END IF;

    -- Validar FK de personal
    IF p_idPersonal IS NULL OR p_idPersonal <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de personal no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_idPersonal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal seleccionado no existe.';
    END IF;

    -- Validar total de la compra
    IF p_total IS NULL OR p_total < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El total de la compra no puede ser negativo o nulo.';
    END IF;

    -- Inserción de la compra
    INSERT INTO compra (
        id_proveedor,
        id_almacen,
        id_personal,
        fecha,
        total
    )
    VALUES (
        p_idProveedor,
        p_idAlmacen,
        p_idPersonal,
        NOW(),
        p_total
    );
END$$

DELIMITER ;

-- Ejemplo de uso:
CALL sp_compra_insertar(1, 1, 7, 1500.00);


-- ============================================================
-- 4. STORED PROCEDURE: sp_compra_actualizar
-- Actualiza una compra existente con sus debidas validaciones.
-- ============================================================
DROP PROCEDURE IF EXISTS sp_compra_actualizar;
DELIMITER //

CREATE PROCEDURE sp_compra_actualizar(
    IN p_id_compra INT,
    IN p_id_proveedor INT,
    IN p_id_almacen INT,
    IN p_id_personal INT,
    IN p_total DECIMAL(10,2)
)
BEGIN
    -- 1. Validar que el ID de compra sea válido
    IF p_id_compra <= 0 OR p_id_compra IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de la compra no es válido.';
    END IF;

    -- 2. Validar que la compra a modificar exista
    IF NOT EXISTS (SELECT 1 FROM compra WHERE id_compra = p_id_compra) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La compra que intenta modificar no existe.';
    END IF;

    -- 3. Validar que el proveedor exista
    IF NOT EXISTS (SELECT 1 FROM proveedor WHERE id_proveedor = p_id_proveedor) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El proveedor especificado no existe.';
    END IF;

    -- 4. Validar que el almacén exista
    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_id_almacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén especificado no existe.';
    END IF;

    -- 5. Validar que el personal exista
    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_id_personal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal especificado no existe.';
    END IF;

    -- 6. Validar que el total no sea negativo
    IF p_total < 0 OR p_total IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El total de la compra no puede ser negativo.';
    END IF;

    -- Si pasa todas las validaciones, actualizamos el registro
    UPDATE compra
    SET id_proveedor = p_id_proveedor,
        id_almacen   = p_id_almacen,
        id_personal  = p_id_personal,
        total        = p_total
    WHERE id_compra = p_id_compra;
END //

DELIMITER ;

-- Ejemplo de uso:
CALL sp_compra_actualizar(1, 1, 1, 7, 3850.00);
/*FINALIZA*/



/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA VENTA :::::::::::::::::::*/

-- ============================================================
-- 1. Vista para mostrar ventas
-- ============================================================
CREATE OR REPLACE VIEW vw_venta_activa AS
SELECT 
    v.id_venta,
    v.fecha,
    v.total,
    v.id_cliente,
    c.nombre_cliente,
    v.id_almacen,
    a.nombre_almacen,
    v.id_personal,
    CONCAT(p.nombres, ' ', p.apellidos) AS nombre_personal
FROM venta v
INNER JOIN cliente c ON v.id_cliente = c.id_cliente
INNER JOIN almacen a ON v.id_almacen = a.id_almacen
INNER JOIN personal p ON v.id_personal = p.id_personal;

-- Ejemplo de consulta a la vista:
SELECT * FROM vw_venta_activa;


-- ============================================================
-- 2. Stored Procedure para buscar ventas
-- Utiliza obligatoriamente la vista vw_venta_activa
-- ============================================================
USE bdalmacen;

DROP PROCEDURE IF EXISTS sp_venta_buscar;
DELIMITER //

CREATE PROCEDURE sp_venta_buscar(
    IN p_filtro VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
)
BEGIN
    DECLARE v_filtro VARCHAR(102) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    SET v_filtro = TRIM(IFNULL(p_filtro, ''));

    SELECT 
        id_venta,
        fecha,
        total,
        id_cliente,
        nombre_cliente,
        id_almacen,
        nombre_almacen,
        id_personal,
        nombre_personal
    FROM vw_venta_activa
    WHERE (v_filtro = '' 
        OR nombre_cliente LIKE CONCAT('%', v_filtro, '%')
        OR nombre_almacen LIKE CONCAT('%', v_filtro, '%')
        OR nombre_personal LIKE CONCAT('%', v_filtro, '%')
        OR CAST(id_venta AS CHAR) = v_filtro);
END //

DELIMITER ;

-- Ejemplos de uso:
-- CALL sp_venta_buscar('Corporación ABC');
-- CALL sp_venta_buscar(''); -- Devuelve todas las ventas


-- ============================================================
-- 3. Stored Procedure para guardar (insertar) registros de venta
-- ============================================================
DROP PROCEDURE IF EXISTS sp_venta_insertar;
DELIMITER //

CREATE PROCEDURE sp_venta_insertar(
    IN p_id_cliente INT,
    IN p_id_almacen INT,
    IN p_id_personal INT,
    IN p_total DECIMAL(10,2)
)
BEGIN
    -- 1. Validar que los identificadores recibidos no sean nulos ni inválidos
    IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del cliente no es válido.';
    END IF;

    IF p_id_almacen IS NULL OR p_id_almacen <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del almacén no es válido.';
    END IF;

    IF p_id_personal IS NULL OR p_id_personal <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del personal no es válido.';
    END IF;

    -- 2. Validar que el total sea un monto válido
    IF p_total IS NULL OR p_total < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El total de la venta no puede ser negativo o nulo.';
    END IF;

    -- 3. Validar la existencia de las entidades referenciadas
    IF NOT EXISTS (SELECT 1 FROM cliente WHERE id_cliente = p_id_cliente) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente especificado no existe en la base de datos.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_id_almacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén especificado no existe en la base de datos.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_id_personal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal especificado no existe en la base de datos.';
    END IF;

    -- 4. Proceder con la inserción del registro
    INSERT INTO venta (
        id_cliente, 
        id_almacen, 
        id_personal, 
        fecha, 
        total
    ) VALUES (
        p_id_cliente, 
        p_id_almacen, 
        p_id_personal, 
        NOW(), 
        p_total
    );
END //

DELIMITER ;

-- Ejemplo de uso:
-- CALL sp_venta_insertar(1, 1, 5, 250.00);


-- ============================================================
-- 4. Stored Procedure para modificar registros de venta
-- ============================================================
DROP PROCEDURE IF EXISTS sp_venta_actualizar;
DELIMITER //
CREATE PROCEDURE sp_venta_actualizar(
    IN p_id_venta INT,
    IN p_id_cliente INT,
    IN p_id_almacen INT,
    IN p_id_personal INT,
    IN p_total DECIMAL(10,2)
)
BEGIN
    -- 1. Validar que la venta a modificar exista
    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de la venta no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM venta WHERE id_venta = p_id_venta) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La venta que intenta modificar no existe.';
    END IF;

    -- 2. Validar parámetros recibidos
    IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente especificado no es válido.';
    END IF;

    IF p_id_almacen IS NULL OR p_id_almacen <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén especificado no es válido.';
    END IF;

    IF p_id_personal IS NULL OR p_id_personal <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal especificado no es válido.';
    END IF;

    IF p_total IS NULL OR p_total < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El total de la venta no puede ser negativo o nulo.';
    END IF;

    -- 3. Validar existencia de las llaves relacionales
    IF NOT EXISTS (SELECT 1 FROM cliente WHERE id_cliente = p_id_cliente) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente especificado no existe.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_id_almacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén especificado no existe.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_id_personal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal especificado no existe.';
    END IF;

    -- 4. Actualizar la venta
    UPDATE venta
    SET id_cliente = p_id_cliente,
        id_almacen = p_id_almacen,
        id_personal = p_id_personal,
        total = p_total
    WHERE id_venta = p_id_venta;
END //
DELIMITER ;

-- Ejemplo de uso:
-- CALL sp_venta_actualizar(1, 1, 1, 5, 2920.00);
/*FINALIZA*/

/*::::::::::::::::::MANTENIMIENTO CRUD: TABLA MOVIMIENTO_INVENTARIO::::::::::::::::::*/
-- =============================================================================
-- 1. VISTA: vw_movimiento_inventario_activo
-- =============================================================================

CREATE OR REPLACE VIEW vw_movimiento_inventario_activo AS
SELECT 
    mi.id_movimiento_inventario,
    mi.id_producto,
    p.nombre_producto,
    p.codigo AS codigo_producto,
    mi.id_almacen,
    a.nombre_almacen,
    mi.id_personal,
    CONCAT(per.nombres, ' ', per.apellidos) AS nombre_personal,
    mi.tipo_movimiento,
    mi.cantidad,
    mi.fecha
FROM movimiento_inventario mi
INNER JOIN producto p 
    ON mi.id_producto = p.id_producto
INNER JOIN almacen a 
    ON mi.id_almacen = a.id_almacen
INNER JOIN personal per 
    ON mi.id_personal = per.id_personal;

-- Ejemplo de consulta de la vista:
SELECT * FROM vw_movimiento_inventario_activo;


-- =============================================================================
-- 2. STORED PROCEDURE: BÚSQUEDA DE MOVIMIENTOS
-- =============================================================================

DROP PROCEDURE IF EXISTS sp_movimiento_inventario_buscar;

DELIMITER //

CREATE PROCEDURE sp_movimiento_inventario_buscar(
    IN p_filtro VARCHAR(100)
)
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_filtro);

    SELECT 
        id_movimiento_inventario,
        id_producto,
        nombre_producto,
        codigo_producto,
        id_almacen,
        nombre_almacen,
        id_personal,
        nombre_personal,
        tipo_movimiento,
        cantidad,
        fecha
    FROM vw_movimiento_inventario_activo
    WHERE (v_filtro = '' OR v_filtro IS NULL 
           OR nombre_producto LIKE CONCAT('%', v_filtro, '%')
           OR codigo_producto LIKE CONCAT('%', v_filtro, '%')
           OR tipo_movimiento LIKE CONCAT('%', v_filtro, '%')
           OR nombre_almacen LIKE CONCAT('%', v_filtro, '%'));
END //

DELIMITER ;

-- Ejemplos de uso:
-- CALL sp_movimiento_inventario_buscar('ENTRADA');
-- CALL sp_movimiento_inventario_buscar('');


-- ========================================
-- 3. STORED PROCEDURE: INSERTAR (GUARDAR) 
-- ========================================
DROP PROCEDURE IF EXISTS sp_movimiento_inventario_insertar;

DELIMITER $$

CREATE PROCEDURE sp_movimiento_inventario_insertar(
    IN p_id_producto INT,
    IN p_id_almacen INT,
    IN p_id_personal INT,
    IN p_tipo_movimiento VARCHAR(20),
    IN p_cantidad INT
)
BEGIN
    -- Declaramos la variable limpia correctamente
    DECLARE v_tipoLim VARCHAR(20);
    SET v_tipoLim = UPPER(TRIM(p_tipo_movimiento));

    -- Validamos que los campos obligatorios no vengan vacíos o nulos
    IF v_tipoLim = '' OR v_tipoLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de movimiento no puede estar vacío.';
    END IF;

    IF p_id_producto <= 0 OR p_id_producto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;

    IF p_id_almacen <= 0 OR p_id_almacen IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del almacén no es válido.';
    END IF;

    IF p_id_personal <= 0 OR p_id_personal IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del personal no es válido.';
    END IF;

    IF p_cantidad = 0 OR p_cantidad IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad del movimiento debe ser diferente de cero.';
    END IF;

    -- Verificar si ya existe un movimiento idéntico registrado para evitar duplicados
    IF NOT EXISTS (
        SELECT 1 
        FROM movimiento_inventario 
        WHERE id_producto = p_id_producto
          AND id_almacen = p_id_almacen
          AND id_personal = p_id_personal
          AND tipo_movimiento = v_tipoLim
          AND cantidad = p_cantidad
    ) THEN
        -- Si no existe, insertamos con el valor limpio y la fecha actual
        INSERT INTO movimiento_inventario (
            id_producto, 
            id_almacen, 
            id_personal, 
            tipo_movimiento, 
            cantidad, 
            fecha
        ) 
        VALUES (
            p_id_producto, 
            p_id_almacen, 
            p_id_personal, 
            v_tipoLim, 
            p_cantidad, 
            NOW()
        );
    ELSE
        -- Si ya existe, lanzamos un error controlado
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El movimiento de inventario ya existe, no se puede insertar un duplicado.';
    END IF;
END$$

DELIMITER ;

-- Ejemplo de uso:
CALL sp_movimiento_inventario_insertar(1, 1, 2, 'ENTRADA', 15);


-- =============================================================================
-- 4. STORED PROCEDURE: MODIFICAR (ACTUALIZAR)
-- =============================================================================

DROP PROCEDURE IF EXISTS sp_movimiento_inventario_actualizar;

DELIMITER //

CREATE PROCEDURE sp_movimiento_inventario_actualizar(
    IN p_id_movimiento_inventario INT,
    IN p_id_producto INT,
    IN p_id_almacen INT,
    IN p_id_personal INT,
    IN p_tipo_movimiento VARCHAR(20),
    IN p_cantidad INT
)
BEGIN
    DECLARE v_tipoLim VARCHAR(20);
    SET v_tipoLim = UPPER(TRIM(p_tipo_movimiento));

    -- Validar ID del registro a modificar
    IF p_id_movimiento_inventario IS NULL OR p_id_movimiento_inventario <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del movimiento de inventario no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM movimiento_inventario WHERE id_movimiento_inventario = p_id_movimiento_inventario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El movimiento de inventario que intenta modificar no existe.';
    END IF;

    -- Validar existencia en tablas padre
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_id_producto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto especificado no existe.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_id_almacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén especificado no existe.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_id_personal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal especificado no existe.';
    END IF;

    -- Validar texto y cantidad
    IF v_tipoLim = '' OR v_tipoLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de movimiento no puede estar vacío.';
    END IF;

    IF p_cantidad IS NULL OR p_cantidad = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad del movimiento debe ser diferente de cero.';
    END IF;

    -- Actualizar registro
    UPDATE movimiento_inventario
    SET id_producto     = p_id_producto,
        id_almacen      = p_id_almacen,
        id_personal     = p_id_personal,
        tipo_movimiento = v_tipoLim,
        cantidad        = p_cantidad
    WHERE id_movimiento_inventario = p_id_movimiento_inventario;
END //

DELIMITER ;

-- Ejemplo de uso:
-- CALL sp_movimiento_inventario_actualizar(1, 3, 1, 2, 'ENTRADA', 10);
/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA INVENTARIO :::::::::::::::::::*/
--=============================================================================
-- 1. Vista para listar inventario activo relacionando producto y almacén
--=============================================================================
CREATE OR REPLACE VIEW vw_inventario_activo AS
SELECT 
    I.id_inventario,
    I.id_producto,
    P.nombre_producto,
    I.id_almacen,
    A.nombre_almacen,
    I.stock_actual,
    I.stock_minimo,
    I.ultima_actualizacion
FROM inventario I
INNER JOIN producto P ON I.id_producto = P.id_producto
INNER JOIN almacen A ON I.id_almacen = A.id_almacen;

-- Prueba de la vista
SELECT * FROM vw_inventario_activo;

--=============================================================================
-- 2. Procedure para buscar registros de inventario por nombre de producto o almacén
--=============================================================================
DROP PROCEDURE IF EXISTS sp_inventario_buscar;
DELIMITER //
CREATE PROCEDURE sp_inventario_buscar(IN p_filtro VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_filtro);

    SELECT 
        id_inventario,
        id_producto,
        nombre_producto,
        id_almacen,
        nombre_almacen,
        stock_actual,
        stock_minimo,
        ultima_actualizacion
    FROM vw_inventario_activo
    WHERE (v_filtro = '' OR nombre_producto LIKE CONCAT('%', v_filtro, '%') OR nombre_almacen LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_inventario_buscar('Paracetamol');
CALL sp_inventario_buscar(''); -- Devuelve todos los registros si se envía vacío

--=============================================================================
-- 3. Procedure para insertar registro en inventario
--=============================================================================
DROP PROCEDURE IF EXISTS sp_inventario_insertar;
DELIMITER $$
CREATE PROCEDURE sp_inventario_insertar(
    IN p_idProducto INT,
    IN p_idAlmacen INT,
    IN p_stockActual INT,
    IN p_stockMinimo INT
)
BEGIN
    -- Validaciones de IDs y stock
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de producto especificado no es válido.';
    END IF;

    IF p_idAlmacen <= 0 OR p_idAlmacen IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de almacén especificado no es válido.';
    END IF;

    IF p_stockActual < 0 OR p_stockMinimo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Las cantidades de stock no pueden ser valores negativos.';
    END IF;

    -- Validar existencia en tablas padre
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto seleccionado no existe en la base de datos.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM almacen WHERE id_almacen = p_idAlmacen) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El almacén seleccionado no existe en la base de datos.';
    END IF;

    -- Validar que no exista la combinación Producto + Almacén
    IF NOT EXISTS (
        SELECT 1 
        FROM inventario 
        WHERE id_producto = p_idProducto AND id_almacen = p_idAlmacen
    ) THEN
        INSERT INTO inventario (id_almacen, id_producto, stock_actual, stock_minimo, ultima_actualizacion) 
        VALUES (p_idAlmacen, p_idProducto, p_stockActual, p_stockMinimo, NOW());
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe un registro de inventario para este producto en el almacén especificado.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_inventario_insertar(1, 2, 100, 10);
--=============================================================================
-- 4. Procedure para modificar un registro de inventario
--=============================================================================
DROP PROCEDURE IF EXISTS sp_inventario_actualizar;
DELIMITER //
CREATE PROCEDURE sp_inventario_actualizar(
    IN p_idInventario INT,
    IN p_idProducto INT,
    IN p_idAlmacen INT,
    IN p_stockActual INT,
    IN p_stockMinimo INT
)
BEGIN
    -- 1. Validar que el código de inventario sea válido
    IF p_idInventario <= 0 OR p_idInventario IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código del registro de inventario no es válido.';
    END IF;

    -- 2. Validar cantidades de stock
    IF p_stockActual < 0 OR p_stockMinimo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Las cantidades de stock no pueden ser valores negativos.';
    END IF;

    -- 3. Validar que el registro exista
    IF NOT EXISTS (SELECT 1 FROM inventario WHERE id_inventario = p_idInventario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El registro de inventario que intenta modificar no existe.';
    END IF;

    -- 4. Validar que no exista otra asignación duplicada de Producto + Almacén
    IF EXISTS (
        SELECT 1 FROM inventario 
        WHERE id_producto = p_idProducto AND id_almacen = p_idAlmacen AND id_inventario != p_idInventario
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro registro de inventario asignado a ese producto y almacén.';
    ELSE
        UPDATE inventario
        SET id_producto = p_idProducto,
            id_almacen = p_idAlmacen,
            stock_actual = p_stockActual,
            stock_minimo = p_stockMinimo,
            ultima_actualizacion = NOW()
        WHERE id_inventario = p_idInventario;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
sp_inventario_actualizar(11, 1, 2, 150, 15);
/*FINALIZA*/

/*:::::::::::::::::: MANTENIMIENTO CRUD A LA TABLA PERSONAL :::::::::::::::::::*/
--=============================================================================
-- 1. Vista para listar personal relacionando tipo, cargo, área y sucursal
--=============================================================================
CREATE OR REPLACE VIEW vw_personal_activo AS
SELECT 
    PER.id_personal,
    PER.id_tipo_personal,
    TP.nombre_tipo_personal,
    PER.id_cargo,
    C.nombre_cargo,
    PER.id_area,
    A.nombre_area,
    PER.id_sucursal,
    S.nombre AS nombre_sucursal,
    PER.nombres,
    PER.apellidos,
    PER.documento
FROM personal PER
INNER JOIN tipo_personal TP ON PER.id_tipo_personal = TP.id_tipo_personal
INNER JOIN cargo C ON PER.id_cargo = C.id_cargo
INNER JOIN area A ON PER.id_area = A.id_area
INNER JOIN sucursal S ON PER.id_sucursal = S.id_sucursal;

-- Prueba de la vista
SELECT * FROM vw_personal_activo;

--=============================================================================
-- 2. Procedure para buscar personal por nombres, apellidos o documento
--=============================================================================
DROP PROCEDURE IF EXISTS sp_personal_buscar;
DELIMITER //
CREATE PROCEDURE sp_personal_buscar(IN p_filtro VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_filtro);

    SELECT 
        id_personal,
        id_tipo_personal,
        nombre_tipo_personal,
        id_cargo,
        nombre_cargo,
        id_area,
        nombre_area,
        id_sucursal,
        nombre_sucursal,
        nombres,
        apellidos,
        documento
    FROM vw_personal_activo
    WHERE (v_filtro = '' 
        OR nombres LIKE CONCAT('%', v_filtro, '%') 
        OR apellidos LIKE CONCAT('%', v_filtro, '%') 
        OR documento LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;
-- . Probar la Búsqueda (por nombre, apellido o documento)
CALL sp_personal_buscar('Juan');
CALL sp_personal_buscar('77889900');
CALL sp_personal_buscar(''); -- Lista todo el personal

--=============================================================================
-- 3. Procedure para insertar nuevo personal
--=============================================================================
DROP PROCEDURE IF EXISTS sp_personal_insertar;
DELIMITER $$
CREATE PROCEDURE sp_personal_insertar(
    IN p_idTipoPersonal INT,
    IN p_idCargo INT,
    IN p_idArea INT,
    IN p_idSucursal INT,
    IN p_nombres VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_documento VARCHAR(20)
)
BEGIN
    DECLARE v_nombres VARCHAR(100);
    DECLARE v_apellidos VARCHAR(100);
    DECLARE v_documento VARCHAR(20);

    SET v_nombres = TRIM(p_nombres);
    SET v_apellidos = TRIM(p_apellidos);
    SET v_documento = TRIM(p_documento);

    -- Validaciones
    IF p_idTipoPersonal <= 0 OR p_idTipoPersonal IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de tipo de personal no es válido.';
    END IF;

    IF p_idCargo <= 0 OR p_idCargo IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de cargo no es válido.';
    END IF;

    IF p_idArea <= 0 OR p_idArea IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de área no es válido.';
    END IF;

    IF p_idSucursal <= 0 OR p_idSucursal IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de sucursal no es válido.';
    END IF;

    IF v_nombres = '' OR v_nombres IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Los nombres no pueden estar vacíos.';
    END IF;

    IF v_apellidos = '' OR v_apellidos IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Los apellidos no pueden estar vacíos.';
    END IF;

    IF v_documento = '' OR v_documento IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El documento no puede estar vacío.';
    END IF;

    -- Validar documento duplicado
    IF NOT EXISTS (SELECT 1 FROM personal WHERE documento = v_documento) THEN
        INSERT INTO personal (id_tipo_personal, id_cargo, id_area, id_sucursal, nombres, apellidos, documento) 
        VALUES (p_idTipoPersonal, p_idCargo, p_idArea, p_idSucursal, v_nombres, v_apellidos, v_documento);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un personal registrado con ese mismo número de documento.';
    END IF;
END$$
DELIMITER ;
-- 2. Probar la Inserción de un nuevo Personal (Asegúrate de usar IDs válidos de tu BD)
-- Parámetros: (idTipoPersonal, idCargo, idArea, idSucursal, nombres, apellidos, documento)
CALL sp_personal_insertar(1, 1, 1, 1, 'Juan Carlos', 'Pérez Gómez', '77889900');

--=============================================================================
-- 4. Procedure para modificar un registro de personal
--=============================================================================
DROP PROCEDURE IF EXISTS sp_personal_actualizar;
DELIMITER //
CREATE PROCEDURE sp_personal_actualizar(
    IN p_idPersonal INT,
    IN p_idTipoPersonal INT,
    IN p_idCargo INT,
    IN p_idArea INT,
    IN p_idSucursal INT,
    IN p_nombres VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_documento VARCHAR(20)
)
BEGIN
    DECLARE v_nombres VARCHAR(100);
    DECLARE v_apellidos VARCHAR(100);
    DECLARE v_documento VARCHAR(20);

    SET v_nombres = TRIM(p_nombres);
    SET v_apellidos = TRIM(p_apellidos);
    SET v_documento = TRIM(p_documento);

    IF p_idPersonal <= 0 OR p_idPersonal IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El código de personal no es válido.';
    END IF;

    IF v_nombres = '' OR v_nombres IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Los nombres no pueden estar vacíos.';
    END IF;

    IF v_apellidos = '' OR v_apellidos IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Los apellidos no pueden estar vacíos.';
    END IF;

    IF v_documento = '' OR v_documento IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El documento no puede estar vacío.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_idPersonal) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El registro de personal que intenta modificar no existe.';
    END IF;

    IF EXISTS (
        SELECT 1 FROM personal 
        WHERE documento = v_documento AND id_personal != p_idPersonal
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe otro personal registrado con ese mismo número de documento.';
    ELSE
        UPDATE personal
        SET id_tipo_personal = p_idTipoPersonal,
            id_cargo = p_idCargo,
            id_area = p_idArea,
            id_sucursal = p_idSucursal,
            nombres = v_nombres,
            apellidos = v_apellidos,
            documento = v_documento
        WHERE id_personal = p_idPersonal;
    END IF;
END //
DELIMITER ;
-- 4. Probar la Actualización / Modificación
-- Reemplaza '1' por el id_personal recién creado si es distinto
CALL sp_personal_actualizar(1, 1, 1, 1, 1, 'Juan Carlos', 'Pérez Mendoza', '77889900');



