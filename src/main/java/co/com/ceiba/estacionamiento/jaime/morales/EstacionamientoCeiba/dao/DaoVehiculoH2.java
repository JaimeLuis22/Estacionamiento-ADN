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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Vehiculo;

@Repository
public class DaoVehiculoH2 implements DaoVehiculo{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {        
        // Manejo de parametros por indice
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        
        // Manejo de parametros por nombre
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    private static final String SQL_SELECT_VEHICULO = "SELECT * FROM VEHICULO";
    
    private static final String SQL_INSERT_VEHICULO = "INSERT INTO VEHICULO (placa, cilidranje, id_tipo, id_bahia) values (:placa, :cilidranje, :idTipo, :idBahia)";
    
    private static final String SQL_UPDATE_VEHICULO = "UPDATE VEHICULO set placa = :placa, cilidranje = :cilidranje, id_tipo = :idTipo, id_bahia = :idBahia WHERE id_vehiculo = :idVehiculo";

	@Override
	public void insertVehiculo(Vehiculo vehiculo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehiculo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_VEHICULO, parameterSource);		
	}

	@Override
	public void updateVehiculo(Vehiculo vehiculo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehiculo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_VEHICULO, parameterSource);		
	}

	@Override
	public void deleteVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehiculo findVehiculoByPlaca(Vehiculo vehiculo) {
		String sql = "SELECT * FROM VEHICULO WHERE placa = :placa";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(vehiculo);

        RowMapper<Vehiculo> vehiculoRowMapper = BeanPropertyRowMapper.newInstance(Vehiculo.class);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, vehiculoRowMapper);
	}

	@Override
	public List<Vehiculo> findAllVehiculos() {
		RowMapper<Vehiculo> vehiculoRowMapper = BeanPropertyRowMapper.newInstance(Vehiculo.class);
        return this.jdbcTemplate.query(SQL_SELECT_VEHICULO, vehiculoRowMapper);
	}

	@Override
	public int countVehiculos() {
		String sql = "SELECT count(*) FROM VEHICULO";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
