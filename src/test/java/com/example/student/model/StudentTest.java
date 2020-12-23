package com.example.student.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StudentTest {

    @Test
    void getId() {
        Student student = new Student();
        student.setId(123L);
        Assertions.assertEquals(123L, student.getId());
    }

    @Test
    void getFirstname() {
        Student student = new Student();
        student.setFirstname("Jack");
        Assertions.assertEquals("Jack", student.getFirstname());
    }

    @Test
    void getSurname() {
        Student student = new Student();
        student.setSurname("Murphy");
        Assertions.assertEquals("Murphy", student.getSurname());
    }

    @Test
    void setId() {
        Student student = new Student();
        student.setId(456L);
        Assertions.assertEquals(456L, student.getId());
    }

    @Test
    void setFirstname() {
        Student student = new Student();
        student.setFirstname("Anne");
        Assertions.assertEquals("Anne", student.getFirstname());
    }

    @Test
    void setSurname() {
        Student student = new Student();
        student.setSurname("Kennedy");
        Assertions.assertEquals("Kennedy", student.getSurname());
    }
}