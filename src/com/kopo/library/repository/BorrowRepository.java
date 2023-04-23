package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Borrow;

import java.util.List;


public interface BorrowRepository {

    // 대출 가능한 도서 목록 조회
    List<Borrow> findAllBorrowAble();

    // 대출된 도서 목록 조회
    List<Borrow> findAllBorrow();


    // 도서 대출 기능(삽입)
    void insertBorrow(Borrow borrow);

    // 도서 반납 기능(삭제)
    void deleteBorrow(Borrow borrow);


    // 반납 기한 연장(수정)
    void updateBorrowExtends(Borrow borrow);



    // 삽입
    void insertBook(Borrow borrow);

    // 수정
    void updateBook(Borrow borrow);

    // 삭제
    void deleteBook(Borrow borrow);


    // 조회
    Borrow findById(Long id);
    Borrow findByTitle(String title);

}