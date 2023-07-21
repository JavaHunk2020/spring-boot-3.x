package com.kuebiko.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SignupService signupService;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * Who will call this methid ???? spring security
	 */
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<SignupDTO> optional = signupService.findByName(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		SignupDTO signup = optional.get();
		// return UserDetailsImpl.build(signup);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(signup.getRole()));
		return new org.springframework.security.core.userdetails.User(username, encoder.encode(signup.getPassword()), authorities);
	}

}
