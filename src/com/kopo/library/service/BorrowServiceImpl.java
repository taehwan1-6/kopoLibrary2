package com.kopo.library.service;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Borrow;
import com.kopo.library.repository.BookRepository;
import com.kopo.library.repository.BorrowRepository;

import java.util.List;

public class BorrowServiceImpl implements BorrowService{

    private BorrowRepository borrowRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public List<Book> findAllBorrowAble() {
        return borrowRepository.findAllBorrowAble();
    }

    @Override
    public List<Borrow> findAllBorrow() {
        borrowRepository.findAllBorrow();
        return null;
    }

    @Override
    public void insertBorrow(Borrow borrow) {
        borrowRepository.insertBorrow(borrow);
    }

    @Override
    public void deleteBorrow(Borrow borrow) {

    }

    @Override
    public void updateBorrowExtend(Borrow borrow) {

    }

    @Override
    public Borrow findById(Long id) {
        return borrowRepository.findById(id);
    }

    @Override
    public Borrow findByTitle(String title) {
        return null;
    }
}
