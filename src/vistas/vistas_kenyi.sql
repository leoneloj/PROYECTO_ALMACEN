-- ============================================================
-- CREAR VISTA : AREA
-- ============================================================

-- 1. Vista mostrar áreas
CREATE OR REPLACE VIEW vw_area_activa AS
SELECT 
    A.id_area,
    A.nombre_area,
    A.descripcion
FROM area A;

-- Prueba de la vista
SELECT * FROM vw_area_activa;


-- ============================================================
-- MÓDULO CRUD: AREA
-- ============================================================


-- 2. Procedure para buscar las áreas
DROP PROCEDURE IF EXISTS sp_area_buscar;
DELIMITER //
CREATE PROCEDURE sp_area_buscar(IN p_nombreArea VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(52);
    SET v_filtro = TRIM(p_nombreArea);
    
    SELECT 
        id_area,
        nombre_area,
        descripcion
    FROM vw_area_activa
    WHERE (v_filtro = '' OR nombre_area LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_area_buscar('Logística');
CALL sp_area_buscar('');

-- 3. Procedure para insertar un área
DROP PROCEDURE IF EXISTS sp_area_insertar;
DELIMITER $$
CREATE PROCEDURE sp_area_insertar(
    IN p_nombreArea VARCHAR(50),
    IN p_descripcion VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nombreArea);
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del área no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM area WHERE nombre_area = v_nombreLim) THEN
        INSERT INTO area (nombre_area, descripcion)
        VALUES (v_nombreLim, NULLIF(TRIM(p_descripcion), ''));
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El área ya existe, no se puede insertar un duplicado.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_area_insertar('Innovación y Desarrollo', 'Nuevos proyectos tecnológicos');

-- 4. Procedure para modificar un área
DROP PROCEDURE IF EXISTS sp_area_actualizar;
DELIMITER //
CREATE PROCEDURE sp_area_actualizar (
    IN p_idArea INT,
    IN p_nuevoNombre VARCHAR(50),
    IN p_nuevaDescripcion VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nuevoNombre);
    
    IF p_idArea <= 0 OR p_idArea IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del área no es válido.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del área no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM area WHERE id_area = p_idArea) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El área que intenta modificar no existe.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM area
        WHERE nombre_area = v_nombreLim AND id_area != p_idArea
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otra área registrada con ese mismo nombre.';
    ELSE
        UPDATE area
        SET nombre_area = v_nombreLim,
            descripcion = NULLIF(TRIM(p_nuevaDescripcion), '')
        WHERE id_area = p_idArea;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_area_actualizar(1, 'Tecnologías de la Información Avanzada', 'Sistemas e infraestructura moderna');





-- ============================================================
-- CREAR VISTA: CARGO
-- ============================================================

-- 1. Vista mostrar cargos activos
CREATE OR REPLACE VIEW vw_cargo_activo AS
SELECT 
    C.id_cargo,
    C.nombre_cargo,
    CASE 
        WHEN C.estado_cargo = 1 THEN 'Activo'
        ELSE 'Inactivo'
    END AS estado_cargo
FROM cargo C
WHERE C.estado_cargo = 1;

-- Prueba de la vista
SELECT * FROM vw_cargo_activo;


-- 1. Vista mostrar cargos inactivos
CREATE OR REPLACE VIEW vw_cargo_inactivos AS
SELECT 
    C.id_cargo,
    C.nombre_cargo,
    CASE 
        WHEN C.estado_cargo = 0 THEN 'Inactivo'
        ELSE 'Activo'
    END AS estado_cargo
FROM cargo C
WHERE C.estado_cargo = 0;

-- Prueba de la vista
SELECT * FROM vw_cargo_inactivos;


-- ============================================================
-- MÓDULO CRUD: CARGO
-- ============================================================

-- 2. Procedure para buscar cargos
DROP PROCEDURE IF EXISTS sp_cargo_buscar;
DELIMITER //
CREATE PROCEDURE sp_cargo_buscar(IN p_nombreCargo VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(52);
    SET v_filtro = TRIM(p_nombreCargo);
    
    SELECT 
        id_cargo,
        nombre_cargo
    FROM vw_cargo_activo
    WHERE (v_filtro = '' OR nombre_cargo LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_cargo_buscar('Almacén');
CALL sp_cargo_buscar('');

-- 3. Procedure para insertar un cargo
DROP PROCEDURE IF EXISTS sp_cargo_insertar;
DELIMITER $$
CREATE PROCEDURE sp_cargo_insertar(IN p_nombreCargo VARCHAR(50))
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nombreCargo);
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del cargo no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM cargo WHERE nombre_cargo = v_nombreLim) THEN
        INSERT INTO cargo (nombre_cargo, estado_cargo)
        VALUES (v_nombreLim, 1);
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo ya existe, no se puede insertar un duplicado.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_cargo_insertar('Practicante de Sistemas');

-- 4. Procedure para modificar un cargo
DROP PROCEDURE IF EXISTS sp_cargo_actualizar;
DELIMITER //
CREATE PROCEDURE sp_cargo_actualizar (
    IN p_idCargo INT,
    IN p_nuevoNombre VARCHAR(50)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nuevoNombre);
    
    IF p_idCargo <= 0 OR p_idCargo IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del cargo no es válido.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del cargo no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM cargo WHERE id_cargo = p_idCargo) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo que intenta modificar no existe.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM cargo
        WHERE nombre_cargo = v_nombreLim AND id_cargo != p_idCargo
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro cargo registrado con ese mismo nombre.';
    ELSE
        UPDATE cargo
        SET nombre_cargo = v_nombreLim
        WHERE id_cargo = p_idCargo;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_cargo_actualizar(1, 'Administrador General del Sistema');

-- 5. Procedure para dar de baja un cargo
DROP PROCEDURE IF EXISTS sp_cargo_desactivar;
DELIMITER //
CREATE PROCEDURE sp_cargo_desactivar(IN p_idCargo INT)
BEGIN
    IF p_idCargo <= 0 OR p_idCargo IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del cargo no es válido.';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM cargo WHERE id_cargo = p_idCargo) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El ID de cargo especificado no existe.';
    END IF;
    
    IF EXISTS (SELECT 1 FROM cargo WHERE id_cargo = p_idCargo AND estado_cargo = 0) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo ya se encuentra desactivado.';
    END IF;
    
    UPDATE cargo
    SET estado_cargo = 0
    WHERE id_cargo = p_idCargo;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_cargo_desactivar(10);




-- ============================================================
-- VISTA :EMPRESA
-- ============================================================

-- 1. Vista mostrar empresas
CREATE OR REPLACE VIEW vw_empresa_activa AS
SELECT 
    E.id_empresa,
    E.razon_social,
    E.ruc,
    E.telefono,
    E.correo
FROM empresa E;

-- Prueba de la vista
SELECT * FROM vw_empresa_activa;

-- ============================================================
-- MÓDULO CRUD: EMPRESA
-- ============================================================


-- 2. Procedure para buscar empresas
DROP PROCEDURE IF EXISTS sp_empresa_buscar;
DELIMITER //
CREATE PROCEDURE sp_empresa_buscar(IN p_criterio VARCHAR(100))
BEGIN
    DECLARE v_filtro VARCHAR(102);
    SET v_filtro = TRIM(p_criterio);
    
    SELECT 
        id_empresa,
        razon_social,
        ruc,
        telefono,
        correo
    FROM vw_empresa_activa
    WHERE (v_filtro = '' OR razon_social LIKE CONCAT('%', v_filtro, '%') OR ruc LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_empresa_buscar('Global');
CALL sp_empresa_buscar('');

-- 3. Procedure para insertar una empresa
DROP PROCEDURE IF EXISTS sp_empresa_insertar;
DELIMITER $$
CREATE PROCEDURE sp_empresa_insertar(
    IN p_razonSocial VARCHAR(100),
    IN p_ruc VARCHAR(20),
    IN p_telefono VARCHAR(12),
    IN p_correo VARCHAR(100)
)
BEGIN
    DECLARE v_razonLim VARCHAR(100);
    DECLARE v_rucLیم VARCHAR(20);
    
    SET v_razonLim = TRIM(p_razonSocial);
    SET v_rucLیم = TRIM(p_ruc);
    
    IF v_razonLim = '' OR v_razonLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La razón social no puede estar vacía.';
    END IF;
    
    IF v_rucLیم = '' OR v_rucLیم IS NULL OR LENGTH(v_rucLیم) < 11 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El número de RUC no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM empresa WHERE ruc = v_rucLیم) THEN
        INSERT INTO empresa (razon_social, ruc, telefono, correo)
        VALUES (v_razonLim, v_rucLیم, NULLIF(TRIM(p_telefono), ''), NULLIF(TRIM(p_correo), ''));
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe una empresa registrada con ese mismo RUC.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_empresa_insertar('Inversiones Tecnológicas Perú S.A.C.', '20600000011', '912345699', 'contacto@invertech.pe');

-- 4. Procedure para modificar una empresa
DROP PROCEDURE IF EXISTS sp_empresa_actualizar;
DELIMITER //
CREATE PROCEDURE sp_empresa_actualizar (
    IN p_idEmpresa INT,
    IN p_nuevaRazonSocial VARCHAR(100),
    IN p_nuevoRuc VARCHAR(20),
    IN p_nuevoTelefono VARCHAR(12),
    IN p_nuevoCorreo VARCHAR(100)
)
BEGIN
    DECLARE v_razonLim VARCHAR(100);
    DECLARE v_rucLیم VARCHAR(20);
    
    SET v_razonLim = TRIM(p_nuevaRazonSocial);
    SET v_rucLیم = TRIM(p_nuevoRuc);
    
    IF p_idEmpresa <= 0 OR p_idEmpresa IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la empresa no es válido.';
    END IF;
    
    IF v_razonLim = '' OR v_razonLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La razón social no puede estar vacía.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM empresa WHERE id_empresa = p_idEmpresa) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La empresa que intenta modificar no existe.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM empresa
        WHERE ruc = v_rucLیم AND id_empresa != p_idEmpresa
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otra empresa registrada con ese mismo número de RUC.';
    ELSE
        UPDATE empresa
        SET razon_social = v_razonLim,
            ruc = v_rucLیم,
            telefono = NULLIF(TRIM(p_nuevoTelefono), ''),
            correo = NULLIF(TRIM(p_nuevoCorreo), '')
        WHERE id_empresa = p_idEmpresa;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_empresa_actualizar(1, 'Comercializadora Global Actualizada S.A.C.', '20100000001', '912345601', 'ventas@global.com');


-- ============================================================
--VISTA: SUCURSAL
-- ============================================================

-- 1. Vista mostrar sucursales (incluyendo la razón social de la empresa)
CREATE OR REPLACE VIEW vw_sucursal_activa AS
SELECT 
    S.id_sucursal,
    S.id_empresa,
    E.razon_social AS empresa_nombre,
    S.nombre AS nombre_sucursal,
    S.direccion,
    S.telefono
FROM sucursal S
INNER JOIN empresa E ON S.id_empresa = E.id_empresa;

-- Prueba de la vista
SELECT * FROM vw_sucursal_activa;


-- ============================================================
-- MÓDULO CRUD: SUCURSAL
-- ============================================================
-- 2. Procedure para buscar sucursales
DROP PROCEDURE IF EXISTS sp_sucursal_buscar;
DELIMITER //
CREATE PROCEDURE sp_sucursal_buscar(IN p_nombreSucursal VARCHAR(60))
BEGIN
    DECLARE v_filtro VARCHAR(62);
    SET v_filtro = TRIM(p_nombreSucursal);
    
    SELECT 
        id_sucursal,
        id_empresa,
        empresa_nombre,
        nombre_sucursal,
        direccion,
        telefono
    FROM vw_sucursal_activa
    WHERE (v_filtro = '' OR nombre_sucursal LIKE CONCAT('%', v_filtro, '%') OR empresa_nombre LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_sucursal_buscar('Lima');
CALL sp_sucursal_buscar('');

-- 3. Procedure para insertar una sucursal
DROP PROCEDURE IF EXISTS sp_sucursal_insertar;
DELIMITER $$
CREATE PROCEDURE sp_sucursal_insertar(
    IN p_idEmpresa INT,
    IN p_nombre VARCHAR(60),
    IN p_direccion VARCHAR(100),
    IN p_telefono VARCHAR(12)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(60);
    SET v_nombreLim = TRIM(p_nombre);
    
    IF p_idEmpresa <= 0 OR p_idEmpresa IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la empresa no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM empresa WHERE id_empresa = p_idEmpresa) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La empresa seleccionada no existe.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la sucursal no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM sucursal WHERE id_empresa = p_idEmpresa AND nombre = v_nombreLim) THEN
        INSERT INTO sucursal (id_empresa, nombre, direccion, telefono)
        VALUES (p_idEmpresa, v_nombreLim, TRIM(p_direccion), NULLIF(TRIM(p_telefono), ''));
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe una sucursal con ese nombre para esta empresa.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_sucursal_insertar(1, 'Sucursal Miraflores', 'Av. Larco 456', '014000099');

-- 4. Procedure para modificar una sucursal
DROP PROCEDURE IF EXISTS sp_sucursal_actualizar;
DELIMITER //
CREATE PROCEDURE sp_sucursal_actualizar (
    IN p_idSucursal INT,
    IN p_idEmpresa INT,
    IN p_nuevoNombre VARCHAR(60),
    IN p_nuevaDireccion VARCHAR(100),
    IN p_nuevoTelefono VARCHAR(12)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(60);
    SET v_nombreLim = TRIM(p_nuevoNombre);
    
    IF p_idSucursal <= 0 OR p_idSucursal IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la sucursal no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM sucursal WHERE id_sucursal = p_idSucursal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La sucursal que intenta modificar no existe.';
    END IF;
    
    IF p_idEmpresa <= 0 OR p_idEmpresa IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la empresa no es válido.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la sucursal no puede estar vacío.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM sucursal
        WHERE id_empresa = p_idEmpresa AND nombre = v_nombreLim AND id_sucursal != p_idSucursal
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otra sucursal con ese mismo nombre registrada en la empresa.';
    ELSE
        UPDATE sucursal
        SET id_empresa = p_idEmpresa,
            nombre = v_nombreLim,
            direccion = TRIM(p_nuevaDireccion),
            telefono = NULLIF(TRIM(p_nuevoTelefono), '')
        WHERE id_sucursal = p_idSucursal;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_sucursal_actualizar(1, 1, 'Sede Central Lima Modificada', 'Av. Javier Prado Este 1234 - Of. 201', '014000001');




-- ============================================================
--VISTA: SUBCATEGORIA
-- ============================================================

-- 1. Vista mostrar subcategorías (incluyendo el nombre de la categoría principal)
CREATE OR REPLACE VIEW vw_subcategoria_activa AS
SELECT 
    S.id_subcategoria,
    S.id_categoria,
    C.nombre AS categoria_nombre,
    S.nombre_subcategoria,
    S.descripcion
FROM subcategoria S
INNER JOIN categoria C ON S.id_categoria = C.id_categoria;

-- Prueba de la vista
SELECT * FROM vw_subcategoria_activa;


-- ============================================================
-- MÓDULO CRUD: SUBCATEGORIA
-- ============================================================


-- 2. Procedure para buscar subcategorías
DROP PROCEDURE IF EXISTS sp_subcategoria_buscar;
DELIMITER //
CREATE PROCEDURE sp_subcategoria_buscar(IN p_nombreSubcategoria VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(52);
    SET v_filtro = TRIM(p_nombreSubcategoria);
    
    SELECT 
        id_subcategoria,
        id_categoria,
        categoria_nombre,
        nombre_subcategoria,
        descripcion
    FROM vw_subcategoria_activa
    WHERE (v_filtro = '' OR nombre_subcategoria LIKE CONCAT('%', v_filtro, '%') OR categoria_nombre LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_subcategoria_buscar('Laptops');
CALL sp_subcategoria_buscar('');

-- 3. Procedure para insertar una subcategoría
DROP PROCEDURE IF EXISTS sp_subcategoria_insertar;
DELIMITER $$
CREATE PROCEDURE sp_subcategoria_insertar(
    IN p_idCategoria INT,
    IN p_nombreSubcategoria VARCHAR(50),
    IN p_descripcion VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nombreSubcategoria);
    
    IF p_idCategoria <= 0 OR p_idCategoria IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la categoría no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM categoria WHERE id_categoria = p_idCategoria) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La categoría principal seleccionada no existe.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la subcategoría no puede estar vacío.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM subcategoria WHERE id_categoria = p_idCategoria AND nombre_subcategoria = v_nombreLim) THEN
        INSERT INTO subcategoria (id_categoria, nombre_subcategoria, descripcion)
        VALUES (p_idCategoria, v_nombreLim, NULLIF(TRIM(p_descripcion), ''));
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe una subcategoría con ese nombre dentro de la categoría.';
    END IF;
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_subcategoria_insertar(1, 'Consolas de Videojuegos', 'Equipos de entretenimiento interactivo');

-- 4. Procedure para modificar una subcategoría
DROP PROCEDURE IF EXISTS sp_subcategoria_actualizar;
DELIMITER //
CREATE PROCEDURE sp_subcategoria_actualizar (
    IN p_idSubcategoria INT,
    IN p_idCategoria INT,
    IN p_nuevoNombre VARCHAR(50),
    IN p_nuevaDescripcion VARCHAR(100)
)
BEGIN
    DECLARE v_nombreLim VARCHAR(50);
    SET v_nombreLim = TRIM(p_nuevoNombre);
    
    IF p_idSubcategoria <= 0 OR p_idSubcategoria IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la subcategoría no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM subcategoria WHERE id_subcategoria = p_idSubcategoria) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La subcategoría que intenta modificar no existe.';
    END IF;
    
    IF p_idCategoria <= 0 OR p_idCategoria IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la categoría no es válido.';
    END IF;
    
    IF v_nombreLim = '' OR v_nombreLim IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de la subcategoría no puede estar vacío.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM subcategoria
        WHERE id_categoria = p_idCategoria AND nombre_subcategoria = v_nombreLim AND id_subcategoria != p_idSubcategoria
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otra subcategoría con ese mismo nombre en la categoría.';
    ELSE
        UPDATE subcategoria
        SET id_categoria = p_idCategoria,
            nombre_subcategoria = v_nombreLim,
            descripcion = NULLIF(TRIM(p_nuevaDescripcion), '')
        WHERE id_subcategoria = p_idSubcategoria;
    END IF;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_subcategoria_actualizar(1, 1, 'Audio, Video y Streaming', 'Equipos de sonido, televisores y streaming');



-- ============================================================
-- VISTA: DETALLE COMPRA
-- ============================================================

-- 1. Vista mostrar detalles de compra (incluye información relacionada de compra y producto)
CREATE OR REPLACE VIEW vw_detalle_compra_activa AS
SELECT 
    DC.id_detalle_compra,
    DC.id_compra,
    C.fecha AS fecha_compra,
    DC.id_producto,
    P.codigo AS codigo_producto,
    P.nombre_producto,
    DC.cantidad,
    DC.precio_compra,
    DC.subtotal
FROM detalle_compra DC
INNER JOIN compra C ON DC.id_compra = C.id_compra
INNER JOIN producto P ON DC.id_producto = P.id_producto;

-- Prueba de la vista
SELECT * FROM vw_detalle_compra_activa;

-- ============================================================
-- MÓDULO CRUD: DETALLE COMPRA
-- ============================================================

-- 2. Procedure para buscar detalles de compra por ID de Compra
DROP PROCEDURE IF EXISTS sp_detalle_compra_buscar;
DELIMITER //
CREATE PROCEDURE sp_detalle_compra_buscar(IN p_idCompra INT)
BEGIN
    SELECT 
        id_detalle_compra,
        id_compra,
        fecha_compra,
        id_producto,
        codigo_producto,
        nombre_producto,
        cantidad,
        precio_compra,
        subtotal
    FROM vw_detalle_compra_activa
    WHERE (p_idCompra = 0 OR p_idCompra IS NULL OR id_compra = p_idCompra);
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_compra_buscar(1);
CALL sp_detalle_compra_buscar(0); -- Devuelve todos los registros si se envía 0 o nulo

-- 3. Procedure para insertar un detalle de compra
DROP PROCEDURE IF EXISTS sp_detalle_compra_insertar;
DELIMITER $$
CREATE PROCEDURE sp_detalle_compra_insertar(
    IN p_idCompra INT,
    IN p_idProducto INT,
    IN p_cantidad INT,
    IN p_precioCompra DECIMAL(10,2)
)
BEGIN
    DECLARE v_subtotal DECIMAL(10,2);
    
    -- Validar que la compra exista
    IF p_idCompra <= 0 OR p_idCompra IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la compra no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM compra WHERE id_compra = p_idCompra) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La compra especificada no existe.';
    END IF;
    
    -- Validar que el producto exista
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto especificado no existe.';
    END IF;
    
    -- Validar restricciones de cantidad y precio
    IF p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero.';
    END IF;
    
    IF p_precioCompra < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de compra no puede ser negativo.';
    END IF;
    
    -- Cálculo automático del subtotal
    SET v_subtotal = p_cantidad * p_precioCompra;
    
    -- Inserción del registro
    INSERT INTO detalle_compra (id_compra, id_producto, cantidad, precio_compra, subtotal)
    VALUES (p_idCompra, p_idProducto, p_cantidad, p_precioCompra, v_subtotal);
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_compra_insertar(1, 2, 5, 250.00);

-- 4. Procedure para modificar un detalle de compra
DROP PROCEDURE IF EXISTS sp_detalle_compra_actualizar;
DELIMITER //
CREATE PROCEDURE sp_detalle_compra_actualizar (
    IN p_idDetalleCompra INT,
    IN p_idCompra INT,
    IN p_idProducto INT,
    IN p_cantidad INT,
    IN p_precioCompra DECIMAL(10,2)
)
BEGIN
    DECLARE v_subtotal DECIMAL(10,2);
    
    -- Validar ID del detalle
    IF p_idDetalleCompra <= 0 OR p_idDetalleCompra IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del detalle de compra no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM detalle_compra WHERE id_detalle_compra = p_idDetalleCompra) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El detalle de compra que intenta modificar no existe.';
    END IF;
    
    -- Validar que la compra exista
    IF p_idCompra <= 0 OR p_idCompra IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la compra no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM compra WHERE id_compra = p_idCompra) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La compra especificada no existe.';
    END IF;
    
    -- Validar que el producto exista
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto especificado no existe.';
    END IF;
    
    -- Validar cantidad y precio
    IF p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero.';
    END IF;
    
    IF p_precioCompra < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de compra no puede ser negativo.';
    END IF;
    
    -- Recálculo del subtotal
    SET v_subtotal = p_cantidad * p_precioCompra;
    
    -- Actualización
    UPDATE detalle_compra
    SET id_compra = p_idCompra,
        id_producto = p_idProducto,
        cantidad = p_cantidad,
        precio_compra = p_precioCompra,
        subtotal = v_subtotal
    WHERE id_detalle_compra = p_idDetalleCompra;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_compra_actualizar(1, 1, 3, 2, 2500.00);



-- ============================================================
-- VISTA: DETALLE VENTA
-- ============================================================

-- 1. Vista mostrar detalles de venta (incluye información relacionada de venta y producto)
CREATE OR REPLACE VIEW vw_detalle_venta_activa AS
SELECT 
    DV.id_detalle_venta,
    DV.id_venta,
    V.fecha AS fecha_venta,
    DV.id_producto,
    P.codigo AS codigo_producto,
    P.nombre_producto,
    DV.cantidad,
    DV.precio_venta,
    (DV.cantidad * DV.precio_venta) AS subtotal
FROM detalle_venta DV
INNER JOIN venta V ON DV.id_venta = V.id_venta
INNER JOIN producto P ON DV.id_producto = P.id_producto;

-- Prueba de la vista
SELECT * FROM vw_detalle_venta_activa;

-- ============================================================
-- MÓDULO CRUD: DETALLE VENTA
-- ============================================================

-- 2. Procedure para buscar detalles de venta por ID de Venta
DROP PROCEDURE IF EXISTS sp_detalle_venta_buscar;
DELIMITER //
CREATE PROCEDURE sp_detalle_venta_buscar(IN p_idVenta INT)
BEGIN
    SELECT 
        id_detalle_venta,
        id_venta,
        fecha_venta,
        id_producto,
        codigo_producto,
        nombre_producto,
        cantidad,
        precio_venta,
        subtotal
    FROM vw_detalle_venta_activa
    WHERE (p_idVenta = 0 OR p_idVenta IS NULL OR id_venta = p_idVenta);
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_venta_buscar(1);
CALL sp_detalle_venta_buscar(0); -- Devuelve todos los registros si se envía 0 o nulo

-- 3. Procedure para insertar un detalle de venta
DROP PROCEDURE IF EXISTS sp_detalle_venta_insertar;
DELIMITER $$
CREATE PROCEDURE sp_detalle_venta_insertar(
    IN p_idVenta INT,
    IN p_idProducto INT,
    IN p_cantidad INT,
    IN p_precioVenta DECIMAL(10,2)
)
BEGIN
    -- Validar que la venta exista
    IF p_idVenta <= 0 OR p_idVenta IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la venta no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM venta WHERE id_venta = p_idVenta) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La venta especificada no existe.';
    END IF;
    
    -- Validar que el producto exista
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto especificado no existe.';
    END IF;
    
    -- Validar restricciones de cantidad y precio
    IF p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero.';
    END IF;
    
    IF p_precioVenta < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de venta no puede ser negativo.';
    END IF;
    
    -- Inserción del registro
    INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_venta)
    VALUES (p_idVenta, p_idProducto, p_cantidad, p_precioVenta);
END$$
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_venta_insertar(1, 2, 1, 250.00);

-- 4. Procedure para modificar un detalle de venta
DROP PROCEDURE IF EXISTS sp_detalle_venta_actualizar;
DELIMITER //
CREATE PROCEDURE sp_detalle_venta_actualizar (
    IN p_idDetalleVenta INT,
    IN p_idVenta INT,
    IN p_idProducto INT,
    IN p_cantidad INT,
    IN p_precioVenta DECIMAL(10,2)
)
BEGIN
    -- Validar ID del detalle
    IF p_idDetalleVenta <= 0 OR p_idDetalleVenta IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del detalle de venta no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM detalle_venta WHERE id_detalle_venta = p_idDetalleVenta) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El detalle de venta que intenta modificar no existe.';
    END IF;
    
    -- Validar que la venta exista
    IF p_idVenta <= 0 OR p_idVenta IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID de la venta no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM venta WHERE id_venta = p_idVenta) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La venta especificada no existe.';
    END IF;
    
    -- Validar que el producto exista
    IF p_idProducto <= 0 OR p_idProducto IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del producto no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM producto WHERE id_producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto especificado no existe.';
    END IF;
    
    -- Validar cantidad y precio
    IF p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero.';
    END IF;
    
    IF p_precioVenta < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de venta no puede ser negativo.';
    END IF;
    
    -- Actualización
    UPDATE detalle_venta
    SET id_venta = p_idVenta,
        id_producto = p_idProducto,
        cantidad = p_cantidad,
        precio_venta = p_precioVenta
    WHERE id_detalle_venta = p_idDetalleVenta;
END //
DELIMITER ;

-- Ejemplo de uso:
CALL sp_detalle_venta_actualizar(1, 1, 3, 1, 2800.00);

-- ============================================================
-- VISTAS: USUARIO
-- ============================================================
 
-- 1. Vista mostrar usuarios activos
CREATE OR REPLACE VIEW vw_usuario_activo AS
SELECT 
    U.id_usuario,
    U.codigo,
    U.password,
    U.id_cargo,
    C.nombre_cargo,
    U.id_personal,
    IFNULL(CONCAT(P.nombres, ' ', P.apellidos), 'Sin personal') AS personal,
    CASE 
        WHEN U.estado_usuario = 1 THEN 'Activo'
        ELSE 'Inactivo'
    END AS estado_usuario
FROM usuario U
INNER JOIN cargo C ON U.id_cargo = C.id_cargo
LEFT JOIN personal P ON U.id_personal = P.id_personal
WHERE U.estado_usuario = 1;
 
-- Prueba de la vista
SELECT * FROM vw_usuario_activo;
 
-- 2. Vista mostrar usuarios inactivos
CREATE OR REPLACE VIEW vw_usuario_inactivo AS
SELECT 
    U.id_usuario,
    U.codigo,
    U.password,
    U.id_cargo,
    C.nombre_cargo,
    U.id_personal,
    IFNULL(CONCAT(P.nombres, ' ', P.apellidos), 'Sin personal') AS personal,
    CASE 
        WHEN U.estado_usuario = 1 THEN 'Activo'
        ELSE 'Inactivo'
    END AS estado_usuario
FROM usuario U
INNER JOIN cargo C ON U.id_cargo = C.id_cargo
LEFT JOIN personal P ON U.id_personal = P.id_personal
WHERE U.estado_usuario = 0;
 
-- Prueba de la vista
SELECT * FROM vw_usuario_inactivo;
 
-- ============================================================
-- MÓDULO CRUD: USUARIO
-- ============================================================
 
-- 3. Procedure para buscar usuarios por código, cargo o personal
DROP PROCEDURE IF EXISTS sp_usuario_buscar;
DELIMITER //
CREATE PROCEDURE sp_usuario_buscar(IN p_filtroUsuario VARCHAR(50))
BEGIN
    DECLARE v_filtro VARCHAR(50);
    SET v_filtro = TRIM(IFNULL(p_filtroUsuario, ''));
    
    SELECT 
        id_usuario,
        codigo,
        password,
        id_cargo,
        nombre_cargo,
        id_personal,
        personal,
        estado_usuario
    FROM vw_usuario_activo
    WHERE (v_filtro = '' 
           OR codigo LIKE CONCAT('%', v_filtro, '%') 
           OR nombre_cargo LIKE CONCAT('%', v_filtro, '%')
           OR personal LIKE CONCAT('%', v_filtro, '%'));
END //
DELIMITER ;
 
-- Ejemplo de uso:
CALL sp_usuario_buscar('admin');
CALL sp_usuario_buscar('');
 
-- 4. Procedure para insertar un usuario
DROP PROCEDURE IF EXISTS sp_usuario_insertar;
DELIMITER $$
CREATE PROCEDURE sp_usuario_insertar(
    IN p_codigo VARCHAR(15),
    IN p_password VARCHAR(255),
    IN p_idCargo INT,
    IN p_idPersonal INT
)
BEGIN
    DECLARE v_codigoLim VARCHAR(15);
    SET v_codigoLim = TRIM(IFNULL(p_codigo, ''));
    
    IF v_codigoLim = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de usuario no puede estar vacío.';
    END IF;
    
    IF p_password IS NULL OR TRIM(p_password) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La contraseña no puede estar vacía.';
    END IF;
    
    IF p_idCargo IS NULL OR p_idCargo <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del cargo no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM cargo WHERE id_cargo = p_idCargo AND estado_cargo = 1) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo seleccionado no existe o está inactivo.';
    END IF;
    
    IF p_idPersonal IS NOT NULL 
       AND NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_idPersonal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal seleccionado no existe.';
    END IF;
    
    IF EXISTS (SELECT 1 FROM usuario WHERE codigo = v_codigoLim) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de usuario ya se encuentra registrado.';
    ELSE
        INSERT INTO usuario (codigo, password, id_cargo, id_personal, estado_usuario)
        VALUES (v_codigoLim, TRIM(p_password), p_idCargo, p_idPersonal, 1);
    END IF;
END$$
DELIMITER ;
 
-- Ejemplo de uso (comentado para no insertar datos al ejecutar el script):
 CALL sp_usuario_insertar('USR_PRUEBA', 'hash_de_prueba', 3, NULL);
 
-- 5. Procedure para modificar un usuario (código, cargo y personal)
DROP PROCEDURE IF EXISTS sp_usuario_actualizar;
DELIMITER //
CREATE PROCEDURE sp_usuario_actualizar (
    IN p_idUsuario INT,
    IN p_codigo VARCHAR(15),
    IN p_idCargo INT,
    IN p_idPersonal INT
)
BEGIN
    DECLARE v_codigoLim VARCHAR(15);
    SET v_codigoLim = TRIM(IFNULL(p_codigo, ''));
    
    IF p_idUsuario IS NULL OR p_idUsuario <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del usuario no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario que intenta modificar no existe.';
    END IF;
    
    IF v_codigoLim = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de usuario no puede estar vacío.';
    END IF;
    
    IF p_idCargo IS NULL OR p_idCargo <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del cargo no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM cargo WHERE id_cargo = p_idCargo AND estado_cargo = 1) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo seleccionado no existe o está inactivo.';
    END IF;
    
    IF p_idPersonal IS NOT NULL 
       AND NOT EXISTS (SELECT 1 FROM personal WHERE id_personal = p_idPersonal) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El personal seleccionado no existe.';
    END IF;
    
    IF EXISTS (
        SELECT 1 FROM usuario
        WHERE codigo = v_codigoLim AND id_usuario != p_idUsuario
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe otro usuario registrado con ese mismo código.';
    ELSE
        UPDATE usuario
        SET codigo = v_codigoLim,
            id_cargo = p_idCargo,
            id_personal = p_idPersonal
        WHERE id_usuario = p_idUsuario;
    END IF;
END //
DELIMITER ;
 
-- Ejemplo de uso (comentado):
 CALL sp_usuario_actualizar(11, 'USR_PRUEBA2', 3, NULL);
 
-- 6. Procedure para dar de baja lógica a un usuario
DROP PROCEDURE IF EXISTS sp_usuario_desactivar;
DELIMITER //
CREATE PROCEDURE sp_usuario_desactivar(IN p_idUsuario INT)
BEGIN
    IF p_idUsuario IS NULL OR p_idUsuario <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del usuario no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario especificado no existe.';
    END IF;
    
    IF EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario AND estado_usuario = 0) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya se encuentra inactivo.';
    END IF;
    
    UPDATE usuario 
    SET estado_usuario = 0 
    WHERE id_usuario = p_idUsuario;
END //
DELIMITER ;
 
-- Ejemplo de uso (comentado para no desactivar usuarios reales):
 CALL sp_usuario_desactivar(11);
 
-- 7. Procedure para reactivar un usuario
DROP PROCEDURE IF EXISTS sp_usuario_reactivar;
DELIMITER //
CREATE PROCEDURE sp_usuario_reactivar(IN p_idUsuario INT)
BEGIN
    IF p_idUsuario IS NULL OR p_idUsuario <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del usuario no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario especificado no existe.';
    END IF;
    
    IF EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario AND estado_usuario = 1) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya se encuentra activo.';
    END IF;
    
    UPDATE usuario 
    SET estado_usuario = 1 
    WHERE id_usuario = p_idUsuario;
END //
DELIMITER ;
 
-- Ejemplo de uso (comentado):
CALL sp_usuario_reactivar(11);
 
-- 8. Procedure para restablecer la contraseña (recibe el hash desde Java)
DROP PROCEDURE IF EXISTS sp_usuario_reset_password;
DELIMITER //
CREATE PROCEDURE sp_usuario_reset_password(
    IN p_idUsuario INT,
    IN p_password VARCHAR(255)
)
BEGIN
    IF p_idUsuario IS NULL OR p_idUsuario <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El ID del usuario no es válido.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id_usuario = p_idUsuario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario especificado no existe.';
    END IF;
    
    IF p_password IS NULL OR TRIM(p_password) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La nueva contraseña no puede estar vacía.';
    END IF;
    
    UPDATE usuario 
    SET password = TRIM(p_password) 
    WHERE id_usuario = p_idUsuario;
END //
DELIMITER ;
 
-- Ejemplo de uso (comentado):
 CALL sp_usuario_reset_password(11, 'hash_de_prueba');