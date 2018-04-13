package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.repositories.UserRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockitoTest 
{
	//@MockBean
	@Mock
	private UserRepository userRepository;
	
	//@Autowired
	@InjectMocks
	private UserService userService;
	
	@Test
	public void should_get_list_of_all_the_users()
	{
		BDDMockito.given(userRepository.findAll())
		.willReturn(Arrays.asList(
				new User(1, "neha", "neha@admin.com","abcd")
				,new User(2, "siva", "siva@admin.com","abcd")));
		
		List<User> allUsers = userService.getAllUsers();
		assertThat(allUsers.size()).isEqualTo(2);
		verify(userRepository, times(1)).findAll();
	}
}
