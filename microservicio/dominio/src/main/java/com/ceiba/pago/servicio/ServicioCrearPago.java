package com.ceiba.pago.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;


public class ServicioCrearPago {

    private static final String EL_PAGO_YA_EXISTE_EN_EL_SISTEMA = "El pago ya existe en el sistema";

    private final RepositorioPago repositorioPago;

    public ServicioCrearPago(RepositorioPago repositorioPago) {
        this.repositorioPago = repositorioPago;
    }

    public Long ejecutar(Pago pago) {
        validarExistenciaPrevia(pago);
        return this.repositorioPago.crear(pago);
    }

    private void validarExistenciaPrevia(Pago pago) {
        boolean existe = this.repositorioPago.existe(pago.getCodigoFactura());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PAGO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
