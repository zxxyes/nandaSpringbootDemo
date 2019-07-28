package com.example.demo.service;

import com.example.demo.exception.JokeNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {
    @Autowired
    private JokeRepository jokeRepository;

    public List<Joke> getAllJokes(){
        return jokeRepository.findAll();
    }

    public Joke getJokeById(int id) throws JokeNotFoundException {
            return jokeRepository.findById(id)
                    .orElseThrow(()->new JokeNotFoundException("Joke can not found in JokeRepository"));
    }

}
