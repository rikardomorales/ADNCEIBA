package com.ceiba.pago.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
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
        Pago pago = PagoTestDataBuilder.build();
        Mockito.when(repositorioPago.crear(pago)).thenReturn(ID_PAGO);
        servicioCrearPago.ejecutar(pago);

        //assert
        assertEquals(pago.getIdPago(), ID_PAGO);
    }

    @Test
    public void validarPagoExistenciaPreviaTest() {
        // arrange
        Pago pago = new PagoTestDataBuilder().conIdPago(1L).build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);

        //act
        Mockito.when(repositorioPago.existe(pago.getCodigoFactura())).thenReturn(Boolean.TRUE);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPago.ejecutar(pago), ExcepcionDuplicidad.class, servicioCrearPago.EL_PAGO_YA_EXISTE_EN_EL_SISTEMA);
    }

}
