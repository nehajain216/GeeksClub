package com.nj.geeksclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj.geeksclub.entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer>  {

}
