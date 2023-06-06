package com.kuebiko.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kuebiko.dto.SignupDTO;

//One bean (Java object) can autowired other bean(Java Object)
@Service
public class DatabaseServiceLogic {
	
	@Autowired
	private DataSource  dataSource;
	
	public List<SignupDTO>  findAll(){
		String sql="select * from asignup_tbl";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		List<SignupDTO>  list=jdbcTemplate.
				query(sql,new BeanPropertyRowMapper(SignupDTO.class));
		return list;
	}
	
	
	public void save(String username ,String email, String gender) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
			JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
			String sql="insert into asignup_tbl (name,email,gender) values(?,?,?)";
			Object data[]= {username,email,gender };
			jdbcTemplate.update(sql,data);
	}

}
