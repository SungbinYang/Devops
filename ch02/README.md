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
