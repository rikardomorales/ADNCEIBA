package com.ceiba.pago.servicio;

import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class ServicioCrearPagoTest {
    private static final Long ID_PAGO =  99999L;

    @Test
    public void crearPagoTest() throws Exception {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder = new PagoTestDataBuilder().conIdPago(ID_PAGO);

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        //act
        Pago pago = new PagoTestDataBuilder().build();
        Mockito.when(repositorioPago.crear(pago)).thenReturn(ID_PAGO);
        servicioCrearPago.ejecutar(pago);

        //assert
        assertEquals(pago.getIdPago(), ID_PAGO);
    }
}
