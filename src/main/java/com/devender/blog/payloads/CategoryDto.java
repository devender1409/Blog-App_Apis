package com.devender.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer id;
	
	@NotEmpty(message = "Category Title can't be Blank")
	private String categoryTitle;
	
	@NotEmpty(message = "Category Title Can't be left blank")
	@Size(min=5,max=255,message = "Category Description must be in between 5 - 255 characters")
	private String categoryDescription;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		builder.append("CategoryDto [id=");
		builder.append(id);
		builder.append(", categoryTitle=");
		builder.append(categoryTitle);
		builder.append(", categoryDescription=");
		builder.append(categoryDescription);
		builder.append("]");
		return builder.toString();
	}
	
	
}
