![logo](https://static.wanted.co.kr/images/events/3178/58ac3248.jpg)

# 지리기반 맛집 추천 웹 서비스

## Table of Contents
- [개요](#개요)
- [Skils](#skils)
- [Installation](#Installation)
- [Running Tests](#running-tests)
- [API Reference](#api-reference)
- [프로젝트 진행 및 이슈 관리](#프로젝트-진행-및-이슈-관리)
- [구현과정(설계 및 의도)](#구현과정(설계-및-의도))
- [TIL 및 회고](#til-및-회고)
- [Authors](#authors)
- [References](#references)

<br/>


## 개요
본 서비스는 공공데이터를 활용하여, 지역 음식점 목록을 자동으로 업데이트 하고 이를 활용한다. 사용자 위치에맞게 맛집 및 메뉴를 추천하여 더 나은 다양한 음식 경험을 제공하고, 음식을 좋아하는 사람들 간의 소통과 공유를 촉진하려 합니다.

- Via Github
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    1. Enter the name of the badge you need.
    1. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- You could also visit the live site at [ileriayo.github.io/markdown-badges/](https://ileriayo.github.io/markdown-badges/)

<br/>


## Skils
가상환경: ![Static Badge](https://img.shields.io/badge/Conda-green)<br/>
언어 및 프레임워크: ![Static Badge](https://img.shields.io/badge/JAVA-17-green) ![Static Badge](https://img.shields.io/badge/Spring_boot-REST-green)<br/>
데이터 베이스: ![Static Badge](https://img.shields.io/badge/Postgresql-9.2.4-blue) <br/>
배포 : ![Static Badge](https://img.shields.io/badge/Docker-039BC6) ![Static Badge](https://img.shields.io/badge/AWS-EC2-orange) ![Static Badge](https://img.shields.io/badge/Github-Actions-black)  <br/> ETC : ![Static Badge](https://img.shields.io/badge/Celery-black) ![Static Badge](https://img.shields.io/badge/Redis-red)

<br/>


## Installation

Install my-project with npm

```bash
  # Package 설치
  npm install my-project
  cd my-project
  
  # 초기 설정 : 데이터 및  스케쥴러 실행
  npm install my-project
  cd my-project
```

<br/>


## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```

> Coverage ScreenShot ![Static Badge](https://img.shields.io/badge/Test_Passed-20/20-green)
![coverage](https://user-images.githubusercontent.com/48683566/56673924-0b84ae00-66b1-11e9-93ac-fb76ff96a5a0.png)

<br/>


## API Reference

<details>
<summary>Get all items - click</summary>

#### Request
```javascript
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    [{
        "id": 10,
        "name": "shirt",
        "color": "red",
        "price": "$23"
    },...
    ]
```
</details>
<details>
<summary>Get all items - click</summary>

#### Request
```javascript
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    [{
        "id": 10,
        "name": "shirt",
        "color": "red",
        "price": "$23"
    },...
    ]
```
</details>
<details>
<summary>Get all items - click</summary>

#### Request
```javascript
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    [{
        "id": 10,
        "name": "shirt",
        "color": "red",
        "price": "$23"
    },...
    ]
```
</details>

<br/>


## 프로젝트 진행 및 이슈 관리

[![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)](https://bow-hair-db3.notion.site/cdb6eb37500b4580a80252ea3d7c3963?pvs=4)

<br/>


## 구현과정(설계 및 의도)
(노션, 블로그 등의 페이지로 안내 가능)
<details>
<summary>유저 모델과 실행결과 모델관의 관계 설정 시 00 고려 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

<details>
<summary>00 구현 시 동시성 고려 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

<details>
<summary>RESTful API 설계 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

<br/>


## TIL 및 회고

<details>
<summary>Django ORM 조회 시 발생하는 00 버그 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

<details>
<summary>Django ORM 조회 시 발생하는 00 버그 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)

- 00가 00 하는 문제

</details>

(또는 블로그, 노션 등 링크 연동)

- [Django ORM 조회 시 발생하는 00 버그 발생](#google.com)
- [00 서비스 개발 회고록](#google.com)

<br/>


## Authors

- [@user1](https://www.github.com/2)
- [@user2](https://www.github.com/2)
- [@user3](https://www.github.com/2)

<br/>


## References

- [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
- [Awesome README](https://github.com/matiassingers/awesome-readme)
- [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)


