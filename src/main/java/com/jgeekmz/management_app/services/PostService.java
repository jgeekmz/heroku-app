package com.jgeekmz.management_app.services;

import com.jgeekmz.management_app.models.Post;
import com.jgeekmz.management_app.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    // Get All Clients
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // Get Client By Id
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    // Delete Client
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    // Update Client
    public void save(Post post) {
        postRepository.save(post);
    }
}