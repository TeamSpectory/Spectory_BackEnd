# 📃 자기소개서 작성의 시작, Spectory  

<img src = "https://user-images.githubusercontent.com/50194490/177195899-b2dfe958-ff17-4179-98ef-fd1f74b5cf09.jpg" width = 75%>  

## ▶ Spectory는 이럴 때 여러분을 도와드릴 수 있어요! 
- 😥 누구에게나 어려운 자기소개서, 조금 더 쉽게 써보고 싶을 때
- 😵 내가 그동안 뭘 했었지? 한 눈에 보고 싶을 때
- 🤔 앞으로 어떤 활동을 더 채우면 좋을지 고민하게 될 때

## ▶ About Team Spectory  
| Android | Desgin | Server | Server |
| --- | --- | --- | --- |  
|  📘  |  📘   |  📘   |   📘 |  
| [조윤진](https://github.com/cyjadela) | [임예림](https://github.com/Yerim96) | [이상혁](https://github.com/hyeok2Wkd) | [최다연](https://github.com/dayeondayeon) |  


## [▶ API](https://github.com/TeamSpectory/Spectory_BackEnd/wiki)  
| 도메인 | 화면 | 기능 설명 | 담당자 | 
| ---- |------|----------|--------|
| User | 로그인 | id, pw 기반의 로컬 로그인| 이상혁 |
| User | 로그인 | 로그인 성공시 JWT 토큰 발급| 최다연 |
| User | 회원가입 | id, pw, nickname 정보로 회원가입, 중복 검사, 비밀번호 암호화 | 최다연 |
| User | 내 프로필 조회 | JWT 토큰 인증 후 회원 탈퇴 | 최다연 |
| User | 내 프로필 조회 | 회원 정보 조회 | 최다연 |  
| Post | 내 프로필 조회 | JWT 토큰 인증 후 모든 글 삭제하기 | 최다연 | 
| Post | 글 작성하기 | 글 작성하기 | 이상혁 |
| Post | 글 작성하기 | 이미지 포함 글 작성, S3 업로드 | 최다연 |  
| Post | 글 목록 | 전체 글 불러오기 | 이상혁 |
| Post | 글 수정하기 | 글 수정하기 | 이상혁 |
| Post | 게시글 상세정보 | JWT 토큰 인증 후 글 삭제하기 | 최다연 |
| Post | 게시글 상세정보 | 게시글 상세정보 확인 | 최다연 |  


## ▶ Spectory Architecture  
<img src = "https://user-images.githubusercontent.com/50194490/177201322-c820639c-85e2-4d0f-a48a-f7e0976e9586.jpg" width = 75%>  

## ▶ Spectory Structure  
```bash
└─com
    └─spectory
        │  Application.java
        │  Message.java
        │  Status.java
        │
        ├─Config
        │      BaseTimeEntity.java
        │      JsonWebTokenProvider.java
        │      S3Component.java
        │      SpringSecurity.java
        │
        ├─Post
        │  ├─controller
        │  │      PostApiController.java
        │  │
        │  ├─domain
        │  │      ImageUpload.java
        │  │      Post.java
        │  │      PostRepository.java
        │  │
        │  ├─dto
        │  │      PostDeleteRequestDto.java
        │  │      PostDetailResponseDto.java
        │  │      PostListResponseDto.java
        │  │      PostModifyRequestDto.java
        │  │      PostSaveRequestDto.java
        │  │
        │  └─service
        │          ImageUploadService.java
        │          PostService.java
        │
        └─User
            ├─controller
            │      UserApiController.java
            │
            ├─domain
            │      User.java
            │      UserRepository.java
            │
            ├─dto
            │      ResponseDto.java
            │      UserDeleteRequestDto.java
            │      UserJoinRequestDto.java
            │      UserJoinResponseDto.java
            │      UserLoginRequestDto.java
            │      UserLoginResponseDto.java
            │      UserProfileResponseDto.java
            │
            └─service
                    UserService.java
                    
```

## ▶ Spectory ERD  
<img src = "https://user-images.githubusercontent.com/50194490/177384561-920c9b96-97e5-4e95-85e8-604b672c61ff.jpg">  

## ▶ [Spectory Figma](https://www.figma.com/file/W1TTeCJ44de6gdOB6okgNm/spectory-project)  
- Design : 임예림


## ▶ Commit Message Rules
- 간단하고 명료한 형태로, [Commit Message] : [작업한 부분에 대한 설명]의 형태로 작성

| Message | 설명 |
|---|---|
| Feat | 새로운 기능 구현, 파일 생성 등의 작업 |
| Fix | 에러, 버그 등을 수정 - ex 에러 난 상태로 커밋해두고 다시 에러 고칠 때 |
| Refactor | 간단한 변수명 등 수정 |
| Docs | README 등의 문서 수정 |
| Chores | 프로젝트 구조 등 코딩 외의 변경 |
