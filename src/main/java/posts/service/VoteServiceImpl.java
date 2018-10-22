package posts.service;

import posts.model.PostDTO;
import posts.model.VoteDTO;
import posts.repository.VoteRepository;
import posts.repository.model.VoterDBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoteServiceImpl implements VoteService {

	private static Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

	private VoteRepository voteRepository;

	private PostService postService;

	@Autowired
	public void setVoteRepository(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Override
	public boolean insertVote(VoteDTO voteDto) {
		logger.debug("run service method insert vote");
		PostDTO postDTO = postService.findById(voteDto.getPostId());
		if (postDTO == null) {
			return false;
		}
		VoterDBO voterDBO = new VoterDBO(voteDto.getUserId(), voteDto.getPostId(), voteDto.getVote());
		voteRepository.insertVote(voterDBO);
		return true;
	}

	@Override
	public List<Long> getTopPostsIds(int count) {
		logger.debug("run service method getTopVotedPostsIds");
		return voteRepository.getTopVotedPostsIds(count);
	}
}
