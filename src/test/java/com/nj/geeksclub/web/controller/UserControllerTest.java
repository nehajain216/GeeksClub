package com.nj.geeksclub.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;

import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.services.UserService;
import com.nj.geeksclub.web.controllers.UserController;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=UserController.class)
//@ContextConfiguration(classes= {GeeksclubApplication.class,WebSecurityConfig.class})
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testShowAllUsers() throws Exception
	{
		//setup
		List<User> users = new ArrayList<>(Arrays.asList(new User(1, "neha", "neha@admin.com", "admin")
				,new User(2, "siva", "siva@admin.com", "abcd")));
		BDDMockito.given(userService.getAllUsers()).willReturn(users);
		
		//assert
		this.mvc.perform(get("/admin/users")
				.with(user("neha@admin.com").password("admin").roles("USER","ADMIN"))
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(view().name("users"))
				.andExpect(model().attribute("users", hasSize(2)));
		
		verify(userService).getAllUsers();
			
	}
}
