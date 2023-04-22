package com.kopo.library.service;

import com.kopo.library.domain.Book;

import java.util.List;

public class BookServiceImpl implements BookService{

    BookService bookService;

    public BookServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void insertBook(Book book) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public List<Book> findAllBook() {
        return null;
    }

    @Override
    public Book findById(Long id) {
        return bookService.findAllBook();
    }

    @Override
    public Book findByName(String name) {
        return null;
    }

    @Override
    public Book restore(Book book) {
        return null;
    }
}
