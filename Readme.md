# API Spec

## 테스트 swagger url

- http://localhost:8080/swagger
- 스웨거에 기본적인 request및 reponse 스키마도 같이 정리해 두었습니다.

## DB

- DB의 경우 내장디비인 h2를 사용했습니다. 개발환경에서도 내장 디비이므로 개발환경에 관계없이 작성하였습니다.
- sql문은 import.sql에 기본적인 값을 설정해 두었습니다.

## API 명세

### 코드 그룹 서비스
- 공통
    - 서버 에러시 500 statusCode 제공
- code group 등록
    - method : POST
    - request :
        - {
          "codeGroup": "코드그룹(영어)",
          "codeGroupName": "코드그룹1"
          }
    - response
        - 성공시 {
          "statusCode": 201, //status code
          "result": 5 // 생성된 코드 그룹 고유 idx
          }
        - 코드그룹 미입력 및 코드그룹명 미입력, 코드 그룹이 존재할경우 400 에러와 메세지 제공

- code group 수정
    - method : PATAH
    - request :
        - {
          "codeGroup": "code_group6", //코드 그룹
          "codeGroupName": "코드그룹1", //코드 그룹 명
          "codeIdx": 1 // 코드 그룹 고유 idx
          }
    - response
        - 성공시 204 statusCode 제공
        - 코드그룹 미입력 및 코드그룹명 미입력, 코드 그룹이 존재할경우, 조회하는 코드 그룹명이 존재하지 않을경우 400 에러와 메세지 제공

- code group 삭제
    - method : DELETE
    - request : idx
    - response
        - 성공시 204 statusCode 제공
        - 조회하는 코드 그룹명이 존재하지 않을 경우 및 코드 그룹안에 코드가 존재할 경우 400 에러와 메세지 제공

- code group 조회
    - method : GET
    - request : 없음
    - response
        - {
          "statusCode": 200,
          "result": {
          "codeGroupValues": [
          {
          "idx": 1,
          "codeGroup": "code_group6",
          "codeGroupName": "코드그룹1"
          },
          {
          "idx": 2,
          "codeGroup": "code_group2",
          "codeGroupName": "코드그룹2"
          },
          {
          "idx": 3,
          "codeGroup": "code_group3",
          "codeGroupName": "코드그룹3"
          },
          {
          "idx": 4,
          "codeGroup": "code_group4",
          "codeGroupName": "코드그룹4"
          }
          ]
          }
          }
    
### 코드 서비스
- 공통
    - 서버 에러시 500 statusCode 제공
- code 등록
  - method : POST
  - request : {
    "codeGroupIdx": 1, //코드 그룹 고유 idx
    "code": "CODE1", // 코드
    "codeName": "코드명1" // 코드명
    }
  - response
- response
  - 성공시 {
  "statusCode": 201, //status code
  "result": null 
  }
  - 코드 미입력, 코드명 미입력, 코드 그룹 idx 미입력 시 400 에러와 메세지 제공
  - 코드 그룹 idx로 조회한 코드그룹이 존재하지 않을 경우 400 에러와 메세지 제공
  - 코드 그룹 내 동일 코드가 존재시 400 에러와 메세지 제공
- code list 조회
  - method : GET
  - request : groupIdx
  - response
  - {
    "statusCode": 200,
    "result": {
    "codes": [
    {
    "idx": 1,
    "code": "code1",
    "codeName": "코드1"
    },
    {
    "idx": 2,
    "code": "code2",
    "codeName": "코드2"
    },
    {
    "idx": 5,
    "code": "CODE1",
    "codeName": "코드명1"
    }
    ]
    }
    }
- code 단건 조회
  - method : GET
  - request : idx
  - response : {
    "statusCode": 200,
    "result": {
    "idx": 1,
    "code": "code1",
    "codeName": "코드1"
    }
    }
- code 수정
  - method : PATCH
  - request : {
    "codeGroupIdx": 1, //코드 그룹 고유 idx
    "idx": 1, //코드 고유 idx
    "code": "CODE1", //코드 
    "codeName": "코드명1" // 코드명
    }
  - response
    - 성공시 204 statusCode 제공
    - 조회하는 코드 그룹명이 존재하지 않을 경우 및 코드 고유 idx 조회시 존재하지 않을경우, 해당하는 코드 그룹 내 동일한 코드명이 존재할 경우 400 에러와 메세지 제공

- code 삭제
  - method :DELETE
  - request : idx
  - response 
    - 성공시 204 statusCode 제공
    - 조회한 코드가 존재하지 않을 경우 400에러 제공
- code 그룹 전체 삭제
  - method: DELETE
  - request : groupIdx
  - response 
    - 성공시 204 statusCode 제공
