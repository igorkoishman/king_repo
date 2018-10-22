package posts.service;

import posts.model.VoteDTO;

import java.util.List;

public interface VoteService {

	boolean insertVote(VoteDTO voteDto);

	List<Long> getTopPostsIds(int count);
}
