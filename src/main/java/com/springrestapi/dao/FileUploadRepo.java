package com.springrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrestapi.model.FileUpload;

@Repository
public interface FileUploadRepo extends JpaRepository<FileUpload, Integer>{

}
