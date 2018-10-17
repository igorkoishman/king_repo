package core.service;

import core.clients.PostRepository;
import core.model.PostDBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public void save(PostDTO post) {
		postRepository.save(new PostDBO(post.getPost()));

	}

	@Override
	@Cacheable(value = "posts", key = "#postDTO.postId")
	public PostDTO findById(PostDTO postDTO) {
		Optional<PostDBO> byId = postRepository.findById(postDTO.getPostId());
		return new PostDTO(byId.get().getPostId(), byId.get().getPost());
	}
}
