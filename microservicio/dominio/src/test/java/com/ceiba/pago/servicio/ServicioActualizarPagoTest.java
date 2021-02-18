package com.ceiba.pago.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
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

    @Test
    public void actualizarPagoTest(){
        // arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Pago pago = pagoTestDataBuilder.build();
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);


        //act
        Mockito.when(repositorioPago.existeincluyendoId(Mockito.anyLong(),Mockito.anyString())).thenReturn(Boolean.TRUE);
        servicioActualizarPago.ejecutar(pago);

        //assert
        Mockito.verify(repositorioPago).actualizar(pago);
    }

    @Test
    public void validarPagoExistenciaPreviaTest(){
        // arrange
        Pago pago = new PagoTestDataBuilder().conIdPago(1L).build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);

        //act
        Mockito.when(repositorioPago.existeExcluyendoId(Mockito.anyLong(),Mockito.anyString())).thenReturn(Boolean.TRUE);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarPago.ejecutar(pago), ExcepcionNoExiste.class, servicioActualizarPago.EL_PAGO_NO_EXISTE_EN_EL_SISTEMA );
    }

}
