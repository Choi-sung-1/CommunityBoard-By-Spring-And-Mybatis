package com.project.toyProject.controller;

import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Long sessionId = (Long)session.getAttribute("sessionId");
        if (sessionId != null) {
            MemberVO loginMember = memberService.findMemberById(sessionId);
            model.addAttribute("loginMember",loginMember);
//            성공시 포스트쪽으로 수정
            return "home";
        }
        return "home";
    }
}
