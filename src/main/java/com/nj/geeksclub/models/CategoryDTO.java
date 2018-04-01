package com.nj.geeksclub.models;

import java.util.List;

import com.nj.geeksclub.entities.Link;

public class CategoryDTO {
	
	private int id;
	private String name;
	private List<Link> links;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	

}
