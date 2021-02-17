package com.ceiba.pago.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class ServicioCrearPagoTest {
    private static final String VALOR_INCREMENTO_ESPERADO = "1100000";
    private static final String VALOR_FECHA_ESPERADO = "2021-02-18";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";
    private static final Long ID_PAGO =  99999L;

    @Test
    public void crearPagoTest() {
        // arrange
        PagoTestDataBuilder PagoTestDataBuilder = new PagoTestDataBuilder().conIdPago(ID_PAGO);

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        //act
        Pago pago = new PagoTestDataBuilder().build();
        Mockito.when(repositorioPago.crear(pago)).thenReturn(ID_PAGO);

        //assert
        assertEquals(pago.getIdPago(), ID_PAGO);
    }
}
