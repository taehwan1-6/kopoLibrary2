package com.kopo.library.service;

import com.kopo.library.domain.Member;
import com.kopo.library.repository.MemberRepoDbImpl;
import com.kopo.library.repository.MemberRepository;

import java.sql.Connection;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void insertMember(Member member) {

    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void deleteMember(Member member) {

    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAllMember();
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public Member restore(Member member) {
        return null;
    }
}
