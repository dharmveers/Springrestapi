package com.springrestapi.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrestapi.dao.CourseDao;
import com.springrestapi.dao.FileUploadRepo;
import com.springrestapi.model.Course;
import com.springrestapi.model.FileUpload;
import com.springrestapi.services.CourseServices;

@Service
public class CourseServicesImpl implements CourseServices{
	
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private FileUploadRepo uploadRepo;
	
	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}
	@Override
	public Course getCourse(int course_id) {
		return courseDao.getOne(course_id);
	}

	@Override
	public Course AddCourse(Course course) {
		courseDao.save(course);
		return course;
	}
	@Override
	public String updateCourse(Course course) {
		courseDao.save(course);
		return "course updated succeessfully..";
	}
	@Override
	public void deleteCourse(int course_id) {
		Course entty=courseDao.getOne(course_id);
		courseDao.delete(entty);
	}
	
}
