package com.kopo.library.service;

import com.kopo.library.domain.Book;
import com.kopo.library.repository.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void insertBook(Book book) {
        bookRepository.insertBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.deleteBook(book);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAllBook();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
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
