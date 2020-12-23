package com.example.student.controller;

import com.example.student.Service.StudentService;
import com.example.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("api/v2/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @CrossOrigin
    @PostMapping("api/v2/student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();
//        return ResponseEntity.created(location).body(student);
    }


    @CrossOrigin
    @GetMapping("api/v2/student")
    public List<Student> listStudents(){
        return studentService.listStudents();
    }


    @CrossOrigin
    @GetMapping("api/v2/student/{id}")
    public Student getStudentById(@PathVariable Long id){
         return studentService.getStudentById(id);
    }


    @CrossOrigin
    @DeleteMapping("api/v2/student/{id}")
    public String deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "DELETED";
    }


    @CrossOrigin
    @PutMapping("api/v2/student/{id}")
    public Student updateStudentById(@RequestBody Student newStudent, @PathVariable long id ){
        return studentService.updateStudentById(newStudent, id);
    }

}
