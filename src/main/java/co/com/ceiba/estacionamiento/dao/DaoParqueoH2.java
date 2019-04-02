package co.com.ceiba.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;
import co.com.ceiba.estacionamiento.excepcion.error.ErrorCodes;

@Repository
public class DaoParqueoH2 implements DaoParqueo{

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
    
    private static final String SQL_SELECT_PARQUEO = "SELECT * FROM PARQUEO";
    
    private static final String SQL_INSERT_PARQUEO = "INSERT INTO PARQUEO (fecha_inicial, fecha_fin, costo, estado, id_vehiculo) values (:fechaInicial, :fechaFin, :costo, :estado, :idVehiculo)";
    
    private static final String SQL_UPDATE_PARQUEO = "UPDATE PARQUEO set fecha_inicial = :fechaInicial, fecha_fin = :fechaFin, costo = :costo, estado = :estado WHERE id_parqueo = :idParqueo";
    
    /**
	 * Metodo que inserta un parqueo en la base de datos
	 * @param parqueo
	 */
	@Override
	public void insertParqueo(Parqueo parqueo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parqueo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_PARQUEO, parameterSource);		
	}

	/**
	 * Metodo que actualiza un parqueo en la base de datos
	 * @param parqueo
	 */
	@Override
	public void updateParqueo(Parqueo parqueo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parqueo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_PARQUEO, parameterSource);
	}

	 /**
     * Metodo que elimina un parqueo en la base de datos
     * @param parqueo
     */
	@Override
	public void deleteParqueo(Parqueo parqueo) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Metodo que encuentra un parqueo por el id
     * @param idParqueo
     * @return
     */
	@Override
	public Parqueo findParqueoById(long idParqueo) throws EstacionamientoException{
		Parqueo parqueo = null;
		try {
			String sql = "SELECT * FROM PARQUEO WHERE id_parqueo = ?";
        
			BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
			parqueo = jdbcTemplate.queryForObject(sql, parqueoRowMapper, idParqueo);
		} catch (EmptyResultDataAccessException e) {
			//throw new EstacionamientoException(e.getMessage(), ErrorCodes.ERROR_NEG_402.getCodigo());
			parqueo = null;
		}	
        
        return parqueo;
	}

	/**
     * Metodo que retorna un listado de parqueos
     * @return
     */
	@Override
	public List<Parqueo> findAllParqueos() {
		RowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);
        return this.jdbcTemplate.query(SQL_SELECT_PARQUEO, parqueoRowMapper);
	}

	/**
     * Metodo que cuenta los parqueos registrados en la base de datos
     * @return
     */
	@Override
	public int countParqueos() {
		String sql = "SELECT count(*) FROM PARQUEO";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
     * Metodo que encuentra un parqueo por el idVehiculo
     * @param idVehiculo
     * @return
     */
	@Override
	public Parqueo findParqueoByIdVehiculo(int idVehiculo) {
		Parqueo parqueo = null;
		String sql = "SELECT * FROM PARQUEO WHERE id_vehiculo = ?";
		
		BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
        parqueo = jdbcTemplate.queryForObject(sql, parqueoRowMapper, idVehiculo);
		
        return parqueo;
	}

	
}
