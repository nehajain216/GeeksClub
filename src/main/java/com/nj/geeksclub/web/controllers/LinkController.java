package com.nj.geeksclub.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.repositories.UserRepository;
import com.nj.geeksclub.services.CategoryService;
import com.nj.geeksclub.services.LinkService;


@Controller
public class LinkController {
	private LinkService linkService;
	private CategoryService categoryService;
	private UserRepository userRepository;

	@Autowired
	public LinkController(LinkService linkService,CategoryService categoryService,UserRepository userRepository) {
		this.linkService = linkService;
		this.categoryService = categoryService;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/links/new")
	public String newLinkForm(Model model)
	{
		model.addAttribute("link", new Link());
		return "newlink";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		List<Category> allCategory = categoryService.getAllCategory();
		return allCategory;
	}
	
	@PostMapping("/links")
	public String createLink(@Valid Link link, BindingResult result)
	{
		if(link.getCategory().getId() == 0)
		{
			result.rejectValue("category.id", "validator_blank_category");
		}
		if(result.hasErrors())
		{
			return "newlink";
		}
		
		Optional<com.nj.geeksclub.entities.User> userOptional = userRepository.findByEmail(getUserName());
		com.nj.geeksclub.entities.User user = userOptional.get();
		link.setCreatedBy(user);
		link.setCreatedOn(LocalDate.now());
		linkService.saveLink(link);
		return "redirect:/";
		
	}
	
	public String getUserName()
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}

}
