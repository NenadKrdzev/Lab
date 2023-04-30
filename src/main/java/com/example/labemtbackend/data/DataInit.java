package com.example.labemtbackend.data;

import com.example.labemtbackend.models.Category;
import com.example.labemtbackend.service.AuthorService;
import com.example.labemtbackend.service.BookService;
import com.example.labemtbackend.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    private final CountryService countryService;
    private final BookService bookService;
    private final AuthorService authorService;

    public DataInit(CountryService countryService,BookService bookService,AuthorService authorService) {
        this.countryService = countryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void initData(){


        this.countryService.save("Macedonia","Europe");
        this.countryService.save("Thailand","Asia");
        this.countryService.save("South Africa","Africa");
        this.countryService.save("Croatia","Europe");
        this.countryService.save("Italy","Europe");
        this.countryService.save("New Zealand","Australia");
        this.authorService.save("Igor","Dzambazov", 1L);
        this.bookService.save("Mjaukedonija", Category.NOVEL,1L,25);
        this.bookService.save("Kniga1", Category.NOVEL,1L,22);
        this.bookService.save("kniga2", Category.NOVEL,1L,20);
    }

}