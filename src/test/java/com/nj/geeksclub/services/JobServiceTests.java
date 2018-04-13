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
import org.mockito.junit.MockitoJUnitRunner;

import com.nj.geeksclub.entities.Job;
import com.nj.geeksclub.repositories.JobRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTests {
	
	@Mock
	private JobRepository jobRepository;
	
	@InjectMocks
	private JobService jobService;
	
	@Test
	public void Should_get_all_the_jobs()
	{
		BDDMockito.given(jobRepository.findAll()).
		willReturn(Arrays.asList(
				new Job(1, "test1")
				,new Job(2, "test2")));
		
		List<Job> allJobs = jobService.getAllJobs();
		assertThat(allJobs.size()).isEqualTo(2);
		verify(jobRepository).findAll();
	}

}
