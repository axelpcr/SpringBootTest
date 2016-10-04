package zergling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zergling.dao.ZerglingDao;
import zergling.model.Zergling;

import java.util.List;

/**
 * Service to Zergling Object.
 * <p/>
 * Created by axel on 04/10/16.
 */
@Service
public class ZerglingService {

	@Autowired
	private ZerglingDao zerglingDao;

	public List<Zergling> getZerglings() {
		return zerglingDao.findAll();
	}

	public Zergling getZergling(Integer id) {
		return zerglingDao.findOne(id);
	}

	public void deleteZergling(Integer id) {
		zerglingDao.delete(id);
	}

	public Zergling setZergling(Zergling zergling) {
		return zerglingDao.save(zergling);
	}

	public Zergling updateZergling(Zergling zergling) {
		return zerglingDao.save(zergling);
	}

}
