package com.ceiba.pago.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pago.comando.ComandoPago;
import com.ceiba.pago.comando.fabrica.FabricaPago;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.servicio.ServicioActualizarPago;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarPago implements ManejadorComando<ComandoPago> {

    private final FabricaPago fabricaPago;
    private final ServicioActualizarPago servicioActualizarPago;

    public ManejadorActualizarPago(FabricaPago fabricaPago, ServicioActualizarPago servicioActualizarPago) {
        this.fabricaPago = fabricaPago;
        this.servicioActualizarPago = servicioActualizarPago;
    }

    public void ejecutar(ComandoPago comandoPago) {
        try
        {
            Pago pago = this.fabricaPago.crear(comandoPago);
            this.servicioActualizarPago.ejecutar(pago);
        }catch (Exception e)
        {
            throw e;
        }
    }
}
