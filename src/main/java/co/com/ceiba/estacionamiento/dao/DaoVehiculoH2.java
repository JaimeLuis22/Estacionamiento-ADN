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

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.excepcion.EstacionamientoException;

@Repository
public class DaoVehiculoH2 implements DaoVehiculo{
	
	// Propiedad que escribe en el log
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoVehiculoH2.class);
	
	// Manejo de parametros por nombre
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// Manejo de parametros por indice
    private JdbcTemplate jdbcTemplate;
    
    // Sql para consultar en base de datos
    private static final String SQL_COUNT_VEHICULO 			= "SELECT count(*) FROM VEHICULO";
    private static final String SQL_SELECT_VEHICULO 		= "SELECT * FROM VEHICULO";    
    private static final String SQL_INSERT_VEHICULO 		= "INSERT INTO VEHICULO (placa, cilidranje, id_tipo, id_bahia) values (:placa, :cilidranje, :idTipo, :idBahia)";
    private static final String SQL_UPDATE_VEHICULO 		= "UPDATE VEHICULO set placa = :placa, cilidranje = :cilidranje, id_tipo = :idTipo, id_bahia = :idBahia WHERE id_vehiculo = :idVehiculo";
    private static final String SQL_FIND_VEHICULO_BY_PLACA 	= "SELECT * FROM VEHICULO WHERE placa = :placa";
    
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
	 * Metodo que inserta un vehiculo en la base de datos
	 * @param vehiculo
	 */
	@Override
	public void insertVehiculo(Vehiculo vehiculo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehiculo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_VEHICULO, parameterSource);		
	}

	/**
	 * Metodo que actualiza un vehiculo en la base de datos
	 * @param vehiculo
	 */
	@Override
	public void updateVehiculo(Vehiculo vehiculo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehiculo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_VEHICULO, parameterSource);		
	}

	/**
     * Metodo que elimina un vehiculo en la base de datos
     * @param vehiculo
     */
	@Override
	public String deleteVehiculo(Vehiculo vehiculo) {
		return "eliminado";	
	}

	/**
     * Metodo que encuentra un vehiculo por la placa
     * @param vehiculo
     * @return
     */
	@Override
	public Vehiculo findVehiculoByPlaca(Vehiculo vehiculo) throws EstacionamientoException{
		Vehiculo vehiculoR = null;		
		try {
	        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(vehiculo);	        
	        RowMapper<Vehiculo> vehiculoRowMapper = BeanPropertyRowMapper.newInstance(Vehiculo.class);
	        vehiculoR = this.namedParameterJdbcTemplate.queryForObject(SQL_FIND_VEHICULO_BY_PLACA, namedParameters, vehiculoRowMapper);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("[DaoVehiculoH2][findVehiculoByPlaca] Excepcion: "+e.getMessage(), e);
		}        
        return vehiculoR;
	}

	/**
     * Metodo que retorna los vehiculos registrados en la base de datos
     * @return
     */
	@Override
	public List<Vehiculo> findAllVehiculos() {
		RowMapper<Vehiculo> vehiculoRowMapper = BeanPropertyRowMapper.newInstance(Vehiculo.class);
        return this.jdbcTemplate.query(SQL_SELECT_VEHICULO, vehiculoRowMapper);
	}

	/**
     * Metodo que cuenta los vehiculos registrados en la base de datos
     * @return
     */
	@Override
	public int countVehiculos() {
        return this.jdbcTemplate.queryForObject(SQL_COUNT_VEHICULO, Integer.class);
	}
}
