package com.rasha.libraryManagement.member;

import com.rasha.libraryManagement.book.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void testCreateMember() {
       Member member = new Member();
        member.setFirstName("ali");
       member.setLastName("shirmohammadi");
       member.setNationalCode((long) 32424216);

       Member savedMember = memberRepository.save(member);

       Member existMember = entityManager.find(Member.class, savedMember.getId());

        assert (existMember.getFirstName()).equals(member.getFirstName());
        assert (existMember.getLastName()).equals(member.getLastName());
        assert (existMember.getNationalCode()).equals(member.getNationalCode());

    }

}