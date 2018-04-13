package com.nj.geeksclub.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nj.geeksclub.entities.Category;
import com.nj.geeksclub.entities.Link;
import com.nj.geeksclub.exceptions.CustomException;
import com.nj.geeksclub.repositories.CategoryRepository;
import com.nj.geeksclub.repositories.LinkRepository;

@RunWith(MockitoJUnitRunner.class)
public class LinkServiceTests {
	
	@Mock
	private LinkRepository linkRepository;
	@Mock
	private CategoryRepository categoryRepository;
	
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
	public void should_save_the_newlink()
	{
		Link link = new Link(1, "test1", "test1");
		BDDMockito.given(linkRepository.save(link)).willReturn(link);
		Link saveLink = linkService.saveLink(link);
		//assertEquals(1, saveLink.getId()); -- from JUNIT
		assertThat(saveLink.getId()).isEqualTo(1);
		verify(linkRepository).save(link);
	}
	
	@Test
	//(expected=CustomException.class)
	public void should_throw_exception_for_duplicate_link()
	{
		Link link = new Link(1, "test1", "test1");
		BDDMockito.given(linkRepository.findByUrl(link.getUrl())).willReturn(Optional.of(link));
		//BDDMockito.given(linkRepository.save(link)).willThrow(new CustomException("URL already exists"));
		//Link saveLink = linkService.saveLink(link);
		assertThatExceptionOfType(CustomException.class).isThrownBy(() -> {linkService.saveLink(link); })
        .withMessage("URL already exists");
	}
	
	@Test
	public void should_get_all_categories() {
		// setup
		List<Category> categories = new ArrayList<>(Arrays.asList(new Category(1, "Java"), new Category(2, "Kotlin")));
		BDDMockito.given(categoryRepository.findAll()).willReturn(categories);

		// act
		List<Category> allCategory = linkService.getAllCategory();

		// assert
		assertThat(allCategory.size()).isEqualTo(2);
		verify(categoryRepository).findAll();

	}
	
	@Test
	public void should_get_links_basedon_category_id()
	{
		//setup
		int id=1;
		List<Link> links = new ArrayList<>(Arrays.asList(
				new Link(1, "test1", "test1")
				, new Link(2, "test2", "test2")));
		BDDMockito.given(linkRepository.findByCategoryId(id)).willReturn(links);
		
		//act
		List<Link> linksByCategory = linkService.getLinksByCategory(id);
		
		//assert
		assertThat(linksByCategory.size()).isEqualTo(2);
		verify(linkRepository).findByCategoryId(id);
	}

}
