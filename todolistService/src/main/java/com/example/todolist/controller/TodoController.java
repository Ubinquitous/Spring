package com.example.todolist.controller;

import com.example.todolist.controller.dto.TodoRequestDto;
import com.example.todolist.controller.dto.TodoResponseDto;
import com.example.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo/api")
    public Long create(@RequestBody TodoRequestDto requestDto) {
        return todoService.save(requestDto);
    }

    @GetMapping("/todo/api")
    public List<TodoResponseDto> readAll() {
        return todoService.findAll();
    }

    @GetMapping("/todo/api/{id}")
    public TodoResponseDto readOne(@PathVariable Long id) {
        return todoService.findOne(id);
    }

    @PutMapping("/todo/api/{id}")
    public Long update(@RequestBody TodoRequestDto requestDto,
                       @PathVariable Long id) {
        return todoService.updateById(id, requestDto);
    }

    @DeleteMapping("/todo/api")
    public void deleteAll() {
        todoService.deleteAll();
    }

    @DeleteMapping("/todo/api/{id}")
    public void deleteOne(@PathVariable Long id) {
        todoService.deleteOne(id);
    }
}
