package com.example.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column(nullable = false)
    private Boolean completed;

    @Builder
    public TodoEntity(String content, Boolean completed) {
        this.content = content;
        this.completed = completed;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateCompleted(Boolean completed) {
        this.completed = completed;
    }
}
