package posts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import posts.model.Comment;
import posts.model.PostDTO;
import posts.repository.PostRepository;
import posts.repository.model.PostDBO;

@Component
public class PostServiceImpl implements PostService {

  private static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
  private PostRepository postRepository;
  private ObjectMapper objectMapper;
  private TopRatedPost topRatedPost;
  private static final TypeReference<List<Comment>> typeRef = new TypeReference<List<Comment>>() {
  };

  @Autowired
  public void setTopRatedPost(TopRatedPost topRatedPost) {
    this.topRatedPost = topRatedPost;
  }

  @Autowired
  public void setPostRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  @Cacheable(value = "posts", key = "#postId")
  public PostDTO findById(long postId) {
    logger.debug("run service method find by id");
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
    logger.debug("run service method find by ids");
    List<PostDTO> response = new ArrayList<>();
    if (!CollectionUtils.isEmpty(postIds)) {
      List<PostDBO> postDBOs = (List<PostDBO>) postRepository.findPosts(postIds);
      if (!CollectionUtils.isEmpty(postDBOs)) {
        for (PostDBO postDBO : postDBOs) {
          response.add(createDTO(postDBO));
        }
      }
    }
    return response;
  }

  @Override
  public PostDTO addPost(PostDTO postDTO) {
    logger.debug("run service method add post");
    try {
      PostDBO postDBO = postRepository.save(new PostDBO(objectMapper.writeValueAsString(postDTO.getComments())));
      return new PostDTO(postDBO.getPostId(), objectMapper.readValue(postDBO.getPost(), typeRef));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  @CachePut(value = "posts", key = "#postDTO.postId")
  public PostDTO updatePost(PostDTO postDTO) {
    logger.debug("run service method update post");
    PostDTO postFromRepo = findById(postDTO.getPostId());
    if (null != postFromRepo) {
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
    }
    return postFromRepo;
  }

  @Override
  @Cacheable("top-posts")
  public List<PostDTO> getTopPosts() {
    logger.debug("run service method get top rated posts");
    return topRatedPost.recalculateTopPost();
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
}
