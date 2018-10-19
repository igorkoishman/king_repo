package core.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserForPost implements Serializable {

	private long id;
	private long post;

	public UserForPost() {
	}

	public UserForPost(long id, long post) {
		this.id = id;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPost() {
		return post;
	}

	public void setPost(long post) {
		this.post = post;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserForPost that = (UserForPost) o;
		return id == that.id && post == that.post;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, post);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserForPost{");
		sb.append("id=").append(id);
		sb.append(", post=").append(post);
		sb.append('}');
		return sb.toString();
	}
}

