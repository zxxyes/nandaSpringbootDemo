package com.example.demo.service;

import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTests {

    @Autowired
    JokeService jokeService;

    @MockBean
    JokeRepository repository;

    @Before
    public void setUp(){
        Joke joke = new Joke("Joke1");
        joke.setId(11);
        Joke joke1 = new Joke("Joke2");
        joke1.setId(22);
        List<Joke> allJokes = Arrays.asList(joke, joke1);

        Mockito.when(repository.findById(joke.getId())).thenReturn(Optional.of(joke));
        Mockito.when(repository.findById(joke1.getId())).thenReturn(Optional.of(joke1));

        Mockito.when(repository.findById(0)).thenReturn(null);

        Mockito.when(repository.save(new Joke("Joke2"))).thenReturn(joke1);

        Mockito.when(repository.findAll()).thenReturn(allJokes);
    }
    @Test
    public void given2Jokes_when_getAllJokes_thenReturn2Records() {
        Joke joke = new Joke("Joke1");
        Joke joke1 = new Joke("Joke2");
        List<Joke> allJokes = this.jokeService.getAllJokes();

        assertThat(allJokes).hasSize(2)
                .extracting(Joke::getTitle)
                .contains(joke.getTitle(),joke1.getTitle());
    }

    @Test
    public void givenRightJokeId_thenReturnJoke(){
        Joke joke = this.jokeService.getJokeById(11);
        assertThat(joke).extracting("title").contains("Joke1");
    }
}
