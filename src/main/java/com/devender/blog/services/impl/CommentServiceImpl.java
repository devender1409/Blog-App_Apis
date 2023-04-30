package com.devender.blog.services.impl;

import com.devender.blog.entities.Comment;
import com.devender.blog.entities.Post;
import com.devender.blog.exceptions.ResourceNotFoundException;
import com.devender.blog.payloads.CommentDto;
import com.devender.blog.payloads.PostDto;
import com.devender.blog.repositories.CommentRepo;
import com.devender.blog.repositories.PostRepo;
import com.devender.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," post id ",postId));

        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException(" Comment "," commentId ",commentId));
        this.commentRepo.delete(comment);

    }
}
