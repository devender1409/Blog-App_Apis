package com.devender.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@Column(name="title",length=100,nullable=false)
	private String categoryTitle;
	
	@Column(name="description", length=255)
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [categoryId=");
		builder.append(categoryId);
		builder.append(", categoryTitle=");
		builder.append(categoryTitle);
		builder.append(", categoryDescription=");
		builder.append(categoryDescription);
		builder.append("]");
		return builder.toString();
	}
	
	

}
