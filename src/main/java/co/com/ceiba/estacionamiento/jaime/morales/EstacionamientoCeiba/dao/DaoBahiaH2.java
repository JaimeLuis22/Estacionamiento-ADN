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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Bahia;

@Repository
public class DaoBahiaH2 implements DaoBahia{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {        
        // Manejo de parametros por indice
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        
        // Manejo de parametros por nombre
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    private static final String SQL_SELECT_BAHIA = "SELECT * FROM BAHIA";
    
    private static final String SQL_INSERT_BAHIA = "INSERT INTO BAHIA (numero, estado, id_tipo) values (:numero, :estado, :idTipo)";
    
    private static final String SQL_UPDATE_BAHIA = "UPDATE BAHIA set numero = :numero, estado = :estado, id_tipo = :idTipo WHERE id_bahia = :idBahia";

	@Override
	public void insertBahia(Bahia bahia) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bahia);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_BAHIA, parameterSource);		
	}

	@Override
	public void updateBahia(Bahia bahia) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bahia);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_BAHIA, parameterSource);
		
	}

	@Override
	public void deleteBahia(Bahia bahia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bahia findBahiaByNumero(Bahia bahia) {
		String sql = "SELECT * FROM BAHIA WHERE numero = :numero";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(bahia);

        RowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, bahiaRowMapper);
	}

	@Override
	public List<Bahia> findAllBahias() {
		RowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);
        return this.jdbcTemplate.query(SQL_SELECT_BAHIA, bahiaRowMapper);
	}

	@Override
	public int countBahias() {
		String sql = "SELECT count(*) FROM BAHIA";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Bahia findBahiaById(long idBahia) {
		Bahia bahia = null;
		String sql = "SELECT * FROM BAHIA WHERE id_bahia = ?";
        
		BeanPropertyRowMapper<Bahia> bahiaRowMapper = BeanPropertyRowMapper.newInstance(Bahia.class);   
		bahia = jdbcTemplate.queryForObject(sql, bahiaRowMapper, idBahia);
        
        return bahia;
	}

	
}
