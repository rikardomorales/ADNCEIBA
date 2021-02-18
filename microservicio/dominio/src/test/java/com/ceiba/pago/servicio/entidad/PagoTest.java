package com.ceiba.pago.servicio.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;


public class PagoTest {
    private static final String SE_DEBE_INGRESAR_DOCUMENTO_IDENTIFICACION = "Se debe ingresar el documento de identificación";

    @Test(expected=ExcepcionValorObligatorio.class)
    public void validarIdentificacionObligatoriaTest() {
        // arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conDocumentoIdentificacionDeudor(null);

        //act
        Pago pago = pagoTestDataBuilder.build();

        //assert
        Pago pagoTest = new Pago(pago.getIdPago(),pago.getDocumentoIdentificacionDeudor(),
                                 pago.getCodigoFactura(),pago.getValorAdeudado(),pago.getValorPagado(),
                                 pago.getFechaVencimientoPago(),pago.getFechaPago());
     }

}
