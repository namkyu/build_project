## Project summary
파일 단위 배포 project
상용 서버에 파일 단위로 배포를 할 수 있게 소스를 package, install, rollback 해주는 유틸리티 프로그램

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