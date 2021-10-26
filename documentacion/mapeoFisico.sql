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
    foto LONGBLOB,
    hobby VARCHAR(300) DEFAULT '',
    descripcion VARCHAR(300) DEFAULT '',
    gustos VARCHAR(300) DEFAULT '',
    CONSTRAINT perfil_usuario_fk FOREIGN KEY (nombre_editor)
    REFERENCES usuario(nombre_usuario) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE etiqueta (
    nombre_etiqueta VARCHAR(30) NOT NULL PRIMARY KEY
);

CREATE TABLE etiqueta_editor (

    nombre_editor VARCHAR(20) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    PRIMARY KEY(nombre_editor, nombre_etiqueta),
    CONSTRAINT etiqueta_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES perfil(nombre_editor) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT etiqueta_nombre_fk FOREIGN KEY (nombre_etiqueta)
    REFERENCES etiqueta(nombre_etiqueta) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE categoria (
   
    nombre_categoria VARCHAR(30) NOT NULL PRIMARY KEY
);


CREATE TABLE revista(

    registro_revista INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_editor VARCHAR(20) NOT NULL,
    nombre_revista VARCHAR(35) NOT NULL,
    archivo LONGBLOB NOT NULL,
    fecha_publicacion DATE NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    nombre_categoria VARCHAR(30) NOT NULL,
    costo_suscripcion DECIMAL(7,2) NOT NULL,
    fecha_aceptacion DATE DEFAULT '2021-01-01',
    estado_revista ENUM('ACEPTADA','ENESPERA') DEFAULT 'ENESPERA',
    costo_por_dia DECIMAL(7,2) DEFAULT 0,
    fecha_modificacion_cpd DATE DEFAULT '2021-01-01',
    like_revista ENUM('SI','NO') NOT NULL,
    comentario ENUM('SI','NO') NOT NULL,
    suscripcion ENUM('SI','NO') NOT NULL,
    CONSTRAINT revista_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES perfil(nombre_editor) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT categoria_revista_fk FOREIGN KEY (nombre_categoria)
    REFERENCES categoria(nombre_categoria) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE etiqueta_revista(

    registro_revista INT NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    PRIMARY KEY (registro_revista, nombre_etiqueta),
    CONSTRAINT etiqueta_revista_fk FOREIGN KEY (registro_revista)
    REFERENCES revista(registro_revista) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT nombre_etiqueta_fk FOREIGN KEY (nombre_etiqueta)
    REFERENCES etiqueta(nombre_etiqueta) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE suscripcion(

    registro_suscripcion INT NOT NULL AUTO_INCREMENT,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    registro_revista INT NOT NULL,
    nombre_revista VARCHAR(30) NOT NULL,
    total_pago DECIMAL(7,2),
    intervalo_pago ENUM('MENSUAL','ANUAL') NOT NULL,
    fecha_registro DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado_suscripcion ENUM('VIGENTE','CANCELADO') NOT NULL,
    like_suscripcion ENUM('SI','NO') NOT NULL,
    PRIMARY KEY(registro_suscripcion,nombre_suscriptor,registro_revista),
    CONSTRAINT nombre_suscriptor_fk FOREIGN KEY (nombre_suscriptor)
    REFERENCES perfil(nombre_editor) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT registro_revista_fk FOREIGN KEY (registro_revista)
    REFERENCES revista(registro_revista) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE cuenta_editor(

    registro_cuenta INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_editor VARCHAR(20) NOT NULL,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    registro_revista VARCHAR(35) NOT NULL,
    total_pagar DECIMAL(7,2) NOT NULL,
    costo_descuento DECIMAL(7,2) NOT NULL,
    ganancia DECIMAL(7,2) NOT NULL,
    fecha_pago DATE NOT NULL,
    CONSTRAINT cuenta_editor_fk FOREIGN KEY (nombre_editor)
    REFERENCES perfil(nombre_editor) ON UPDATE CASCADE
);

CREATE TABLE comentario(
    registro_comentario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    registro_revista INT NOT NULL,
    nombre_suscriptor VARCHAR(20) NOT NULL,
    texto VARCHAR(200) NOT NULL,
    fecha_comentario DATE NOT NULL,
    CONSTRAINT comentario_suscriptor_fk FOREIGN KEY (nombre_suscriptor)
    REFERENCES suscripcion(nombre_suscriptor) ON UPDATE CASCADE
    
);

CREATE TABLE administrador(

    nombre_usuario VARCHAR(30) NOT NULL PRIMARY KEY,
    estado_administrador ENUM('VIGENTE', 'CANCELADO') NOT NULL,
    CONSTRAINT usuario_administrador_fk FOREIGN KEY (nombre_usuario)
    REFERENCES usuario(nombre_usuario) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO usuario (nombre_usuario, password, tipo) VALUES ('admin','admin','ADMIN');
INSERT INTO administrador (nombre_usuario, estado_administrador) VALUES ('admin', 'VIGENTE');

CREATE TABLE porcentaje_impuesto(
    registro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    porcentaje DECIMAL(5,2) NOT NULL,
    fecha_actualizacion DATE NOT NULL
);

CREATE TABLE tipo_anuncio(

    nombre_tipo VARCHAR(30) NOT NULL PRIMARY KEY,
    costo_dia DECIMAL(7,2) NOT NULL
);

INSERT INTO tipo_anuncio (nombre_tipo, costo_dia) VALUES ('TEXTO', 0), ('TEXTO E IMAGEN', 0),('TEXTO Y VIDEO',0);

CREATE TABLE anuncio(

    registro_anuncio INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    tipo_anuncio VARCHAR(30) NOT NULL,
    nombre_anuncio VARCHAR(30) NOT NULL,
    nombre_anunciante VARCHAR(30) NOT NULL,
    texto_anuncio  VARCHAR(300) NOT NULL,
    contenido_anuncio VARCHAR(300),
    cantidad_apariciones INT DEFAULT 0,
    total_pagar DECIMAL(7,2) NOT NULL,
    estado_anuncio ENUM('ACTIVO', 'INACTIVO','VENCIDO') NOT NULL,
    url_anuncio VARCHAR(100) DEFAULT ' ',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT tipo_anuncio_fk FOREIGN KEY (tipo_anuncio)
    REFERENCES tipo_anuncio(nombre_tipo) ON UPDATE CASCADE
);

CREATE TABLE etiqueta_anuncio(

    registro_anuncio INT NOT NULL PRIMARY KEY,
    nombre_anuncio VARCHAR(30) NOT NULL,
    nombre_etiqueta VARCHAR(30) NOT NULL,
    CONSTRAINT registro_anuncio_fk FOREIGN KEY (registro_anuncio)
    REFERENCES anuncio(registro_anuncio) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT registro_etiqueta_anuncio_fk FOREIGN KEY (nombre_etiqueta)
    REFERENCES etiqueta(nombre_etiqueta) ON UPDATE CASCADE ON DELETE CASCADE
);

