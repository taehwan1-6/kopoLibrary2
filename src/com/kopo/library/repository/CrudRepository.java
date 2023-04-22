package com.kopo.library.repository;


import java.util.List;
import java.util.Objects;

public interface CrudRepository<T> {

    // CRUD
    // 삽입
    void insertObjects();

    // 수정
    void updateObjects(Objects objects);

    // 삭제
    void deleteObjects(Objects objects);


    // 조회
    List<T> findAllObjects();

    Objects findById(Long id);

    Objects findByTitle(String title);

    Objects restore(Objects objects);
}
