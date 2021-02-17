package com.ceiba.configuracion;

import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.ServicioActualizarPago;
import com.ceiba.pago.servicio.ServicioCrearPago;
import com.ceiba.pago.servicio.ServicioEliminarPago;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {
    @Bean
    public ServicioCrearPago servicioCrearPago(RepositorioPago repositorioPago) {
        return new ServicioCrearPago(repositorioPago);
    }

    @Bean
    public ServicioEliminarPago servicioEliminarPago(RepositorioPago repositorioPago) {
        return new ServicioEliminarPago(repositorioPago);
    }

    @Bean
    public ServicioActualizarPago servicioActualizarPago(RepositorioPago repositorioPago) {
        return new ServicioActualizarPago(repositorioPago);
    }
    
    
}
