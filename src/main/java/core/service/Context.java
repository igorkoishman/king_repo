package core.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Context {

	private List<PostDTO> topList;

	public Context() {
	}

	public Context(List<PostDTO> topList) {
		this.topList = topList;
	}

	public List<PostDTO> getTopList() {
		return topList;
	}

	public void setTopList(List<PostDTO> topList) {
		this.topList = topList;
	}
}
