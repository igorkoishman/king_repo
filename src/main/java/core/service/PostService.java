package core.service;

public interface PostService {

	void save(PostDTO postDTO);

	PostDTO findById(PostDTO postDTO);

}
