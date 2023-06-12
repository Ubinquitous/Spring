package com.example.last.service;

import com.example.last.domain.Post;
import com.example.last.domain.PostRepository;
import com.example.last.domain.dto.PostRequestDto;
import com.example.last.domain.dto.PostResponseDto;
import com.example.last.domain.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponseDto> findAll(){
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> result = new ArrayList<>();

        for(Post post : posts){
            result.add(new PostResponseDto(post));
        }

        return result;
    }

    public PostResponseDto findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없다"));
        return new PostResponseDto(post);
    }

    @Transactional
    public Long create(PostRequestDto dto){
        Post post = Post.builder().
                title(dto.getTitle()).
                contents(dto.getContents()).
                author(dto.getAuthor()).
                build();
        return postRepository.save(post).getId();
    }

    @Transactional
    public Long delete(Long id){
        postRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto dto){
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("없다"));

        post.update(dto.getTitle(), dto.getContents());
        return post.getId();
    }
}
