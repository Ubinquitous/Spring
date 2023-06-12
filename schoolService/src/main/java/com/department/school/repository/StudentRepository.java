package com.department.school.repository;

import com.department.school.domain.Department;
import com.department.school.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.department = :department ORDER BY s.id DESC")
    List<Student> findByDepartment(Department department);
}
