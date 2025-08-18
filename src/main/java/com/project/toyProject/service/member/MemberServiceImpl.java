package com.project.toyProject.service.member;

import com.project.toyProject.dao.FileDAO;
import com.project.toyProject.dao.MemberDAO;
import com.project.toyProject.dao.ProfileDAO;
import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import com.project.toyProject.domain.dto.member.MemberProfileDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.domain.vo.ProfileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final ProfileDAO profileDAO;
    private final FileDAO fileDAO;
    @Override
    public void join(MemberVO memberVO) {


        memberDAO.insertMember(memberVO);
        Long foundPK = memberDAO.selectLoginMemberPk(memberVO.getMemberLoginId(),memberVO.getMemberPassword());
        initProfile(foundPK);
    }

    @Override
    public Optional<MemberVO> login(MemberLoginDTO memberLoginDTO) {

        Long foundPK = memberDAO.selectLoginMemberPk(memberLoginDTO.getMemberLoginId(),memberLoginDTO.getMemberPassword());
        if(foundPK != null){
//            여기서 찾아온 pk로 memberVO 객체 받아온다음 리턴
            return memberDAO.selectLoginMember(foundPK);
        }
        return Optional.empty();
    }

    @Override
    public MemberVO findMemberById(Long id) {
        return memberDAO.selectLoginMember(id).orElse(null);
    }

    @Override
    public MemberProfileDTO findMemberProfileById(Long id) {
        return profileDAO.selectLoginMemberProfile(id);
    }

    @Override
    public void updateMemberProfile(@ModelAttribute MemberProfileDTO memberProfileDTO, MultipartFile file, Long memberId) {
        profileDAO.updateProfile(memberProfileDTO.getProfileOneLineBio(),memberId);
        if (file != null && !file.isEmpty()) {
            fileDAO.updateFile(updateFilePath(memberId, file));
        }
        memberProfileDTO.setId(memberId);
        memberDAO.updateMember(memberProfileDTO);
    }

    @Override
    public Boolean duplicateMemberLoginId(String memberLoginId) {
       MemberVO memberVO =  memberDAO.duplicateMember(memberLoginId);
       if (memberVO != null) {
           return true;
       }
        return false;
    }

    //    기본 프로필 생성 함수
    public void initProfile(Long memberId){
//        기본 프로필 한줄소개 설정
        ProfileVO profileVO = new ProfileVO();
        profileVO.setProfileOneLineBio("");
        profileVO.setMemberId(memberId);

//         기본 프로필 이미지 설정
        String filePath = "/Users/ChoiSungWon/Documents/Develope/spring/file/profile";
        File dir = new File(filePath);
        File imageFile = new File("/Users/ChoiSungWon/Documents/Develope/spring/file/default/DefaultImage.jpg");
        if (!dir.exists()){
            dir.mkdirs();
        }
//        default에 있는 이미지를 기본 이미지로 insert
        File targetFile = new File(dir, imageFile.getName());
        try {
            // 이미지 복사
            Files.copy(imageFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("이미지가 성공적으로 복사되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileVO fileVO = new FileVO();
        fileVO.setFileOriginalName("DefaultImage.jpg");
        String savedName = UUID.randomUUID() + "_" + "DefaultImage.jpg";
        fileVO.setFileSavedName(savedName);
        fileVO.setFilePath(savedName); // <== 경로 말고 파일명만 저장
        fileVO.setFileType("PROFILE");
        fileVO.setReferenceId(memberId);

        fileDAO.insertFile(fileVO);
        profileDAO.insertProfile(profileVO);
    }

//    파일 업데이트 함수
    public FileVO updateFilePath(Long referenceId,MultipartFile file){
        FileVO fileVO = new FileVO();
        fileVO.setReferenceId(referenceId);
//        fileVO.setFileType("PROFILE");
        fileVO.setFileOriginalName(file.getOriginalFilename());
        fileVO.setFileSavedName(UUID.randomUUID()+"_"+file.getOriginalFilename());
        fileVO.setFilePath("/Users/ChoiSungWon/Documents/Develope/spring/file/profile/"+file.getOriginalFilename());
        try{
            file.transferTo(new File(fileVO.getFilePath()));
        }catch (IOException e){
            throw new RuntimeException("파일 저장 실패.",e);
        }
        return fileVO;
    }
}
