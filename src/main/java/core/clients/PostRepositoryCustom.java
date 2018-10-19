package core.clients;

import core.model.PostDBO;

import java.util.List;

public interface PostRepositoryCustom {


	Iterable<PostDBO> findPosts(List<Long> postIds);

}
