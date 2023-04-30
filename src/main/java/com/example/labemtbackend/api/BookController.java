package com.example.labemtbackend.api;

import com.example.labemtbackend.models.Book;
import com.example.labemtbackend.models.Category;
import com.example.labemtbackend.models.dto.BookDto;
import com.example.labemtbackend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listAll() {
        return this.bookService.listAll();
    }

//    @PostMapping("/add")
//    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
//            return this.bookService.save(bookDto)
//                    .map(book -> ResponseEntity.ok().body(book))
//                    .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//@PostMapping("/add")
//public void save(@RequestBody BookDto bookDto) {
//    this.bookService.addBook(bookDto);
//}
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestParam String name, @RequestParam Category category,
                                     @RequestParam Long authorId, @RequestParam int availableCopies) {
        return this.bookService.save(name, category, authorId, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestParam String name, @RequestParam Category category,
                                     @RequestParam Long authorId, @RequestParam int availableCopies) {
        return this.bookService.edit(id, name, category, authorId, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Book> edit(@PathVariable Long id,@RequestBody BookDto bookDto){
//        return this.bookService.edit(id,bookDto)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(()->ResponseEntity.badRequest().build());
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent()) {
            return this.bookService.taken(id)
                    .map(book -> ResponseEntity.ok().body(book))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
