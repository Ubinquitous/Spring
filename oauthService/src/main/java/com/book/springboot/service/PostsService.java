package com.book.springboot.service;

import com.book.springboot.domain.posts.Posts;
import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.web.dto.PostsListResponseDto;
import com.book.springboot.web.dto.PostsResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import com.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        return new PostsResponseDto(post);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return posts.getId();
    }

    public List<PostsListResponseDto> findAllDesc() {
        List<Posts> allDesc = postsRepository.findAllDesc();
        List<PostsListResponseDto> result = new ArrayList<>();

        for (Posts posts : allDesc) {
            result.add(new PostsListResponseDto(posts));
        }

        return result;
    }

    public Long delete(Long id) {
        postsRepository.deleteById(id);

        return id;
    }
}
