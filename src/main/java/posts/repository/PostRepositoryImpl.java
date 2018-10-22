package posts.repository;

import posts.repository.model.PostDBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class PostRepositoryImpl implements PostRepositoryCustom {

	private static Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Iterable<PostDBO> findPosts(List<Long> postIds) {
		logger.debug("run query find post");
		Query nativeQuery = entityManager.createNativeQuery("SELECT `post_id`,`post` FROM `post` p WHERE p.`post_id` IN (?1)", PostDBO.class);
		nativeQuery.setParameter(1, postIds);
		return nativeQuery.getResultList();
	}
}