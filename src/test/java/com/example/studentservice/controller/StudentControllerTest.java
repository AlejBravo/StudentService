package com.example.studentservice.controller;

import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class StudentControllerTest {
    @MockBean
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    private MockMvc mockMvc;

    List<Student> studentList;
    Student student;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        student = new Student(1L , "Alejandra" , "Bravo" , LocalDate.of(1992,9,22));
        studentList = new ArrayList<>();
        studentList.add( new Student(1L, "Maria", "Rodriguez", LocalDate.of(1960,12,24)));
        studentList.add(new Student(2L, "Caro", "Velez", LocalDate.of(1921,11,11)));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() {
    }

    @Test
    void findStudentById() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void getAgeAverage() {
    }

    @Test
    void getOlderStudents() {
    }

    @Test
    void getYoungerStudents() {
    }

    @Test
    void getAverageOlder18() {
    }

    @Test
    void getAverageUnder18() {
    }
}