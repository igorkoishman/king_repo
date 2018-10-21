package core.repository;

import core.repository.model.VoterDBO;
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
public class VoteRepositoryImpl implements VoteRepositoryCustom {

	private static Logger logger = LoggerFactory.getLogger(VoteRepositoryImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int insertVote(VoterDBO voterDBO) {
		logger.debug("run method insert vote");
		Query query = entityManager.createNativeQuery("insert into `votes` (id, post,vote) VALUES (?,?,?)");
		query.setParameter(1, voterDBO.getUserForPost().getId());
		query.setParameter(2, voterDBO.getUserForPost().getPost());
		query.setParameter(3, voterDBO.getVote());
		return query.executeUpdate();
	}

	@Override
	public List<Long> getTopVotedPostsIds(int count) {
		logger.debug("run method get top post by ids vote");
		Query nativeQuery = entityManager.createNativeQuery(
				"SELECT post FROM `votes` vo WHERE vo.`vote` = 1   GROUP BY(vo.`post`) ORDER BY SUM(vo.`vote`) DESC,vo.`post` DESC limit ?");
		nativeQuery.setParameter(1, count);
		return nativeQuery.getResultList();
	}

}