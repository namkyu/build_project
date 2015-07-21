## Project summary
파일 단위 배포 project
상용 서버에 파일 단위로 배포를 할 수 있게 소스를 package, install, rollback 해주는 유틸리티 프로그램


## jar 파일 생성 방법
프로젝트를 clone 받은 후 다음의 goal을 입력 후 메이븐 빌드한다.
```
clean package -Dmaven.test.skip=true
```
빌드가 완료되면 deploy 폴더에 build_project.jar 파일이 생성됨.


## 사용방법
+ 생성된 build_project.jar 파일의 동일한 경로에 R001 폴더를 생성한다. (폴더의 이름은 아무거나 상관 없음)
+ R001 폴더로 이동 후 package.txt 파일 생성한다.
+ packge.txt 파일에 배포하고자 하는 파일들의 full path를 추가해 준다.
+ 다음과 같은 형태로의 file full path 추가
```
/home/nklee/was/wepApps/mySpringProject/resources/bootstrap/img/favicon.png
/home/nklee/was/wepApps/mySpringProject/resources/bootstrap/img/glyphicons-halflings.png
```
+ 여기까지 작업을 한 후 아래 Run을 참고하여 PACKAGE 를 실행하면 R001.tar.gz 파일이 생성된다.
+ R001.tar.gz 파일을 배포하고자 하는 서버에 업로드 한 후 아래 Run을 참고하여 INSTALL을 진행하면 기존 파일은 백업을 한 후 배포가 진행된다. (파일 copy 작업만 이뤄지며 서버 재시작은 하지 않는다.)

## Command option
**_PACKAGE_** : 적용할 소스 패키징  
**_INSTALL_** : 소스 적용  
**_ROLLBACK_** : 소스 원복  
**_PUTALL_** : FTP file upload  

## Run
**java -jar build_project.jar [명령옵션] [패키지번호]**
> PACKAGE example : java -jar build_project.jar PACKAGE R001  
> INSTALL example : java -jar build_project.jar INSTALL R001  
> ROLLBACK example : java -jar build_project.jar ROLLBACK R001  

**java -jar build_project.jar [명령옵션] [패키지번호] [업로드파일명]**
> PUTALL example : java -jar build_project.jar PUTALL R001 R001.tar.gz

## Required
+ build_project.jar 파일이 있는 경로에 패키지번호 디렉토리가 존재해야 함
+ 패키지번호 디렉토리 안에는 적용할 소스 리스트가 기록되어 있는 package.txt 파일이 존재해야 함
+ PUTALL 기능 사용 시 conf.xml 파일에 업로드할 FTP 서버 정보를 기입한다. 해당 파일은 build_project.jar와 같은 경로에 위치해야 함
