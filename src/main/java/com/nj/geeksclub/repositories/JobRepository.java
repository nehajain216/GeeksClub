package com.nj.geeksclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
