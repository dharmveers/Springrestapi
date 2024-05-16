package com.springrestapi.services.Impl;

import com.springrestapi.dao.FileUploadRepo;
import com.springrestapi.model.FileUpload;
import com.springrestapi.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileUploadRepo uploadRepo;
	
	@Override
	public String saveFile(FileUpload upload) {
			this.uploadRepo.save(upload);
			return "file Upload success!";
	}
}
