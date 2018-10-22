package posts.controller.apimodel;

import java.util.Objects;

public class PostRequest {

	Long postId;
	String message;

	public PostRequest() {
	}

	public PostRequest(Long postId, String message) {
		this.postId = postId;
		this.message = message;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostRequest postRequest = (PostRequest) o;
		return Objects.equals(postId, postRequest.postId) && Objects.equals(message, postRequest.message);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, message);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PostRequest{");
		sb.append("postId=").append(postId);
		sb.append(", message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
