package com.rasha.libraryManagement.member;

import com.lowagie.text.DocumentException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;
    @GetMapping("/member")
    public String listMember (Model model){
        model.addAttribute("member",memberService.getAllMembers());
        return "member";
    }

    @GetMapping("/member/new")
    public String addMemberForm(Model model){
      Member member=new Member();
        model.addAttribute("member",member);
        return "add_member";

    }
    @PostMapping("/member")
    public String addMember( @ModelAttribute("member") Member member){
        memberService.addMember(member);
        return "redirect:/member";
    }
    @GetMapping("/member/edit/{id}")
    public String editMemberForm(@PathVariable Long id , Model model){
        model.addAttribute("member",memberService.getMemberById(id));
        return "edite_member";
    }
    @PostMapping("/member/{id}")
    public String editMember(@PathVariable Long id, @ModelAttribute("member")Member member){
        Member editemember=memberService.getMemberById(id);
       editemember.setId(id);
       editemember.setFirstName(member.getFirstName());
       editemember.setLastName(member.getLastName());
        editemember.setNationalCode(member.getNationalCode());
        editemember.setDateOfBirth(member.getDateOfBirth());
       memberService.editeMember(editemember);
        return "redirect:/member";

    }
    @GetMapping("/member/{id}")
    public String delitMember( @PathVariable Long id){
       memberService.delitMember(id);
        return "redirect:/member";
    }
@Autowired
MemberService service;

    @GetMapping("/member/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Members_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Member> listMembers = service.getAllMembers();

        MemberFDFExporter exporter = new MemberFDFExporter(listMembers);
        exporter.export(response);

    }

}
