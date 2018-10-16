package core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity @IdClass(VoteId.class)
public class Vote implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Id
	Long pass;
	String value;


}
