echo "> Git Pull"

git pull

echo "> 프로젝트 Build 시작"

./gradlew clean build

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(lsof -t -i:8080)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -9 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

nohup java -Duser.timezone=Asia/Seoul -jar build/libs/*.jar &