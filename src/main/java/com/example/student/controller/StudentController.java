package com.example.student.controller;

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
    private StudentRepository studentRepository;


    @CrossOrigin
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }


    @CrossOrigin
    @GetMapping
    public Page<Student> listStudets(Pageable pageable){
        return studentRepository.findAll(pageable);
    }


    @CrossOrigin
    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable Long id){
         return studentRepository.getOne(id);
    }


    @CrossOrigin
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElse( null);
    }


    @CrossOrigin
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody Student newStudent, @PathVariable long id ){
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstname(newStudent.getFirstname());
                    student.setSurname(newStudent.getSurname());
                    studentRepository.save(student);
                    return ResponseEntity.ok(student);
                }).orElse( null);
    }

}
