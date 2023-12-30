# NK에듀 학생 관리 시스템 백엔드 개발 프로젝트

- 프로젝트 설명
  - `NK인피니트영수전문학원`에서의 학생 관리 시스템을 위한 백엔드 프로젝트 레포지토리이다. 학생, 학부모, 관리자에 대한 데이터베이스 설정과 이에 대한 API 를 제공하고자 한다.

- API 명세서
  - [다음 링크](https://www.notion.so/00d0bf199c184008a23bd0795b3637dc?v=08a86e2587a6418fb1a48c641bdb8865&pvs=4)에서 확인이 가능함.
  - 계속해서 추가 기능 소요에 대한 파악 후 정의를 이어가고자 한다.

- 데이터베이스 ERD
  - 아래와 같은 관계도로 데이터베이스 시스템을 구성하고자 한다.
  - 이외 기능에 대한 관계도는 추후 기능 회의를 진행하며 피드백을 반영할 예정이다.

- 개발 환경
  - Java 1.8
  - Java Spring Boot 2.7.14
  - Lombok
  - MySQL
  - JPA

- 패키지 구조
  - `com.nkedu.back` : SpringBootApplication
  - `com.nkedu.back.api` : API 인터페이스
  - `com.nkedu.back.serviceImpl` : API 구현체 클래스
  - `com.nkedu.back.controller` : API 컨트롤러 클래스
  - `com.nkedu.back.model` : 데이터베이스 객체 클래스

- 깃허브 관리
  - develop 브랜치를 바탕으로 기능마다 새로운 브랜치를 만들어 개발을 진행한다.
