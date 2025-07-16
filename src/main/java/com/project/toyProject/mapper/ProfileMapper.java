package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Profile;

@Mapper
public interface ProfileMapper {
//    회원 기본 프로필 저장
    public void insertProfile(ProfileVO profileVO);
//    회원 프로필 조회
    public MemberProfileDTO selectMemberProfile(Long id);
//    회원 프로필 수정
    public void updateProfile(@Param("profileOneLineBio") String profileOneLineBio,@Param("id") Long memberId);
}
