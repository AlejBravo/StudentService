package com.example.studentservice.service;

import com.example.studentservice.exceptions.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) throws NoEntityException {
        return studentRepository.findById(id).orElseThrow(() -> new NoEntityException("Student doesn't exist" + id));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) throws NoEntityException {
        Student studentOld = studentRepository.findById(student.getId()).orElseThrow(
                () -> new NoEntityException("No existe Student con " + student.getId()));
        studentOld.setSurname(student.getSurname());
        studentOld.setName(student.getName());
        studentOld.setBirthday(student.getBirthday());
        return studentRepository.save(studentOld);
    }

    public int getAgeAverage() {
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears()).average().orElseThrow();
    }

    public int getOlderStudent() {
        List<Student> student = studentRepository.findAll();
        return studentRepository.findAll().stream()
                .mapToInt(s-> Period.between(s.getBirthday(), LocalDate.now()).getYears()).max().orElse(0);
    }

    public int getYoungerStudent() {
        List<Student> student = studentRepository.findAll();
        return studentRepository.findAll().stream()
                .mapToInt(s-> Period.between(s.getBirthday(), LocalDate.now()).getYears()).min().orElse(0);
    }
    public String getData() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(s->s.getId() + " "+ s.getSurname() + " " + s.getName())
                .collect(Collectors.joining(" - "));
    }

    public List<Student> getOlderStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter( s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()>= 18)
                .collect(Collectors.toList());
    }

    public List<Student> getYoungerStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter( s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()< 18)
                .collect(Collectors.toList());
    }

    public int getAverageOlder18() {
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .mapToInt(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears())
                .filter(s -> s >= 18)
                .average().orElseThrow();
    }

    public int getAverageUnder18() {
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .mapToInt(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears())
                .filter(s -> s < 18)
                .average().orElseThrow();
    }
}

