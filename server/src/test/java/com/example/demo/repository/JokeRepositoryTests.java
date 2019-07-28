package com.example.demo.repository;


import com.example.demo.model.Joke;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//mvn test -Dtest=JokeRepositoryTests


@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeRepositoryTests {
    @Autowired
    JokeRepository jokeRepository;

    Joke joke1,joke2;
    @Before
    public void setUp(){
        this.jokeRepository.deleteAll();
        joke1 = new Joke();
        joke1.setTitle("Joke1");
        joke1.setContent("1111");
        jokeRepository.save(joke1);
        joke2 = new Joke();
        joke2.setTitle("Joke2");
        joke2.setContent("2222");
        jokeRepository.save(joke2);
    }

    @Test
    public void getJokeList_Then_ReturnJokeList(){
        List<Joke> jokeList = this.jokeRepository.findAll();
        assertThat(jokeList.size()).isEqualTo(2);
        Joke joke = jokeList.get(0);
        assertThat(joke.getId()).isNotNull();
        assertThat(joke).extracting("content").contains("2222");
    }

    @After
    public void cleanJokes(){
        this.jokeRepository.deleteAll();
    }
}
