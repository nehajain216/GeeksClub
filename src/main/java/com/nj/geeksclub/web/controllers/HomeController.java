package com.nj.geeksclub.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.models.CategoryDTO;
import com.nj.geeksclub.services.AnnouncementService;
import com.nj.geeksclub.services.CategoryService;
import com.nj.geeksclub.services.JobService;

@Controller
public class HomeController {
	
	private AnnouncementService announcementService;
	private JobService jobService;
	private CategoryService categoryService;

	@Autowired
	public HomeController(AnnouncementService announcementService,JobService jobService,CategoryService categoryService) {
		this.announcementService = announcementService;
		this.jobService = jobService;
		this.categoryService = categoryService;
	}
	
	@GetMapping("/")
	public String getAnnouncements(Model model)
	{
		model.addAttribute("announcements",announcementService.getAllAnnouncements());
		model.addAttribute("jobs", jobService.getAllJobs());
		List<Category> allCategory = categoryService.getAllCategory();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (Category category : allCategory) {
			List<Link> links = categoryService.getLinksByCategory(category.getId());
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setLinks(links);
			categoryDTOs.add(categoryDTO);
		}
		
		model.addAttribute("categories",categoryDTOs);
		return "index";
	}
}
