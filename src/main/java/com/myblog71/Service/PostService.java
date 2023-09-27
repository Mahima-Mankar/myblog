package com.myblog71.Service;

import com.myblog71.Payload.PostDto;
import com.myblog71.Payload.PostResponse;

public interface PostService {
    PostDto savePost(PostDto postDto);


    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);

    PostDto getPostById(long id);

    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}