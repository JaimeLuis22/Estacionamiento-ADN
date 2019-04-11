package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.dominio.Tipo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Repository
public class DaoTipoH2 implements DaoTipo{
	
	// Propiedad que escribe en el log
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoTipoH2.class);
	
	// Manejo de parametros por nombre
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// Manejo de parametros por indice
    private JdbcTemplate jdbcTemplate;
    
    // Sql para consultar en base de datos
    private static final String SQL_COUNT_TIPO 			= "SELECT count(*) FROM TIPO";
    private static final String SQL_SELECT_TIPO 		= "SELECT * FROM TIPO";    
    private static final String SQL_INSERT_TIPO 		= "INSERT INTO TIPO (nombre) values (:nombre)";    
    private static final String SQL_UPDATE_TIPO 		= "UPDATE TIPO set nombre = :nombre WHERE id_tipo = :idTipo";
    private static final String SQL_FIND_TIPO_BY_ID 	= "SELECT * FROM TIPO WHERE id_tipo = ?";
    private static final String SQL_FIND_TIPO_BY_NOMBRE = "SELECT * FROM TIPO WHERE nombre = :nombre";
    
    /**
     * Metodo que inyecta el data source
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
	 * Metodo que inserta un tipo en la base de datos
	 * @param tipo
	 */
	@Override
	public void insertTipo(Tipo tipo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(tipo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_TIPO, parameterSource);		
	}

	/**
	 * Metodo que actualiza un tipo en la base de datos
	 * @param tipo
	 */
	@Override
	public void updateTipo(Tipo tipo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(tipo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_TIPO, parameterSource);
		
	}

	/**
     * Metodo que elimina un tipo en la base de datos
     * @param tipo
     */
	@Override
	public String deleteTipo(Tipo tipo) {
		return "eliminado";
	}

	/**
     * Metodo que encuentra un tipo por el nombre
     * @param tipo
     * @return
     */
	@Override
	public Tipo findTipoByNombre(Tipo tipo) throws EstacionamientoException{
		Tipo tipoR = null;		
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(tipo);			
			RowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);
			tipoR = this.namedParameterJdbcTemplate.queryForObject(SQL_FIND_TIPO_BY_NOMBRE, namedParameters, tipoRowMapper);
		} catch (Exception e) {
			LOGGER.error("[DaoTipoH2][findTipoByNombre] Excepcion: "+e.getMessage(), e);
		}
        return tipoR;
	}

	/**
     * Metodo que retorna un listado de tipos de la base de datos
     * @return
     */
	@Override
	public List<Tipo> findAllTipos() {
        RowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);
        return this.jdbcTemplate.query(SQL_SELECT_TIPO, tipoRowMapper);
	}

	/**
     * Metodo que cuenta los tipos registrados en la base de datos
     * @return
     */
	@Override
	public int countTipos() {
        return this.jdbcTemplate.queryForObject(SQL_COUNT_TIPO, Integer.class);
	}

	/**
     * Metodo que encuentra un tipo por el id
     * @param idTipo
     * @return
     */
	@Override
	public Tipo findTipoById(long idTipo) {
		Tipo tipo = null;
		try {
			BeanPropertyRowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);   
			tipo = jdbcTemplate.queryForObject(SQL_FIND_TIPO_BY_ID, tipoRowMapper, idTipo);
		} catch (Exception e) {
			LOGGER.error("[DaoTipoH2][findTipoById] Excepcion: "+e.getMessage(), e);
		}        
        return tipo;
	}

}
