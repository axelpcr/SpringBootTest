package test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.model.Zergling;
import test.service.ZerglingService;

@RestController
public class ZerglingWebService {

	@Autowired
	private ZerglingService zerglingService;

	@RequestMapping(value = "/zergling", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getZerglings() {
		return new ResponseEntity(zerglingService.getZerglings(), HttpStatus.OK);
	}

	@RequestMapping(value = "/zergling/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getZergling(@PathVariable Integer id) {
		return new ResponseEntity(zerglingService.getZergling(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/zergling", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity setZergling(@RequestBody Zergling zergling) {
		return new ResponseEntity(zerglingService.setZergling(zergling), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/zergling", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateZergling(Zergling zergling) {
		return new ResponseEntity(zerglingService.updateZergling(zergling), HttpStatus.OK);
	}

	@RequestMapping(value = "/zergling/{id}", method = RequestMethod.DELETE, produces = MediaType
			.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteZergling(@PathVariable("id") Integer id) {
		zerglingService.deleteZergling(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
