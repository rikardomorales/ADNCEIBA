package com.ceiba.pago.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.util.Util;
import java.util.Date;

public class ServicioActualizarPago {

    private static final String EL_PAGO_NO_EXISTE_EN_EL_SISTEMA = "El pago no se puede actualizar porque, no existe en el sistema";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";
    private final RepositorioPago repositorioPago;

    public ServicioActualizarPago(RepositorioPago repositorioPago) {
        this.repositorioPago = repositorioPago;
    }

    public void ejecutar(Pago pago) throws Exception {
        validarExistenciaPrevia(pago);
        pago  = validarFechaPago(pago);
        pago  = validarHoraPago(pago);
        this.repositorioPago.actualizar(pago);
    }

    private void validarExistenciaPrevia(Pago pago) {
        boolean existe = this.repositorioPago.existeincluyendoId(pago.getIdPago(),pago.getCodigoFactura());

        if(!existe)
        {
            throw new ExcepcionDuplicidad(EL_PAGO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    public Pago validarFechaPago(Pago pago){
        Date dtmFechaPago = Util.convertDate(pago.getFechaPago(),FORMATO_FECHA);
        int dia = Util.getDayOfMonth(dtmFechaPago);

        if(dia>15)
        {
            double incremento = Double.parseDouble(pago.getValorAdeudado())+(Double.parseDouble(pago.getValorAdeudado()))*0.1;
            pago.setValorAdeudado(incremento+"");
        }

        return pago;
    }

    public Pago validarHoraPago(Pago pago) {
        Date dtmFechaPago = Util.convertDate(pago.getFechaPago(),FORMATO_FECHA);
        int hora = Util.getHourOfDay(dtmFechaPago);

        if(hora>=8)
        {
            String dtmFechaPagoNueva = "";
            dtmFechaPagoNueva = Util.convertDate(Util.getDateAdd(dtmFechaPago,1),FORMATO_FECHA);
            pago.setFechaPago(dtmFechaPagoNueva);
        }

        return pago;
    }
}
