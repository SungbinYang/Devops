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

