package core.service;

import core.clients.VoteRepository;
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
		voteRepository.insertVote(voteDto);
		return true;
	}

	@Override
	public List<Long> getTopPostsIds(int count) {
		return voteRepository.getTopVotedPostsIds(count);
	}
}
