package com.example.student.controller;

import com.example.student.Service.StudentService;
import com.example.student.model.Student;
import com.example.student.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @CrossOrigin
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }


    @CrossOrigin
    @GetMapping
    public Page<Student> listStudents(Pageable pageable){
        return studentService.listStudents(pageable);
    }


    @CrossOrigin
    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable Long id){
         return studentService.getStudentById(id);
    }


    @CrossOrigin
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
        return studentService.deleteStudentById(id);
    }


    @CrossOrigin
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody Student newStudent, @PathVariable long id ){
        return studentService.updateStudentById(newStudent, id);
    }

}
