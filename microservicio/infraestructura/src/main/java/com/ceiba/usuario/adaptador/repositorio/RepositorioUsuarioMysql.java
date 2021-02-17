package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioMysql implements RepositorioUsuario {
    private static final String NAMESPACE = "usuario";
    private static final String CREAR = "crear";
    private static final String ACTUALIZAR = "actualizar";
    private static final String ELIMINAR = "eliminar";
    private static final String EXISTE = "existe";
    private static final String EXISTE_EXCLUYE_ID = "existeExcluyendoId";
    private static final String CAMPO_ID = "id";
    private static final String CAMPO_NOMBRE = "nombre";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = NAMESPACE, value=CREAR)
    private static String sqlCrear;

    @SqlStatement(namespace = NAMESPACE, value=ACTUALIZAR)
    private static String sqlActualizar;

    @SqlStatement(namespace = NAMESPACE, value=ELIMINAR)
    private static String sqlEliminar;

    @SqlStatement(namespace = NAMESPACE, value=EXISTE)
    private static String sqlExiste;

    @SqlStatement(namespace = NAMESPACE, value=EXISTE_EXCLUYE_ID)
    private static String sqlExisteExcluyendoId;

    public RepositorioUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Usuario usuario) {
        return this.customNamedParameterJdbcTemplate.crear(usuario, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_NOMBRE, nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Usuario usuario) {
        this.customNamedParameterJdbcTemplate.actualizar(usuario, sqlActualizar);
    }

    @Override
    public boolean existeExcluyendoId(Long id, String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID, id);
        paramSource.addValue(CAMPO_NOMBRE, nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteExcluyendoId,paramSource, Boolean.class);
    }
}
