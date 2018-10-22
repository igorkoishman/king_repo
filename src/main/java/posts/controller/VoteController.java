package posts.controller;

import posts.controller.apimodel.Response;
import posts.controller.apimodel.VoteRequest;
import posts.service.VoteService;
import posts.model.VoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("vote")
public class VoteController {

	private static Logger logger = LoggerFactory.getLogger(VoteController.class);

	private VoteService voteService;

	@Autowired
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> addVote(@RequestBody VoteRequest voteRequest) {
		logger.info("get into add method");
		if ((voteRequest.getVote() != -1 && voteRequest.getVote() != 1)) {
			return ResponseEntity.badRequest().body(new Response("vote have to be -1/1"));
		}
		try {
			VoteDTO voteDTO = convertToVoteDTO(voteRequest);
			voteService.insertVote(voteDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (DataIntegrityViolationException constraintViolationException) {
			logger.error(constraintViolationException.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response("You already voted for that post"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private VoteDTO convertToVoteDTO(@RequestBody VoteRequest voteRequest) {
		VoteDTO voteDTO = new VoteDTO();
		voteDTO.setPostId(voteRequest.getPostId());
		voteDTO.setUserId(voteRequest.getVoterId());
		voteDTO.setVote(voteRequest.getVote());
		return voteDTO;
	}

}
