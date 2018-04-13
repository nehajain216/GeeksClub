package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nj.geeksclub.entities.Role;
import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTests {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;
	
	@Test
	public void Should_get_user_details_based_on_email()
	{
		//setup 
		String userEmail = "neha@admin.com";
		User user = new User(1, "Neha", "neha@admin.com","abcd");
		Set<Role> roles = new HashSet<>();
		Role role = new Role(1,"admin");
		roles.add(role);
		user.setRoles(roles);
		BDDMockito.given(userRepository.findByEmail(userEmail)).willReturn(Optional.of(user));
		
		//act
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
		
		//assert
		assertThat(userDetails.getUsername()).isEqualTo(userEmail);
		assertThat(userDetails.getAuthorities()).hasSize(1);
		assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("admin");
		
	}
	
	@Test
	public void Should_throw_exception_if_useremail_doesnot_exist()
	{
		//setup
		String email = "abcd@gmail.com";
		BDDMockito.given(userRepository.findByEmail(email)).willReturn(Optional.empty());
		
		//act will be part of assert here
		//UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		
		//assert
		assertThatExceptionOfType(UsernameNotFoundException.class)
		.isThrownBy(() -> {customUserDetailsService.loadUserByUsername(email);})
		.withMessage("Email abcd@gmail.com not found");
	}

}
