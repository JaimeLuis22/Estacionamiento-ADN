package co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dao;

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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Parqueo;

@Repository
public class DaoParqueoH2 implements DaoParqueo{

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    
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
    
	@Override
	public void insertParqueo(Parqueo parqueo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parqueo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_PARQUEO, parameterSource);		
	}

	@Override
	public void updateParqueo(Parqueo parqueo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parqueo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_PARQUEO, parameterSource);
	}

	@Override
	public void deleteParqueo(Parqueo parqueo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parqueo findParqueoById(long idParqueo) {
		Parqueo parqueo = null;
		String sql = "SELECT * FROM PARQUEO WHERE id_parqueo = ?";
        
		BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
        parqueo = jdbcTemplate.queryForObject(sql, parqueoRowMapper, idParqueo);
        
        return parqueo;
	}

	@Override
	public List<Parqueo> findAllParqueos() {
		RowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);
        return this.jdbcTemplate.query(SQL_SELECT_PARQUEO, parqueoRowMapper);
	}

	@Override
	public int countParqueos() {
		String sql = "SELECT count(*) FROM PARQUEO";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Parqueo findParqueoByIdVehiculo(int idVehiculo) {
		Parqueo parqueo = null;
		String sql = "SELECT * FROM PARQUEO WHERE id_vehiculo = ?";
		
		BeanPropertyRowMapper<Parqueo> parqueoRowMapper = BeanPropertyRowMapper.newInstance(Parqueo.class);   
        parqueo = jdbcTemplate.queryForObject(sql, parqueoRowMapper, idVehiculo);
		
        return parqueo;
	}

	
}
