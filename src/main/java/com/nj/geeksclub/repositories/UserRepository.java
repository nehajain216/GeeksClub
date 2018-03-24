package com.nj.geeksclub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>  
{
	Optional<User> findByEmail(String email);
}
