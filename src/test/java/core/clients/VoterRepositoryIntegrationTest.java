package core.clients;

import core.model.UserForPost;
import core.model.VoterDBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoterRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private VoteRepository voterRepository;

	@Test
	public void find_By_User_And_post_Id() {
		// given
		VoterDBO alex = new VoterDBO(123,2l,1);
		entityManager.persist(alex);
		entityManager.flush();

		// when
		Iterable<VoterDBO> all = voterRepository.findAll();

		// then
		assertTrue(all.iterator().next().getUserForPost().getId()==alex.getUserForPost().getId());
		System.out.println(all.iterator().next());
		Optional<VoterDBO> byId = voterRepository.findById(new UserForPost(123, 2l));
		System.out.printf(byId.get().toString());
	}

}
