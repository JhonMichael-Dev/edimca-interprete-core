package com.edimca.core.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.edimca.core.model.OrderLepton;
import com.edimca.core.model.ValidatorModel;

public interface FileServiceAPI {
	
	public ValidatorModel storeFile(MultipartFile file) throws Exception;

	public ValidatorModel translateFile(OrderLepton order) throws Exception;
	
	public ResponseEntity<Resource> getFile(String fileName) throws Exception;

}
