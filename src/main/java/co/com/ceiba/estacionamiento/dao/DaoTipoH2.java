package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

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
import co.com.ceiba.estacionamiento.excepcion.error.ErrorCodes;

@Repository
public class DaoTipoH2 implements DaoTipo{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Metodo que inyecta el data source
     * @param dataSource
     */
    @Autowired
    public void setDataSource(DataSource dataSource) {        
        // Manejo de parametros por indice
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        
        // Manejo de parametros por nombre
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    private static final String SQL_SELECT_TIPO = "SELECT * FROM TIPO";
    
    private static final String SQL_INSERT_TIPO = "INSERT INTO TIPO (nombre) values (:nombre)";
    
    private static final String SQL_UPDATE_TIPO = "UPDATE TIPO set nombre = :nombre WHERE id_tipo = :idTipo";

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
	public void deleteTipo(Tipo tipo) {
		// TODO Auto-generated method stub
		
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
			String sql = "SELECT * FROM TIPO WHERE nombre = :nombre";
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(tipo);
			
			RowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);
			tipoR = this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, tipoRowMapper);
		} catch (Exception e) {
			throw new EstacionamientoException(e.getMessage(), ErrorCodes.ERROR_NEG_402.getCodigo());
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
		String sql = "SELECT count(*) FROM TIPO";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
     * Metodo que encuentra un tipo por el id
     * @param idTipo
     * @return
     */
	@Override
	public Tipo findTipoById(long idTipo) {
		Tipo tipo = null;
		String sql = "SELECT * FROM TIPO WHERE id_tipo = ?";
        
		BeanPropertyRowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);   
		tipo = jdbcTemplate.queryForObject(sql, tipoRowMapper, idTipo);
        
        return tipo;
	}

}
