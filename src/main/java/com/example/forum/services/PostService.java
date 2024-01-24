package com.example.forum.services;

import com.example.forum.models.Post;
import com.example.forum.models.User;

import java.util.List;

public interface PostService {

    List<Post> getAll();

    Post get(int id);

    Post create(Post post, User user);

    Post update(Post post, User user);

    void delete (Post post,User user);
}
