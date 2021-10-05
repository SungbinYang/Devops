## Jenkins Pipe Line 만들기

- 기존 젠킨스

![스크린샷 2021-10-05 오후 10 25 17](https://user-images.githubusercontent.com/18282470/136031828-eeeec28c-fc35-4149-8d14-39f4b8032d3e.png)

- 젠킨스 파이프라인

![스크린샷 2021-10-05 오후 10 26 07](https://user-images.githubusercontent.com/18282470/136031981-edefd040-ddc7-44f3-b652-1ecd66afac92.png)

- 젠킨스 >  새로운 ITEM
- 젠킨스 >  aws_pipeline
- 젠킨스 >  구성 > 하단부
- 젠킨스 >  구성 >  script

``` script
pipeline {
   agent any

   tools {
      maven "maven＂ // 메이븐 기존에 세팅한 명칭 :: 메이븐 확인
   }

   stages {
       stage('git Pull') {
         steps {
             git 'https://yangsungbin@bitbucket.org/devOpsReview/reviewaws.git’ // 세팅한 git 주소 :: 소스 내려받기
         }
       }
      stage('Build') {
         steps {
            sh "mvn -Dmaven.test.failure.ignore=true -N -f review/pom.xml clean package＂ // 빌드하기, pom.xml 의 위치
         }

      }
      stage('Deploy'){
           steps {
                sh "scp -i \"/Users/robert/Documents/key/chicken.pem\" review/**/*.war ec2-user@13.209.48.204:/opt/tomcat/webapps"  // WAR 파일 복사 구문
           }
      }
       stage('RESTART'){
           steps {
                sh "ssh -i \"/Users/robert/Documents/key/chicken.pem\" ec2-user@13.209.48.204 sudo systemctl restart tomcat＂  // 재시작 명령어
           }
          
      }
   }
}
```
- 젠킨스 >  aws_pipeline >  Build Now

![스크린샷 2021-10-05 오후 10 47 33](https://user-images.githubusercontent.com/18282470/136035770-a94717ee-49d0-404a-b95a-c9bbdc18a5de.png)
