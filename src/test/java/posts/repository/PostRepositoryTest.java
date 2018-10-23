package posts.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PostRepository postRepository;

	@Test
	public void successFindByIdTest(){
	}
	@Test
	public void notExistsIdFindById(){
	}

	@Test
	public void successFindPostsByIds(){

	}
	@Test
	public void notExistingAllIds(){
	}

	@Test
	public void fetchByIdsOneIsNotExists(){
	}
}
