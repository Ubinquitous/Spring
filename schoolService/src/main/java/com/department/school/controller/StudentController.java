package com.department.school.controller;

import com.department.school.controller.dto.SaveManyStudentRequestDto;
import com.department.school.controller.dto.SaveStudentRequestDto;
import com.department.school.domain.Student;
import com.department.school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/api/students")
    public Long createStudent(@RequestBody SaveStudentRequestDto requestDto){
        return studentService.save(requestDto);
    }

    @PostMapping("/api/students/many")
    public String createManyStudent(@RequestBody SaveManyStudentRequestDto requestDto){
        return studentService.saveMany(requestDto);
    }

    @GetMapping("/api/students")
    public List<Student> getAllStudents(){
        return studentService.getAll();
    }

    @GetMapping("/api/students/{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.getById(id);
    }

    @GetMapping("/api/students/dept/{id}")
    public List<Student> getStudentByDept(@PathVariable Long id){
        return studentService.getByDept(id);
    }
}
