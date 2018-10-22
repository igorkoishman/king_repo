package posts.controller;

import posts.controller.apimodel.PostRequest;
import posts.controller.apimodel.Response;
import posts.service.PostService;
import posts.model.Comment;
import posts.model.PostDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {

	private static Logger logger = LoggerFactory.getLogger(PostController.class);

	private PostService postService;

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> addPost(@RequestBody PostRequest postRequest) {
		logger.info("get into add method");
		PostDTO postDTO;
		if (StringUtils.isBlank(postRequest.getMessage())) {
			return ResponseEntity.badRequest().body(new Response("The message cannot be empty"));
		}
		try {
			postDTO = postService.addPost(fromRequestToDTO(postRequest));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> updatePost(@RequestBody PostRequest postRequest) {
		logger.info("get into update method");
		if (StringUtils.isBlank(postRequest.getMessage()) || postRequest.getPostId() == null || postRequest.getPostId() < 1) {
			return new ResponseEntity<>(new Response("The message and id cannot be empty"), HttpStatus.BAD_REQUEST);
		}
		try {
			PostDTO postDTO = fromRequestToDTO(postRequest);
			postService.updatePost(postDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	@ResponseBody
	protected ResponseEntity getById(@PathVariable("postId") Long postId) {
		logger.info("get into getById method");
		try {
			PostDTO postDTO = postService.findById(postId);
			if (null != postDTO) {
				return new ResponseEntity(postDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/top", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	@ResponseBody
	protected ResponseEntity getTopPosts() {
		logger.info("get into top-posts method");
		try {
			List<PostDTO> topList = postService.getTopPosts();
			if (!CollectionUtils.isEmpty(topList)) {
				return new ResponseEntity(topList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private PostDTO fromRequestToDTO(@RequestBody PostRequest postRequest) {
		PostDTO postDTO = new PostDTO();
		List<Comment> comments = new ArrayList<>();
		comments.add(new Comment(postRequest.getMessage()));
		postDTO.setComments(comments);
		postDTO.setPostId(postRequest.getPostId());
		return postDTO;
	}

}
