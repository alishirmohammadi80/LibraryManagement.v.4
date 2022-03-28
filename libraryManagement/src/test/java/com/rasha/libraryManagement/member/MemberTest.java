package com.rasha.libraryManagement.member;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void setFirstName() {
        String Actual ="ali";
        Member member = new Member();
       member.setFirstName("ali");
        String Expected =member.getFirstName();
        assertEquals(Expected, Actual);
    }

    @Test
    void setLastName() {
        String Actual ="shirmohammadi";
        Member member = new Member();
        member.setLastName("shirmohammadi");
        String Expected =member.getLastName();
        assertEquals(Expected, Actual);
    }



    @Test
    void setNationalCode() {
        long Actual =324242164;
        Member member = new Member();
        member.setNationalCode((long) 324242164);
       long Expected =member.getNationalCode();
        assertEquals(Expected, Actual);

    }
}