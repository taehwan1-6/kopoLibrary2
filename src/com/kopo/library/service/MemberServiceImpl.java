package com.kopo.library.service;

import com.kopo.library.domain.Member;
import com.kopo.library.repository.MemberRepository;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void insertMember(Member member) {
        memberRepository.insertMember(member);
    }

    @Override
    public void updateMember(Member member) {
        memberRepository.updateMember(member);
    }

    @Override
    public void deleteMember(Member member) {
        memberRepository.deleteMember(member);
    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAllMember();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public Member restore(Member member) {
        return memberRepository.restore(member);
    }

}
