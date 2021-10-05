CREATE DATABASE revista_app;

CREATE USER 'usuario1'@'localhost' IDENTIFIED BY 'ConTr@.34';

GRANT ALL PRIVILEGES ON revista_app.* TO 'usuario1'@'localhost';

USE revista_app;

CREATE TABLE usuario(
    nombre_usuario VARCHAR(20) PRIMARY KEY NOT NULL,
    password VARCHAR (20) NOT NULL,
    tipo ENUM('EDITOR','ADMIN')
);

CREATE TABLE perfil(

    nombre_editor VARCHAR(20) PRIMARY KEY NOT NULL,
    foto BLOB,
    hobby VARCHAR(300) DEFAULT '',
    descripcion VARCHAR(300) DEFAULT '',
    gustos VARCHAR(300) DEFAULT '',
    CONSTRAINT perfil_usuario_fk FOREIGN KEY (nombre_editor)
    REFERENCES usuario(nombre_usuario)

);

CREATE TABLE etiqueta_editor (

    nombre_editor VARCHAR(20) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    PRIMARY KEY(nombre_editor, nombre_etiqueta),
    CONSTRAINT etiqueta_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES perfil(nombre_editor)
);

CREATE TABLE categoria (
    registro_categoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(30) NOT NULL
);

CREATE TABLE revista(

    registro_revista INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_editor VARCHAR(20) NOT NULL,
    nombre_revista VARCHAR(35) NOT NULL,
    archivo MEDIUMBLOB NOT NULL,
    fecha_publicacion DATE NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    costo_suscripcion DECIMAL(7,2) NOT NULL,
    CONSTRAINT revista_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES perfil(nombre_editor)
);

CREATE TABLE caracteristica_revista(
    registro_revista INT NOT NULL PRIMARY KEY,
    fecha_aceptacion DATE,
    estado_revista ENUM('ACEPTADA','EN ESPERA') NOT NULL,
    costo_por_dia DECIMAL(7,2),
    fecha_modificacion_cpd DATE,
    like_revista ENUM('SI','NO') NOT NULL,
    comentario ENUM('SI','NO') NOT NULL,
    suscripcion ENUM('SI','NO') NOT NULL,
    CONSTRAINT caracteristica_revista_fk FOREIGN KEY (registro_revista)
    REFERENCES revista(registro_revista)
);

CREATE TABLE etiqueta_revista(

    registro_revista INT NOT NULL,
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
    intervalo_pago ENUM('MENSUAL','ANUAL') NOT NULL,
    fecha_registro DATE NOT NULL,
    estado_suscripcion ENUM('VIGENTE','CANCELADO') NOT NULL,
    like_suscripcion ENUM('SI','NO') NOT NULL,
    PRIMARY KEY(registro_suscripcion,nombre_suscriptor,registro_revista),
    CONSTRAINT nombre_suscriptor_fk FOREIGN KEY (nombre_suscriptor)
    REFERENCES perfil(nombre_editor),
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
    REFERENCES perfil(nombre_editor)
);

CREATE TABLE comentario(
    registro_comentario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    registro_revista INT NOT NULL,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    texto VARCHAR(200) NOT NULL,
    fecha_comentario DATE NOT NULL
    
);

CREATE TABLE administrador(

    nombre_usuario VARCHAR(30) NOT NULL PRIMARY KEY,
    estado_administrador ENUM('VIGENTE', 'CANCELADO') NOT NULL,
    CONSTRAINT usuario_administrador_fk FOREIGN KEY (nombre_usuario)
    REFERENCES usuario(nombre_usuario)
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
    contenido_anuncio VARCHAR(50),
    cantidad_apariciones INT DEFAULT 0,
    total_pagar DECIMAL(7,2) NOT NULL,
    estado_anuncio ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    url_anuncio VARCHAR(100) DEFAULT ' ',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT tipo_anuncio_fk FOREIGN KEY (tipo_anuncio)
    REFERENCES tipo_anuncio(nombre_tipo)
);

CREATE TABLE etiqueta_anuncio(

    registro_anuncio INT NOT NULL PRIMARY KEY,
    nombre_anuncio VARCHAR(30) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    CONSTRAINT registro_anuncio_fk FOREIGN KEY (registro_anuncio)
    REFERENCES anuncio(registro_anuncio)
);

