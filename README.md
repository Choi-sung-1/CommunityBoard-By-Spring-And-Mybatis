# 📝 Toy Project - 게시판 웹 애플리케이션

## 📌 1. 프로젝트 소개
Spring Boot 기반으로 개발한 기본 게시판 웹 애플리케이션입니다.  
회원 관리부터 게시글 작성, 댓글, 좋아요, 페이징 처리, 카카오맵 API를 이용한 위치 기능까지 포함되어 있습니다.  
실무에서 자주 사용되는 기능들을 학습하고 적용해보는 기본 토이 프로젝트입니다.

---

## ⚙️ 2. 기술 스택

**Backend**  
- Java 17  
- Spring Boot, Spring MVC  
- MyBatis  
- Spring Security / BCrypt  

**Frontend**  
- Thymeleaf, HTML, CSS, JavaScript  
- Bootstrap  
- Fetch API (Ajax) 비동기 통신  

**Database**  
- H2 (테스트용), Oracle  

**API / 라이브러리**  
- Kakao Map API, Lombok  

**개발 도구**  
- IntelliJ IDEA, Git / GitHub  

---

## 📂 3. 프로젝트 구조 (간략화)
    toyProject
    ┣ 📂 src
    ┃ ┣ 📂 main
    ┃ ┃ ┣ 📂 java/com/project/toyProject
    ┃ ┃ ┃ ┣ 📂 config # 애플리케이션 전반 설정 파일
    ┃ ┃ ┃ ┣ 📂 controller # Controller 계층
    ┃ ┃ ┃ ┣ 📂 dao # Repository 계층
    ┃ ┃ ┃ ┣ 📂 domain # VO, DTO 클래스
    ┃ ┃ ┃ ┣ 📂 interceptor # 요청 전후 처리 담당 클래스
    ┃ ┃ ┃ ┣ 📂 mapper # MyBatis Mapper 인터페이스
    ┃ ┃ ┃ ┣ 📂 service # 비즈니스 로직 관련 클래스
    ┃ ┃ ┃ ┣ 📂 validation # 입력값 검증 관련 클래스
    ┃ ┃ ┣ 📂 resources
    ┃ ┃ ┃ ┣ 📂 mapper # MyBatis Mapper XML
    ┃ ┃ ┃ ┣ 📂 templates # Thymeleaf HTML 파일
    ┃ ┃ ┃ ┣ 📂 static/css # CSS
    ┃ ┃ ┃ ┣ 📂 static/js # JavaScript
    ┗ 📄 pom.xml

---

## 📝 4. 테이블 구조

![테이블 구조](https://github.com/user-attachments/assets/4a9138a0-6831-467c-a04a-5591563b38e7)

---

## 🚀 5. 주요 기능

- 회원가입 / 로그인 (Spring Security + BCrypt)  
- 회원 프로필: 한줄 소개, 이미지 업로드, 개인정보 작성/수정 가능  
- 게시글 작성, 수정, 삭제  
- 게시글 목록 조회, 정렬 및 페이징 처리  
- 이미지 업로드 및 게시글 내 이미지 표시  
- 댓글 작성, 삭제  
- 게시글 좋아요 및 조회수  
- Kakao Map API를 이용한 게시글 위치 표시  
- 입력 검증 및 예외 처리  
- 비동기 통신(Fetch API) 적용 (중복체크 등)  

---

## 🖥 6. 실행 방법

1. 프로젝트 클론
```bash
git clone https://github.com/username/toyProject.git
cd toyProject

DB 설정: application.properties에서 H2 DB 설정 변경
config/WebConfig 클래스 설정: 이미지 저장 경로 변경
빌드 & 실행
서버 실행 후 접속: http://localhost:8080
