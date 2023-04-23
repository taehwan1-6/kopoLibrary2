package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Member;

import java.sql.PreparedStatement;
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

    /**
     * 대출 가능 여부 상태 변경 메소드
     * @param status(대출 가능 여부 true/false)
     * @param bookId(대출 가능 여부 상태 변경하려는 도서의 BOOK_ID)
     */
    void isPossibleBorrowChange(boolean status, Long bookId);
}
