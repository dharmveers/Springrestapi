package com.springrestapi.services;

import java.util.List;

import com.springrestapi.model.Course;
import com.springrestapi.model.FileUpload;

public interface CourseServices {
	public List<Course> getCourses();
	
	public Course getCourse(int course_id);
	
	public Course AddCourse(Course course);
	
	public String updateCourse(Course course);
	
	public void deleteCourse(int id);
	

}
