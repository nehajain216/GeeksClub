package com.nj.geeksclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
