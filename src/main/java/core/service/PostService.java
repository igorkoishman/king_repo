package core.service;

import core.model.PostDTO;

import java.util.List;

public interface PostService {

	PostDTO addPost(PostDTO postDTO);

	PostDTO updatePost(PostDTO post);

	PostDTO findById(long postId);

	List<PostDTO> findByIds(List<Long> ids);

	List<PostDTO> getTopPosts();

}
