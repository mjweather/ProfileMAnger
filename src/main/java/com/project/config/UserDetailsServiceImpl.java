package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.project.Dao.Reporsitory;
import com.project.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private Reporsitory userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetching user from database
		User user = userRepository.getUserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("User name is null");
		}
		UserDetailsImpl details=new UserDetailsImpl(user);
		
		return details;
	}

}
