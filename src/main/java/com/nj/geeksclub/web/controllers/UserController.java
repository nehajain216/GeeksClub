package com.nj.geeksclub.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nj.geeksclub.entities.User;
import com.nj.geeksclub.services.UserService;

@Controller
public class UserController 
{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/admin/users")
	public String showAllUsers(Model model)
	{
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("users", allUsers);
		return "users";
	}
	
}
