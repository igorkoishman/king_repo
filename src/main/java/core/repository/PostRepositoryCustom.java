package core.repository;

import core.repository.model.PostDBO;

import java.util.List;

public interface PostRepositoryCustom {


	Iterable<PostDBO> findPosts(List<Long> postIds);

}
