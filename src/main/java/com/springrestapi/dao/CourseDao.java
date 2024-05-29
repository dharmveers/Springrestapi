package com.springrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrestapi.model.Course;
import com.springrestapi.model.FileUpload;

public interface CourseDao extends JpaRepository<Course, Integer>{

	void save(FileUpload upload);

}
