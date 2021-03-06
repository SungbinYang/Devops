## DevOps의 필요성

- 2008년 애자일 컨퍼런스에서 앤드루 클레이 쉐이퍼(Andrew Clay Shafer)와 패트릭 드부와(Patrick Debios)가 "애자일 인프라스트럭처"에 대해 논의하며 처음으로 사용
- 소통, 협업, 통합 및 자동화를 강조하는 소프트웨어 개발 방법론
- 개발과 운영이 상호의존적으로 대응해야 한다는 의미
- 개발과 운영 사이의 역할
- 툴 체인
  - Jira
  - Confluence
  - Bitbucket
  - Jenkins
- 업무환경 (클라우드)
  - CLOUD SERVER (EC2, S3, RDS)
  - Docker

## Jira & Confluence에 대한 간략한 설명

- Jira : 협업 도구 (보드를 통해서 업무를 관리할 수 있다. 또한 업무를 배정하고 처리하고 해당 업무의 소스를 볼 수 있다.)
- Confluence : 협업 도구 (업무 자료를 정리하는데 매우 강력한 도구, 위키의 기능에 업무협업하는 유저들을 태깅할 수 있다. 또한 히스토리 관리 측면에서 탁월한 기능들을 제공합니다. 또한 내부적으로 있는 웹훅 기능을 활용하면, 문자나 메일, 알람등등 다양하게 활용할 수 있다. )
- Bitbucket : 형상 관리 툴 (소스를 누구나 같이 볼 수 있고, 동시에 수정하거나
  수정 이후에 소스 저장소에 있는 소스와 개인 공간에 있는 소스들을 비교함으로써, 어떻게 다른 사람이 소스를 변경했는지를 살펴 볼 수 있습니다. 또한 이후에 연동하는 영역을 통해서 지라에 빗버킷을 연동할 수 있게 되며,
  업무 별로 소스에 대해서 파악하기 훨씬 용이하게 됩니다.)
- Jenkins : 형상 관리 툴 (빗버킷에 올라가있는 소스를 가져와서 Maven이나 Gradle 등을 활용하여 빌드 할 수 있고, 또한 이런 과정들을 파이프라인으로 만들어 패키지화 하여 배포할 수 있다.)
- 지라부터 컨플루언스 그리고 빗버킷과 젠킨스를 통해 업무의 자동화를 경험하게 되면 조금 더 업무가 편해지는 것은 확실한 일이다.

## 개발 프로세스

- 구성 (IaC) : 인프라 구축 단계, 초기 개발시에 보안 및 장애를 대비할수 있는 단계
  - 코드 -> 빌드 -> 테스트 -> 패키징(ex. war) -> 릴리즈(운영서버에 반영하는 단계) -> 모니터링(로그 분석등) -> 코드...

## AWS와 Docker의 필요성.

- AWS
  - 클라우드 서버 구축 서비스
  - 전통적 IDC(International Data Corporation)
  - 점차 클라우드 서비스 중요도 증대
- Docker
  - 오픈소스 컨테이너 프로젝트로 만들어진 도커
  - 간단한 프로젝트 도커로 만들기
  - 프로젝트 환경 스택 (Java, MariaDB(RDS), MAVEN, iBatis, Spring Boot, Spring Security, s3(이미지))
  - 카카오 로그인 + 오프라인 후기 서비스 -> 도커 관리

## AWS의 장점과 단점

- 장점
  - 탄력적인 웹 규모 컴퓨팅
  - 다양한 Command(API) 제공
  - 유연한 클라우드 호스팅 서비스
  - 통합
  - 안전성
  - 보안
- 단점
  - 베어 메탈 성능을 원할 때
  - 웹페이지가 몇 개 뿐일때
  - 솔루션에 적합
  - 가격

## AWS의 종류

- Server
  - EC2(Elastic Compute Cloud)
  - Lambda
  - VPC(Virtual Private Cloud)
- Storage
  - S3
  - EBS(Elastic Block Store)
- DataBase
  - RDS(ex. mysql, mariadb, postgresql)
  - DynamoDB
  - RedShift
  - AuroraDB
- Management
  - Cloud Watch : 어플리케이션 전반적인 로깅 작업이 가능
- Analysis
  - Kinesis : 실시간 데이터 수집 및 완전 관리형

## Docker의 장점

- 실행시점에 상관없이 구성 시점을 고를 수 있음(눈송이 서버)
  - 버전관리 차원에서 동시에 배포가 가능 (이미지화)
  - 눈송이 서버: 서버가 많아질때 각 서버별로 버전이 상이함 -> 이미지를 똑같이 컨테이너에 배포하게되면 해당문제는 많이 줄어듬
- 개발 프로그램 설치와 삭제가 용이
  - Java, mysql, oracle, elk, nginx 등 서버 프로그램 설치와 삭제가 용이
- 운영체제 도커 실행 소스 일관성, 유연성
  - 초기 인츠라 환경설정은 복잡하고 어려우나, 쉽고 일관성이 있게 만들어줌
- 이미지 용량이 크게 줄어듬
  - 도커는 리눅스 컨테이너를 사용
- 여러군데 배포할 수 있는 확장성
  - GITHUB와 비슷한 느낌 PUSH / PULL 용이
