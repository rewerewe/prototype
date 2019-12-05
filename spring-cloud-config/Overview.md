# 개요
---
## Spring Cloud Config?

<img src="asset/img/Spring Cloud Config.png" />

* Java Spring 환경에서 중앙 집중식 설정 파일의 외부화. Micro Service Application(MSA)에 적합.
* 설정 파일의 외부화 : Version Control System(ex. git, svn)에 미리 설정.
* 설정 변경 후 재기동 필요 여부 : Server, Client App을 기초 설정하면 Client의 재기동 불필요.
---
## 기존 시스템
* <strong>App 재기동 필수.</strong>
* Spring MVC Legacy/Spring Boot Application에서 환경설정은 각각 설정.
* 또한 App 서버의 종류(ex. dev, stg, prd)를 환경설정 파일로 구분하는 현장이 대다수.
---
## Spring Cloud Config 시스템
* Legacy System과 달리 재기동 불필요.
* 종류 : <strong>Config Server</strong>, ZooKeeper Configuration, Consul Configuration
