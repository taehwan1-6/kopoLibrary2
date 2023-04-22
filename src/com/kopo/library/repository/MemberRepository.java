package com.kopo.library.repository;

import com.kopo.library.domain.Member;

import java.util.List;

public interface MemberRepository {
    // CRUD
    // 삽입
    void insertMember(Member member);

    // 수정
    void updateMember(Member member);

    // 삭제
    void deleteMember(Member member);


    // 조회
    List<Member> findAllMember();

    Member findById(Long id);

    Member findByName(String name);

    Member restore(Member member);
}
