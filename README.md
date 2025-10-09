# 🧾 프로젝트 개요
🎵 **음표상점 (MusicNote-Store)**  
음반과 악보를 사고파는 전자상거래 웹사이트입니다.  
일반적인 전자상거래 기능(회원, 상품, 장바구니, 결제, 주문/배송, 리뷰)을 갖추고 있으며,  
**감정/날씨/장르 기반 AI 음악 추천 챗봇**을 통해 차별화된 쇼핑 경험을 제공합니다.  

---


# 🛠️ 사용 기술

| 구분              | 기술 스택                                                                 | 아이콘 |
|-------------------|---------------------------------------------------------------------------|--------|
| **Backend**       | Java 17, Spring Boot 3, Spring Security 6, JPA(Hibernate), Oracle DB      | ![Java](https://img.shields.io/badge/Java-17-007396?style=flat&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=springsecurity&logoColor=white) ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white) ![Oracle](https://img.shields.io/badge/Oracle-F80000?style=flat&logo=oracle&logoColor=white) |
| **Testing**       | JUnit5, Mockito                                                           | ![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white) ![Mockito](https://img.shields.io/badge/Mockito-25A162?style=flat&logo=java&logoColor=white) |
| **Deployment**    | Docker, AWS EC2, AWS S3, AWS RDS(Oracle)                                  | ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white) ![AWS](https://img.shields.io/badge/AWS-232F3E?style=flat&logo=amazonaws&logoColor=white) |
| **Version Control** | Git, GitHub                                                             | ![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white) |
| **Collaboration** | Notion (일정/문서 관리)                                                   | ![Notion](https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white) |


# 📌 주요 기능
| 기능 영역       | 상세 설명 |
|----------------|-----------|
| 사용자 관리     | 회원가입, 로그인, 역할 기반 권한 설정 (USER / SELLER / ADMIN) |
| 상품 관리       | 음반/악보 등록, 검색/필터, 상세 페이지, 디지털 악보 미리보기 |
| 장바구니 & 결제 | 장바구니 담기/삭제/수량 변경, Stripe/아임포트 테스트 결제 |
| 주문/배송 관리  | 주문 생성, 상태 변경(결제완료/배송중/완료), 송장 등록, 디지털 다운로드 권한 발급 |
| 리뷰 & Q&A      | 구매자 리뷰 작성/조회, 상품별 질문/답변 |
| AI 추천 챗봇    | 감정/날씨/장르 기반 음악 추천 (LangChain, 추후 개발) |
| 관리자 기능     | 판매자 승인, 신고 처리, 카테고리/태그 관리, 팝업 관리 |

---


# 📂 프로젝트 목적
- **서비스적 목적**
  - 음반(피지컬)과 악보(디지털/피지컬) 거래 지원
  - 전자상거래 기본 기능 구현 및 보안 적용(Spring Security 기반 인증/인가)
- **개발적 목적**
  - Backend 개발 역량 향상 (Spring Boot + Security + JPA + Batch + Oracle)
  - **Docker** 기반 컨테이너 환경 구성 및 **AWS EC2** 배포 경험
  - **AWS S3**를 통한 디지털 악보 파일 저장 및 서명 URL 발급
  - **AWS RDS(Oracle)** 클라우드 DB 운영
  - **CI/CD 파이프라인** (GitHub Actions → AWS) 구축으로 자동 빌드/배포 경험
- **특화 기능**
  - LangChain 기반 AI 챗봇 → 감정 분석, 날씨 분석, 장르 기반 음악 추천

---


# 📂 폴더구조

```
src/main/java/com/store/store
├── component/   # 컴포넌트 파일 및 도메인 별 DTO <-> Entity 변환 전담 mapper 클래스
├── config/      # 전역 설정 (SecurityConfig, PasswordEncoderConfig)
├── controller/  # REST API 엔드포인트, 요청/응답 처리
├── domain/      # 엔티티, ENUMS 객체 (JPA Entity, Enum)
├── dto/         # 데이터 전송 객체 (Request/Response DTO)
├── exception/   # 예외 처리 클래스 (Custom Exception, Handler)
├── repository/  # 데이터 접근 계층 (JPA Repository)
└── service/     # 비즈니스 로직 계층
```

# 🌟 주요 기능 (MVP)
- 회원 관리 & 보안
  - 회원가입 / 로그인 / 로그아웃
  - JWT 기반 인증 및 권한 제어
  - 비밀번호 암호화 및 재설정 기능
- AI 기반 이상 로그인 감지 (비정상 접근 시 알림)
- 상품 관리 (음반/악보)
  - 상품 등록 / 수정 / 삭제 (관리자)
  - 상품 목록 조회, 검색, 필터링 (사용자)
  - 상품 상세 페이지 (음반/악보 미리보기, 설명, 가격)
- 장바구니 & 주문
- 장바구니 담기 / 수정 / 삭제
- 주문 생성 및 결제 연동 (MVP 단계에서는 Mock 결제 API 사용)
- 주문 내역 조회
- 추천 AI 챗봇
  - 감정/날씨/장르 기반 음악 추천
  - 사용자 입력(텍스트) → 감정 분석 → 맞춤 음반/악보 추천
  - 날씨 API 연동하여 상황별 추천 제공
- 알림
  - 주문 완료, 결제 성공 시 알림
  - 추천 챗봇 응답 실시간 표시
