package com.nj.geeksclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.exceptions.CustomException;
import com.nj.geeksclub.repositories.LinkRepository;

@Service
public class LinkService {

	private LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
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
}
