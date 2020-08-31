package org.example.app.util;

import org.example.app.model.Book;
import org.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Component
public class TestDataCreator {
    private final BookService bookService;

    @Autowired
    public TestDataCreator(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void insertTestData() {
        createTestData().forEach(this.bookService::add);
    }

    private List<Book> createTestData() {
        Book b1 = new Book();
        b1.setTitle("The Tomorrow War");
        b1.setAuthor("Alexander Zorich");
        b1.setPrice(800.50);

        Book b2 = new Book();
        b2.setTitle("Without Mercy");
        b2.setAuthor("Alexander Zorich");
        b2.setPrice(1000);

        Book b3 = new Book();
        b3.setTitle("The Lord of the Rings");
        b3.setAuthor("John Ronald Reuel Tolkien");
        b3.setPrice(2500);

        Book b4 = new Book();
        b4.setTitle("Roadside Picnic");
        b4.setAuthor("The brothers Arkady Natanovich Strugatsky");
        b4.setPrice(180);

        return Arrays.asList(b1, b2, b3, b4);
    }
}
