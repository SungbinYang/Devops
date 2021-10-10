## 도커 설치하기 & 컨테이너 접속하기

- 도커 설치하기 > EC2 > AWS 인스턴스 실행 
- 도커 설치하기 > 터미널 접속하기(맥은 터미널, 윈도우는 putty)
- 도커 설치하기 > 도커 실행 (docker as –a)
- 도커 설치하기 > yum install 도커 ( sudo yum install docker-io --yes )
- 도커 설치하기 > 도커 설치 완료
- 도커 설치하기 > 도커 데몬 실행 (sudo systemctl start docker)
- 도커 설치하기 > 도커 권한 부여
- 컨테이너 접속하기 (docker exec –it 컨테이너 이름 /bin/bash)

## 도커 이미지 만들기 및 실행
- Dockerfile 생성
  * vi Dockerfile
    ``` docker
    FROM tomcat:9-jre8-alpine # 기존에 alpine 에 올라간 톰켓 이미지
    COPY server.xml /usr/local/tomcat/conf # server.xml에 jndi 정보 및 구조정보
    // # tomcat 폴더 클린
    RUN rm -rf /usr/local/tomcat/webapps/ROOT.war 
    RUN rm -rf /usr/local/tomcat/webapps/ROOT
    RUN rm -rf /usr/local/tomcat/webapps/docs
    RUN rm -rf /usr/local/tomcat/webapps/examples
    RUN rm -rf /usr/local/tomcat/webapps/host-manager
    RUN rm -rf /usr/local/tomcat/webapps/manager
    COPY ROOT.war /usr/local/tomcat/webapps // # war 파일 복사
    ENV TZ=Asia/Seoul # docker container의 timezone을 서울로 변경
    RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
    EXPOSE 8080 # 8080포트
    ```
   * sudo systemctl stop tomcat
   * sudo systemctl status tomcat
   * 도커 이미지 실행하기(docker run --rm -d -p 8080:8080  review/aws) 

## Docker Hub
- 도커 프로세스 기동 확인 (docker ps -a)
- 도커 이미지 리스트 확인 (docker images)
- 도커 로그인 (docker login)
- 도커 태그 붙이기, 도커에 올리기 (tag, push) (docker tag review/aws:latest sungbin/review:0.0.1) (sudo docker push sungbin/review:0.0.1)
- 도커 설명 추가 하기
- 도커 검색하기
- 도커 설치후 실행해보기
- 인스턴스 설치 후 터미널 접속
- 도커 설치
  ``` bash
  sudo yum install docker-io
  sudo systemctl start docker
  sudo setfacl -m user:ec2-user:rw /var/run/docker.sock
  ```
- 도커 이미지 설치 (docker pull sungbin/review:0.0.1)
- 도커 이미지 확인 (docker images)
- 도커 이미지 실행 (docker run --rm -d -p 8080:8080  sungbin/review:0.0.1)
- 서비스 접속 RDS 권한 설정 필요
- RDS > 데이터베이스 선택 > 보안 그룹 > EC2 보안 그룹 추가 

## 도커 API
- https://docs.docker.com/engine/api/v1.40/#
- 컨테이너 리스트 (/containers/json)
- 컨테이너 생성 (/containers/create)
- 컨테이너 시작 (/containers/{id}/start)
- 컨테이너 정지 (/containers/{id}/stop)
- 컨테이너 재시작 (/containers/{id}/restart)
- 컨테이너 종료 (/container/{id}/kill)
