package com.example.labemtbackend.service;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.models.Book;
import com.example.labemtbackend.models.Category;
import com.example.labemtbackend.models.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> listAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(String name, Category category , Long authorId ,int availableCopies);
    void addBook(BookDto bookDto);
    Optional<Book> edit(Long id,String name, Category category , Long authorId ,int availableCopies);
    Optional<Book> editBook(Long id,BookDto bookDto);
    Optional<Book> taken(Long id);
    void deleteById(Long id);
}
