package com.kuebiko.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kuebiko.dto.SignupDTO;

//One bean (Java object) can autowired other bean(Java Object)
@Service
public class DatabaseServiceLogic {
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
	
	public List<SignupDTO>  findAll() {
		String sql="select * from asignup_tbl";
		//JdbcTemplate has all the logic for jdbc programming internally
		List<SignupDTO>  list=jdbcTemplate.
				query(sql,new BeanPropertyRowMapper(SignupDTO.class));
		return list;
	}
	
	public void save(String username ,String email, String gender) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
			String sql="insert into asignup_tbl (name,email,gender) values(?,?,?)";
			Object data[]= {username,email,gender };
			jdbcTemplate.update(sql,data);
	}
	
	public Optional<SignupDTO> findByUsername(String name) {
		String sql = "select *  from asignup_tbl where name = ?";
		List<SignupDTO> list = jdbcTemplate.query(sql, new Object[] { name },
				new BeanPropertyRowMapper(SignupDTO.class));
		SignupDTO dto = null;
		if (list.size() > 0) {
			dto = list.get(0);
		}
		// Optional - class which was introduce java8 -2014
		return Optional.ofNullable(dto);
	}
	
}
