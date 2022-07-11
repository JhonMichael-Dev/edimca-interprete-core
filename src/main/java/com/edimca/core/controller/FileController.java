package com.edimca.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edimca.core.model.OrderLepton;
import com.edimca.core.model.ValidatorModel;
import com.edimca.core.service.FileServiceAPI;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileServiceAPI fileServiceAPI;

	// @CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/storeFile")
	public ValidatorModel storeFile(@RequestBody MultipartFile file) throws Exception {
		return fileServiceAPI.storeFile(file);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/translateFile")
	public ValidatorModel translateFile(@RequestBody OrderLepton order) throws Exception {
		return fileServiceAPI.translateFile(order);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getFile/{fileName}", method = RequestMethod.GET, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE )
	public ResponseEntity<Resource> prueba(@PathVariable(value = "fileName") String fileName) throws Exception {
		return fileServiceAPI.getFile(fileName);
	}
}
