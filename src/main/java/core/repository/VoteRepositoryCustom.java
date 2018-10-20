package core.repository;

import core.repository.model.VoterDBO;

import java.util.List;

public interface VoteRepositoryCustom {

	int insertVote(VoterDBO voterDBO);

	List<Long> getTopVotedPostsIds(int count);
}
