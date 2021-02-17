package com.ceiba.pago.controlador;

import com.ceiba.pago.consulta.ManejadorListarPagos;
import com.ceiba.pago.modelo.dto.DtoPago;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@Api(tags={"Controlador consulta pagos"})
public class ConsultaControladorPago {

    private final ManejadorListarPagos manejadorListarPagos;

    public ConsultaControladorPago(ManejadorListarPagos manejadorListarPagos) {
        this.manejadorListarPagos = manejadorListarPagos;
    }

    @GetMapping
    @ApiOperation("Listar Pagos")
    public List<DtoPago> listar() {
        return this.manejadorListarPagos.ejecutar();
    }

}