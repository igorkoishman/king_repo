package core.controller;

import core.clients.VoteRepository;
import core.model.VoterDBO;
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

public class VoteController {

	@Autowired
	private VoteRepository voteRepository;
	private static Logger logger = LoggerFactory.getLogger(VoteController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> voting(@RequestBody VoterDBO voterDBO) {
		logger.info("get into register method");
		try {
			voteRepository.save(voterDBO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
