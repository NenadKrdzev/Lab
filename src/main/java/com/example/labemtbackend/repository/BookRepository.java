package com.example.labemtbackend.repository;

import com.example.labemtbackend.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    void deleteByName(String name);
}
