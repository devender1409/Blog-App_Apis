package com.devender.blog.controllers;

import com.devender.blog.payloads.ApiResponse;
import com.devender.blog.payloads.CommentDto;
import com.devender.blog.services.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createdComment = this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully",true), HttpStatus.OK);
    }

}
