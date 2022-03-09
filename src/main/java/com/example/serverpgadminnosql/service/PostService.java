package com.example.serverpgadminnosql.service;

import com.example.serverpgadminnosql.model.Post;
import com.example.serverpgadminnosql.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Integer id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post editPost(Integer postId, Post post) {
        Post updatedPost = getPostById(postId);
        if (updatedPost != null) {
            updatedPost.setTitle(post.getTitle());
            updatedPost.setBody(post.getBody());
            postRepository.save(updatedPost);
            return updatedPost;
        }
        return null;
    }

    public boolean deletePost(Integer postId) {
        Post post = getPostById(postId);
        if (post == null) {
            return false;
        }
        try {
            postRepository.delete(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
