package org.example.app.controller;

import org.example.app.model.Book;
import org.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBook", this.bookService.getAll());
        return "books";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        if (book.getId() == 0) {
            this.bookService.add(book);
        } else {
            this.bookService.update(book);
        }
        return "redirect:/books";
    }

    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable long id) {
        this.bookService.remove(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String updateBook(@PathVariable long id, Model model) {
        model.addAttribute("book", this.bookService.getById(id));
        model.addAttribute("listBook", this.bookService.getAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String bookData(@PathVariable long id, Model model) {
        model.addAttribute("book", this.bookService.getById(id));
        return "book";
    }
}
