package core.repository;

import core.repository.model.UserForPost;
import core.repository.model.VoterDBO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoterRepositoryIntegrationITest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private VoteRepository voteRepository;

	@Test
	public void find_By_UserForPost() {
		// given
		VoterDBO alex = new VoterDBO(123, 2l, 1);
		entityManager.persist(alex);
		entityManager.flush();

		Optional<VoterDBO> byId = voteRepository.findById(new UserForPost(123, 2l));
		assertTrue("the resault is not as expected", byId.get().getUserForPost().getId() == alex.getUserForPost().getId());
	}

	@Test
	public void find_all_only_one_in_db() {
		// given
		VoterDBO alex = new VoterDBO(123, 2l, 1);
		entityManager.persist(alex);
		entityManager.flush();

		List<VoterDBO> all = (List<VoterDBO>) voteRepository.findAll();

		Assert.assertEquals("the size not as expected", 1, all.size());
		assertTrue("the resault is not as expected", all.iterator().next().getUserForPost().getId() == alex.getUserForPost().getId());
	}

	@Test
	public void find_all_3_in_db() {
		VoterDBO alex1 = new VoterDBO(1, 2l, 1);
		VoterDBO alex2 = new VoterDBO(4, 2l, 1);
		VoterDBO alex3 = new VoterDBO(12, 25l, -1);
		List<VoterDBO> posters = new ArrayList<>();
		posters.add(alex1);
		posters.add(alex2);
		posters.add(alex3);
		entityManager.persistAndFlush(alex1);
		entityManager.persistAndFlush(alex2);
		entityManager.persistAndFlush(alex3);

		List<VoterDBO> all = (List<VoterDBO>) voteRepository.findAll();
		all.sort(new Comparator<VoterDBO>() {

			@Override
			public int compare(VoterDBO o1, VoterDBO o2) {
				return o1.getUserForPost().getId() < o2.getUserForPost().getId() ? 1 : 0;
			}
		});
		Assert.assertEquals("the size not as expected", posters.size(), all.size());
		for (int i = 0; i < posters.size(); i++) {
			Assert.assertEquals("the resault is not as expected", posters.get(i), posters.get(i));

		}
	}

	@Test
	public void getTopVotedPostsIdsNoEntityInRepoTest() {
		List<Long> topVotedPostsIds = voteRepository.getTopVotedPostsIds(3);
		Assert.assertTrue("the result set is not empty", topVotedPostsIds.isEmpty());
	}

	@Test
	public void insertVote() {
		VoterDBO expected = new VoterDBO(1l, 2l, 1);
		Assert.assertEquals(1, voteRepository.insertVote(expected));
		Optional<VoterDBO> fromRepo = voteRepository.findById(new UserForPost(1l, 2l));
		Assert.assertEquals("the DBOs is not equals", expected, fromRepo.get());
	}

}
