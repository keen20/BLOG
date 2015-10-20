package com.blog.model;

import java.util.Date;


public class Blog {
	private String content;
	private Date created_time;
	private int id;
	private String title;
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	private int categoryId;
	public String getContent() {
		return content;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + 
				",created_time=" + created_time + "]";
	}
}
