package posts.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import posts.model.PostDTO;
import posts.model.VoteDTO;
import posts.repository.VoteRepository;
import posts.repository.model.VoterDBO;

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
  public VoteDTO insertVote(VoteDTO voteDto) {
    logger.debug("run service method insert vote");
    PostDTO postDTO = postService.findById(voteDto.getPostId());
    if (postDTO != null) {
      VoterDBO voterDBO = new VoterDBO(voteDto.getUserId(), voteDto.getPostId(), voteDto.getVote());
      if (voteRepository.insertVote(voterDBO) == 1) {
        return voteDto;
      }
    }
    return null;
  }

  @Override
  public List<Long> getTopPostsIds(int count) {
    logger.debug("run service method getTopVotedPostsIds");
    return voteRepository.getTopVotedPostsIds(count);
  }
}
