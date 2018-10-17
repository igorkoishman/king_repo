package core.clients;

import core.model.PostDBO;
import core.model.UserForPost;
import core.model.VoterDBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Transactional
public interface PostRepository extends CrudRepository<PostDBO, Long> {

}
