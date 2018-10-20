package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopRatedPost {

	private static final Logger log = LoggerFactory.getLogger(TopRatedPost.class);

	private VoteService voteService;
	private PostService postService;
	private Context context;

	@Scheduled(fixedRate = 5000)
	public void recalculateTopPost() {
		List<Long> topPostsIds = voteService.getTopPostsIds(10);
		List<PostDTO> postDTOs = postService.findByIds(topPostsIds);
		context.setTopList(postDTOs);
	}

	public VoteService getVoteService() {
		return voteService;
	}

	@Autowired
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	public PostService getPostService() {
		return postService;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Autowired
	public void setContext(Context context) {
		this.context = context;
	}

}