package com.ceiba.usuario.comando.fabrica;

import com.ceiba.usuario.modelo.entidad.Usuario;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Component
public class FabricaUsuario {

    public Usuario crear(ComandoUsuario comandoUsuario) {
        String strFecha = comandoUsuario.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(strFecha, formatter);

        return new Usuario(
                comandoUsuario.getId(),
                comandoUsuario.getNombre(),
                comandoUsuario.getClave(),
                dateTime
        );
    }

}
