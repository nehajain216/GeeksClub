package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Test
	public void should__get_list_of_all_the_users()
	{
		BDDMockito.given(userRepository.findAll()).willReturn(Arrays.asList(
				new User(1, "neha", "neha@admin.com","abcd")
				,new User(2, "siva", "siva@admin.com","abcd")));
		
		List<User> allUsers = userService.getAllUsers();
		assertThat(allUsers.size()).isEqualTo(2);
		// userRepository.findAll() -- will check if this method is called once
		verify(userRepository).findAll();
	}

}
