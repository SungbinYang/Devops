# 스프링 프로젝트 세팅

## 버젼 관리와 자동화 빌드툴 이해하기
- Build Tool : Maven
- Language: Java 8
- IDE: Intellij
- view: jsp
- Framework: Spring Boot, Spring Security
- ORM: MyBatis

## 프로젝트 세팅
- JAVA 설치
  ``` bash
  yum list | grep jdk   -- jdk리스트 확인 
  sudo yum install java-1.8.0-openjdk – java 오픈 소스 설치 
  java –version   -- java 버젼 확인
  ```
- TOMCAT 설치
  ``` bash
  sudo yum install wget  -- wget 패키지 설치
  sudo mkdir /opt/tomcat  -- 톰켓을 설치할 폴더 생성
  wget http://mirror.navercorp.com/apache/tomcat/tomcat-8/v8.5.47/bin/apache-tomcat-8.5.47.tar.gz -- 다운로드
  sudo tar xvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1  -- 압축 해제
  ```
- TOMCAT 세팅
  ``` bash
  sudo useradd -M tomcat – 톰켓 계정 추가
  sudo chgrp -R tomcat /opt/tomcat  -- 해당 폴더를 tomcat user 권한 부여
  sudo chown -R tomcat webapps/ work/ temp/ logs/  -- 소유권을 톰켓으로 정의
  sudo chmod g+x conf – Config 폴더 권한 부여
  sudo chmod 777 webapps  -- 파일 삭제 및 수정 권한 부여
  sudo systemctl enable tomcat  -- 톰켓 사용 가능하도록  
  sudo systemctl status tomcat  -- 톰켓 확인
  sudo systemctl start tomcat  -- 톰켓 시작
  Cd /opt/tomcat/webapps
  sudo rm -rf manager
  sudo rm -rf host-manager
  sudo rm -rf examples
  sudo rm -rf docs
  
  sudo vi /etc/systemd/system/tomcat.service
  ```
- tomcat.service
  ``` bash
  # Systemd unit file for tomcat
  [Unit]
  Description=Apache Tomcat Web Application Container
  After=syslog.target network.target

  [Service]
  Type=forking

  Environment=JAVA_HOME=/usr/lib/jvm/jre
  Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
  Environment=CATALINA_HOME=/opt/tomcat
  Environment=CATALINA_BASE=/opt/tomcat
  Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
  Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'
  ExecStart=/opt/tomcat/bin/startup.sh
  ExecStop=/bin/kill -15 $MAINPID\
  User=tomcat
  Group=tomcat
  UMask=0007
  RestartSec=10
  Restart=always


  [Install]
  WantedBy=multi-user.target
  ```

- server.xml

  ``` xml
    <Host name="localhost" appBase="webapps"
    unpackWARs="true" autoDeploy="true">
     <!-- SingleSignOn valve, share authentication between web applications
    Documentation at: /docs/config/valve.html -->
    <!--
    <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
    -->
     <!-- Access log processes all example.
    Documentation at: /docs/config/valve.html
    Note: The pattern used is equivalent to using pattern="common" -->
    <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
    prefix="localhost_access_log" suffix=".txt"
    pattern="%h %l %u %t &quot;%r&quot; %s %b" />
     <Context docBase="review" path="/" reloadable="true" source="org.eclipse.jst.jee.server:review"/>
    </Host>
  ```
