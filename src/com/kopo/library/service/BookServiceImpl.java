package com.kopo.library.service;

import com.kopo.library.domain.Book;
import com.kopo.library.repository.CrudRepository;
import com.kopo.library.repository.MemberRepository;

import java.util.List;
import java.util.Objects;

public class BookServiceImpl implements CrudService{

    private CrudRepository bookRepository;

    public BookServiceImpl(CrudRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void insertObjects(Objects objects) {

    }

    @Override
    public void updateObjects(Objects objects) {

    }

    @Override
    public void deleteObjects(Objects objects) {

    }

    @Override
    public List<Book> findAllObjects() {
        return bookRepository.findAllObjects();
    }

    @Override
    public Objects findById(Long id) {
        return null;
    }

    @Override
    public Objects findByTitle(String title) {
        return null;
    }

    @Override
    public Objects restore(Objects objects) {
        return null;
    }
}
