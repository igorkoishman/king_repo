package core.repository;

import core.repository.model.UserForPost;
import core.repository.model.VoterDBO;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VoteRepository extends CrudRepository<VoterDBO, UserForPost>, VoteRepositoryCustom {

}
