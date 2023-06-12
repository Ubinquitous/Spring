package com.department.school.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveManyStudentRequestDto {
    private List<SaveStudentRequestDto> list;
}
