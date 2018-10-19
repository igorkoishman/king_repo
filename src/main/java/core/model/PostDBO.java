package core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post")
public class PostDBO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "postId", updatable = false, nullable = false)
	private Long postId;
	private String post;

	public PostDBO() {
	}

	public PostDBO(String post) {
		this.post = post;
	}

	public PostDBO(Long postId, String post) {
		this.postId = postId;
		this.post = post;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
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
		PostDBO postDbo = (PostDBO) o;
		return Objects.equals(postId, postDbo.postId) && Objects.equals(post, postDbo.post);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, post);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PostDBO{");
		sb.append("postId=").append(postId);
		sb.append(", post='").append(post).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
