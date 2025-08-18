package com.project.toyProject.service.member;

import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    로그인
    public Optional<MemberVO> login(MemberLoginDTO memberLoginDTO);
//    회원 조회
    public MemberVO findMemberById(Long id);
//    회원 프로필 조회
    public MemberProfileDTO findMemberProfileById(Long id);
//    회원 프로필 수정
    public void updateMemberProfile(MemberProfileDTO memberProfileDTO, MultipartFile file,Long memberId);
//    회원 아이디 중복체크
    public Boolean duplicateMemberLoginId(String memberLoginId);
}
