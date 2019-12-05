# 가이드
---
* 테스트만 하는 경우 2, 3번 참조 후 바로 8번으로 skip해도 됨.
---
## 1. 참조
<br>https://yaboong.github.io/spring-cloud/2018/11/25/spring-cloud-config

---
## 2. 설치
* [OpenJdk 1.8](https://github.com/ojdkbuild/ojdkbuild) : Java 개발 필수.
* [Spring Tools Suite 4](https://spring.io/tools) : Spring Boot 개발용. 8번부터 테스트할 경우 불필요.
---
## 3. Github
### Fork
* Fork : 다른 Github 저장소를 내 저장소에 복제
* Github에 접속하고 회원가입(Sign Up), 로그인(Sign In)
* [Fork할 예제 URI](https://github.com/idealful/prototype/spring-cloud-config)

### 설정 파일 생성
* config/scc.yml
```
server:
  type:
    name: default
```
* config/scc-conf1.yml
```
server:
  type:
    name: dev
```
* config/scc-conf2.yml
```
server:
  type:
    name: prd
```

### Git Clone
* Fork한 경우 자신의 Github URI로 변경해야 함. Git Bash에서 아래를 실행.
```
cd /c/data/dev/workspaceGithubPrototype/spring-cloud-config
git clone https://github.com/idealful/prototype/spring-cloud-config.git .
```
---
## 4. Sts 설정

### Sts에서 Git 인식
* Window > Perspective > Open Perspective > Other... > Git
* Git Repositories 탭 > Add an existing local Git repository
* Directory 항목을 아래 경로로 지정. Sts workspace에 추가될 App 소스를 Git에 인식시키기 위함.
```
C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config
```
* Search Results 체크

### 프로젝트 생성 전 확인
1. Spring MVC -> Spring Boot
1. 대세는 json like!
* 설정 파일은 xml, properties -> yml(yaml)
* 의존성 관리 및 빌드 도구는 Ant, Maven -> Gradle

### 로컬에서 Spring Boot 프로젝트 생성 : Server
* File > New > Spring Start Project
* Name : scc-server
* Use Default Location 체크해제 후 Location을 수동으로 변경. Sts workspace의 App 소스를 Git에 인식시키기 위함.
```
C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\scc-server
```
* Spring Cloud Config > Config Server 체크, Finish
* 파일 삭제 : /scc-server/src/main/resources/application.properties
* 파일 생성 : /scc-server/src/main/resources/application.yml
```
#server:
#  port: 10000 # scc-server port

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/idealful/prototype # Github URI
          searchPaths: spring-cloud-config/config # Path
```
* 실행 파일에 설정 어노테이션 추가 : /scc-server/src/main/java/com/example/demo/SccServerApplication.java
```
...
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Config Server 설정
@SpringBootApplication
...
```

### 로컬에서 Spring Boot 프로젝트 생성 : Client
* File > New > Spring Start Project
* Name : scc-client
* Spring Cloud Config > Config Client 체크, Finish
* Use Default Location 체크해제 후 Location을 수동으로 변경. Sts workspace의 App 소스를 Git에 인식시키기 위함.
```
C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\scc-client
```
* 파일 수정 : /scc-client/build.gradle
```
...
dependencies {
    implementation('org.springframework.boot:spring-boot-starter-actuator') // 갱신
    implementation('org.springframework.boot:spring-boot-starter-web') // Spring Web
...
```
* 파일 삭제 : /scc-client/src/main/resources/application.properties
* 파일 생성 : /scc-client/src/main/resources/application.yml
```
#server:
#  port: 10001 # scc-client port

#spring:
#  application:
#    name: scc # App Name 설정 : scc.yml
#  cloud:
#    config:
#      profile: conf1 # Cloud Config Profile 설정 : scc-conf1.yml

management:
  endpoints:
    web:
      exposure:
        include: "refresh" # 갱신
```
* 파일 생성 : /scc-client/src/main/resources/bootstrap.yml
```
#spring:
#  cloud:
#    config:
#      uri: http://localhost:10000 # scc-server URI
```
* Spring Web Controller 파일 생성 : /scc-client/src/main/java/com/example/demo/ClientController.java
```
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 실시간 반영을 위한 어노테이션
public class ClientController {
	@Value("${server.type.name}") // Github의 설정 값
	private String serverTypeName;

	@GetMapping("/serverTypeName")
	public String getServerTypeName() {
		return serverTypeName;
	}
}
```
* 프로젝트 의존성 갱신(필수) : Project Explorer > Gradle > Refresh Gradle Project
---
## 5. Sts 테스트
### Server 확인
* Project scc-server 선택 > Run Configurations... > Spring Boot App > Arguments > VM Arguments:에 아래의 값을 입력 후 > Apply > Run
* scc-server의 Tomcat port=10000
```
-Dserver.port=10000
```
* 이후부터는 Run as > Spring Boot App 실행
* 웹 브라우저(FireFox 추천)에서 아래의 URI를 각 탭마다 입력 후 실행
<br>http://localhost:10000/scc/default
<br>http://localhost:10000/scc/conf1
<br>http://localhost:10000/scc/conf2

### Client 기동
* Project scc-client 선택 > Run Configurations... > Spring Boot App > Arguments > VM Arguments:에 아래의 값을 입력 후 > Apply > Run
* scc-client의 Tomcat port=10001
* scc-server의 port=10000
* github의 file name prefix=scc
* github의 file name suffix=conf1
* github의 file name full=scc-conf1.yml
```
-Dserver.port=10001 -Dspring.cloud.config.uri=http://localhost:10000 -Dspring.application.name=scc -Dspring.cloud.config.profile=conf1
```
* 이후부터는 Run as > Spring Boot App 실행
* 웹 브라우저에서 아래의 URI 입력 후 실행하여 'dev' 출력 확인.
<br>http://localhost:10001/serverTypeName

### Client 설정 변경
* 파일 수정(Github에서 수동 Commit 혹은 로컬에서 수정 후 Commit and Push) : scc-conf1.yml
```
server:
  type:
    name: stg
```

### Client 설정 검증 : 실패
* 웹 브라우저에서 아래의 URI 입력 후 실행하여 'dev' 출력 확인. 변경 인식 실패.
<br>http://localhost:10001/serverTypeName

### Client 설정 갱신
* Window Key + R을 누르고 cmd를 입력해 명령 프롬프트를 실행.
* 설정 갱신 명령어 실행. 대신 Postman을 사용해도 됨.
```
curl -X POST http://localhost:10001/actuator/refresh
```
* 결과 메시지가 ["config.client.version","server.type.name"] 가 나오면 성공.
* 만약 'No message available' 에러 메시지가 발생하면 scc-client를 마우스 우클릭해서 Gradle > Refresh Gradle Project를 수동 실행해야 함.

### Client 설정 검증 : 성공
* 웹 브라우저에서 아래의 URI 입력 후 실행하여 'stg' 출력 확인. 변경 인식 성공.
<br>http://localhost:10001/serverTypeName
---
## 6. 옵션

### .gitignore 파일 생성 및 설정
* 버전 관리할 필요가 없는 소스와 gradle 빌드 파일 등은 Git에서 관리하지 않고 무시할 필요 있음.
```
**/bin/**
*.lock
*.bin
```
* .gitignore 정상 인식을 위한 갱신 : Sts 종료 후 재실행

### Sts server, client 소스를 Git(Local) Commit and Github(Remote) Push
1. Window > Perspective > Open Perspective > Other... > Git
1. Git Staging 탭에서 Unstages Changes(Git 버전과 비교해서 추가, 수정, 삭제된 소스)를 +버튼을 눌러 Staged Changes(Commit할 대상)으로 변경.
1. Commit Message를 원하는 메시지로 입력하고 Commit
1. Git Repositories 탭에서 우클릭 후 Push branch 'master'를 누름. master는 기본 git 저장소(Repository).
---
## 7. 빌드

### 선행작업
* PowerShell 실행
* test 폴더 생성(이미 생성되어있는 로컬 pc의 경우 skip)
```
cd C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config
mkdir test
```

### Server
* 프로젝트 경로 접근
```
cd C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\scc-server
```
* Gradle Jar 빌드
```
./gradlew bootjar
```
* 빌드된 jar 파일 확인
```
cd .\build\libs\
ls scc-server-0.0.1-SNAPSHOT.jar
```
* test 폴더로 이동
```
cp .\scc-server-0.0.1-SNAPSHOT.jar ..\..\..\test
cd ..\..\..\test
ls
```
* jar 이름 변경
```
mv .\scc-server-0.0.1-SNAPSHOT.jar scc-server.jar
ls
```

### Client
* 프로젝트 경로 접근
```
cd C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\scc-client
```
* Gradle Jar 빌드
```
./gradlew bootjar
```
* 빌드된 jar 파일 확인
```
cd .\build\libs\
ls scc-client-0.0.1-SNAPSHOT.jar
```
* test 폴더로 이동
```
cp .\scc-client-0.0.1-SNAPSHOT.jar ..\..\..\test
cd ..\..\..\test
ls
```
* jar 이름 변경
```
mv .\scc-client-0.0.1-SNAPSHOT.jar scc-client.jar
ls
```
---
## 8. 실행

### Server

#### 기동
* PowerShell을 새 창에서 실행
```
cd C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\test
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
cd C:\data\dev\workspaceGithubPrototype\scc-spring-cloud-config\test
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