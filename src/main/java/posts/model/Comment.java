package posts.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	Long commentId;
	String commentContent;
	Date commentDate;

	public Comment() {
	}

	public Comment(String commentContent) {
		this.commentId = 1l;
		this.commentContent = commentContent;
		this.commentDate = new Date();
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

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
