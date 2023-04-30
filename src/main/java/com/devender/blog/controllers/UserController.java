package com.devender.blog.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devender.blog.payloads.ApiResponse;
import com.devender.blog.payloads.UserDto;
import com.devender.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//post
	@PostMapping(value = "/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//PUT-update user
	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
//	DELETE-delete user
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
//		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
//		ApiResponse response =  new ApiResponse("User Deleted SuccessFully",true);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted SuccessFully",true),HttpStatus.OK);

	}
	
	//GET-all users
	@GetMapping(value = "/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
		
	}
	
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid){
		UserDto userById= this.userService.getUserById(uid);
		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
	}
	
}
