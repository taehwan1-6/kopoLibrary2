package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Borrow;

import java.util.List;


public interface BorrowRepository<T> {

    // 대출 가능한 도서 목록 조회
    List<T> findAllBorrowAble();

    // 대출된 도서 목록 조회
    List<T> findAllBorrow();


    // 도서 대출 기능(삽입)
    void insertBorrow(Borrow borrow);

    // 도서 반납 기능(삭제)
    void deleteBorrow(Borrow borrow);


    // 반납 기한 연장(수정)
    void updateBorrowExtend(Borrow borrow);



    // 조회
    Borrow findById(Long id);
    Borrow findByTitle(String title);

    /**
     * bookId 검색 메소드
     * : borrowId를 이용해 대출된 도서를 반납한다. 그러면 isPossibleBorrow(대출가능여부상태)를 true로 바꾸어주어야
     *   하기 때문에, book에 접근하기 위해 bookId를 얻는 메소드를 만든다.
     * @param borrowId
     * @return bookId
     */
    Long findBookId(Long borrowId);

}
