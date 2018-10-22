package posts.repository;

import posts.repository.model.PostDBO;

import java.util.List;

public interface PostRepositoryCustom {


	Iterable<PostDBO> findPosts(List<Long> postIds);

}
