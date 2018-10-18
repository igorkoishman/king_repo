package core.model;

import java.io.Serializable;

public class Comment implements Serializable {

	Long commentId;
	String commentContent;
	Long commentDate;

	public Comment() {
	}

	public Comment(String commentContent) {
		this.commentId = 0l;
		this.commentContent = commentContent;
		this.commentDate = System.currentTimeMillis();
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Long getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Long commentDate) {
		this.commentDate = commentDate;
	}
}
