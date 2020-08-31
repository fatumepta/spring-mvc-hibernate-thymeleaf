package org.example.app.service;

import org.example.app.dao.BookDao;
import org.example.app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void add(Book book) {
        this.bookDao.add(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        this.bookDao.update(book);
    }

    @Override
    @Transactional
    public void remove(long id) {
        this.bookDao.remove(id);
    }

    @Override
    @Transactional
    public Book getById(long id) {
        return this.bookDao.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return this.bookDao.getAll();
    }
}
