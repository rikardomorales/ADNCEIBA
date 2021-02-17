package com.ceiba.pago.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.util.Util;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServicioActualizarPagoTest {
    private static final String VALOR_INCREMENTO_ESPERADO = "1100000";
    private static final String VALOR_FECHA_ESPERADO = "2021-02-18";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";

    @Test
    public void validarFechaPagoTest() throws Exception {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder =
                new PagoTestDataBuilder().conFechaVencimientoPago("2021-03-30").conFechaPago("2021-02-17").conValorAdeudado("1000000");
        Pago pago = new PagoTestDataBuilder().build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        Pago pagoRespueta = servicioActualizarPago.validarFechaPago(pago);

        // act - assert
        assertEquals(VALOR_INCREMENTO_ESPERADO,pagoRespueta.getValorAdeudado());
    }

    @Test
    public void validarHoraPagoTest() throws Exception {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder =
                new PagoTestDataBuilder().conFechaVencimientoPago("2021-03-30").conFechaPago("2021-02-17").conValorAdeudado("1000000");
        Pago pago = new PagoTestDataBuilder().build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        Pago pagoRespueta = servicioActualizarPago.validarHoraPago(pago);
        Date dtmFechaPago = Util.convertDate(pagoRespueta.getFechaPago(),FORMATO_FECHA);
        Date dtmFechaEsperada = Util.convertDate(VALOR_FECHA_ESPERADO,FORMATO_FECHA);

        // act - assert
        assertTrue(Util.esIgualFecha(dtmFechaPago,dtmFechaEsperada));
    }

    @Test
    public void validarPagoExistenciaPreviaTest() {
        // arrange
        Pago pago = new PagoTestDataBuilder().conIdPago(1L).build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existeExcluyendoId(Mockito.anyLong(),Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);
        // act - assert
        BasePrueba.assertThrows(() -> {
            try {
                servicioActualizarPago.ejecutar(pago);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ExcepcionDuplicidad.class,"El Pago ya existe en el sistema");
    }

    @Test
    public void validarPagoTest() throws Exception {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder =
                new PagoTestDataBuilder().conFechaVencimientoPago("2021-03-30").conFechaPago("2021-02-17").conValorAdeudado("1000000");
        Pago pago = new PagoTestDataBuilder().build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        BasePrueba.assertThrows(() -> {
            try {
                servicioActualizarPago.ejecutar(pago);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ExcepcionDuplicidad.class,"El Pago ya existe en el sistema");
    }
}