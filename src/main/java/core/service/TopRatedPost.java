package core.service;

import core.model.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopRatedPost {

	private static final Logger log = LoggerFactory.getLogger(TopRatedPost.class);

	private VoteService voteService;
	private PostService postService;

	@Scheduled(fixedRate = 5000)
	@CachePut(value = "top-posts")
	@Async
	public List<PostDTO> recalculateTopPost() {
		log.info("running async task to recalculate top posts on thread id {}", Thread.currentThread().getId());
		List<Long> topPostsIds = voteService.getTopPostsIds(10);
		List<PostDTO> postDTOs = postService.findByIds(topPostsIds);
		return postDTOs;

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

}