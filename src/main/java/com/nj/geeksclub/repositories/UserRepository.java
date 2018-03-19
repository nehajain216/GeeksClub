package com.nj.geeksclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

}
