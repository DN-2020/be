#!/bin/bash

REPOSITORY=/home/dn2020/reserve/be

cd $REPOSITORY

echo "> 버전 최신화"

git pull

mvn clean

echo "> 프로젝트 Build 시작"

mvn package

echo "> Build 파일 복사"

cp ./target/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f db2020)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls $REPOSITORY/ |grep 'db2020' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar $REPOSITORY/$JAR_NAME &


echo -e " > 배포 완료"

