package core.service;

import core.model.PostDTO;
import core.model.VoteDTO;
import core.repository.VoteRepository;
import core.repository.model.VoterDBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private PostService postService;

	@Override
	public boolean insertVote(VoteDTO voteDto) {
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
		return voteRepository.getTopVotedPostsIds(count);
	}
}
