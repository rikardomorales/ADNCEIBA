package com.ceiba.pago.puerto.dao;

import com.ceiba.pago.modelo.dto.DtoPago;

import java.util.List;

public interface DaoPago{

    /**
     * Permite listar pagos
     * @return los pagos
     */
    List<DtoPago> listar();

    /**
     * Permite listar pagos por cedula
     * @return los pagos
     */
    List<DtoPago> listarPorCedula(String documentoIdentificacionDeudor);
}
