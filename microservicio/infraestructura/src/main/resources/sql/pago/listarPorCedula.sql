select id_Pago,documento_Identificacion_Deudor,codigo_Factura,
valor_Adeudado,valor_Pagado,fecha_Vencimiento_Pago,fecha_Pago
from pagos where documento_Identificacion_Deudor=:documentoIdentificacionDeudor