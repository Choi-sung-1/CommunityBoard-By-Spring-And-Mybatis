package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void insertMember(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }
    public Optional<MemberVO> selectLoginMemberById(String memberLoginId) {
        return memberMapper.selectLoginMemberById(memberLoginId);
    }
    public Optional<MemberVO> selectLoginMember(Long id) {
        return memberMapper.selectLoginMember(id);
    }
    public void updateMember(MemberProfileDTO memberProfileDTO) {
        memberMapper.updateMember(memberProfileDTO);
    }
    public MemberVO duplicateMember(String memberLoginId){
        return memberMapper.duplicateMember(memberLoginId);
    }
}
