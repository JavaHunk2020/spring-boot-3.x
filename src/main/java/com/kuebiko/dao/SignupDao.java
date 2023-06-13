/*package com.kuebiko.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kuebiko.dao.entity.SignupEntity;

//Creating bean of the class which encapsulate database logic
@Repository
public class SignupDao {

	//@Autowired
	 //DataSource datasource;
	//JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void  deleteBySid(int sid) {
		String sql="delete from asignup_tbl where sid=?";
		//JdbcTemplate has all the logic for jdbc programming internally
		jdbcTemplate.update(sql,new Object[] {sid});
	}
	
	public List<SignupEntity>  findAll() {
		String sql="select * from asignup_tbl";
		//JdbcTemplate has all the logic for jdbc programming internally
		List<SignupEntity>  list=jdbcTemplate.
				query(sql,new BeanPropertyRowMapper(SignupEntity.class));
		return list;
	}
	
	public void save(String username ,String email, String gender) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
			String sql="insert into asignup_tbl (name,email,gender) values(?,?,?)";
			Object data[]= {username,email,gender };
			jdbcTemplate.update(sql,data);
	}
	
	public Optional<SignupEntity> findByUsername(String name) {
		String sql = "select *  from asignup_tbl where name = ?";
		List<SignupEntity> list = jdbcTemplate.query(sql, new Object[] { name },
				new BeanPropertyRowMapper(SignupEntity.class));
		SignupEntity entity = null;
		if (list.size() > 0) {
			entity = list.get(0);
		}
		// Optional - class which was introduce java8 -2014
		return Optional.ofNullable(entity);
	}

}*/
