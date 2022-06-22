package com.example.studentservice.controller;

import com.example.studentservice.exceptions.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id) throws NoEntityException {
        return studentService.findStudentById(id);
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping("/updateStudent/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable Long id) throws NoEntityException {
        studentService.updateStudent(student);
    }

    @GetMapping("/average")
    public int getAgeAverage(){
        return studentService.getAgeAverage();
    }

    @GetMapping("/older")
    public int getOlderStudent(){
        return studentService.getOlderStudent();
    }

    @GetMapping("/younger")
    public int getYoungerStudent(){
        return studentService.getYoungerStudent();
    }

    @GetMapping("/data")
    public String getData(){
        return studentService.getData();
    }

    @GetMapping("/listOlder")
    public List<Student> getOlderStudents(){
        return studentService.getOlderStudents();
    }

    @GetMapping("/listYounger")
    public List<Student> getYoungerStudents(){
        return studentService.getYoungerStudents();
    }

    @GetMapping("/older18")
    public int getAverageOlder18(){
        return studentService.getAverageOlder18();
    }

    @GetMapping("/under18")
    public int getAverageUnder18(){
        return studentService.getAverageUnder18();
    }

}
