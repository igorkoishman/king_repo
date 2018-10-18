package core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.clients.PostRepository;
import core.model.Comment;
import core.model.PostDBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private ObjectMapper objectMapper;

	@Override
	@CachePut(value = "posts", key = "#postDTO.postId")
	public PostDTO addPost(PostDTO post) {
		try {
			postRepository.save(new PostDBO(objectMapper.writeValueAsString(post.getComments())));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return post;
	}

	@Override
	@CachePut(value = "posts", key = "#postDTO.postId")
	public PostDTO updatePost(PostDTO post) {
		PostDTO postFromRepo = findById(post);
		List<Comment> comments = postFromRepo.getComments();
		Comment newComment = post.getComments().get(0);
		long nextCommentID = comments.size() + 1L;
		newComment.setCommentId(nextCommentID);
		comments.add(newComment);
		try {
			PostDBO postDBO = new PostDBO(post.getPostId(), objectMapper.writeValueAsString(postFromRepo.getComments()));
			postRepository.save(postDBO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return postFromRepo;
	}

	@Override
	@Cacheable(value = "posts", key = "#postDTO.postId")
	public PostDTO findById(PostDTO postDTO) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Optional<PostDBO> byId = postRepository.findById(postDTO.getPostId());
		if (byId.isPresent()) {
			try {
				List<Comment> comments = objectMapper.readValue(byId.get().getPost(), new TypeReference<List<Comment>>() {

				});
				return new PostDTO(byId.get().getPostId(), comments);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public PostRepository getPostRepository() {
		return postRepository;
	}

	@Autowired
	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
