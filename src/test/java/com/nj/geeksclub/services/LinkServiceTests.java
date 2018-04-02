package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.creation.bytebuddy.ByteBuddyMockMaker;
import org.mockito.junit.MockitoJUnitRunner;

import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.repositories.LinkRepository;

@RunWith(MockitoJUnitRunner.class)
public class LinkServiceTests {
	
	@Mock
	private LinkRepository linkRepository;
	
	@InjectMocks
	private LinkService linkService;
	
	@Test
	public void should_get_all_the_links()
	{
		BDDMockito.given(linkRepository.findAll())
		.willReturn(Arrays.asList(
				new Link(1, "test1", "test1")
				,new Link(2, "test2", "test2")));
		
		List<Link> allLinks = linkService.getAllLinks();
		assertThat(allLinks.size()).isEqualTo(2);
		verify(linkRepository).findAll();
	}
	
	@Test
	public void should_save_the_user()
	{
		BDDMockito.doNothing().when(linkRepository).save(new Link(1, "test1", "test1"));
		verify(linkRepository).save(new Link(1, "test1", "test1"));
	}

}
