package core.service;

import java.util.Objects;

public class VoteDTO {

	private long userId;
	private long PostId;
	int vote;

	public VoteDTO() {
	}

	public VoteDTO(long userId, long postId, int vote) {
		this.userId = userId;
		PostId = postId;
		this.vote = vote;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPostId() {
		return PostId;
	}

	public void setPostId(long postId) {
		PostId = postId;
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
		VoteDTO voteDTO = (VoteDTO) o;
		return userId == voteDTO.userId && PostId == voteDTO.PostId && vote == voteDTO.vote;
	}

	@Override
	public int hashCode() {

		return Objects.hash(userId, PostId, vote);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("VoteDTO{");
		sb.append("userId=").append(userId);
		sb.append(", PostId=").append(PostId);
		sb.append(", vote=").append(vote);
		sb.append('}');
		return sb.toString();
	}
}
