package com.kopo.library.service;

import com.kopo.library.domain.Book;

import java.util.List;

public interface BookService {
    // CRUD
    // 삽입
    void insertBook(Book book);

    // 수정
    void updateBook(Book book);

    // 삭제
    void deleteBook(Book book);


    // 조회
    List<Book> findAllBook();

    Book findById(Long id);

    Book findByName(String name);

    Book restore(Book book);
}
