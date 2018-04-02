package com.nj.geeksclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Integer> {

	public List<Link> findByCategoryId(int id);
	
	public Optional<Link> findByUrl(String url);
}
