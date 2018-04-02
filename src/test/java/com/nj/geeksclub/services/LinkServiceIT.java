package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.repositories.LinkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceIT {
	
	@MockBean
	LinkRepository linkRepository;
	
	@Autowired
	LinkService linkService;
	
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


}
