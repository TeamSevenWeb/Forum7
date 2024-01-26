package com.example.forum.services;

import com.example.forum.filters.PostsFilterOptions;
import com.example.forum.models.Post;
import com.example.forum.models.User;

import java.util.List;

public interface PostService {

    List<Post> getAll(PostsFilterOptions postsFilterOptions);

    Post get(int id);

    void create(Post post, User user);

    void update(Post post, User user);

    void delete (Post post,User user);
}
