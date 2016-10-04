package zergling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zergling.model.Zergling;


/**
 * Created by axel on 04/10/16.
 */
@Repository
public interface ZerglingDao extends JpaRepository<Zergling, Integer> {

}
