package core.service;

import java.util.List;

public interface VoteService {

	boolean insertVote(VoteDTO voteDto);

	List<Long> getTopPostsIds(int count);
}
