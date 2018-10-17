package core.controller;

import core.service.PostDTO;
import core.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("post")
public class PostController {

	@Autowired
	private PostService postRepository;
	private static Logger logger = LoggerFactory.getLogger(PostController.class);

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> addPost(@RequestBody String post) {
		logger.info("get into register method");
		try {
			postRepository.save(new PostDTO(post));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
		logger.info("get into register method");
		try {
			postRepository.save(postDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
