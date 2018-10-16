package core.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable // This tells Hibernate to make a table out of this class
public class UserForPost implements Serializable{


	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Long post;


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


}

