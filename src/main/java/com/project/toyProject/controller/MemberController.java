package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.service.member.MemberService;
import com.project.toyProject.validation.member.MemberJoinValidator;
import com.project.toyProject.validation.member.MemberLoginValidator;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/member/*")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberJoinValidator memberJoinValidator;
    private final MemberLoginValidator memberLoginValidator;
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "/member/join";
    }
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("memberVO") MemberVO memberVO, BindingResult bindingResult) {
        memberJoinValidator.validate(memberVO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/member/join";
        }
        memberService.join(memberVO);
        log.info(memberVO.toString());
        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberLoginDTO", new MemberLoginDTO());
        return "/member/login";
    }
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("memberLoginDTO") MemberLoginDTO memberLoginDTO,BindingResult bindingResult, HttpSession session,@RequestParam(defaultValue = "/post/list")String redirectURI) {

        memberLoginValidator.validate(memberLoginDTO, bindingResult);
        if (bindingResult.hasErrors()){
            return "/member/login";
        }
        Optional<MemberVO> loginMember = memberService.login(memberLoginDTO);

        if (loginMember !=null && loginMember.isPresent()) {
            session.setAttribute("sessionId", loginMember.get().getId());
            return "redirect:"+redirectURI;
        }
        bindingResult.reject("loginFailed","아이디 또는 비밀번호가 일치하지않습니다.");
        return "/member/login";
    }
    @GetMapping("/profile")
    public String profile(HttpSession session,Model model) {
        Long sessionId = (Long) session.getAttribute("sessionId");
        MemberProfileDTO memberProfile = memberService.findMemberProfileById(sessionId);
        log.info(memberProfile.toString());
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("filePath", "/profileImages/" + memberProfile.getFileOriginalName());
        model.addAttribute("memberId",sessionId);
        return "/member/profile";
    }
    @GetMapping("/profile/edit/{memberId}")
    public String editProfile(@PathVariable("memberId") Long memberId, Model model) {
        MemberProfileDTO memberProfile = memberService.findMemberProfileById(memberId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("filePath", "/profileImages/" + memberProfile.getFileOriginalName());
        return "/member/profileEdit";
    }
    @PostMapping("/profile/edit/{memberId}")
    public String editProfile(@PathVariable Long memberId,
                              @Validated @ModelAttribute MemberProfileDTO memberProfileDTO,
                              @RequestParam("profileImage") MultipartFile pfImage) {
        memberService.updateMemberProfile(memberProfileDTO,pfImage,memberId);
        return "redirect:/member/profile";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/member/duplicate")
    @ResponseBody
    public boolean checkId(@RequestParam String memberLoginId) {
        // false → 사용 가능 /  true→ 이미 존재
        return !memberService.duplicateMemberLoginId(memberLoginId);
    }
}
