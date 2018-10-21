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
	public void getTopVotedPostsIdsTest() {
		VoterDBO voter1 = new VoterDBO(11, 2l, 1);
		VoterDBO voter2 = new VoterDBO(21, 2l, 1);
		VoterDBO voter3 = new VoterDBO(31, 3l, 1);
		VoterDBO voter4 = new VoterDBO(41, 3l, 1);
		VoterDBO voter5 = new VoterDBO(51, 4l, 1);
		VoterDBO voter6 = new VoterDBO(61, 4l, -1);
		VoterDBO voter7 = new VoterDBO(71, 5l, 1);
		VoterDBO voter8 = new VoterDBO(81, 6l, 1);
		entityManager.persistAndFlush(voter1);
		entityManager.persistAndFlush(voter2);
		entityManager.persistAndFlush(voter3);
		entityManager.persistAndFlush(voter4);
		entityManager.persistAndFlush(voter5);
		entityManager.persistAndFlush(voter6);
		entityManager.persistAndFlush(voter7);
		entityManager.persistAndFlush(voter8);

		List<Long> topVotedPostsIds = voteRepository.getTopVotedPostsIds(3);
		Assert.assertEquals(3, topVotedPostsIds.size());
		//		long value1 = topVotedPostsIds.get(0).longValue();
		//		long value2 = topVotedPostsIds.get(1).longValue();
		//		long value3 = topVotedPostsIds.get(2).longValue();
		//		Assert.assertEquals(6, value1);

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
