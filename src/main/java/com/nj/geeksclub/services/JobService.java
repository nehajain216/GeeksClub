package com.nj.geeksclub.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nj.geeksclub.entities.Job;
import com.nj.geeksclub.repositories.JobRepository;

@Service
public class JobService {
	
	private JobRepository jobRepository;

	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	public List<Job> getAllJobs()
	{
		return jobRepository.findAll();
	}

}
