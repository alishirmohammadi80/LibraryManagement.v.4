package com.rasha.libraryManagement.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
  public Member addMember(Member member){
      return memberRepository.save(member);
  }
    public Member delitMember(Long id){
       memberRepository.deleteById(id);
        return null;
    }
    public Member editeMember(Member member){
        return memberRepository.save(member);

    }
    public List<Member> getAllMembers(){
        return (List<Member>) memberRepository.findAll();

    }
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).get();
    }

}



