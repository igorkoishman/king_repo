package core.clients;

import core.service.VoteDTO;

import java.util.List;

public interface VoteRepositoryCustom {
	int insertVote(VoteDTO voteDTO);

	List<Long> getTopVotedPostsIds(int count);
}
