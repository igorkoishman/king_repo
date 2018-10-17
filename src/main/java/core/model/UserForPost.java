package core.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class UserForPost implements Serializable{

	private Integer id;
	private Long post;

	public UserForPost() {
	}

	public UserForPost(Integer id, Long post) {
		this.id = id;
		this.post = post;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getPost() {
		return post;
	}

	public void setPost(Long post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "UserForPost{" + "id=" + id + ", post=" + post + '}';
	}
}

