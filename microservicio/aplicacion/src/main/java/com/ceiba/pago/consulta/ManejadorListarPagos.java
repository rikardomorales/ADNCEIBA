package com.ceiba.pago.consulta;

import com.ceiba.pago.modelo.dto.DtoPago;
import com.ceiba.pago.puerto.dao.DaoPago;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPagos {

    private final DaoPago daoPago;

    public ManejadorListarPagos(DaoPago daoPago){
        this.daoPago = daoPago;
    }

    public List<DtoPago> ejecutar(){ return this.daoPago.listar(); }

    public List<DtoPago> ejecutar(String identificacion){ return this.daoPago.listarPorCedula(identificacion); }
}