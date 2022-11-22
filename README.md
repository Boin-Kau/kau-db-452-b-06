# Java JDBC을 활용한 직원검색시스템
 
## 프로젝트 개요
### 요구사항
- 조건에 맞는 직원들을 검색, 추가, 삭제, 수정할 수 있는 자바 프로그램 또는 웹 애플리케이션 구현하기
- JDBC를 사용하여 MYSQL에 접근

### 팀원소개 및 역할
- 김보인
- 김서현
- 강래현
- 윤승현
  
  
## 프로젝트 설명
### 프로젝트 구조
```text
kau-db-term-project
  > build
  > src.main.java.com.kau.db
    > controller
      | EmployeeListController.java 
    > entity
      | Employee.java 
      | EmployeeReq.java 
      | Dependent.java 
    > filter
      | CharacterEncodingFilter.java 
    > service
      | EmployeeService.java 
  > src.main.java.webapp
      | main.jsp
  > .gitignore // git 에 포함되지 않아야 하는 폴더, 파일들을 작성 해놓는 곳
```
### 상세 설명
#### 1. Controller
> 1) API 통신의 **Routing** 처리
> 2) Request를 다른 계층에 넘기고 처리된 결과 값을 Response로 전송해주는 역할

#### 2. Entity
> 1) Response 응답을 위한 객체들

#### 3. Filter
> 1) Servlet Filter : Servlet이 호출되기 전에 수행(전처리)되게 하고 싶거나, 호출 되고 난 뒤에 수행(후처리) 하고 싶은 공통적인 기능들을 Servlet Filter로 구현

#### 4. Service
> 1) Controller로부터 인자를 받음
> 2) Mysql 연결 후 쿼리 실행
> 3) 쿼리 결과값을 Controller로 돌려줌

<br/>

### 버전 관리
- Java : openjdk version "1.8.0_292"
- MySQL Connector : mysql-connector-java-8.0.28.jar
- Tomcat : v9
