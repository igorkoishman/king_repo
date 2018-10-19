package core.service;

import core.clients.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public void inserVote(VoteDTO voteDto) {
		voteRepository.insertVote(voteDto);
	}
}
