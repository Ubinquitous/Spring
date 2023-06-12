package com.example.last.controller;

import com.example.last.domain.dto.PostRequestDto;
import com.example.last.domain.dto.PostResponseDto;
import com.example.last.domain.dto.PostUpdateRequestDto;
import com.example.last.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("api/post")
    public List<PostResponseDto> findAll(){
        return postService.findAll();
    }

    @GetMapping("api/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping("api/post")
    public Long save(@RequestBody PostRequestDto dto){
        return postService.create(dto);
    }

    @PutMapping("api/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto dto){
        return postService.update(id, dto);
    }

    @DeleteMapping("api/post/{id}")
    public Long delete(@PathVariable Long id){
        return postService.delete(id);
    }
}
