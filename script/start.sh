#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITIORY=/home/ec2-user/app/step3

PROJECT_NAME=whms

echo "> Build 파일 복사"
echo "> cp $REPOSITIORY/zip/*.jar $REPOSITIORY"

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
-Dspring.config.location=classpath:/application.properties,classpath:/application-dev.properties,/home/ec2-user/resources/application-oauth.properties,/home/ec2-user/resources/application-real-db.properties \
-Dspring.profiles.active=$IDLE_PROFILE \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &