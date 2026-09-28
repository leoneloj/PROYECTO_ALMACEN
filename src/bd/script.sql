-- ============================================================
-- BASE DE DATOS: bdalmacen
-- Sistema de gestión para almacén con trazabilidad y módulo de acceso
-- Motor: MySQL / InnoDB
-- ============================================================

SET SQL_MODE = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS bdalmacen;

CREATE DATABASE bdalmacen
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE bdalmacen;

-- ============================================================
-- 1. ELIMINAR VISTAS Y TABLAS SI EXISTEN (Orden inverso)
-- ============================================================

DROP VIEW IF EXISTS view_usuarios;
DROP TABLE IF EXISTS movimiento_inventario;
DROP TABLE IF EXISTS detalle_venta;
DROP TABLE IF EXISTS venta;
DROP TABLE IF EXISTS detalle_compra;
DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS inventario;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS subcategoria;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS almacen;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS personal;
DROP TABLE IF EXISTS sucursal;
DROP TABLE IF EXISTS empresa;
DROP TABLE IF EXISTS proveedor;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS area;
DROP TABLE IF EXISTS cargo;
DROP TABLE IF EXISTS tipo_personal;

-- ============================================================
-- 2. TABLAS DE CATÁLOGO Y CONFIGURACIÓN (Incluye Módulo de Acceso)
-- ============================================================

CREATE TABLE tipo_personal (
    id_tipo_personal INT NOT NULL AUTO_INCREMENT,
    nombre_tipo_personal VARCHAR(50) NOT NULL,
    observaciones VARCHAR(100) NULL,
    PRIMARY KEY (id_tipo_personal),
    CONSTRAINT uq_tipo_personal_nombre UNIQUE (nombre_tipo_personal)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- Tabla Cargo (Utilizada para definir perfiles y validar el acceso de los usuarios)
CREATE TABLE cargo (
    id_cargo INT NOT NULL AUTO_INCREMENT,
    nombre_cargo VARCHAR(50) NOT NULL,
    estado_cargo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_cargo),
    CONSTRAINT uq_cargo_nombre UNIQUE (nombre_cargo)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE area (
    id_area INT NOT NULL AUTO_INCREMENT,
    nombre_area VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NULL,
    PRIMARY KEY (id_area),
    CONSTRAINT uq_area_nombre UNIQUE (nombre_area)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE empresa (
    id_empresa INT NOT NULL AUTO_INCREMENT,
    razon_social VARCHAR(100) NOT NULL,
    ruc VARCHAR(20) NOT NULL,
    telefono VARCHAR(12) NULL,
    correo VARCHAR(100) NULL,
    PRIMARY KEY (id_empresa),
    CONSTRAINT uq_empresa_ruc UNIQUE (ruc)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE sucursal (
    id_sucursal INT NOT NULL AUTO_INCREMENT,
    id_empresa INT NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NULL,
    PRIMARY KEY (id_sucursal),
    CONSTRAINT fk_sucursal_empresa FOREIGN KEY (id_empresa)
        REFERENCES empresa (id_empresa)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_sucursal_empresa ON sucursal (id_empresa);

CREATE TABLE personal (
    id_personal INT NOT NULL AUTO_INCREMENT,
    id_tipo_personal INT NOT NULL,
    id_cargo INT NOT NULL,
    id_area INT NOT NULL,
    id_sucursal INT NOT NULL,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    documento VARCHAR(15) NOT NULL,
    PRIMARY KEY (id_personal),
    CONSTRAINT uq_personal_documento UNIQUE (documento),
    CONSTRAINT fk_personal_tipo FOREIGN KEY (id_tipo_personal) REFERENCES tipo_personal (id_tipo_personal) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_personal_cargo FOREIGN KEY (id_cargo) REFERENCES cargo (id_cargo) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_personal_area FOREIGN KEY (id_area) REFERENCES area (id_area) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_personal_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursal) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_personal_sucursal ON personal (id_sucursal);

-- Tabla Usuario (Control de acceso al sistema conectada con cargo y personal)
CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(15) NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_cargo INT NOT NULL,
    id_personal INT NULL,
    estado_usuario TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_usuario),
    CONSTRAINT uq_usuario_codigo UNIQUE (codigo),
    CONSTRAINT fk_usuario_cargo FOREIGN KEY (id_cargo) REFERENCES cargo (id_cargo) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_usuario_personal FOREIGN KEY (id_personal) REFERENCES personal (id_personal) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_usuario_cargo ON usuario (id_cargo);

CREATE TABLE almacen (
    id_almacen INT NOT NULL AUTO_INCREMENT,
    id_sucursal INT NOT NULL,
    nombre_almacen VARCHAR(60) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_almacen),
    CONSTRAINT fk_almacen_sucursal FOREIGN KEY (id_sucursal)
        REFERENCES sucursal (id_sucursal)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_almacen_sucursal ON almacen (id_sucursal);

CREATE TABLE categoria (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NULL,
    PRIMARY KEY (id_categoria),
    CONSTRAINT uq_categoria_nombre UNIQUE (nombre)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE subcategoria (
    id_subcategoria INT NOT NULL AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    nombre_subcategoria VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NULL,
    PRIMARY KEY (id_subcategoria),
    CONSTRAINT uq_subcategoria_cat_nombre UNIQUE (id_categoria, nombre_subcategoria),
    CONSTRAINT fk_subcategoria_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria (id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_subcategoria_categoria ON subcategoria (id_categoria);

-- ============================================================
-- 3. PRODUCTOS E INVENTARIO
-- ============================================================

CREATE TABLE producto (
    id_producto INT NOT NULL AUTO_INCREMENT,
    id_subcategoria INT NOT NULL,
    codigo VARCHAR(45) NOT NULL,
    nombre_producto VARCHAR(100) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    unidad_medida VARCHAR(20) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_producto),
    CONSTRAINT uq_producto_codigo UNIQUE (codigo),
    CONSTRAINT chk_producto_precio CHECK (precio_venta >= 0),
    CONSTRAINT fk_producto_subcategoria FOREIGN KEY (id_subcategoria)
        REFERENCES subcategoria (id_subcategoria)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_producto_subcategoria ON producto (id_subcategoria);

CREATE TABLE inventario (
    id_inventario INT NOT NULL AUTO_INCREMENT,
    id_almacen INT NOT NULL,
    id_producto INT NOT NULL,
    stock_actual INT NOT NULL DEFAULT 0,
    stock_minimo INT NOT NULL DEFAULT 0,
    ultima_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_inventario),
    CONSTRAINT uq_inventario_almacen_producto UNIQUE (id_almacen, id_producto),
    CONSTRAINT chk_inventario_stock CHECK (stock_actual >= 0 AND stock_minimo >= 0),
    CONSTRAINT fk_inventario_almacen FOREIGN KEY (id_almacen) REFERENCES almacen (id_almacen) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_inventario_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_inventario_almacen ON inventario (id_almacen);
CREATE INDEX idx_inventario_producto ON inventario (id_producto);

-- ============================================================
-- 4. CLIENTES Y PROVEEDORES
-- ============================================================

CREATE TABLE cliente (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre_cliente VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NULL,
    telefono VARCHAR(12) NULL,
    correo VARCHAR(100) NULL,
    PRIMARY KEY (id_cliente),
    CONSTRAINT uq_cliente_correo UNIQUE (correo)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE proveedor (
    id_proveedor INT NOT NULL AUTO_INCREMENT,
    ruc VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NULL,
    correo VARCHAR(100) NULL,
    direccion VARCHAR(100) NULL,
    PRIMARY KEY (id_proveedor),
    CONSTRAINT uq_proveedor_ruc UNIQUE (ruc)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ============================================================
-- 5. TRANSACCIONES Y MOVIMIENTOS CON TRAZABILIDAD
-- ============================================================

CREATE TABLE compra (
    id_compra INT NOT NULL AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    id_almacen INT NOT NULL,
    id_personal INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id_compra),
    CONSTRAINT chk_compra_total CHECK (total >= 0),
    CONSTRAINT fk_compra_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_compra_almacen FOREIGN KEY (id_almacen) REFERENCES almacen (id_almacen) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_compra_personal FOREIGN KEY (id_personal) REFERENCES personal (id_personal) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_compra_proveedor ON compra (id_proveedor);
CREATE INDEX idx_compra_almacen ON compra (id_almacen);

CREATE TABLE detalle_compra (
    id_detalle_compra INT NOT NULL AUTO_INCREMENT,
    id_compra INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_detalle_compra),
    CONSTRAINT chk_detalle_compra_cant CHECK (cantidad > 0),
    CONSTRAINT chk_detalle_compra_precio CHECK (precio_compra >= 0),
    CONSTRAINT fk_detalle_compra_compra FOREIGN KEY (id_compra) REFERENCES compra (id_compra) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detalle_compra_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_detalle_compra_compra ON detalle_compra (id_compra);
CREATE INDEX idx_detalle_compra_producto ON detalle_compra (id_producto);

CREATE TABLE venta (
    id_venta INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_almacen INT NOT NULL,
    id_personal INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id_venta),
    CONSTRAINT chk_venta_total CHECK (total >= 0),
    CONSTRAINT fk_venta_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_venta_almacen FOREIGN KEY (id_almacen) REFERENCES almacen (id_almacen) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_venta_personal FOREIGN KEY (id_personal) REFERENCES personal (id_personal) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_venta_cliente ON venta (id_cliente);
CREATE INDEX idx_venta_almacen ON venta (id_almacen);

CREATE TABLE detalle_venta (
    id_detalle_venta INT NOT NULL AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_detalle_venta),
    CONSTRAINT chk_detalle_venta_cant CHECK (cantidad > 0),
    CONSTRAINT chk_detalle_venta_precio CHECK (precio_venta >= 0),
    CONSTRAINT fk_detalle_venta_venta FOREIGN KEY (id_venta) REFERENCES venta (id_venta) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detalle_venta_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_detalle_venta_venta ON detalle_venta (id_venta);
CREATE INDEX idx_detalle_venta_producto ON detalle_venta (id_producto);

CREATE TABLE movimiento_inventario (
    id_movimiento_inventario INT NOT NULL AUTO_INCREMENT,
    id_producto INT NOT NULL,
    id_almacen INT NOT NULL,
    id_personal INT NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_movimiento_inventario),
    CONSTRAINT chk_movimiento_cantidad CHECK (cantidad <> 0),
    CONSTRAINT fk_movimiento_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_movimiento_almacen FOREIGN KEY (id_almacen) REFERENCES almacen (id_almacen) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_movimiento_personal FOREIGN KEY (id_personal) REFERENCES personal (id_personal) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_movimiento_producto ON movimiento_inventario (id_producto);
CREATE INDEX idx_movimiento_almacen ON movimiento_inventario (id_almacen);


-- ============================================================
-- 7. RESTAURAR CONFIGURACIÓN Y VERIFICACIÓN
-- ============================================================

SET FOREIGN_KEY_CHECKS = 1;

SHOW TABLES;
USE bdalmacen;

-- Desactivar temporalmente foreign keys para una carga limpia
SET FOREIGN_KEY_CHECKS = 0;

-- 1. tipo_personal
INSERT INTO tipo_personal (id_tipo_personal, nombre_tipo_personal, observaciones) VALUES
(1, 'Contratado Fijo', 'Personal en planilla permanente'),
(2, 'Contratado Temporal', 'Contrato por locación o temporada'),
(3, 'Practicante', 'Formativo universitario o técnico'),
(4, 'Tercerizado', 'Outsourcing de servicios'),
(5, 'Consultor', 'Asesor externo'),
(6, 'Directivo', 'Ejecutivo de alta gerencia'),
(7, 'Operativo', 'Personal de planta y almacén'),
(8, 'Supervisión', 'Jefaturas y supervisores'),
(9, 'Auditor', 'Personal de control interno'),
(10, 'Vendedor Expres', 'Fuerza de ventas externa');

-- 2. cargo
INSERT INTO cargo (id_cargo, nombre_cargo, estado_cargo) VALUES
(1, 'Administrador del Sistema', 1),
(2, 'Jefe de Almacén', 1),
(3, 'Auxiliar de Almacén', 1),
(4, 'Gerente Comercial', 1),
(5, 'Vendedor de Campo', 1),
(6, 'Cajero', 1),
(7, 'Supervisor de Compras', 1),
(8, 'Auditor de Inventarios', 1),
(9, 'Recepcionista', 1),
(10, 'Soporte TI', 1);

-- 3. area
INSERT INTO area (id_area, nombre_area, descripcion) VALUES
(1, 'Tecnologías de Información', 'Sistemas e infraestructura tecnológica'),
(2, 'Logística y Almacén', 'Gestión de inventarios y despacho'),
(3, 'Ventas y Comercial', 'Atención a clientes y facturación'),
(4, 'Compras y Abastecimiento', 'Gestión con proveedores'),
(5, 'Recursos Humanos', 'Gestión del talento humano'),
(6, 'Contabilidad y Finanzas', 'Gestión financiera y tributaria'),
(7, 'Administración General', 'Dirección de la empresa'),
(8, 'Control de Calidad', 'Inspección de insumos y mercadería'),
(9, 'Atención al Cliente', 'Soporte postventa y reclamos'),
(10, 'Mantenimiento', 'Cuidado de instalaciones y equipos');

-- 4. empresa
INSERT INTO empresa (id_empresa, razon_social, ruc, telefono, correo) VALUES
(1, 'Comercializadora Global S.A.C.', '20100000001', '912345601', 'contacto@global.com'),
(2, 'Distribuidora del Norte E.I.R.L.', '20100000002', '912345602', 'ventas@distnorte.com'),
(3, 'Importaciones Andinas S.A.', '20100000003', '912345603', 'info@andinas.com'),
(4, 'Logística Integral Peruana S.R.L.', '20100000004', '912345604', 'operaciones@logistica.pe'),
(5, 'Almacenes del Sur S.A.C.', '20100000005', '912345605', 'contacto@almacenessur.com'),
(6, 'Corporación Industrial R&M S.A.', '20100000006', '912345606', 'admin@rmcorp.com'),
(7, 'Inversiones Pacífico E.I.R.L.', '20100000007', '912345607', 'ventas@pacifico.com'),
(8, 'Grupo Comercial Mayorista S.A.C.', '20100000008', '912345608', 'informes@grupomayorista.pe'),
(9, 'Mercaderías Express S.A.', '20100000009', '912345609', 'contacto@mexpress.com'),
(10, 'Soluciones Logísticas A&G S.R.L.', '20100000010', '912345610', 'gerencia@solucioneslogistics.pe');

-- 5. sucursal
INSERT INTO sucursal (id_sucursal, id_empresa, nombre, direccion, telefono) VALUES
(1, 1, 'Sede Central Lima', 'Av. Javier Prado Este 1234', '014000001'),
(2, 1, 'Sucursal Arequipa', 'Av. Ejército 567', '054400002'),
(3, 2, 'Sucursal Trujillo', 'Av. España 890', '044400003'),
(4, 2, 'Sucursal Chiclayo', 'Av. Balta 432', '074400004'),
(5, 3, 'Sucursal Huancayo', 'Calle Real 112', '064400005'),
(6, 3, 'Sucursal Cusco', 'Av. El Sol 981', '084400006'),
(7, 4, 'Sucursal Callao', 'Av. Néstor Gambetta 3300', '014000007'),
(8, 4, 'Sucursal Piura', 'Av. Grau 450', '073400008'),
(9, 5, 'Sucursal Tacna', 'Av. Bolognesi 789', '052400009'),
(10, 5, 'Sucursal Iquitos', 'Jr. Próspero 210', '065400010');

-- 6. personal
INSERT INTO personal (id_personal, id_tipo_personal, id_cargo, id_area, id_sucursal, nombres, apellidos, documento) VALUES
(1, 1, 1, 1, 1, 'Juan', 'Pérez Gómez', '40000001'),
(2, 1, 2, 2, 1, 'María', 'Rodríguez López', '40000002'),
(3, 1, 3, 2, 1, 'Carlos', 'Sánchez Torres', '40000003'),
(4, 2, 4, 3, 2, 'Ana', 'Martínez Díaz', '40000004'),
(5, 1, 5, 3, 2, 'Luis', 'Fernández Castro', '40000005'),
(6, 1, 6, 6, 3, 'Sofia', 'Ramírez Morales', '40000006'),
(7, 1, 7, 4, 3, 'Jorge', 'Vargas Mendoza', '40000007'),
(8, 2, 8, 8, 4, 'Elena', 'Flores Salazar', '40000008'),
(9, 3, 9, 9, 4, 'Pedro', 'Benítez Ríos', '40000009'),
(10, 1, 10, 1, 5, 'Lucía', 'Herrera Vega', '40000010');

-- 7. usuario
INSERT INTO usuario (id_usuario, codigo, password, id_cargo, id_personal, estado_usuario) VALUES
(1, 'USR_ADMIN', '$2y$10$e8.p44pM/d...hash1', 1, 1, 1),
(2, 'USR_JALM1', '$2y$10$e8.p44pM/d...hash2', 2, 2, 1),
(3, 'USR_AUX1', '$2y$10$e8.p44pM/d...hash3', 3, 3, 1),
(4, 'USR_GCOM', '$2y$10$e8.p44pM/d...hash4', 4, 4, 1),
(5, 'USR_VEND1', '$2y$10$e8.p44pM/d...hash5', 5, 5, 1),
(6, 'USR_CAJA1', '$2y$10$e8.p44pM/d...hash6', 6, 6, 1),
(7, 'USR_SCOMP', '$2y$10$e8.p44pM/d...hash7', 7, 7, 1),
(8, 'USR_AUDIT', '$2y$10$e8.p44pM/d...hash8', 8, 8, 1),
(9, 'USR_RECEP', '$2y$10$e8.p44pM/d...hash9', 9, 9, 1),
(10, 'USR_TI', '$2y$10$e8.p44pM/d...hash10', 10, 10, 1);

-- 8. almacen
INSERT INTO almacen (id_almacen, id_sucursal, nombre_almacen, ubicacion, estado) VALUES
(1, 1, 'Almacén Principal Lima', 'Nave A - Sector 1', 1),
(2, 1, 'Almacén de Productos Frios Lima', 'Nave B - Sector 2', 1),
(3, 2, 'Almacén Arequipa Central', 'Zona C - Stand 4', 1),
(4, 3, 'Almacén Trujillo Norte', 'Nave 1', 1),
(5, 4, 'Almacén Chiclayo Centro', 'Sector 3', 1),
(6, 5, 'Almacén Huancayo Distribución', 'Planta Baja', 1),
(7, 6, 'Almacén Cusco Regional', 'Nave Sur', 1),
(8, 7, 'Almacén Portuario Callao', 'Zona Franca R-2', 1),
(9, 8, 'Almacén Piura Despacho', 'Módulo D', 1),
(10, 9, 'Almacén Tacna Comercial', 'Stand 12', 1);

-- 9. categoria
INSERT INTO categoria (id_categoria, nombre, descripcion) VALUES
(1, 'Electrónica', 'Dispositivos y componentes electrónicos'),
(2, 'Cómputo', 'Equipos informáticos y periféricos'),
(3, 'Oficina', 'Suministros de papelería y escritorio'),
(4, 'Herramientas', 'Herramientas manuales y elétricas'),
(5, 'Iluminación', 'Lámparas y focos LED'),
(6, 'Seguridad', 'Equipos de protección e insumos de seguridad'),
(7, 'Limpieza', 'Productos químicos y utensilios de aseo'),
(8, 'Redes', 'Cableado y conectividad'),
(9, 'Mobiliario', 'Muebles de oficina y archivo'),
(10, 'Electrohogar', 'Electrodomésticos menores');

-- 10. subcategoria
INSERT INTO subcategoria (id_subcategoria, id_categoria, nombre_subcategoria, descripcion) VALUES
(1, 1, 'Audio y Video', 'Equipos de sonido y televisores'),
(2, 1, 'Componentes', 'Placas y chips'),
(3, 2, 'Laptops', 'Computadoras portátiles'),
(4, 2, 'Accesorios Cómputo', 'Mouses, teclados y mousespads'),
(5, 3, 'Papelería', 'Resmas y hojas bond'),
(6, 3, 'Escritura', 'Bolígrafos y plumones'),
(7, 4, 'Manuales', 'Martillos, desarmadores y llaves'),
(8, 5, 'Focos LED', 'Iluminación eficiente de alto ahorro'),
(9, 6, 'Calzado de Seguridad', 'Botas dieléctricas y puntas de acero'),
(10, 7, 'Desinfectantes', 'Limpiadores industriales y gel');

-- 11. producto
INSERT INTO producto (id_producto, id_subcategoria, codigo, nombre_producto, precio_venta, unidad_medida, estado) VALUES
(1, 1, 'PROD-001', 'Audífonos Bluetooth X1', 120.00, 'Unidad', 1),
(2, 2, 'PROD-002', 'Memoria RAM 16GB DDR4', 250.00, 'Unidad', 1),
(3, 3, 'PROD-003', 'Laptop Intel Core i5 16GB', 2800.00, 'Unidad', 1),
(4, 4, 'PROD-004', 'Mouse Inalámbrico Ergonómico', 45.00, 'Unidad', 1),
(5, 5, 'PROD-005', 'Resma Papel Bond A4 75g', 18.50, 'Paquete', 1),
(6, 6, 'PROD-006', 'Caja de Bolígrafos Azules 12u', 12.00, 'Caja', 1),
(7, 7, 'PROD-007', 'Set Desarmadores de Precisión', 65.00, 'Juego', 1),
(8, 8, 'PROD-008', 'Foco LED 15W Luz Blanca', 8.50, 'Unidad', 1),
(9, 9, 'PROD-009', 'Botas de Seguridad Talla 42', 150.00, 'Par', 1),
(10, 10, 'PROD-010', 'Alcohol en Gel 1L', 15.00, 'Botella', 1);

-- 12. inventario
INSERT INTO inventario (id_inventario, id_almacen, id_producto, stock_actual, stock_minimo) VALUES
(1, 1, 1, 50, 10),
(2, 1, 2, 30, 5),
(3, 1, 3, 15, 2),
(4, 2, 4, 100, 20),
(5, 2, 5, 200, 50),
(6, 3, 6, 80, 15),
(7, 3, 7, 25, 5),
(8, 4, 8, 300, 50),
(9, 5, 9, 40, 10),
(10, 6, 10, 120, 30);

-- 13. cliente
INSERT INTO cliente (id_cliente, nombre_cliente, direccion, telefono, correo) VALUES
(1, 'Corporación ABC S.A.C.', 'Av. Los Olivos 100', '987654321', 'compras@abc.com'),
(2, 'Servicios Digitales Tech E.I.R.L.', 'Calle Las Flores 230', '987654322', 'contacto@techdigital.pe'),
(3, 'Constructora del Centro S.R.L.', 'Av. Industrial 450', '987654323', 'admin@constructora.com'),
(4, 'Carlos Alberto Ruiz', 'Jr. Unión 554', '987654324', 'carlos.ruiz@gmail.com'),
(5, 'Comercial Santa Rosa', 'Av. Central 890', '987654325', 'santarosa@outlook.com'),
(6, 'Industrias del Metal S.A.', 'Av. Argentina 1200', '987654326', 'ventas@indmetal.com'),
(7, 'Mariana Espinoza Paredes', 'Calle Bolognesi 321', '987654327', 'mariana.espinoza@hotmail.com'),
(8, 'Inversiones Globales R&S', 'Av. Arequipa 1500', '987654328', 'contacto@inversionesrs.pe'),
(9, 'Consultores Asociados', 'Jr. Carabaya 670', '987654329', 'info@consultores.com'),
(10, 'Distribuidora San Clara', 'Av. Grau 110', '987654330', 'ventas@santaclara.pe');

-- 14. proveedor
INSERT INTO proveedor (id_proveedor, ruc, nombre, telefono, correo, direccion) VALUES
(1, '20500000001', 'Importadora Tech Supply S.A.C.', '955000001', 'ventas@techsupply.pe', 'Av. Argentina 890, Lima'),
(2, '20500000002', 'Distribuidora Papelera Peruana S.A.', '955000002', 'contacto@papelex.pe', 'Av. Materiales 450, Lima'),
(3, '20500000003', 'Ferretería Industrial S.R.L.', '955000003', 'pedidos@feind.pe', 'Calle Los Ferreteros 123, Callao'),
(4, '20500000004', 'Iluminación del Pacífico E.I.R.L.', '955000004', 'ventas@ilumpacifico.pe', 'Av. Colonial 670, Callao'),
(5, '20500000005', 'Seguridad Industrial E&M', '955000005', 'info@seguridadem.pe', 'Jr. Arica 340, Lima'),
(6, '20500000006', 'Suministros Químicos S.A.C.', '955000006', 'ventas@sumquimica.pe', 'Av. Maquinarias 220, Lima'),
(7, '20500000007', 'Mobiliario Corporativo S.A.', '955000007', 'contacto@mobcorp.pe', 'Av. Universitaria 1100, Lima'),
(8, '20500000008', 'Electro Componentes Peru', '955000008', 'ventas@electrocomp.pe', 'Jr. Paruro 550, Lima'),
(9, '20500000009', 'Redes y Telecom S.R.L.', '955000009', 'soporte@redestelecom.pe', 'Av. Primavera 400, Lima'),
(10, '20500000010', 'Mayorista de Oficina S.A.C.', '955000010', 'ventas@mayoristadeoficina.pe', 'Calle Los Cedros 900, Lima');

-- 15. compra
INSERT INTO compra (id_compra, id_proveedor, id_almacen, id_personal, fecha, total) VALUES
(1, 1, 1, 7, '2026-03-01 09:00:00', 3700.00),
(2, 1, 1, 7, '2026-03-02 10:30:00', 1250.00),
(3, 2, 2, 7, '2026-03-03 11:15:00', 370.00),
(4, 3, 3, 7, '2026-03-04 14:00:00', 650.00),
(5, 4, 4, 7, '2026-03-05 15:45:00', 850.00),
(6, 5, 5, 7, '2026-03-06 09:30:00', 1500.00),
(7, 6, 6, 7, '2026-03-07 10:00:00', 300.00),
(8, 7, 1, 7, '2026-03-08 11:30:00', 1200.00),
(9, 8, 1, 7, '2026-03-09 16:00:00', 2500.00),
(10, 9, 2, 7, '2026-03-10 17:15:00', 450.00);

-- 16. detalle_compra
INSERT INTO detalle_compra (id_detalle_compra, id_compra, id_producto, cantidad, precio_compra, subtotal) VALUES
(1, 1, 3, 1, 2500.00, 2500.00),
(2, 1, 1, 10, 120.00, 1200.00),
(3, 2, 2, 5, 250.00, 1250.00),
(4, 3, 5, 20, 18.50, 370.00),
(5, 4, 7, 10, 65.00, 650.00),
(6, 5, 8, 100, 8.50, 850.00),
(7, 6, 9, 10, 150.00, 1500.00),
(8, 7, 10, 20, 15.00, 300.00),
(9, 8, 4, 30, 40.00, 1200.00),
(10, 9, 2, 10, 250.00, 2500.00);

-- 17. venta
INSERT INTO venta (id_venta, id_cliente, id_almacen, id_personal, fecha, total) VALUES
(1, 1, 1, 5, '2026-03-11 10:00:00', 2920.00),
(2, 2, 1, 5, '2026-03-12 11:20:00', 295.00),
(3, 3, 2, 5, '2026-03-13 12:00:00', 370.00),
(4, 4, 3, 5, '2026-03-14 14:10:00', 130.00),
(5, 5, 4, 5, '2026-03-15 15:30:00', 85.00),
(6, 6, 5, 5, '2026-03-16 16:00:00', 1500.00),
(7, 7, 6, 5, '2026-03-17 09:15:00', 150.00),
(8, 8, 1, 5, '2026-03-18 10:45:00', 250.00),
(9, 9, 2, 5, '2026-03-19 11:50:00', 120.00),
(10, 10, 3, 5, '2026-03-20 16:20:00', 450.00);

-- 18. detalle_venta
INSERT INTO detalle_venta (id_detalle_venta, id_venta, id_producto, cantidad, precio_venta) VALUES
(1, 1, 3, 1, 2800.00),
(2, 1, 1, 1, 120.00),
(3, 2, 2, 1, 250.00),
(4, 2, 4, 1, 45.00),
(5, 3, 5, 20, 18.50),
(6, 4, 7, 2, 65.00),
(7, 5, 8, 10, 8.50),
(8, 6, 9, 10, 150.00),
(9, 7, 10, 10, 15.00),
(10, 8, 2, 1, 250.00);

-- 19. movimiento_inventario
INSERT INTO movimiento_inventario (id_movimiento_inventario, id_producto, id_almacen, id_personal, tipo_movimiento, cantidad, fecha) VALUES
(1, 3, 1, 2, 'ENTRADA', 5, '2026-03-01 09:30:00'),
(2, 1, 1, 2, 'ENTRADA', 20, '2026-03-01 09:35:00'),
(3, 2, 1, 2, 'ENTRADA', 10, '2026-03-02 11:00:00'),
(4, 5, 2, 2, 'ENTRADA', 50, '2026-03-03 11:30:00'),
(5, 7, 3, 3, 'ENTRADA', 15, '2026-03-04 14:30:00'),
(6, 3, 1, 3, 'SALIDA', -1, '2026-03-11 10:15:00'),
(7, 1, 1, 3, 'SALIDA', -1, '2026-03-11 10:15:00'),
(8, 2, 1, 3, 'SALIDA', -1, '2026-03-12 11:30:00'),
(9, 5, 2, 3, 'SALIDA', -20, '2026-03-13 12:15:00'),
(10, 8, 4, 3, 'ENTRADA', 100, '2026-03-15 09:00:00');

-- Reactivar foreign keys
SET FOREIGN_KEY_CHECKS = 1;
