package com.ceiba.pago.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoPago {
    private Long idPago;
    private String documentoIdentificacionDeudor;
    private String codigoFactura;
    private String valorAdeudado;
    private String valorPagado;
    private String fechaVencimientoPago;
    private String fechaPago;

}
