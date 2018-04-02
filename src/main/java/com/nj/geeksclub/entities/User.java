package com.nj.geeksclub.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String companyName;
	private String blog;
	private String website;
	private String github;
	private String twitter;
	private String linkedIn;
	private String stackoverflow;
	private String authoredBooks;
	private String conferenceTalks;
	private String interests;
	@Column(nullable=true)
	private Boolean lookingForJobs;
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="user_skill",
	joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="skill_id"))
	private Set<Skill> skills;
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="user_role",
	joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	public User()
	{
		
	}

	public User(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getStackoverflow() {
		return stackoverflow;
	}

	public void setStackoverflow(String stackoverflow) {
		this.stackoverflow = stackoverflow;
	}

	public String getAuthoredBooks() {
		return authoredBooks;
	}

	public void setAuthoredBooks(String authoredBooks) {
		this.authoredBooks = authoredBooks;
	}

	public String getConferenceTalks() {
		return conferenceTalks;
	}

	public void setConferenceTalks(String conferenceTalks) {
		this.conferenceTalks = conferenceTalks;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Boolean getLookingForJobs() {
		return lookingForJobs;
	}

	public void setLookingForJobs(Boolean lookingForJobs) {
		this.lookingForJobs = lookingForJobs;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
