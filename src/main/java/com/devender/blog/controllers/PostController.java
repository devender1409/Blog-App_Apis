package com.devender.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import com.devender.blog.services.FileService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devender.blog.config.AppConstants;
import com.devender.blog.entities.Post;
import com.devender.blog.payloads.ApiResponse;
import com.devender.blog.payloads.PostDto;
import com.devender.blog.payloads.PostResponse;
import com.devender.blog.services.PostService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;

	@Value("${project.image}")
	private String path;

	@Autowired
	private FileService fileService;
	
	@PostMapping(value="/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId
			)
	{
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	
	//getByUser
	@GetMapping(value = "/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return ResponseEntity.ok(posts);
	}
	
	//get by category
	@GetMapping(value = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(posts);
	}
	
	//getAllPosts
	@GetMapping(value = "/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY_POSTID ,required = false) String sortBy,
			@RequestParam(value="order",defaultValue = AppConstants.ORDER,required = false) String order){	
		return ResponseEntity.ok(this.postService.getAllPosts(pageNumber,pageSize,sortBy,order));
	}
	
	//getPostById
	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<PostDto>(this.postService.getPostById(postId),HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping(value = "/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
	}
	
	//update post
	@PutMapping(value="/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	//search
	@GetMapping(value="/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords){
		List<PostDto> posts = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}

	//post image upload

	@PostMapping(value = "/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
														 @PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postService.getPostById(postId);//if not foud thorw resource not found exception

		String fileName = this.fileService.uploadImage(path,image);

		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto,postId);
		return new ResponseEntity<>(updatedPost,HttpStatus.OK);
	}



	//method to serve file
	@GetMapping(value="/images/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());
	}
}
