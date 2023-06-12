package com.example.todolist.controller.dto;

import com.example.todolist.domain.TodoEntity;
import lombok.Getter;

@Getter
public class TodoResponseDto {

    private Long id;
    private String content;
    private Boolean completed;

    public TodoResponseDto(TodoEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.completed = entity.getCompleted();
    }
}
