package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

//    회원가입
    public void insertMember(MemberVO memberVO);
//    회원 정보 조회 by memberLoginId
    public Optional<MemberVO> selectLoginMemberById(String memberLoginId);
//    회원 정보 조회 by Pk
    public Optional<MemberVO> selectLoginMember(Long id);
//    회원 정보 수정
    public void updateMember(MemberProfileDTO memberProfileDTO);
//    회원 아이디 중복 체크
    public MemberVO duplicateMember(String memberLoginId);
}
