CREATE DATABASE revista;

CREATE USER 'usuario1'@'localhost' IDENTIFIED BY 'ConTr@.34';

GRANT ALL PRIVILEGES ON revista.* TO 'usuario1'@'localhost';

USE revista;

CREATE TABLE editor(

    nombre_editor VARCHAR(20) PRIMARY KEY NOT NULL,
    password_editor VARCHAR(20) NOT NULL,
    foto BLOB NOT NULL,
    hobbie VARCHAR(150) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    gustos VARCHAR(150) NOT NULL

);

CREATE TABLE etiqueta_editor (

    nombre_editor VARCHAR(20) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    PRIMARY KEY(nombre_editor, nombre_etiqueta),
    CONSTRAINT etiqueta_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES editor(nombre_editor)
);

CREATE TABLE categoria (
    nombre_categoria VARCHAR(30) NOT NULL PRIMARY KEY
);

CREATE TABLE revista(

    registro_revista INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_editor VARCHAR(20) NOT NULL,
    nombre_revista VARCHAR(35) NOT NULL,
    archivo MEDIUMBLOB NOT NULL,
    fecha_publicacion DATE NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    fecha_aceptacion DATE NOT NULL,
    estado_revista ENUM('aceptada','en espera') NOT NULL,
    costo_por_dia DECIMAL(7,2) NOT NULL,
    fecha_modificacion_cpd DATE NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    costo_suscripcion DECIMAL(7,2) NOT NULL,
    like_revista ENUM('si','no') NOT NULL,
    comentario ENUM('si','no') NOT NULL,
    suscripcion ENUM('si','no') NOT NULL,
    CONSTRAINT revista_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES editor(nombre_editor)
);

CREATE TABLE etiqueta_revista(

    registro revista VARCHAR(35) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    PRIMARY KEY (registro_revista, nombre_etiqueta),
    CONSTRAINT etiqueta_revista_fk FOREIGN KEY (registro_revista)
    REFERENCES revista(registro_revista)

);

CREATE TABLE suscripcion(

    registro_suscripcion INT NOT NULL AUTO_INCREMENT,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    registro_revista INT NOT NULL,
    total_pago DECIMAL(7,2),
    intervalo_pago ENUM('mensual','anual') NOT NULL,
    fecha_registro DATE NOT NULL,
    estado_suscripcion ENUM('vigente','cancelada') NOT NULL,
    like_suscripcion ENUM('si','no') NOT NULL,
    PRIMARY KEY(registro_suscripcion,nombre_suscriptor,registro_revista),
    CONSTRAINT nombre_suscriptor_fk FOREIGN KEY (nombre_suscriptor)
    REFERENCES editor(nombre_editor),
    CONSTRAINT registro_revista_fk FOREIGN KEY (registro_revista)
    REFERENCES revista(registro_revista)
);

CREATE TABLE cuenta_editor(

    registro_cuenta INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_editor VARCHAR(20) NOT NULL,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    nombre_revista VARCHAR(35) NOT NULL,
    total_pagar DECIMAL(7,2) NOT NULL,
    costo_descuento DECIMAL(7,2) NOT NULL,
    ganancia DECIMAL(7,2) NOT NULL,
    fecha_pago DATE NOT NULL,
    CONSTRAINT cuenta_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES editor(nombre_editor)
);

CREATE TABLE comentario(
    registro_comentario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    registro_revista INT NOT NULL,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    texto VARCHAR(200) NOT NULL,
    fecha_comentario DATE NOT NULL,
    
);

CREATE TABLE administrador(

    nombre_administrador VARCHAR(30) NOT NULL PRIMARY KEY,
    password_administrador VARCHAR(20) NOT NULL,
    estado_administrador ENUM('vigente', 'cancelado') NOT NULL
);

CREATE TABLE porcentaje_impuesto(
    registro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    porcentaje DECIMAL(5,2) NOT NULL,
    fecha_actualizacion DATE NOT NULL
);

CREATE TABLE tipo_anuncio(

    nombre_tipo VARCHAR(30) NOT NULL PRIMARY KEY,
    costo_dia DECIMAL(7,2) NOT NULL
);

CREATE TABLE anuncio(

    registro_anuncio INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    tipo_anuncio VARCHAR(30) NOT NULL,
    nombre_anuncio VARCHAR(30) NOT NULL,
    nombre_anunciante VARCHAR(30) NOT NULL,
    texto_anuncio  VARCHAR(50) NOT NULL,
    contenido_anuncio VARCHAR(50) NOT NULL,
    cantidad_apariciones INT DEFAULT 0,
    total_pagar DECIMAL(7,2) NOT NULL,
    estado_anuncio ENUM('activo', 'inactivo') NOT NULL,
    url_anuncio VARCHAR(100) DEFAULT ' ',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);

CREATE TABLE etiqueta_anuncio(

    registro_anuncio INT NOT NULL PRIMARY KEY,
    nombre_anuncio VARCHAR(30) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL
    CONSTRAINT registro_anuncio_fk FOREIGN KEY (registro_anuncio)
    REFERENCES anuncio(registro_anuncio)
);

