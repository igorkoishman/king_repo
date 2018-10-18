package core.service;

import org.springframework.cache.annotation.CachePut;

public interface PostService {

	PostDTO addPost(PostDTO postDTO);

	PostDTO updatePost(PostDTO post);

	PostDTO findById(PostDTO postDTO);

}
