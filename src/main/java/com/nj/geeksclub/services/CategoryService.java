package com.nj.geeksclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.repositories.CategoryRepository;
import com.nj.geeksclub.repositories.LinkRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	private LinkRepository linkRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository, LinkRepository linkRepository) {
		this.categoryRepository = categoryRepository;
		this.linkRepository = linkRepository;
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}

	public List<Link> getLinksByCategory(int id) {
		return linkRepository.findByCategoryId(id);
	}

}
