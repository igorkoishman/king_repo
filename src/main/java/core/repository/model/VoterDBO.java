package core.repository.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "votes")
public class VoterDBO implements Serializable {

	@EmbeddedId
	private UserForPost userForPost;
	private int vote;

	public VoterDBO() {
	}

	public VoterDBO(long userId, long postId, int vote) {
		this.userForPost = new UserForPost(userId, postId);
		this.vote = vote;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public UserForPost getUserForPost() {
		return userForPost;
	}

	public void setUserForPost(UserForPost userForPost) {
		this.userForPost = userForPost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VoterDBO voterDBO = (VoterDBO) o;
		return Objects.equals(userForPost, voterDBO.userForPost) && Objects.equals(vote, voterDBO.vote);
	}

	@Override
	public int hashCode() {

		return Objects.hash(userForPost, vote);
	}

	@Override
	public String toString() {
		return "VoterDBO{" + "userForPost=" + userForPost + ", vote=" + vote + '}';
	}
}
