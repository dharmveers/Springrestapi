package com.springrestapi.services.Impl;

import com.springrestapi.dao.FileUploadRepo;
import com.springrestapi.dtos.FileUploadDto;
import com.springrestapi.model.FileUpload;
import com.springrestapi.services.FileUploadService;
import com.springrestapi.utils.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileUploadRepo uploadRepo;
	
	@Override
	public String saveFile(FileUploadDto upload) throws IOException {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setUsername(upload.getUsername());
		fileUpload.setMobileNo(upload.getMobileNo());
		fileUpload.setAddress(upload.getAddress());
		if (upload.getPdfFile()!=null){
			String url = HelperUtil.uploadFile(upload.getPdfFile());
			fileUpload.setUrls(url);
		}
		uploadRepo.save(fileUpload);
		return "file Upload success!";
	}

}
