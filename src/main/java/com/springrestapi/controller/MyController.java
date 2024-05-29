package com.springrestapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springrestapi.dtos.FileUploadDto;
import com.springrestapi.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrestapi.model.Course;
import com.springrestapi.model.FileUpload;
import com.springrestapi.services.CourseServices;
import com.springrestapi.services.FileUploadService;

@RestController
@CrossOrigin
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

//    @PostMapping(path = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String fileUpload(@RequestPart String data, @RequestPart MultipartFile pdfFile) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        FileUploadDto fileUpload = objectMapper.readValue(data, FileUploadDto.class);
//        fileUpload.setPdfFile(pdfFile);
//        return this.uploadService.saveFile(fileUpload);
//    }
}
