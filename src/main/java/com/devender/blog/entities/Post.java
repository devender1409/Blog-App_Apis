package com.devender.blog.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="post_title",length=50,nullable = false)
	private String title;
	
	@Column(name="content",length=1000,nullable = false)
	private String content;
	
	@Column(name="image_name",length=100,nullable=false)
	private String imageName;
	
	private Date addedDate;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date date) {
		this.addedDate = date ;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
