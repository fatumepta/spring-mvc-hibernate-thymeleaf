package org.example.app.dao;

import org.example.app.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Book book) {
        Session em = this.sessionFactory.getCurrentSession();
        em.persist(book);
    }

    @Override
    public void update(Book book) {
        Session em = this.sessionFactory.getCurrentSession();
        em.merge(book);
    }

    @Override
    public void remove(long id) {
        Session em = this.sessionFactory.getCurrentSession();
        Book bookToBeRemoved = em.getReference(Book.class, id);

        if (bookToBeRemoved != null) {
            em.remove(bookToBeRemoved);
        }
    }

    @Override
    public Book getById(long id) {
        Session em = this.sessionFactory.getCurrentSession();
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        Session em = this.sessionFactory.getCurrentSession();
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
