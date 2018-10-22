package posts.controller.apimodel;

import java.util.Objects;

public class VoteRequest {

	private long voterId;
	private long postId;
	private int vote;

	public VoteRequest() {
	}

	public VoteRequest(long voterId, long postId, int vote) {
		this.voterId = voterId;
		this.postId = postId;
		this.vote = vote;
	}

	public long getVoterId() {
		return voterId;
	}

	public void setVoterId(long voterId) {
		this.voterId = voterId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VoteRequest that = (VoteRequest) o;
		return voterId == that.voterId && postId == that.postId && vote == that.vote;
	}

	@Override
	public int hashCode() {

		return Objects.hash(voterId, postId, vote);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("VoteRequest{");
		sb.append("voterId=").append(voterId);
		sb.append(", postId=").append(postId);
		sb.append(", vote=").append(vote);
		sb.append('}');
		return sb.toString();
	}
}
