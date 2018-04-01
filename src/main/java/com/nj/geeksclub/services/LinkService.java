package com.nj.geeksclub.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Link;
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
	
	public void saveLink(Link link)
	{
		linkRepository.save(link);
	}
}
