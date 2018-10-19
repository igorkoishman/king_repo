package core.clients;

import core.service.VoteDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by gkatzioura on 6/3/16.
 */
@Repository
@Transactional(readOnly = false)
public class VoteRepositoryImpl implements VoteRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int insertVote(VoteDTO voteDTO) {
		Query query = entityManager.createNativeQuery("insert into `votes` (id, post,vote) VALUES (?,?,?)");
		query.setParameter(1, voteDTO.getUserId());
		query.setParameter(2, voteDTO.getPostId());
		query.setParameter(3, voteDTO.getVote());
		return query.executeUpdate();
	}

}