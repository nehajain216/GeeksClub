package com.nj.geeksclub.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "links")
public class Link {

	@Id
	@SequenceGenerator(name = "link_generator", sequenceName = "link_sequence", initialValue = 10)
	@GeneratedValue(generator = "link_generator")
	private int id;

	@NotBlank(message = "{validator_blank_title}")
	private String title;

	@NotBlank(message = "{validator_blank_url}")
	private String url;

	@NotNull(message = "{validator_blank_category}")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private LocalDate createdOn;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	public Link() {

	}

	public Link(int id, @NotBlank(message = "{validator_blank_title}") String title,
			@NotBlank(message = "{validator_blank_url}") String url) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
