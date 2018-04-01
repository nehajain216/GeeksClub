package com.nj.geeksclub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Role;
import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.repositories.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByEmail(userEmail);
		if(!userOptional.isPresent())
		{
			throw new UsernameNotFoundException("Email "+userEmail+"not found");
		}
		User user = userOptional.get();
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User
				(user.getEmail(), user.getPassword(), getAuthorities(user));
		
		return userDetails;
	}


	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<Role> roles = user.getRoles();
		List<String> userRoles = new ArrayList<>();
		for (Role role : roles) {
			userRoles.add(role.getName());
		}
		return AuthorityUtils.createAuthorityList(userRoles.toArray(new String[roles.size()]));
	}

}
