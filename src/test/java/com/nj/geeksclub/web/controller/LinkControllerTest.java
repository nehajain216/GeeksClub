package com.nj.geeksclub.web.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.exceptions.CustomException;
import com.nj.geeksclub.repositories.UserRepository;
import com.nj.geeksclub.services.LinkService;
import com.nj.geeksclub.web.controllers.LinkController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=LinkController.class)
public class LinkControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private LinkService linkService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testNewLinkForm() throws Exception
	{
		List<Category> categories = new ArrayList<>(Arrays.asList(
				new Category(1, "Java")
				,new Category(2, "Kotlin")));
		BDDMockito.given(linkService.getAllCategory()).willReturn(categories);
		
		this.mvc.perform(get("/links/new")
				.with(user("neha@admin.com").password("admin").roles("USER","ADMIN"))
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(view().name("newlink"))
				.andExpect(model().attributeExists("link"))
				.andExpect(model().attribute("categories" ,Matchers.hasSize(Matchers.greaterThan(0))));
	}
	
	@Test
	public void TestcreateLinks() throws Exception
	{
		Link link = new Link(1, "test1", "test1");
		User user = new User(1, "neha", "neha@admin.com", "admin");
		BDDMockito.given(userRepository.findByEmail("neha@admin.com")).willReturn(Optional.of(user));
		BDDMockito.given(linkService.saveLink(Mockito.any(Link.class))).willReturn(link);
		
		this.mvc.perform(post("/links")
				.param("id", "1")
				.param("title", "test1")
				.param("url", "test1")
				.param("category.id", "1")
				.param("category.name", "Java")
				.with(user("neha@admin.com").password("admin").roles("USER","ADMIN"))
				.with(csrf())
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		
		verify(userRepository).findByEmail("neha@admin.com");
		verify(linkService).saveLink(Mockito.any(Link.class));
	}
	
	@Test
	public void TestCreateLink_with_validation_error() throws Exception
	{
		this.mvc.perform(post("/links")
				.param("id", "1")
				.param("title", "test1")
				.param("url", "test1")
				.param("category.id", "0")
				.param("category.name", "Java")
				.with(user("neha@admin.com").password("admin").roles("USER","ADMIN"))
				.with(csrf())
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(view().name("newlink"));
	}
	
	@Test
	public void TestCreateLink_throws_customException() throws Exception
	{
		User user = new User(1, "neha", "neha@admin.com", "admin");
		BDDMockito.given(userRepository.findByEmail("neha@admin.com")).willReturn(Optional.of(user));
		BDDMockito.given(linkService.saveLink(Mockito.any(Link.class))).willThrow(new CustomException("URL is duplicate"));
		
		this.mvc.perform(post("/links")
				.param("id", "1")
				.param("title", "test1")
				.param("url", "test1")
				.param("category.id", "1")
				.param("category.name", "Java")
				.with(user("neha@admin.com").password("admin").roles("USER","ADMIN"))
				.with(csrf())
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(view().name("newlink"));
		
	}

}
