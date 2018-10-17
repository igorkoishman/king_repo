package core.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Voter implements Serializable {

	@EmbeddedId
	private UserForPost userForPost;
	private Integer vote;

	public Voter() {
	}

	public Voter(int userId,Long postId , Integer vote) {
		this.userForPost=new UserForPost(userId, postId );
		this.vote = vote;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public UserForPost getUserForPost() {
		return userForPost;
	}

	public void setUserForPost(UserForPost userForPost) {
		this.userForPost = userForPost;
	}

	@Override
	public String toString() {
		return "Voter{" + "userForPost=" + userForPost + ", vote=" + vote + '}';
	}
}
