package com.springrestapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrestapi.model.Course;
import com.springrestapi.model.FileUpload;
import com.springrestapi.services.CourseServices;
import com.springrestapi.services.FileUploadService;

@RestController
public class MyController {
    @Value("${document.path}")
    private String uri;

    @Autowired
    public CourseServices courseServices;

    @GetMapping("/")
    public String home() {
        return "Welcome to the Home page...";
    }

    //request to get all courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return this.courseServices.getCourses();
    }

    //request to get single course
    @GetMapping("/courses/{course_id}")
    public Course getCourse(@PathVariable int course_id) {
        return this.courseServices.getCourse(course_id);
    }

    //request to add courses
    @PostMapping("/courses")
    public Course AddCourse(@RequestBody Course course) {
        return this.courseServices.AddCourse(course);
    }

    //request for update course
    @PutMapping("/courses")
    public String updateCourse(@RequestBody Course course) {

        return this.courseServices.updateCourse(course);
    }

    //request for delete course by id
    @DeleteMapping("/courses/{course_id}")
    public void deleteCourse(@PathVariable int course_id) {
        this.courseServices.deleteCourse(course_id);
    }

    //fileUpload
    @Autowired
    private FileUploadService uploadService;

    @PostMapping(path = "/upload")
    public String fileUpload(@RequestPart FileUpload fileUpload, @RequestPart MultipartFile pdfFile) throws IOException {
        if(pdfFile.isEmpty()){
			return "No Media File selected";
		}else if(fileUpload==null){
			return "Object is null !";
		}
        return this.uploadService.saveFile(fileUpload);
    }
}
