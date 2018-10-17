package core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
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
		this.post=post;
	}

	public PostDBO(Long postId, String post) {
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
		PostDBO postDbo = (PostDBO) o;
		return Objects.equals(postId, postDbo.postId) && Objects.equals(post, postDbo.post);
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