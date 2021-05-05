# 고객, 주문관리 RESRful Back-End API 개발과제

![Tag](https://img.shields.io/github/v/tag/nubiforms/order-management-restful)
![Travis](https://img.shields.io/travis/com/nubiforms/order-management-restful)

![Languages Count](https://img.shields.io/github/languages/count/nubiforms/order-management-restful)
![Languages Top](https://img.shields.io/github/languages/top/nubiforms/order-management-restful)
![Issues](https://img.shields.io/github/issues/nubiforms/order-management-restful)
![Issues Closed](https://img.shields.io/github/issues-closed/nubiforms/order-management-restful)
![Issues PR](https://img.shields.io/github/issues-pr/nubiforms/order-management-restful)
![Issues PR Closed](https://img.shields.io/github/issues-pr-closed/nubiforms/order-management-restful)
![Commit Activity](https://img.shields.io/github/commit-activity/m/nubiforms/order-management-restful)
![Last Commit](https://img.shields.io/github/last-commit/nubiforms/order-management-restful)

요구사항 문서는 [여기](https://github.com/NuBiFoRMs/order-management-restful/wiki/Requirement)를 참고해 주세요.

## Getting Started / 어떻게 시작하나요?

### Prerequisites / 선행 조건

아래 사항들이 설치가 되어있어야합니다.

* JDK 11
* Gradle
* Docker
* Docker Composer

도커 컨테이너 실행 (`mysql:8.0`, `redis:alpine`)

```bash
$ docker-composer up -d
```

#### Database / 데이터베이스

`mysql-init-file/mysql.sql` 경로에 테이블 생성 쿼리 및 샘플 데이터 생성 쿼리가 포함되어 있습니다.  
해당 스크립트는 `Docker`를 통해 `mysql`이 실행될때 자동으로 수행됩니다.  
`mysql` 쓰기 전용 서버와 읽기 전용 서버 `replication` 설정을 쓰기 전용 유저 `write`, 읽기 전용 유저 `read-only` 구성으로 대체 하였습니다.

##### Sample Data / 샘플 데이터

테스트를 위한 계정이 생성되어 있습니다.

|meberId|password|roles|
|---|---|---|
|admin|admin|ROLE_ADMIN,ROLE_USER|
|testusera|!Q2w3e4r5t|ROLE_USER|
|testuserb|!Q2w3e4r5t|ROLE_USER|
| | ... | |
|testusere|!Q2w3e4r5t|ROLE_USER|

### Build & Run / 빌드 및 실행

프로젝트 빌드 및 실행

#### 실행 방법 1

```bash
$ gradle build
$ java -jar build/libs/idus-0.0.1-SNAPSHOT.jar
```

#### 실행 방법 2

```bash
$ gradle bootRun
```
#### Swagger UI

* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
