package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nj.geeksclub.entities.Announcement;
import com.nj.geeksclub.repositories.AnnouncementRepository;

@RunWith(MockitoJUnitRunner.class)
public class AnnouncementServiceTests {
	
	@Mock
	private AnnouncementRepository announcementRepository;
	
	@InjectMocks
	private AnnouncementService announcementService;
	
	@Test
	public void Should_get_all_the_announcement()
	{
		// setup
		List<Announcement> announcements = new ArrayList<>(Arrays.asList(
				new Announcement(1, "test1", "test1")
				,new Announcement(2, "test2", "test2")));
		BDDMockito.given(announcementRepository.findAll()).willReturn(announcements);
		
		//act
		List<Announcement> allAnnouncements = announcementService.getAllAnnouncements();
		
		//assert
		assertThat(allAnnouncements.size()).isEqualTo(2);
		verify(announcementRepository).findAll();
	}

}
