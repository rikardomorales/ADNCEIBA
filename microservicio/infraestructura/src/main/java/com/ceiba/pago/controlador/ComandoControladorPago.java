package com.ceiba.pago.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pago.comando.ComandoPago;
import com.ceiba.pago.comando.manejador.ManejadorActualizarPago;
import com.ceiba.pago.comando.manejador.ManejadorCrearPago;
import com.ceiba.pago.comando.manejador.ManejadorEliminarPago;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
@Api(tags = { "Controlador comando Pago"})
public class ComandoControladorPago {
	private static final Logger LOG = LoggerFactory.getLogger(ComandoControladorPago.class);

    private final ManejadorCrearPago manejadorCrearPago;
	private final ManejadorEliminarPago manejadorEliminarPago;
	private final ManejadorActualizarPago manejadorActualizarPago;

    @Autowired
    public ComandoControladorPago(ManejadorCrearPago manejadorCrearPago,
									 ManejadorEliminarPago manejadorEliminarPago,
									 ManejadorActualizarPago manejadorActualizarPago) {
        this.manejadorCrearPago = manejadorCrearPago;
		this.manejadorEliminarPago = manejadorEliminarPago;
		this.manejadorActualizarPago = manejadorActualizarPago;
    }

    @PostMapping
    @ApiOperation("Crear Pago")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPago comandoPago) {
        return manejadorCrearPago.ejecutar(comandoPago);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Pago")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarPago.ejecutar(id);
	}

	@PutMapping
	@ApiOperation("Actualizar Pago")
	public void actualizar(@RequestBody ComandoPago comandoPago)
	{
		try
		{
			manejadorActualizarPago.ejecutar(comandoPago);
		}catch(Exception e)
	  	 {
			 LOG.error("---> "+e.getMessage());
		 }
	}
}
