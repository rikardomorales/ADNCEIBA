package com.ceiba.pago.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pago.modelo.dto.DtoPago;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoPago implements RowMapper<DtoPago>, MapperResult {

    @Override
    public DtoPago mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long idPago = resultSet.getLong("id_pago");
        String documentoIdentificacionDeudor = resultSet.getString("documento_identificacion_deudor");
        String codigoFactura = resultSet.getString("codigo_factura");
        String valorAdeudado = resultSet.getString("valor_adeudado");
        String valorPagado = resultSet.getString("valor_pagado");
        String fechaVencimientoPago = resultSet.getString("fecha_vencimiento_Pago");
        String fechaPago = resultSet.getString("fecha_pago");



        return new DtoPago(idPago,documentoIdentificacionDeudor,
                           codigoFactura,valorAdeudado,valorPagado,
                           fechaVencimientoPago,fechaPago);
    }

}
