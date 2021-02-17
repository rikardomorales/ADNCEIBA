package com.ceiba.pago.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {

    private Long idPago;
    private String documentoIdentificacionDeudor;
    private String codigoFactura;
    private String valorAdeudado;
    private String valorPagado;
    private String fechaVencimientoPago;
    private String fechaPago;
}
