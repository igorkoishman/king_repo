package core.controller;

import core.model.Comment;
import core.service.PostDTO;
import core.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {

	private static Logger logger = LoggerFactory.getLogger(PostController.class);

	private PostService postService;

	public PostService getPostService() {
		return postService;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> addPost(@RequestBody Request request) {
		logger.info("get into add method");
		if (StringUtils.isBlank(request.getMessage())) {
			return ResponseEntity.badRequest().body(new Response("The message cannot be empty"));
		}
		try {
			PostDTO postDTO = fromRequestToDTO(request);
			postService.addPost(postDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> updatePost(@RequestBody Request request) {
		logger.info("get into update method");
		if (StringUtils.isBlank(request.getMessage()) || request.getPostId() == null || request.getPostId() < 1) {
			return new ResponseEntity<>(new Response("The message and id cannot be empty"), HttpStatus.BAD_REQUEST);
		}
		try {
			PostDTO postDTO = fromRequestToDTO(request);
			postService.updatePost(postDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	@ResponseBody
	protected ResponseEntity getBiId(@PathVariable("postId") Long postId) {
		logger.info("get into register method");
		try {
			PostDTO postDTO = postService.findById(new PostDTO(postId, null));
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

	private PostDTO fromRequestToDTO(@RequestBody Request request) {
		PostDTO postDTO = new PostDTO();
		List<Comment> comments = new ArrayList<>();
		comments.add(new Comment(request.getMessage()));
		postDTO.setComments(comments);
		postDTO.setPostId(request.getPostId());
		return postDTO;
	}

}
