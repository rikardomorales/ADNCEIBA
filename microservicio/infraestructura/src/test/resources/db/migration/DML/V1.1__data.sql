
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

insert into pagos
(id_pago,documento_Identificacion_Deudor,codigo_Factura,valor_Adeudado,
valor_Pagado,fecha_Vencimiento_Pago,fecha_Pago)
values(99999,'123456789','FV-9898',1000000,0,'2020-03-30','');
