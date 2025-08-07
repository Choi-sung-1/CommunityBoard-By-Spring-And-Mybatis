package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.dto.post.PostEditDTO;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.dto.post.PostSearchDTO;
import com.project.toyProject.domain.vo.MemberVO;
import com.project.toyProject.domain.vo.PostLikeStatusVO;
import com.project.toyProject.domain.vo.PostVO;
import com.project.toyProject.service.comment.CommentServiceImpl;
import com.project.toyProject.service.member.MemberService;
import com.project.toyProject.service.post.PostLikeStatusServiceImpl;
import com.project.toyProject.service.post.PostServiceImpl;
import com.project.toyProject.validation.post.PostEditAndWriteValidator;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post/*")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final MemberService memberService;
    private final PostServiceImpl postService;
    private final PostLikeStatusServiceImpl postLikeStatusService;
    private final CommentServiceImpl commentService;
    private final PostEditAndWriteValidator postEditAndWriteValidator;
    public final static int PAGE_SIZE = 5;

    //    게시글 목록
    @GetMapping("/list")
    public String postList(@RequestParam(defaultValue="1")int page,
                           @RequestParam(required = false) String type,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false) String sort,
                           Model model,HttpSession session) {
//      게시글 검색
        PostSearchDTO postSearchDTO = new PostSearchDTO();
        postSearchDTO.setType(type);
        postSearchDTO.setKeyword(keyword);
        postSearchDTO.setPage(page);
        postSearchDTO.setSort(sort);
        postSearchDTO.setStartRow((page-1)*PAGE_SIZE);
        postSearchDTO.setPageSize(PAGE_SIZE);

//        서비스로직 실행
        List<PostListDTO> posts = postService.findAllPosts(postSearchDTO);
        MemberVO loginMember = memberService.findMemberById((Long)session.getAttribute("sessionId"));

//        페이징 처리
        int totalCount = postService.selectAllPostCount(postSearchDTO);
        int totalPages = (totalCount==0)? 1:(int)Math.ceil((double) totalCount/PAGE_SIZE);
        int blockSize = 5;
        int startPage = Math.max(1,page-2);
        int endPage = Math.min(totalPages,startPage+blockSize-1);

        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("loginMember",loginMember);
        model.addAttribute("posts",posts);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("sort",sort);
        return "/post/postList";
    }

//    게시글 작성 GET
    @GetMapping("/write")
    public String postWrite(Model model, HttpSession session) {
//      게시글 작성은 인터셉터 사용해서 로그인 한 뒤만 사용하게
        model.addAttribute("loginMember",memberService.findMemberById((Long) session.getAttribute("sessionId")));
        model.addAttribute("postVO",new PostVO());
        return "/post/postWrite";
    }
//    게시글 작성 POST
    @PostMapping("/write")
    public String postWrite(@Validated @ModelAttribute PostVO postVO, BindingResult bindingResult, @RequestParam("imageFiles")MultipartFile[] files, HttpSession session) {
        postEditAndWriteValidator.validate(postVO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/post/postWrite";
        }
        postService.writePost(postVO,files,(Long)session.getAttribute("sessionId"));
        return "redirect:/post/list";
    }
//    게시글 상세페이지 GET
    @GetMapping("/detail/{postId}")
    public String postDetail(@PathVariable("postId")Long postId,Model model, HttpSession session) {
        MemberVO member = memberService.findMemberById((Long)session.getAttribute("sessionId"));
        String likeStatus = postLikeStatusService.getPostLikeStatus(postId,member.getId()).getPostLikeStatus();
        model.addAttribute("likeStatus",likeStatus);
        model.addAttribute("loginUser",member);
        model.addAttribute("postDetailDTO",postService.findPostById(postId,session));
        model.addAttribute("comments",commentService.findAll(postId));
        log.info(commentService.toString());
        return "/post/postDetail";
    }
//    게시글 수정 GET
    @GetMapping("/edit/{postId}")
    public String postEdit(@PathVariable("postId")Long postId, Model model, HttpSession session) {
        session.setAttribute("readCountUpStatus",true);
        model.addAttribute("postDetailDTO",postService.findPostById(postId,session));
        model.addAttribute("loginUser",memberService.findMemberById((Long)session.getAttribute("sessionId")));
        return "/post/postEdit";
    }
//    게시글 수정 취소버튼 조회수증가X  셋업
    @GetMapping("/detail/setup/{postId}")
    public String postDetailSetup(@PathVariable("postId")Long postId, Model model, HttpSession session) {
        session.setAttribute("readCountUpStatus",true);
        model.addAttribute("postDetailDTO",postService.findPostById(postId,session));
        model.addAttribute("loginUser",memberService.findMemberById((Long)session.getAttribute("sessionId")));
        return "/post/postDetail";
    }
//    게시글 수정 POST
@PostMapping("/edit/{postId}")
public String postEdit(@Validated @ModelAttribute("postDetailDTO") PostDetailDTO postDetailDTO,BindingResult bindingResult,
                       @PathVariable("postId") Long postId,HttpSession session,Model model) {
    postEditAndWriteValidator.validate(postDetailDTO, bindingResult);
    log.info(postDetailDTO.toString());
    if (bindingResult.hasErrors()) {
        PostDetailDTO postDetailDTO1 = postService.findPostById(postId,session);
        postDetailDTO.setId(postId);
        postDetailDTO.setFileList(postDetailDTO1.getFileList());
        model.addAttribute("postDetailDTO",postDetailDTO);
        model.addAttribute("loginUser",memberService.findMemberById((Long)session.getAttribute("sessionId")));
        return "/post/postEdit";
    }

    PostEditDTO postEditDTO = new PostEditDTO();
    postEditDTO.setPostTitle(postDetailDTO.getPostTitle());
    postEditDTO.setPostContent(postDetailDTO.getPostContent());

    postService.updatePost(postEditDTO, postId);
    session.setAttribute("readCountUpStatus", true);
    return "redirect:/post/detail/" + postId;
}

    //    게시글 좋아요 POST
    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String,Object>> toggleLike(@RequestBody Map<String,Object> payload){
        Long postId = ((Number)payload.get("postId")).longValue();
        Long memberId = ((Number)payload.get("memberId")).longValue();

        postLikeStatusService.toggleLikeStatus(postId,memberId);
        Long likeCount = postService.findPostLikeCountById(postId);
        PostLikeStatusVO postLikeStatusVO = postLikeStatusService.getPostLikeStatus(postId,memberId);
        return ResponseEntity.ok(Map.of("likeCount",likeCount,"likeStatus",postLikeStatusVO.getPostLikeStatus()));
    }
//    게시글 삭제
    @GetMapping("/delete/{postId}")
    public String postDelete(@PathVariable("postId")Long postId) {
        log.info(postId.toString());
        postService.deletePost(postId);
//        댓글도 같이 삭제해줘야함 그래야 오류가 안남
        return "redirect:/post/list";
    }

}
