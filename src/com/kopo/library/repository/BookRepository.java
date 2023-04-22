package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Member;

import java.util.List;

public interface BookRepository {
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

    Book findByTitle(String title);

    Book restore(Book book);
}
