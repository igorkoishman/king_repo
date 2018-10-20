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
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private ObjectMapper objectMapper;

	private Context context;

	private static final TypeReference<List<Comment>> typeRef = new TypeReference<List<Comment>>() {

	};

	@Override
	public PostDTO addPost(PostDTO postDTO) {
		PostDBO postDBO = null;
		try {
			postDBO = postRepository.save(new PostDBO(objectMapper.writeValueAsString(postDTO.getComments())));
			return new PostDTO(postDBO.getPostId(), objectMapper.readValue(postDBO.getPost(), typeRef));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@CachePut(value = "posts", key = "#postDTO.postId")
	public PostDTO updatePost(PostDTO postDTO) {
		PostDTO postFromRepo = findById(postDTO.getPostId());
		List<Comment> comments = postFromRepo.getComments();
		Comment newComment = postDTO.getComments().get(0);
		long nextCommentID = comments.size() + 1L;
		newComment.setCommentId(nextCommentID);
		comments.add(newComment);
		try {
			PostDBO postDBO = new PostDBO(postDTO.getPostId(), objectMapper.writeValueAsString(postFromRepo.getComments()));
			postRepository.save(postDBO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return postFromRepo;
	}

	@Override
	@Cacheable(value = "posts", key = "#postId")
	public PostDTO findById(long postId) {
		Optional<PostDBO> byId = postRepository.findById(postId);
		if (byId.isPresent()) {
			try {
				List<Comment> comments = objectMapper.readValue(byId.get().getPost(), typeRef);
				return new PostDTO(byId.get().getPostId(), comments);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<PostDTO> findByIds(List<Long> postIds) {
		List<PostDBO> postDBOs = (List<PostDBO>) postRepository.findPosts(postIds);
		List<PostDTO> response = new ArrayList<>();
		if (!CollectionUtils.isEmpty(postDBOs)) {
			for (PostDBO postDBO : postDBOs) {
				response.add(createDTO(postDBO));
			}

		}
		return response;

	}

	@Override
	public List<PostDTO> getTopPosts() {
		return context.getTopList();
	}

	private PostDTO createDTO(PostDBO postDBO) {
		List<Comment> comments = null;
		try {
			comments = objectMapper.readValue(postDBO.getPost(), new TypeReference<List<Comment>>() {

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PostDTO(postDBO.getPostId(), comments);
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

	@Autowired
	public void setContext(Context context) {
		this.context = context;
	}

}
