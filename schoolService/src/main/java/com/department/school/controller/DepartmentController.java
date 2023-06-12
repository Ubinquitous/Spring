package com.department.school.controller;

import com.department.school.controller.dto.SaveDepartmentRequestDto;
import com.department.school.controller.dto.SaveManyDepartmentRequestDto;
import com.department.school.controller.dto.SaveManyStudentRequestDto;
import com.department.school.domain.Department;
import com.department.school.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/api/departments")
    public Long createDept(@RequestBody SaveDepartmentRequestDto requestDto) {
        return departmentService.save(requestDto);
    }

    @PostMapping("/api/departments/many")
    public String createManyDepartment(@RequestBody SaveManyDepartmentRequestDto requestDto){
        return departmentService.saveMany(requestDto);
    }

    @GetMapping("/api/departments")
    public List<Department> getAllDepartment(){
        return departmentService.getAll();
    }

    @GetMapping("/api/departments/{id}")
    public Department getDepartment(@PathVariable Long id){
        return departmentService.getById(id);
    }
}
