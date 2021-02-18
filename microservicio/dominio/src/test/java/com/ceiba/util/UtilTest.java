package com.ceiba.util;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class UtilTest {
    private static final String FORMATO_ERRADO = "aacxcs*ASAS";

    @Test(expected=Exception.class)
    public void validarConversionFechaTest() {
        // arrange
        Date fechaPrueba = new Date();

        //act - assert
        Util.convertDate(fechaPrueba,FORMATO_ERRADO);
     }

    @Test
    public void validarSumaDiasTest() {
        // arrange
        Date fechaActual = new Date();
        Date fechaDespues = new Date();

        //act
        fechaDespues = Util.getDateAdd(fechaActual,1);

        assertNotEquals(fechaActual,fechaDespues);
    }


}
