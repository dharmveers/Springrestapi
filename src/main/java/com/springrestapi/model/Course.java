package com.springrestapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class Course {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int course_id;
	private String course_title;
	private String course_desc;
	public int getCourse_id() {
		return course_id;
	}
	public String getCourse_title() {
		return course_title;
	}
	public String getCourse_desc() {
		return course_desc;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}
	public void setCourse_desc(String course_desc) {
		this.course_desc = course_desc;
	}
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_title=" + course_title + ", course_desc=" + course_desc
				+ "]";
	}
	
	
}
