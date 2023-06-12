package com.department.school.service;

import com.department.school.controller.dto.SaveDepartmentRequestDto;
import com.department.school.controller.dto.SaveManyDepartmentRequestDto;
import com.department.school.domain.Department;
import com.department.school.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Long save(SaveDepartmentRequestDto requestDto){
        Optional<Department> byName = departmentRepository.findByName(requestDto.getName());

        if(byName.isPresent()){
            throw new IllegalArgumentException("이미 등록된 학과입니다.");
        }

        return departmentRepository.save(requestDto.toEntity()).getId();
    }

    public String saveMany(SaveManyDepartmentRequestDto requestDto){
        for(SaveDepartmentRequestDto dto : requestDto.getList()){
            Optional<Department> byName = departmentRepository.findByName(dto.getName());

            if(byName.isPresent()){
                throw new IllegalArgumentException("이미 등록된 학과입니다.");
            }

            departmentRepository.save(dto.toEntity());
        }
        return "done!";
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department getById(Long id){
        return departmentRepository.findById(id).orElse(new Department());
    }
}
