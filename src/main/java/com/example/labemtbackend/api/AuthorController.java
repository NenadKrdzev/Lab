package com.example.labemtbackend.api;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listAll(){
        return authorService.listAll();
    }

    @PostMapping("/add")
    ResponseEntity<Author> save(@RequestParam String name,@RequestParam String surname, @RequestParam Long countryId){
        return this.authorService.save(name, surname,countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
