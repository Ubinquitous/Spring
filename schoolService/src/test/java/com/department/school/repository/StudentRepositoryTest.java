package com.department.school.repository;

import com.department.school.domain.Department;
import com.department.school.domain.Gender;
import com.department.school.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void save(){
        Department department = Department.builder()
                .name("SW")
                .personnel(32L)
                .build();
        Student student = Student.builder()
                .id(1101L)
                .name("박우빈")
                .gender(Gender.MALE)
                .department(department)
                .build();
        departmentRepository.save(department);
        studentRepository.save(student);

        assertEquals(studentRepository.findAll().get(0).getId(), 1101L);
        assertEquals(departmentRepository.findAll().get(0).getName(), "SW");
    }
}