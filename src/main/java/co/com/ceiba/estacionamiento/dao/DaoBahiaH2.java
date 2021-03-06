package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.dominio.Bahia;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Repository
public class DaoBahiaH2 implements DaoBahia {
	
	// Propiedad que escribe en el log
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoBahiaH2.class);
    
    // Manejo de parametros por nombre 
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    // Manejo de parametros por indice
    private JdbcTemplate jdbcTemplate;
    
    // Sql para consultar en base de datos
    private static final String SQL_COUNT_BAHIA 				= "SELECT count(*) FROM BAHIA";
    private static final String SQL_SELECT_BAHIA 				= "SELECT * FROM BAHIA";
    private static final String SQL_INSERT_BAHIA 				= "INSERT INTO BAHIA (numero, estado, id_tipo) values (:numero, :estado, :idTipo)";
    private static final String SQL_UPDATE_BAHIA 				= "UPDATE BAHIA set numero = :numero, estado = :estado, id_tipo = :idTipo WHERE id_bahia = :idBahia";
    private static final String SQL_FIND_BAHIA_BY_ID 			= "SELECT * FROM BAHIA WHERE id_bahia = ?";
    private static final String SQL_FIND_BAHIA_BY_NUMERO 		= "SELECT * FROM BAHIA WHERE numero = :numero";
    private static final String SQL_COUNT_BAHIA_BY_ID_TIPO 		= "SELECT count(*) FROM BAHIA WHERE id_tipo = ?";
    private static final String SQL_COUNT_BAHIA_BY_ID_TIPO_STATE = "SELECT count(*) FROM BAHIA WHERE id_tipo = ? AND estado = ?";
    
    /**
     * Metodo que inyecta el data source
     *
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Metodo para insertar una bahia en la base de datos
     *
     * @param bahia
     */
    @Override
    public void insertBahia(Bahia bahia) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bahia);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_BAHIA, parameterSource);
    }

    /**
     * Metodo para actualizar una bahia en la base de datos
     *
     * @param bahia
     */
    @Override
    public void updateBahia(Bahia bahia) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bahia);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_BAHIA, parameterSource);
    }

    /**
     * Metodo que elimina una bahia en la base de datos
     *
     * @param bahia
     */
    @Override
    public String deleteBahia(Bahia bahia) {
        return "eliminado";
    }

    /**
     * Metodo que encuentra una bahia por el numero
     *
     * @param bahia
     * @return
     */
    @Override
    public Bahia findBahiaByNumero(Bahia bahia) throws EstacionamientoException {
        Bahia bahiaRes = null;
        try {
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(bahia);
            RowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);
            bahiaRes = this.namedParameterJdbcTemplate.queryForObject(SQL_FIND_BAHIA_BY_NUMERO, namedParameters, bahiaRowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("[DaoBahiaH2][findBahiaByNumero] Excepcion: " + e.getMessage(), e);
        }

        return bahiaRes;
    }

    /**
     * Metodo que retorna un listado de bahias
     *
     * @return
     */
    @Override
    public List<Bahia> findAllBahias() {
        RowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);
        return this.jdbcTemplate.query(SQL_SELECT_BAHIA, bahiaRowMapper);
    }

    /**
     * Metodo que cuenta las bahias registradas en la base de datos
     *
     * @return
     */
    @Override
    public int countBahias() {
        return this.jdbcTemplate.queryForObject(SQL_COUNT_BAHIA, Integer.class);
    }

    /**
     * Metodo que encuentra una bahia por su id
     *
     * @param idBahia
     * @return
     */
    @Override
    public Bahia findBahiaById(long idBahia) {
        Bahia bahia = null;
        BeanPropertyRowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);
        bahia = jdbcTemplate.queryForObject(SQL_FIND_BAHIA_BY_ID, bahiaRowMapper, idBahia);
        return bahia;
    }

    /**
     * Metodo que cuenta las bahias por el idTipo
     *
     * @param idTipo
     * @return
     */
    @Override
    public int countBahiasByIdTipo(int idTipo) {
        return this.jdbcTemplate.queryForObject(SQL_COUNT_BAHIA_BY_ID_TIPO, Integer.class, idTipo);
    }

    /**
     * Metodo que cuenta las bahias por el idTipo y su estado
     *
     * @param idTipo
     * @return
     */
    @Override
    public int countBahiasByIdTipoState(int idTipo) {
        return this.jdbcTemplate.queryForObject(SQL_COUNT_BAHIA_BY_ID_TIPO_STATE, Integer.class, idTipo, "Disponible");
    }
}
