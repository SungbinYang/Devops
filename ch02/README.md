# 협업 툴 활용

## Jira 협업 툴 소개

- ATLASSIAN ([아틀라시안 홈페이지](https://www.atlassian.com/))
  - Jira Software
- Jira work flow
- open -> stand by -> in progress -> resolved -> release -> done

## Jira 협업 툴 설치하기

- [AWS 홈페이지](https://aws.amazon.com/ko/)
- 사이트 접속 후, 로그인 후, E2C 인스턴스 생성
- AMI는 Amazon Linux 2 선택
- Jira가 용량을 많이 사용함으로 인스턴스 유형은 t2-large 선택
- 인스턴스 생성 마지막 단계로 키페어 등록해야함.
- 키페어는 잘 보관해야함
  ![스크린샷 2021-09-22 오전 12 37 44](https://user-images.githubusercontent.com/18282470/134202060-04322169-4afc-4597-989b-439cf4e1a29a.png)
- 다음으로 터미널로 접속
  ```bash
    ssh -i chicken.pem  ec2-user@13.125.234.161
  ```
- 다음으로 도커 설치
  ```bash
    udo yum install docker-io   -- yes
  ```
- 도커 리스트 확인

  ```bash
    docker ps -a

    sudo systemctl start docker

    sudo setfacl -m user:ec2-user:rw /var/run/docker.sock
  ```

- 기존 지라 도커 컨테이너 삭제
  ```bash
    docker rm --volumes --force "jira-container"
  ```
- 지라 도커 컨테이너 설치
  ```bash
    docker pull cptactionhank/atlassian-jira-software:latest
  ```
- 지라 도커 컨테이너 생성
  ```bash
    docker create --restart=no --name "jira-container" \
    --publish "8080:8080" \
    --volume "hostpath:/var/atlassian/jira" \
    --env "CATALINA_OPTS= -Xms1024m -Xmx1024m -Datlassian.plugins.enable.wait=300" \
    cptactionhank/atlassian-jira-software:latest
  ```
- 지라 도커 컨테이너 실행
  ```bash
    docker start --attach "jira-container"
  ```

![스크린샷 2021-09-22 오전 12 24 29](https://user-images.githubusercontent.com/18282470/134202090-b8297c6b-fe2d-4a73-b0fc-04597139ade9.png)

## Jira 이슈 이해하기
![스크린샷 2021-09-22 오전 1 06 18](https://user-images.githubusercontent.com/18282470/134206715-873e470b-e93c-4ec7-846e-145d3f405e1c.png)

## Jira API 소개 및 Postman 활용하기
- 지라 API ?
  * Application Programming Interface  - API 란 어플리케이션 프로그래밍 인터페이스
- 지라 API : https://docs.atlassian.com/software/jira/docs/api/REST/8.4.2/?_ga=2.93052573.1639889686.1571238783-647032862.1567516016
- 지라 API : ISSUE
  * api/2/issue 
  * Create issue		POST /rest/api/2/issue
  * Create issues		POST /rest/api/2/issue/bulk
  * Get issue		GET /rest/api/2/issue/{issueIdOrKey}
  * Delete issue		DELETE /rest/api/2/issue/{issueIdOrKey}
  * Edit issue		PUT /rest/api/2/issue/{issueIdOrKey}
- [POSTMAN](https://www.getpostman.com/)
![스크린샷 2021-09-22 오전 1 20 36](https://user-images.githubusercontent.com/18282470/134208817-85f8fdf2-d8ab-47a8-bb01-bc69c61e4286.png)
``` json
{
  "expand": "renderedFields,names,schema,operations,editmeta,changelog,versionedRepresentations",
  "id": "10000",
  "self": "http://13.124.208.168:8080/rest/api/2/issue/10000",
  "key": "BLO-1",
  "fields": {
    "issuetype": {
        "self": "http://13.124.208.168:8080/rest/api/2/issuetype/10100",
        "id": "10100", 
        "description": "개발 목적 및 개발 상태 관리",
        "iconUrl": "http://13.124.208.168:8080/secure/viewavatar?size=xsmall&avatarId=10311&avatarType=issuetype",
        "name": "관리 이슈",
        "subtask": false,
        "avatarId": 10311
},…
```
- 지라 API : https://docs.atlassian.com/software/jira/docs/api/REST/8.4.2/?_ga=2.93052573.1639889686.1571238783-647032862.1567516016
![스크린샷 2021-09-22 오전 1 58 00](https://user-images.githubusercontent.com/18282470/134214701-064e30da-8bad-4c45-b2c5-c9d929ef32dc.png)

## Jira 관리자 소개
- 이슈 유형 – 이슈를 관리 합니다 (새로운 이슈 타입을 생성
- 이슈 유형 계획 – 프로젝트에 어떤 이슈 타입을 넣을 수 있을지를 설정합니다. 
- 업무 흐름 – 업무의 단계를 설정합니다. 
- 업무 흐름 계획 – 이슈 유형과 업무 흐름을 매핑합니다.
- 화면 – 편집을 통해서 화면에 보여줄 필드 정보를 추가/수정할 수 있습니다
- 화면 계획 – 커스터마이즈한 화면을 특정 화면 계획과 연결 시킵니다.
- 이슈 유형 화면 계획 – 이유 유형과 화면 계획을 연결합니다. 
- 사용자 정의 필드 추가 – 사용자가 원하는 형태의 필드를 구성합니다 . 
![스크린샷 2021-09-22 오전 9 27 38](https://user-images.githubusercontent.com/18282470/134264960-63650bf7-d76b-4cd1-b1ce-60540eadc64d.png)

## Jira 워크 플로우 설정하기
- 이슈 계획
 
 ![스크린샷 2021-09-22 오전 9 34 00](https://user-images.githubusercontent.com/18282470/134265234-b7927356-7ec5-4ff8-9671-869316422c83.png)
 ![스크린샷 2021-09-22 오전 9 37 08](https://user-images.githubusercontent.com/18282470/134265456-2c23c897-13dc-4efe-ae99-87e4557ac1a1.png)
 ![스크린샷 2021-09-22 오전 9 38 26](https://user-images.githubusercontent.com/18282470/134265540-50b9f56b-294d-42a0-bb57-8cfa3372b47b.png)
 
- 상태 추가
- 전환 추가

![스크린샷 2021-09-22 오전 11 15 38](https://user-images.githubusercontent.com/18282470/134273177-d967c66c-89c8-424e-aa5d-a319c5c378dc.png)

## Jira CustomField 설정하기
- 문제 발생 일시 : 2021.09.22 (날짜 타입)
- 작업자 직군 : 개발자/기획자/디자이너/스케쥴 매니져 (택일)
- 문제 내용: TEXT 타입
- 작업 필요 내용: TEXT 타입
- 비고 : TEXT 타입
- 참교 URL : Confluence URL
- 요청 완료 일시 : 2021.10.22(날짜 타입) 

![스크린샷 2021-09-22 오후 2 09 05](https://user-images.githubusercontent.com/18282470/134286663-9508a14e-edb3-48db-9fd2-aadadec2bc51.png)

## Jira DB 구조 설명
- [JIRA 공식 DB 스키마 사이트](https://developer.atlassian.com/server/jira/platform/attachments/jira-7-9-2-database-schema.pdf)
- Jira DB 테이블
  * ISSUE 관련 테이블 : JIRAISSUE, ISSUESTATUS
  * PROJECT 테이블 : PROJECT
  * 커스텀 필드 테이블 : CUSTOMFIELD, CUSTOMFIELDVALUE, CUSTOMFIELDOPTION
  * ISSUE 링크 테이블 : ISSUELINK
  * ISSUE 관련 테이블 : JIRAISSUE, ISSUESTATUS

![스크린샷 2021-09-22 오후 2 13 46](https://user-images.githubusercontent.com/18282470/134286989-651cd161-0949-448b-88e1-2beecfa67924.png)

![스크린샷 2021-09-22 오후 2 16 17](https://user-images.githubusercontent.com/18282470/134287172-0ac5c305-baa2-4533-98d0-6649571a7956.png)

![스크린샷 2021-09-22 오후 2 17 13](https://user-images.githubusercontent.com/18282470/134287243-5d3b9072-a834-40ce-acd3-73bfae0c5a6c.png)

![스크린샷 2021-09-22 오후 2 17 33](https://user-images.githubusercontent.com/18282470/134287266-cc1453ec-b42e-47c2-a4f3-d2841cabd999.png)

![스크린샷 2021-09-22 오후 2 17 54](https://user-images.githubusercontent.com/18282470/134287324-d3b1b6b8-4b6e-45b7-a069-bf078f55f8e2.png)

## Confluence 협업툴 소개
- Atlassian
  * Confluence

![스크린샷 2021-09-22 오후 3 25 32](https://user-images.githubusercontent.com/18282470/134293328-e6f66a78-b8b2-4d1d-89cf-53e053160f35.png)

- 나를 위한 변경사항 관리를 손 쉽게 할 수 있다.
- 다양하고 강력한 검색 기능

## Confluence 협업툴 사용방법
- E2C 접속
  ``` bash
    ssh -i chicken.pem ec2-user@13.124.208.168
  ```
- 도커 리스트 확인
  ``` bash
    docker ps -a
  ```
- 기존 컨플루언스 도커 컨테이너 삭제
  ``` bash
    docker rm --volumes --force "confluence"
  ```
- 컨풀루언스 도커 컨테이너 설치
  ``` bash
    docker pull atlassian/confluence-server
  ```
- 컨플루언스 도커 컨테이너 생성 및 실행
  ``` bash
    docker run -e JVM_SUPPORT_RECOMMENDED_ARGS=-Djavax.net.ssl.trustStore=/var/atlassian/application-data/confluence/cacerts -v       confluenceVolume:/var/atlassian/application-data/confluence --name="confluence" -d -p 8090:8090 -p 8091:8091 atlassian/confluence-server
  ```
- 컨플루언스 도커 컨테이너 접속 명령어
  ``` bash
    docker exec -it confluence /bin/bash
  ```
- 기존 지라 도커 컨테이너 삭제
  ``` bash
    docker rm --volumes --force "jira-container"
  ```
- 지라 도커 컨테이너 설치
  ``` bash
    docker create -it --name "jira-container"   
    --publish "8080:8080"  
    --volume "hostpath:/var/atlassian/jira"   
    --env "CATALINA_OPTS= -Xms2048m -Xmx2048m -Datlassian.plugins.enable.wait=300"   
    cptactionhank/atlassian-jira-software:latest 
  ```
- 지라 도커 컨테이너 실행
  ``` bash
    docker start --attach "jira-container"
  ```
- 지라 도커 컨테이너 접속 명령어
  ``` bash
    docker exec -it jira-container /bin/bash
  ```

![스크린샷 2021-09-22 오후 4 22 35](https://user-images.githubusercontent.com/18282470/134300291-91284c9e-1536-460f-b1cf-3ede5a33baa6.png)
