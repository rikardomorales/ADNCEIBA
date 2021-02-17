package com.ceiba.pago.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;


public class ServicioCrearPagoTest {
    private static final String VALOR_INCREMENTO_ESPERADO = "1100000";
    private static final String VALOR_FECHA_ESPERADO = "2021-02-18";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";

    @Test
    public void validarPagoExistenciaPreviaTest() {
        // arrange
        Pago pago = new PagoTestDataBuilder().build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPago.ejecutar(pago), ExcepcionDuplicidad.class,"El Pago ya existe en el sistema");
    }
}