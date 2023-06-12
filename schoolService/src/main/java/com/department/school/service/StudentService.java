package com.department.school.service;

import com.department.school.controller.dto.SaveManyStudentRequestDto;
import com.department.school.controller.dto.SaveStudentRequestDto;
import com.department.school.domain.Department;
import com.department.school.domain.Student;
import com.department.school.repository.DepartmentRepository;
import com.department.school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student getById(Long id){
        return studentRepository.findById(id).orElse(new Student());
    }

    public List<Student> getByDept(Long id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 학과가 존재하지 않습니다."));

        return studentRepository.findByDepartment(department);
    }

    public String saveMany(SaveManyStudentRequestDto requestDto){
        for(SaveStudentRequestDto dto : requestDto.getList()){
            Student student = dto.toEntity();
            Optional<Department> byId = departmentRepository.findById(dto.getDepartmentId());
            Optional<Student> byStudentId = studentRepository.findById(dto.getId());

            if(byId.isEmpty()){
                throw new IllegalArgumentException("존재하지 않는 학과입니다.");
            }

            if(byStudentId.isPresent()){
                throw new IllegalArgumentException("이미 등록된 학생입니다.");
            }

            Department department = byId.get();

            changeableDept(department);

            student.chooseDept(department);

            studentRepository.save(student);
        }
        return "done!";
    }

    public Long save(SaveStudentRequestDto requestDto) {
        Student student = requestDto.toEntity();
        Optional<Department> byId = departmentRepository.findById(requestDto.getDepartmentId());
        Optional<Student> byStudentId = studentRepository.findById(requestDto.getId());

        if(byId.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 학과입니다.");
        }

        if(byStudentId.isPresent()){
            throw new IllegalArgumentException("이미 등록된 학생입니다.");
        }

        Department department = byId.get();

        changeableDept(department);

        student.chooseDept(department);

        return studentRepository.save(student).getId();
    }

    private void changeableDept(Department department) {
        department.getPersonnel();

        if(department.getPersonnel() <= studentRepository.findByDepartment(department).size()){
            throw new IllegalArgumentException("학과 정원이 초과되었습니다.");
        }
    }
}
