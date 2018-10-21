package core.repository;

import core.repository.model.UserForPost;
import core.repository.model.VoterDBO;
import org.springframework.data.repository.CrudRepository;


public interface VoteRepository extends CrudRepository<VoterDBO, UserForPost>, VoteRepositoryCustom {

}
