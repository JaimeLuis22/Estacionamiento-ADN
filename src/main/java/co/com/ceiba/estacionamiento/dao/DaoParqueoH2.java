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

import co.com.ceiba.estacionamiento.dominio.Parqueo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Repository
public class DaoParqueoH2 implements DaoParqueo{
	
	// Propiedad que escribe en el log
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoParqueoH2.class);
	
	// Manejo de parametros por nombre
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// Manejo de parametros por indice
    private JdbcTemplate jdbcTemplate;
    
    // Sql para consultar en base de datos
    private static final String SQL_COUNT_PARQUEO 				= "SELECT count(*) FROM PARQUEO";
    private static final String SQL_SELECT_PARQUEO 				= "SELECT * FROM PARQUEO";    
    private static final String SQL_INSERT_PARQUEO 				= "INSERT INTO PARQUEO (fecha_inicial, fecha_fin, costo, estado, id_vehiculo) values (:fechaInicial, :fechaFin, :costo, :estado, :idVehiculo)";
    private static final String SQL_UPDATE_PARQUEO 				= "UPDATE PARQUEO set fecha_inicial = :fechaInicial, fecha_fin = :fechaFin, costo = :costo, estado = :estado WHERE id_parqueo = :idParqueo";
    private static final String SQL_FIND_PARQUEO_BY_ID 			= "SELECT * FROM PARQUEO WHERE id_parqueo = ?";
    private static final String SQL_FIND_PARQUEO_BY_ID_VEHICULO = "SELECT * FROM PARQUEO WHERE id_vehiculo = ?";
    
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
	public String deleteParqueo(Parqueo parqueo) {
		return "eliminado";
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
			BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
			parqueo = jdbcTemplate.queryForObject(SQL_FIND_PARQUEO_BY_ID, parqueoRowMapper, idParqueo);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("[DaoParqueoH2][findParqueoById] Excepcion: "+e.getMessage(), e);
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
        return this.jdbcTemplate.queryForObject(SQL_COUNT_PARQUEO, Integer.class);
	}

	/**
     * Metodo que encuentra un parqueo por el idVehiculo
     * @param idVehiculo
     * @return
     */
	@Override
	public Parqueo findParqueoByIdVehiculo(int idVehiculo) {
		Parqueo parqueo = null;
		try {
			BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
	        parqueo = jdbcTemplate.queryForObject(SQL_FIND_PARQUEO_BY_ID_VEHICULO, parqueoRowMapper, idVehiculo);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("[DaoParqueoH2][findParqueoByIdVehiculo] Excepcion: "+e.getMessage(), e);
		}		
        return parqueo;
	}
}
