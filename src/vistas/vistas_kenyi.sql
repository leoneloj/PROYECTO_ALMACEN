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