package com.example.studentservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student student;
    @BeforeEach
    void setUp() {
        student = new Student(1L, "Alejandra", "Bravo", LocalDate.of(1992,9,22));
    }

    @Test
    void getId() {
        assertTrue(student.getId()== 1);
        assertEquals(1,student.getId());
    }

    @Test
    void getSurname() {
        assertEquals("Bravo",student.getSurname());
    }

    @Test
    void getName() {
        assertEquals("Alejandra",student.getName());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(1992,9,22),student.getBirthday());
    }

    @Test
    void setId() {
        student.setId(Long.parseLong("5"));
        assertEquals(5,student.getId());
    }

    @Test
    void setSurname() {
        student.setSurname("Bravo");
        assertEquals("Bravo",student.getSurname());
    }

    @Test
    void setName() {
        student.setName("Maria");
        assertEquals("Maria",student.getName());
    }

    @Test
    void setBirthday() {
        student.setBirthday(LocalDate.now());
        assertEquals(LocalDate.now(),student.getBirthday());
    }
}