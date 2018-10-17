package core.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class PostDTO {


	Long postId;
	String post;

	public PostDTO(String post) {
		this.post = post;
	}

	public PostDTO(Long postId, String post) {
		this.postId = postId;
		this.post = post;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostDTO postDTO = (PostDTO) o;
		return Objects.equals(postId, postDTO.postId) && Objects.equals(post, postDTO.post);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, post);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("postId", postId).append("post", post).toString();
	}
}
