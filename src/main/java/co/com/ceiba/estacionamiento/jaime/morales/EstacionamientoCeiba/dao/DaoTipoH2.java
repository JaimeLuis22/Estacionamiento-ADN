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

import co.com.ceiba.estacionamiento.jaime.morales.EstacionamientoCeiba.dominio.Tipo;

@Repository
public class DaoTipoH2 implements DaoTipo{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    
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

	@Override
	public void insertTipo(Tipo tipo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(tipo);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_TIPO, parameterSource);		
	}

	@Override
	public void updateTipo(Tipo tipo) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(tipo);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_TIPO, parameterSource);
		
	}

	@Override
	public void deleteTipo(Tipo tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo findTipoByNombre(Tipo tipo) {
		String sql = "SELECT * FROM TIPO WHERE nombre = :nombre";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(tipo);

        RowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, tipoRowMapper);
	}

	@Override
	public List<Tipo> findAllTipos() {
        RowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);
        return this.jdbcTemplate.query(SQL_SELECT_TIPO, tipoRowMapper);
	}

	@Override
	public int countTipos() {
		String sql = "SELECT count(*) FROM TIPO";

        return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Tipo findTipoById(long idTipo) {
		Tipo tipo = null;
		String sql = "SELECT * FROM TIPO WHERE id_tipo = ?";
        
		BeanPropertyRowMapper<Tipo> tipoRowMapper = BeanPropertyRowMapper.newInstance(Tipo.class);   
		tipo = jdbcTemplate.queryForObject(sql, tipoRowMapper, idTipo);
        
        return tipo;
	}

}
