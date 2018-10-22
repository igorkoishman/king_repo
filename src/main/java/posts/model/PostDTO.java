package posts.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PostDTO implements Serializable {

	Long postId;
	List<Comment> comments;

	public PostDTO() {
	}

	public PostDTO(Long postId, List<Comment> comments) {
		this.postId = postId;
		this.comments = comments;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostDTO postDTO = (PostDTO) o;
		return Objects.equals(postId, postDTO.postId) && Objects.equals(comments, postDTO.comments);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, comments);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PostDTO{");
		sb.append("postId=").append(postId);
		sb.append(", comments=").append(comments);
		sb.append('}');
		return sb.toString();
	}
}
