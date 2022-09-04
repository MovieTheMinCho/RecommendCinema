package com.example.mre2.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  @After
  public void cleanUp(){
    postsRepository.deleteAll();
  }

  @Test
  public void testLoad(){
    String title = "title";
    String content = "contest";

    postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("testAuthor")
        .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }

  @Test
  public void testBaseTimeEntityRegister(){
    LocalDateTime now = LocalDateTime.of(2022,6,29,0,0,0);
    postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);
    System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

    assertThat(posts.getCreatedDate()).isAfter(now);
    assertThat(posts.getModifiedDate()).isAfter(now);
  }
}