package com.department.school.controller.dto;

import com.department.school.domain.Department;
import com.department.school.domain.Gender;
import com.department.school.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentRequestDto {
    private Long id;
    private String name;
    private Gender gender;
    private Long departmentId;

    public Student toEntity() {
        return Student.builder()
                .id(this.id)
                .name(this.name)
                .gender(this.gender)
                .build();
    }
}
