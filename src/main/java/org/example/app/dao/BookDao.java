package org.example.app.dao;

import org.example.app.model.Book;

import java.util.List;


public interface BookDao {
    void add(Book book);

    void update(Book book);

    void remove(long id);

    Book getById(long id);

    List<Book> getAll();
}
