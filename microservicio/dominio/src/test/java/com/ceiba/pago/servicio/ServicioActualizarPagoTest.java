package com.ceiba.pago.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.util.Util;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServicioActualizarPagoTest {
    private static final Logger LOG = LoggerFactory.getLogger(ServicioActualizarPagoTest.class);

    private static final String VALOR_INCREMENTO_ESPERADO = "1100000.0";
    private static final String VALOR_FECHA_ESPERADO = "2021-02-18";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";
    private static final Long ID_PAGO =  99999L;


    @Test
    public void validarFechaPagoTest()  {
        // arrange
        PagoTestDataBuilder pagoTestDataBuilder =
                new PagoTestDataBuilder().conFechaVencimientoPago("2021-03-30").conFechaPago("2021-02-17").conValorAdeudado("1000000");
        Pago pago = pagoTestDataBuilder.build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        Pago pagoRespueta = servicioActualizarPago.validarFechaPago(pago);

        // act - assert
        assertEquals(VALOR_INCREMENTO_ESPERADO,pagoRespueta.getValorAdeudado());
    }

    @Test
    public void validarHoraPagoTest()
    {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder =
                new PagoTestDataBuilder().conFechaVencimientoPago("2021-03-30").conFechaPago("2021-02-17").conValorAdeudado("1000000");
        Pago pago = new PagoTestDataBuilder().build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        Pago pagoRespueta = servicioActualizarPago.validarHoraPago(pago);
        try
        {
          Date dtmFechaPago = Util.convertDate(pagoRespueta.getFechaPago(),FORMATO_FECHA);
          Date dtmFechaEsperada = Util.convertDate(VALOR_FECHA_ESPERADO,FORMATO_FECHA);

           // act - assert
           assertTrue(Util.esIgualFecha(dtmFechaPago,dtmFechaEsperada));
         }catch (Exception e)
          {
            LOG.error(e.getMessage());
          }
    }


    @Test(expected= ExcepcionNoExiste.class)
    public void actualizarPagoNoExistenteTest() throws Exception {
        // arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        //act
        pagoTestDataBuilder.conCodigoFactura("FV-9898").conIdPago(99999L);
        Pago pago = new PagoTestDataBuilder().build();
        servicioActualizarPago.ejecutar(pago);

        //assert
        assertEquals(pago.getIdPago(), ID_PAGO);
    }

}
