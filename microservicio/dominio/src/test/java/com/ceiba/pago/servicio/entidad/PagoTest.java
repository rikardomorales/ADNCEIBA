package com.ceiba.pago.servicio.entidad;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class PagoTest {
    private static final String SE_DEBE_INGRESAR_DOCUMENTO_IDENTIFICACION = "Se debe ingresar el documento de identificación";

    @Test(expected=ExcepcionValorObligatorio.class)
    public void validarIdentificacionObligatoriaTest() {
        // arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conDocumentoIdentificacionDeudor(null);
        boolean valido = false;

        //act
        Pago pago = pagoTestDataBuilder.build();

        //assert
        validarObligatorio(pago.getDocumentoIdentificacionDeudor(), SE_DEBE_INGRESAR_DOCUMENTO_IDENTIFICACION);
    }

}
