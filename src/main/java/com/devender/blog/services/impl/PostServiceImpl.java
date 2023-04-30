package com.devender.blog.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devender.blog.entities.Category;
import com.devender.blog.entities.Post;
import com.devender.blog.entities.User;
import com.devender.blog.exceptions.ResourceNotFoundException;
import com.devender.blog.payloads.PostDto;
import com.devender.blog.payloads.PostResponse;
import com.devender.blog.repositories.CategoryRepo;
import com.devender.blog.repositories.PostRepo;
import com.devender.blog.repositories.UserRepo;
import com.devender.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," user id ",userId));
		
		Category categry = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," category id ",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		Date date = new Date(0);
		post.setAddedDate(date);
		
		post.setUser(user);
		post.setCategory(categry);
		return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", " postId ", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", " postId ", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String order) {
		Sort sort = null;
		if(order.equalsIgnoreCase("asc")) sort = Sort.by(sortBy).ascending();
		else sort=Sort.by(sortBy).descending();
	
	    Pageable p = PageRequest.of(pageNumber, pageSize,sort);
	    Page<Post> pagePost = this.postRepo.findAll(p);
	    List<Post> allPosts = pagePost.getContent();
//		List<Post> allPost = this.postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse(postDtos, pagePost.getNumber(), pagePost.getSize(), (int)pagePost.getTotalElements(), pagePost.getTotalPages(),pagePost.isLast());
			
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", " postId ", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return posDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return posDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		return posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}
	
	
	
	
}
