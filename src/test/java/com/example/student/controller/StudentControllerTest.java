package com.example.student.controller;

import com.example.student.Service.StudentService;
import com.example.student.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;



    @Test
    void createStudent() throws Exception {
        when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(new Student(1L, "Mark", "Carroll"));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v2/student")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"firstname\":\"Mark\", \"surname\":\"Carroll\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1,firstname:Mark,surname:Carroll}"));
    }

    @Test
    void listStudents() throws Exception {
        when(studentService.listStudents()).thenReturn(
                Arrays.asList(new Student(1L, "Mark", "Carroll"),
                        new Student(2L, "John", "Murphy")));

        RequestBuilder request = get("/api/v2/student")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1,firstname:Mark,surname:Carroll}, {id:2,firstname:John,surname:Murphy}]"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Mark")))
                .andExpect(jsonPath("$[0].surname", is("Carroll")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstname", is("John")))
                .andExpect(jsonPath("$[1].surname", is("Murphy")));
    }

    @Test
    void getStudentById() throws Exception {
        when(studentService.getStudentById(2L)).thenReturn(new Student(2L, "John", "Murphy"));

        RequestBuilder request = get("/api/v2/student/{id}",2L)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:2,firstname:John,surname:Murphy}"))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.surname", is("Murphy")));
    }


    @Test
    void deleteStudentById() throws Exception{
        when(studentService.deleteStudentById(2L)).thenReturn("DELETED");

        RequestBuilder request = delete("/api/v2/student/{id}", 2L)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("DELETED"));
    }

    @Test
    void updateStudentById() throws Exception {

        Student currentStudent = new Student(1L, "Mark", "Carroll");
        Student updatedStudent = new Student(1L, "Liam", "Carroll");

        when(studentService.updateStudentById(currentStudent, 1L)).thenReturn(updatedStudent);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/v2/student/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"firstname\":\"Liam\", \"surname\":\"Carroll\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}