package com.example.labemtbackend.service.impl;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.models.Book;
import com.example.labemtbackend.models.Category;
import com.example.labemtbackend.models.dto.BookDto;
import com.example.labemtbackend.models.events.BookCreatedEvent;
import com.example.labemtbackend.models.exceptions.AuthorNotFoundException;
import com.example.labemtbackend.models.exceptions.BookNotFoundException;
import com.example.labemtbackend.repository.AuthorRepository;
import com.example.labemtbackend.repository.BookRepository;
import com.example.labemtbackend.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;


import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {
        Book book=new Book();
        Author author=this.authorRepository.findById(authorId).orElseThrow();
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void addBook(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));


        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author ,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        //this.refreshMaterializedView();

       // this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
       // return Optional.of(book);
        this.bookRepository.save(book);

    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies) {
        Book book=this.bookRepository.findById(id).orElseThrow();
        Author author=this.authorRepository.findById(authorId).orElseThrow();
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> editBook(Long id,BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());


        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> taken(Long id) {
        Book book=this.bookRepository.findById(id).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
     this.bookRepository.deleteById(id);
    }
}
