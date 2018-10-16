package core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

public class VoteId implements Serializable {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long pass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPass() {
		return pass;
	}

	public void setPass(Long pass) {
		this.pass = pass;
	}
}
