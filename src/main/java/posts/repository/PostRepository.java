package posts.repository;

import posts.repository.model.PostDBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PostRepository extends CrudRepository<PostDBO, Long>,PostRepositoryCustom {


}
