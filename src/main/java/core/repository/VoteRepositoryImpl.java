package core.repository;

import core.repository.model.VoterDBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by gkatzioura on 6/3/16.
 */
@Repository
@Transactional(readOnly = false)
public class VoteRepositoryImpl implements VoteRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int insertVote(VoterDBO voterDBO) {
		Query query = entityManager.createNativeQuery("insert into `votes` (id, post,vote) VALUES (?,?,?)");
		query.setParameter(1, voterDBO.getUserForPost().getId());
		query.setParameter(2, voterDBO.getUserForPost().getPost());
		query.setParameter(3, voterDBO.getVote());
		return query.executeUpdate();
	}

	@Override
	public List<Long> getTopVotedPostsIds(int count) {
		Query nativeQuery = entityManager.createNativeQuery(
				"SELECT post FROM `votes` vo WHERE vo.`vote` > 0   GROUP BY(post) ORDER BY vo.`post` DESC limit ?");
		nativeQuery.setParameter(1, count);
		return nativeQuery.getResultList();
	}

}