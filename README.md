# Idus 개발 과제

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
#### 브라우저

* [http://localhost:8080](http://localhost:8080)
* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Running the tests / 테스트의 실행

어떻게 테스트가 이 시스템에서 돌아가는지에 대한 설명을 합니다

### 테스트는 이런 식으로 동작합니다

왜 이렇게 동작하는지, 설명합니다

```bash
$ gradle test
```

## Deployment / 배포

Add additional notes about how to deploy this on a live system / 라이브 시스템을 배포하는 방법

## Built With / 누구랑 만들었나요?

* [이름](링크) - 무엇 무엇을 했어요
* [Name](Link) - Create README.md

## Contributiong / 기여

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us. / [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) 를 읽고 이에 맞추어 pull request 를 해주세요.

## License / 라이센스

This project is licensed under the MIT License - see the [LICENSE.md](https://gist.github.com/PurpleBooth/LICENSE.md) file for details / 이 프로젝트는 MIT 라이센스로 라이센스가 부여되어 있습니다. 자세한 내용은 LICENSE.md 파일을 참고하세요.

## Acknowledgments / 감사의 말

* Hat tip to anyone whose code was used / 코드를 사용한 모든 사용자들에게 팁
* Inspiration / 영감
* etc / 기타