package com.example.studentservice.repository;

import com.example.studentservice.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void findById(){
        assertTrue(studentRepository.findById(1L).isPresent());
    }

    @Test
    void findAll(){
        List<Student> list = studentRepository.findAll();

        assertFalse(list.isEmpty());
        assertTrue(list.size()>0);
    }

    @Test
    void create(){
        Student student = new Student(1L, "Bravo", "Alejandra", LocalDate.of(2009,5,21));
        System.out.println(student);
        Student studentSave = studentRepository.save(student);
        System.out.println(studentSave);
        assertTrue(studentSave.getId()>0);
    }
}