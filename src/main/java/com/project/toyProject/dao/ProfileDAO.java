package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.ProfileVO;
import com.project.toyProject.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileDAO {
    private final ProfileMapper profileMapper;
    public void insertProfile(ProfileVO profileVO){
        profileMapper.insertProfile(profileVO);
    }
    public MemberProfileDTO selectLoginMemberProfile(Long id) {
        return profileMapper.selectMemberProfile(id);
    }
    public void updateProfile(String profileOneLineBio,Long memberId) {
        profileMapper.updateProfile(profileOneLineBio,memberId);
    }
}
