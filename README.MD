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

1. JPA dirty checking 그리고 @Transactional (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/JPA%20dirty%20checking%20%EA%B7%B8%EB%A6%AC%EA%B3%A0%20@Transactional">클릭 & detail 확인</a>)
  
2. 대삽질끝에 알게 된 Spring Security의 Filter 순서의 중요성 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/%EB%8C%80%EC%82%BD%EC%A7%88%EB%81%9D%EC%97%90%20%EC%95%8C%EA%B2%8C%20%EB%90%9C%20Spring%20Security%EC%9D%98%20Filter%20%EC%88%9C%EC%84%9C%EC%9D%98%20%EC%A4%91%EC%9A%94%EC%84%B1">클릭 & detail 확인</a>)
  
3. JPA auditing ; 말은 거창해도 설정은 쉽다 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/JPA%20auditing%20;%20%EB%A7%90%EC%9D%80%20%EA%B1%B0%EC%B0%BD%ED%95%B4%EB%8F%84%20%EC%84%A4%EC%A0%95%EC%9D%80%20%EC%89%BD%EB%8B%A4">클릭 & detail 확인</a>)
  
4. 리프레시 토큰에 대한 단위 테스트 성공 (<a href="https://github.com/upqnu/gourmet-rcmmd/wiki/%EB%A6%AC%ED%94%84%EB%A0%88%EC%8B%9C%20%ED%86%A0%ED%81%B0%EC%97%90%20%EB%8C%80%ED%95%9C%20%EB%8B%A8%EC%9C%84%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%84%B1%EA%B3%B5">클릭 & detail 확인</a>)
  
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