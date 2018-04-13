package com.nj.geeksclub.web.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.exceptions.CustomException;
import com.nj.geeksclub.repositories.UserRepository;
import com.nj.geeksclub.services.LinkService;

@Controller
public class LinkController {
	private LinkService linkService;
	private UserRepository userRepository;
	
	private static Logger logger= LoggerFactory.getLogger(LinkController.class);

	@Autowired
	public LinkController(LinkService linkService, UserRepository userRepository) {
		this.linkService = linkService;
		this.userRepository = userRepository;
	}

	@GetMapping("/links/new")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "newlink";
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		List<Category> allCategory = linkService.getAllCategory();
		return allCategory;
	}

	@PostMapping("/links")
	public String createLink(@ModelAttribute("link")@Valid Link link, BindingResult result) {
		if (link.getCategory().getId() == 0) {
			result.rejectValue("category.id", "validator_blank_category");
		}
		if (result.hasErrors()) {
			logger.info("Binding error found");
			return "newlink";
		}

		Optional<com.nj.geeksclub.entities.User> userOptional = userRepository.findByEmail(getUserName());
		com.nj.geeksclub.entities.User user = userOptional.get();
		link.setCreatedBy(user);
		try {
			linkService.saveLink(link);
		} catch (CustomException cx) {
			result.rejectValue("url", "duplicate_url",cx.getMessage());
			logger.info("Duplicate URL found");
			return "newlink";
		}

		logger.info("successful");
		return "redirect:/";

	}

	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			User user = (User) authentication.getPrincipal();
			return user.getUsername();
		}
		return null;
	}

}
