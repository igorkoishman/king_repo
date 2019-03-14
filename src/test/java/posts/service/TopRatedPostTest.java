package posts.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import posts.model.Comment;
import posts.model.PostDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopRatedPostTest {

	@InjectMocks
	private TopRatedPost topRatedPost;
	@Mock
	private VoteService voteService;
	@Mock
	private PostService postService;

//	@Test
//	public void emptyResultFromVotes() {
//		when(voteService.getTopPostsIds(10)).thenReturn(new ArrayList<>());
//		when(postService.findByIds(new ArrayList<>())).thenReturn(new ArrayList<>());
//		List<PostDTO> postDTOS = topRatedPost.recalculateTopPost();
//		Assert.assertNotNull("Return value is not as expected ", postDTOS);
//		Assert.assertEquals("List size is not as expected ", 0, postDTOS.size());
//	}
//
//	@Test
//	public void singleResultFromTheCalculation() {
//		when(voteService.getTopPostsIds(10)).thenReturn(new ArrayList<>(1));
//		List<PostDTO> postDTOs = generatePostDTOsResult(1, 1);
//		when(postService.findByIds(new ArrayList<>())).thenReturn(postDTOs);
//		List<PostDTO> postDTOS = topRatedPost.recalculateTopPost();
//		Assert.assertNotNull("Return value is not as expected ", postDTOS);
//		Assert.assertEquals("List size is not as expected ", 1, postDTOS.size());
//		Assert.assertEquals("List size is not as expected ", postDTOS, postDTOS);
//	}

	@Test
	public void verifyTopXTopFromVotesAndFetchFromDB() {

	}

	@Test
	public void checkRunTimeExceptionOnServiceResponse() {

	}

	private List<PostDTO> generatePostDTOsResult(int numberOfPostsDTOs, int commentsPerPostsDTO) {
		List<PostDTO> postDTOs = new ArrayList<>();
		for (long i = 0; i < numberOfPostsDTOs; i++) {
			List<Comment> comments = generateComments(commentsPerPostsDTO);
			PostDTO postDTO = new PostDTO(i, comments);
			postDTOs.add(postDTO);
		}

		return postDTOs;
	}

	private List<Comment> generateComments(int commentsPerPostsDTO) {
		List<Comment> comments = new ArrayList<>();
		for (int i = 0; i < commentsPerPostsDTO; i++) {
			Comment comment = new Comment("some comment " + i);
			comments.add(comment);
		}
		return comments;
	}

}
