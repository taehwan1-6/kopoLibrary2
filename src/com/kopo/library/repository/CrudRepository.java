package com.kopo.library.repository;

import com.kopo.library.domain.Objects;

import java.util.List;
import java.util.Objects;

public interface CrudRepository {
    // CRUD
    // 삽입
    void insertObjects(Objects objects);

    // 수정
    void updateObjects(Objects objects);

    // 삭제
    void deleteObjects(Objects objects);


    // 조회
    List<Objects> findAllObjects();

    Objects findById(Long id);

    Objects findByTitle(String title);

    Objects restore(Objects objects);
}
