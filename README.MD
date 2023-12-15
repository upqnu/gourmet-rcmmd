본 "MINE" 브랜치는 팀 프로젝트 "지리 기반 맛집 추천 웹 서비스, 구루메"의 GitHub repository를 본인의 GitHub으로 fork한 후, 다시 새롭게 분기한 브랜치이다.

분기한 목적은 (1) 팀 프로젝트에서 본인이 구현한 기능의 포트폴리오 (2) 추후 본인이 의도한대로 코드 리팩토링 또는 기능 추가

* (팀 프로젝트 확인 : https://github.com/Team-Enigma23/location-based-gourmet-recommendation-project)

---


# :pushpin: 지리 기반 맛집 추천 웹 서비스, 구루메
> **지리 기반 맛집 추천 웹 서비스, 구루메!**
>
> 사용자 위치에 기반한 맛집 및 메뉴를 추천해 더 나은 다양한 음식 경험을 제공하는 서비스
  
## <포트폴리오 순서>
1. [제작기간 및 참여인원](#1제작기간-및-참여인원)
2. [개요](#2개요)
3. [사용기술](#3사용기술)
4. [ERD 설계](#4erd-설계)
5. [본인 구현 기능](#5본인-구현-기능)
6. [트러블 슈팅 & TIL](#6트러블-슈팅--til)
7. [회고](#7회고)
  
- [Git commit 메시지 컨벤션](#git-commit-메시지-컨벤션)
- [본인 구현 API Reference](#본인-구현-api-reference)
  
## 1.제작기간 및 참여인원
- 제작기간 : 2023년 11월 1일 ~ 11월 7일
- 팀 프로젝트 (총 4명 / 모두 Java, Spring 기반 백엔드 개발자)
  
## 2.개요
본 서비스는 사용자 위치에 맞는 맛집 및 메뉴를 추천해 더 나은 다양한 음식 경험을 제공하고, 음식을 좋아하는 사람들 간의 소통과 공유를 촉진하려 합니다.<br>
`경기도 공공데이터 API`를 활용해, 지역 음식점 목록을 자동으로 업데이트 하고 이를 활용합니다.<br>
  
## 3.사용기술
- 언어 및 프레임워크 : ![Static Badge](https://img.shields.io/badge/Java-17-F58232) ![Static Badge](https://img.shields.io/badge/Spring_boot-3.0.12-6CB52C)<br/>
- 데이터베이스 : ![Static Badge](https://img.shields.io/badge/h2-2.1.214-1021FF) ![Static Badge](https://img.shields.io/badge/Spring_Data_JPA-3.0.11-80E96E)<br/>
- 배포 : ![Static Badge](https://img.shields.io/badge/Gradle-8.3-02303A) <br/>
- ETC: ![Static Badge](https://img.shields.io/badge/SMTP-039BC6)
  
## 4.ERD 설계
>![coverage](src/main/resources/static/img/ERD.png) <br/>
> - 유저는 위치 정보와 맛집 추천 여부 컬럼을 가진다.
>   - 위치 정보를 통해 유저 근처의 맛집을 추천한다.
>   - 맛집 추천 여부를 받아 원하지 않는다면 추천을 하지 않는다.
> - 평점 추천 서비스를 제공한다.

## 5.본인 구현 기능

**사용자 회원가입 및 JWT를 통한 사용자 인증 구현**
<details>
  
1. 사용자 회원가입 및 인증
    - **JWT**를 통한 사용자 인증 구현 ; 액세스 토큰 발급 / 리프레시를 통한 액세스 토큰 재발급
        - 사용자(Client) 로그인과 동시에 액세스 토큰 발급
            - 사용자 로그인과 동시에 Client(사용자 객체) 및 String tokenType “access”를 매개변수로 유효기간이 24시간인 액세스 토큰 발급 (with 시그니처 알고리즘 HS256)
            - 발급된 액세스 토큰은 요청 헤더(request header)에 담겨서 특정 API로 접근 시 항상 접근권한을 확인
        - 액세스 토큰 유효시간(24시간) 만료 시, 토큰 타입을 “refresh”로 입력하여 유효기간이 1주일로 연장된 새로운 액세스 토큰 발급
            - 유효기간 만료 시, 토큰을 생성할 때의 tokenType을 “refresh”로 입력하여 새로운 액세스 토큰이 발급됨. “access”와는 달리 유효기간이 1주일로 지정되어 있음.
        - 이 방식은 리프레시 토큰이 발급되지 않아 DB의 저장공간을 절약할 수 있고, 리프레시 토큰을 검증하는 로직 없이 액세스 토큰만으로 사용자 인증을 처리하여 인증 로직을 단순화할 수 있음.
  
  
2. MockMvc를 이용하여 토큰 발급 및 갱신에 대한 단위테스트 코드 작성
    - 회원가입, 로그인 및 액세스 토큰 발급의 테스트 코드 작성
    - 액세스 토큰 갱신 (발급된 액세스 토큰의 유효기간 만료시, 유효기간이 연장된 액세스 토큰이 발급)
        1. 테스트 메서드 내에서 임의의 회원가입 및 로그인을 진행하면서 임의의 액세스 토큰을 발급
        2. “refresh”를 매개변수로 입력하여 유효기간이 연장된 액세스 토큰을 발급
        3. 최초 발급된 액세스 토큰, 새로 발급된 액세스 토큰의 헤더, 페이로드, 서명부를 분리해서 String 배열에 저장 후 비교.
            - 테스트 성공 조건 ; 헤더는 동일 / 페이로드는 일부만 제외하고 동일 (두 토큰의 페이로드에 담기는 정보는 대부분 동일하지만 생성일시, 수정일시는 다르기 때문) / 서명부는 상이할 때 성공
</details>

## 6.트러블 슈팅 & TIL
>
> 1. JPA dirty checking 그리고 @Transactional (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/JPA%20dirty%20checking%20%EA%B7%B8%EB%A6%AC%EA%B3%A0%20@Transactional">클릭 & detail 확인</a>)
> 
> 2. 대삽질끝에 알게 된 Spring Security의 Filter 순서의 중요성 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/%EB%8C%80%EC%82%BD%EC%A7%88%EB%81%9D%EC%97%90%20%EC%95%8C%EA%B2%8C%20%EB%90%9C%20Spring%20Security%EC%9D%98%20Filter%20%EC%88%9C%EC%84%9C%EC%9D%98%20%EC%A4%91%EC%9A%94%EC%84%B1">클릭 & detail 확인</a>)
> 
> 3. JPA auditing ; 말은 거창해도 설정은 쉽다 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/JPA%20auditing%20;%20%EB%A7%90%EC%9D%80%20%EA%B1%B0%EC%B0%BD%ED%95%B4%EB%8F%84%20%EC%84%A4%EC%A0%95%EC%9D%80%20%EC%89%BD%EB%8B%A4">클릭 & detail 확인</a>)
> 
> 4. 리프레시 토큰에 대한 단위 테스트 성공 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/%EB%A6%AC%ED%94%84%EB%A0%88%EC%8B%9C%20%ED%86%A0%ED%81%B0%EC%97%90%20%EB%8C%80%ED%95%9C%20%EB%8B%A8%EC%9C%84%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%84%B1%EA%B3%B5">클릭 & detail 확인</a>)
>
  
## 7.회고
  
<details>
<summary>JWT를 통한 사용자 인증을 구현할 때 의외로 나를 가장 힘들게 했던 것은 SecurityConfig 설정이었다. 이를 수정하는 과정에서 Security filter의 순서가 조금만 잘못되어도 사용자 인증을 활용한 기능 구현에 문제가 생길 수 있음을 깨달은 계기가 되었다.</summary>

1. JWT 통한 사용자 인증을 위한 코드 작성 중, `OncePerRequestFilter`을 상속한 `JwtTokenAuthenticationFilter`클래스를 작성 후 postman에서 테스트 진행 결과,  `java.lang.IllegalArgumentException: JWT String argument cannot be null or empty.` 에러가 발생.
2. 구글링 하면서 “모든 HTTP 요청에 대해 토큰을 검증하는 필터링을 할 필요는 없다. 일부 HTTP 요청은 이 필터를 생략해도 된다.”고 판단하게 되었다.
3. 이를 구현하기 위해 추가 구글링 후 `doFilterInternal` 메서드 내부에서 - 토큰이 필요하지 않은 URI들은 JWT 관련 로직을 거치지 않고 다음 필터로 이동하는 로직을 추가하였다.
4. 그런데 위 3번에는 토큰이 불필요한 URI 4개를 메서드 내에 직접 입력했어야 했다. 이런 URI가 만약 1000개가 존재한다면 일일이 입력하기는 불가능하다고 생각했고, 다시 구글링을 통해 다른 방법을 찾았다.
5. 그 결과, SecurityConfig 클래스의 `SecurityFilterChain filterChain` 메서드 내부에서 `addFilterBefore` 를 통해 `jwtTokenAuthenticationFilter` 적용시키는 위치가 잘못된 것을 발견하였다. JWT 인증 없이도 접근 가능한 URI를 먼저 설정한 후에 `jwtTokenAuthenticationFilter` 를 위치시켜야 하는데, 순서가 바뀌었던 것이다.
6. 이 순서를 다시 바꾸었더니 postman에서도 JWT 관련 테스트가 성공하였다. 이 경우 위 3번에 추가한 로직은 더 이상 필요하기 않기에 삭제하였다.
  
- (위 트러블 슈팅 2번 참고)
</details>
  <br>
<details>
<summary>JPA dirty checking에 대해 제대로 학습할 수 있었다.</summary>

- Dirty Checking ; 엔티티 매니저가 변경이 발생한 엔티티를 자동 감지하여 데이터베이스에 반영하는 것.
    - 더 정확히는 영속성 컨테이너가 관리하는 엔티티의 상태를 감지해서, 변경된 부분이 있다면 자동으로 트랜잭션이 끝나는 시점에 데이터베이스에 반영하는 기능
    - 여기서 말하는 dirty는 “엔티티 데이터의 변경된 부분”을 뜻하며 dirty checking은 변경된 부분을 감지한다는 의미
- @Transactional 선언하고 해당 트랜잭션이 commit 되는 시점에 영속성 컨텍스트는 트랜잭션 쓰기 작업에 해당되는 Query를 생성. 즉 해당 트랜잭션 내부에서 영속성 컨텍스트가 엔티티의 변화를 감지하면 수정사항에 맞는 Query 문을 생성한다는 것을 알게 되었다.
- 영속성 컨텍스트가 모든 엔티티의 변화를 감지(Dirty Checking)하는 게 아니라 영속 상태의 엔티티만 Dirty Checking 의 대상이 되며, @Transactional 외부에서 주입된 엔티티는 현재 메서드의 영속성 컨텍스트와 관련없는 엔티티이어서 dirty checking 되지 않음도 명학하게 알게 되었다.

- (위 트러블 슈팅 1번 참고)
</details>
  <br>
<details>
<summary>리프레시 토큰에 대한 단위테스트에 성공하면서 테스트 코드 작성에 대한 막연한 두려움을 어느 정도 떨칠 수 있었다.</summary>

- mockito (MockMvc), assertj 에 조금 더 익숙해질 수 있었다.
- 테스트 코드 작성을 위해 구글링하면서 JWT의 구조를 자세히 공부하게 되었는데, 이 프로젝트에서 처음 액세스 토큰과 리프레시 토큰의 헤더, 페이로드, 서명부를 비교한 결과가 아래와 같다면 테스트 성공이라고 판단하게 되었다.
    - 헤더 : 액세스 토큰과 리프레스 토큰이 일치해야 한다.
    - 페이로드 : 서로 달라야 한다. 왜냐하면 (다른 정보는 같아도) 발급일시가 다르기 때문이다. 구체적으로는 페이로의 중간의 몇 부분만 서로 다르기 때문에 → 페이로드 앞의 20~30글자, 뒤의 20~30글자는 일치하지만 / 전체를 비교했을 때는 달라야 한다.
    - 서명부 : 보안을 위해서 서로 완전히 달라야 한다.

- (위 트러블 슈팅 4번 참고)
</details>
  <br>
<details>
<summary>TIL을 작성하는 나의 태도를 크게 바꾸었다.</summary>

- 그 동안 개발에 대한 학습, 프로젝트를 진행하면서 도움이 되는 내용은 노션에 정리하였다. 다만 정제되지 않은 나만의 표현을 사용하면서 두서 없이 정리하다보니 추후에 이 내용을 필요할 때 다시 보게 되면 무슨 뜻인지 모르는 경우가 많았다.
- 그래서 내가 봐도, 다른 사람들이 봐도 TIL 만 읽어도 어떤 상황이 벌어졌고 어떻게 해결했는지 흐름을 확인할 수 있도록 작성하기 시작하였다.
</details>
  
---
  
## Git commit 메시지 컨벤션

| 표시         | 설명               | 
|:-----------|:-----------------|
| `Feat`     | 새로운 기능을 추가       |
| `Refactor` | 프로덕션 코드 리팩토링                 |
| `Fix`      | 버그 수정            |
| `Comment`  | 필요한 주석 추가 및 변경         |
| `Docs`     | 문서 수정, 생성에 사용         |
| `Test`     | 테스트 코드, 리펙토링 테스트 코드 추가, Production Code(실제로 사용하는 코드) 변경 없음            |
| `Chore`    | 빌드 업무 수정, 패키지 매니저 수정, 패키지 관리자 구성 등 업데이트, Production Code 변경 없음        |
| `Rename`   | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우        |
| `Remove`   | 파일을 삭제하는 작업만 수행한 경우        |
| `Style`    | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우      |


## 본인 구현 API Reference

<details>
<summary>로그인 & 액세스 토큰 발급 - click</summary>

#### Request
```javascript
  POST /api/clients/sign-in
```
```http
Content-Type: application/json

{
    "clientId": "tester1",
    "password": "asdf1234"
}
```

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIxIiwiZXhwIjoxNzAwMzI5NTI4LCJpYXQiOjE3MDAzMjc3MjgsImF1dGhvcml0aWVzIjoiUk9MRV9NRU1CRVIiLCJuYW1lIjoidGVzdGVyMSJ9.gBERUQJ9zGkJcWdxehqw9MXCY7hRTR98CeXJZpsgvRU"
}
```
</details>
<details>
<summary>리프레시 토큰 발급 & 액세스 토큰 갱신 - click </summary>

#### Request
```javascript
  POST /api/token
```
```http
Content-Type: application/json

{
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIxIiwiZXhwIjoxNzAwMzExNzM4LCJpYXQiOjE3MDAzMDk5MzgsIm5hbWUiOiJ0ZXN0ZXIxIiwiYXV0aG9yaXRpZXMiOiJST0xFX01FTUJFUiJ9.XRxWNeFYMBq9_CDO2qVm_zRpC4-Uem2ytpSBt0GJVsM"
}
```

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIxIiwiZXhwIjoxNzAwMzExNzk1LCJpYXQiOjE3MDAzMDk5OTUsIm5hbWUiOiJ0ZXN0ZXIxIiwiYXV0aG9yaXRpZXMiOiJST0xFX01FTUJFUiJ9.olSJWEcCp0OQp8PAQmfoKFIYJeLIwfWv0Ox8F4LEis8"
}
```
</details>