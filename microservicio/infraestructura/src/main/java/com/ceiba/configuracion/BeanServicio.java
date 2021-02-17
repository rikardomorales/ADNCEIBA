package com.ceiba.configuracion;

import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.ServicioActualizarPago;
import com.ceiba.pago.servicio.ServicioCrearPago;
import com.ceiba.pago.servicio.ServicioEliminarPago;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

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
