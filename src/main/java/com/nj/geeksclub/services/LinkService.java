package com.nj.geeksclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.exceptions.CustomException;
import com.nj.geeksclub.repositories.CategoryRepository;
import com.nj.geeksclub.repositories.LinkRepository;

@Service
public class LinkService {

	private LinkRepository linkRepository;
	private CategoryRepository categoryRepository;

	public LinkService(LinkRepository linkRepository, CategoryRepository categoryRepository) {
		this.linkRepository = linkRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Link> getAllLinks()
	{
		return linkRepository.findAll();
	}
	
	public Link saveLink(Link link)
	{
		Optional<Link> findByurl = linkRepository.findByUrl(link.getUrl());
		if(findByurl.isPresent()) 
		{
			throw new CustomException("URL already exists");
		}
		Link saveLink = linkRepository.save(link);
		return saveLink;
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}

	public List<Link> getLinksByCategory(int id) {
		return linkRepository.findByCategoryId(id);
	}
}
