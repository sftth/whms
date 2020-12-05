#!/bin/sh

springbootPid=$(pgrep java)

echo ${springbootPid}

if [ -n "${springbootPid}" ]
then
     result=$(kill -9 ${springbootPid})
     echo springboot Process is killed.
else
     echo running springboot is not found.
fi


sleep 1
echo start springboot jar
#nohup java -jar -Dspring.profiles.active=local /sorc001/sftth318/applications/whms-1.0.jar 1>/dev/null 2>&1 &
java -jar -Dspring.profiles.active=dev /sorc001/sftth318/applications/whms-1.0.jar

echo ps aux | grep java