package com.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void 초기화() {
        postsRepository.deleteAll();
    }

    @Test
    public void 저장되는지() {

        //given
        Posts posts = Posts.builder()
                .title("a title")
                .content("a content")
                .author("author")
                .build();

        postsRepository.save(posts);

        //when
        List<Posts> all = postsRepository.findAll();

        //then
        assertEquals(all.get(0).getTitle(), "a title");
    }

    @Test
    public void baseTimeEntity_적용() {
        postsRepository.save(Posts.builder()
                .title("a title")
                .content("a content")
                .author("bssm")
                .build());

        List<Posts> all = postsRepository.findAll();

        System.out.println("create date : " + all.get(0).getCreatedDate());
        System.out.println("modify date : " + all.get(0).getModifiedDate());
    }
}