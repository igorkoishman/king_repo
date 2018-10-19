package core.clients;

import core.service.VoteDTO;

public interface VoteRepositoryCustom {
	int insertVote(VoteDTO voteDTO);
}
