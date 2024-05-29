package com.springrestapi.services;

import com.springrestapi.dtos.FileUploadDto;
import com.springrestapi.model.FileUpload;

import java.io.IOException;

public interface FileUploadService {
	public String saveFile(FileUploadDto upload) throws IOException;
}
