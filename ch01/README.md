## AWS와 Docker의 필요성

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
  - RDS
  - DynamoDB
  - RedShift
  - AuroraDB
- Management
  - Cloud Watch
- Analysis
  - Kinesis

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
