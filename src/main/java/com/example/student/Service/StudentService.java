package com.example.student.Service;


import com.example.student.dao.StudentRepository;
import com.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student){
        return studentRepository.save(student);
    }


    public List<Student> listStudents(){
        return studentRepository.findAll();
    }


    public Student getStudentById( Long id){
        return studentRepository.getOne(id);
    }


    public String deleteStudentById(Long id){
         studentRepository.deleteById(id);
         return "Student " + id + " is deleted";
    }


    public Student updateStudentById( Student newStudent,  long id ){
        Student studentToUpdate = studentRepository.getOne(id);
        studentToUpdate.setFirstname(newStudent.getFirstname());
        studentToUpdate.setSurname(newStudent.getSurname());
        studentRepository.save(studentToUpdate);
        return studentToUpdate;
    }

}
