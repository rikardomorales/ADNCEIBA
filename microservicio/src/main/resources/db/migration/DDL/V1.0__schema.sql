create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

CREATE TABLE pagos (
    id_Pago int(11) NOT NULL AUTO_INCREMENT,
	documento_Identificacion_deudor int(11) NOT NULL,
    codigo_factura VARCHAR(10) NOT NULL,
	valor_adeudado DECIMAL(20,2) NOT NULL,
	valor_pagado DECIMAL(20,2),
	fecha_vencimiento_pago VARCHAR(10) NOT NULL,
	fecha_pago VARCHAR(10),
    PRIMARY KEY (id_Pago)
);