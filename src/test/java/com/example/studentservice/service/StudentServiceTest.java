package com.example.studentservice.service;

import com.example.studentservice.exceptions.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StudentServiceTest.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L,"Ariel","Ramirez", LocalDate.of(2009,5,21));
    }

    @Test
    void findAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertFalse(studentService.findAll().isEmpty());
    }

    @Test
    void findStudentbyId() throws NoEntityException {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        assertNotNull(studentService.findStudentById(1L));
    }

    @Test
    void addStudent() {
        Student newStudent = new Student(null, "Ale", "Bravo" , LocalDate.now());
        Student createdStudent = new Student(5L, "Caro", "Perez" , LocalDate.now());
        when(studentRepository.save(newStudent)).thenReturn(createdStudent);
        assertNotNull(studentService.addStudent(newStudent));
    }

    @Test
    void deleteStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(studentRepository).delete(student);
        studentRepository.delete(student);
        assertFalse(studentRepository.findById(student.getId()).isPresent());

    }

    @Test
    void updateStudent() throws NoEntityException {
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        Student updateStudent = studentService.updateStudent(student);
        assertNotNull(updateStudent);
        assertNotNull(updateStudent.getId());
    }

    @Test
    void getAgeAverage(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(17, studentService.getAgeAverage());
    }

    @Test
    void getOlderStudent(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(19, studentService.getOlderStudent());
    }

    @Test
    void getYoungerStudent(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(15, studentService.getYoungerStudent());
    }

    @Test
    void getData() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertNotNull(studentService.getData());
    }

    @Test
    void getAverageOlder18(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        studentList.add(new Student(3L,"Rodri","Perez", LocalDate.of(1990,1,15)));
        studentList.add(new Student(4L,"Luis","Reis", LocalDate.of(1992,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(27, studentService.getAverageOlder18());
    }

    @Test
    void getAverageUnder18(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Maria","Rodriguez", LocalDate.of(2002,9,1)));
        studentList.add(new Student(2L,"Andrea","Zabala", LocalDate.of(2007,1,15)));
        studentList.add(new Student(3L,"Rodri","Perez", LocalDate.of(2017,1,15)));
        studentList.add(new Student(4L,"Luis","Reis", LocalDate.of(2019,1,15)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(7, studentService.getAverageUnder18());
    }
}
