package com.example.todolist.service;

import com.example.todolist.controller.dto.TodoRequestDto;
import com.example.todolist.controller.dto.TodoResponseDto;
import com.example.todolist.domain.TodoEntity;
import com.example.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long save(TodoRequestDto requestDto) {
        return todoRepository.save(requestDto.toEntity())
                .getId();
    }

    public List<TodoResponseDto> findAll() {
        List<TodoEntity> result = todoRepository.findAll();
        List<TodoResponseDto> responseDto = new ArrayList<>();

        for (TodoEntity todoEntity : result) {
            responseDto.add(new TodoResponseDto(todoEntity));
        }

        return responseDto;
    }

    public TodoResponseDto findOne(Long id) {
        TodoEntity todoEntity = todoRepository
                .findById(id)
                .orElse(new TodoEntity());

        return new TodoResponseDto(todoEntity);
    }

    @Transactional
    public Long updateById(Long id, TodoRequestDto requestDto) {
        TodoEntity todoEntity = todoRepository
                .findById(id)
                .orElse(new TodoEntity());

        todoEntity.updateContent(requestDto.getContent());
        todoEntity.updateCompleted(requestDto.getCompleted());

        return todoEntity.getId();
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

    public void deleteOne(Long id) {
        todoRepository.deleteById(id);
    }
}
