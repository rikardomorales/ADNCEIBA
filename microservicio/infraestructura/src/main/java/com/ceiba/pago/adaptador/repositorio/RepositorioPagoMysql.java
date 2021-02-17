package com.ceiba.pago.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPagoMysql implements RepositorioPago {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace= "pago", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace= "pago", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace= "pago", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace= "pago", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace= "pago", value="existeExcluyendoId")
    private static String sqlExisteExcluyendoId;

    @SqlStatement(namespace= "pago", value="existeIncluyendoId")
    private static String sqlExisteIncluyendoId;

    public RepositorioPagoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Pago pago) {
        return this.customNamedParameterJdbcTemplate.crear(pago, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPago", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String codigoFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoFactura", codigoFactura);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Pago pago) {
        this.customNamedParameterJdbcTemplate.actualizar(pago, sqlActualizar);
    }

    @Override
    public boolean existeExcluyendoId(Long id, String codigoFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPago", id);
        paramSource.addValue("codigoFactura", codigoFactura);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteExcluyendoId,paramSource, Boolean.class);
    }

    @Override
    public boolean existeincluyendoId(Long id, String codigoFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPago", id);
        paramSource.addValue("codigoFactura", codigoFactura);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteIncluyendoId,paramSource, Boolean.class);
    }
}
