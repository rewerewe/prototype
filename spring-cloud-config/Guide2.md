# 가이드

---

## 1, 3, 4, 5, 6, 7. 생략
* GuideQuick에서는 불필요.

---

## 2. 설치
* [OpenJdk 1.8](https://github.com/ojdkbuild/ojdkbuild) : Java 개발 필수.

---

## 8. 실행

### Server

#### 기동
* PowerShell을 새 창에서 실행
```
cd C:\data\dev\workspaceGithubPrototype\spring-cloud-config\test
```
* java 실행
```
java -jar "-Dserver.port=10000" scc-server.jar
```
#### 웹 브라우저에서 확인
<br>http://localhost:10000/scc/default
<br>http://localhost:10000/scc/conf1
<br>http://localhost:10000/scc/conf2

### Client
#### 기동
* PowerShell을 새 창에서 실행
```
cd C:\data\dev\workspaceGithubPrototype\spring-cloud-config\test
```
* java 실행
```
java -jar "-Dserver.port=10001" "-Dspring.cloud.config.uri=http://localhost:10000" "-Dspring.application.name=scc" "-Dspring.cloud.config.profile=conf1" scc-client.jar
```

#### 웹 브라우저에서 확인
<br>http://localhost:10001/serverTypeName

#### Github의 설정 파일값 수정
* config/scc-conf1.yml
```
server:
  type:
    name: test # 확인하고자하는 임의의 값
```
#### Client 설정 갱신
* Window Key + R을 누르고 cmd를 입력해 명령 프롬프트를 실행.
* 설정 갱신 명령어 실행. 대신 Postman을 사용해도 됨.
```
curl -X POST http://localhost:10001/actuator/refresh
```
* 주의 : 만약 curl을 실행 후, 명령 프롬프트로 돌아오지 못하고 반응이 없다면, Client를 실행한 PowerShell 창에 가서 우클릭으로 프로그램 실행을 방해하는 유저 인터페이스를 취소시키거나 아래와 같이 설정 해야 한다.
```
상단 우클릭 > 속성(P) > 옵션 탭 > 편집 옵션 > 빠른 편집 모드(Q) 체크해제
```