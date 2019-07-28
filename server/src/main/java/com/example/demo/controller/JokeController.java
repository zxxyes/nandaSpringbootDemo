package com.example.demo.controller;

import java.util.List;

import com.example.demo.exception.JokeNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import com.example.demo.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class JokeController {
    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private JokeService jokeService;

    // Get All Jokes
    @GetMapping("/jokes")
    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    // Create a new Joke
    @PostMapping("/jokes")
    public List<Joke> createJoke(@Valid @RequestBody Joke joke) {
        jokeRepository.save(joke);
        return jokeRepository.findAll();
    }

    // Get a Single Joke
    @GetMapping("/jokes/{id}")
    public Joke getJokeById(@PathVariable(value = "id") Integer jokeId) {
        try{
            return jokeService.getJokeById(jokeId);
        }catch(JokeNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Joke Not Found",e);
        }
        //return jokeRepository.findById(jokeId).orElseThrow(() -> new ResourceNotFoundException("Joke", "Id", jokeId));
    }

    // Update a Joke
    @PutMapping("/jokes/{id}")
    public Joke updateJoke(@PathVariable(value = "id") Integer jokeId, @Valid @RequestBody Joke newJoke) {
        Joke joke = jokeRepository.findById(jokeId).orElseThrow(() -> new ResourceNotFoundException("Joke", "Id", jokeId));

        joke.setTitle(newJoke.getTitle());
        joke.setContent(newJoke.getContent());

        Joke updatedJoke = jokeRepository.save(joke);
        return updatedJoke;
    }


    // Delete a Joke
    @DeleteMapping("/jokes/{id}")
    public List<Joke> deleteJoke(@PathVariable(value = "id") Integer jokeId) {
        Joke joke = jokeRepository.findById(jokeId).orElseThrow(() -> new ResourceNotFoundException("Joke", "Id", jokeId));
        jokeRepository.delete(joke);
        return jokeRepository.findAll();
    }

}
