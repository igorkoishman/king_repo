package core.clients;

import core.model.UserForPost;
import core.model.Voter;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VoterRepository extends CrudRepository<Voter, UserForPost> {

}
