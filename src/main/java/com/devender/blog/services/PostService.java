package com.devender.blog.services;

import java.util.List;

import com.devender.blog.payloads.PostDto;
import com.devender.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String order);
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//serch post
	List<PostDto> searchPosts(String keyword);
}
