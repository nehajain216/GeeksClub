package com.nj.geeksclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Announcement;
import com.nj.geeksclub.repositories.AnnouncementRepository;

@Service
public class AnnouncementService {
	
	private AnnouncementRepository announcementRepository;

	@Autowired
	public AnnouncementService(AnnouncementRepository announcementRepository) {
		this.announcementRepository = announcementRepository;
	}
	
	public List<Announcement> getAllAnnouncements()
	{
		return announcementRepository.findAll();
	}

}
