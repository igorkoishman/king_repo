package posts.repository;

import posts.repository.model.UserForPost;
import posts.repository.model.VoterDBO;
import org.springframework.data.repository.CrudRepository;


public interface VoteRepository extends CrudRepository<VoterDBO, UserForPost>, VoteRepositoryCustom {

}
