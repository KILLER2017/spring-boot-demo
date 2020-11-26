#!/bin/bash
## Author LinkinStar
## UPDATE 2019-01-05
version="1.0.1";

workspace="/root/java/performance"
app="demo"
appName="${app}.jar"
appDirectory="out/artifacts/${app}_jar/${app}.jar"


function start()
{
        count=`ps -ef |grep java|grep $appName|wc -l`
        if [ $count != 0 ];then
          echo "Maybe ${appName} is running, please check it..."
        else
          echo "The ${appName} is starting..."
          if [ -z $profile ]; then
            nohup java -jar ${appDirectory} -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms512M -Xmx4G > /dev/null 2>&1 &
          else
            nohup java -jar ${appDirectory} --spring.profiles.active=${profile} -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms512M -Xmx4G > /dev/null 2>&1 &
          fi
        fi
}

function stop()
{
        appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
        if [ -z $appId ];then
            echo "Maybe $appName not running, please check it..."
        else
        echo "The $appName is stopping..."
        kill $appId
        fi
}

function restart()
{
    profile=$(ps -ef |grep java|grep demo|awk '{print $11}'| awk -F "=" '{print $2}')
    stop
    for i in {3..1}
    do
        echo -n "$i "
        sleep 1
    done
    echo 0
    start
}

function backup()
{
    # get backup version
    backupApp=`ls |grep -wv $releaseApp$ |grep .jar$`

    # create backup dir
    if [ ! -d "backup" ];then
        mkdir backup
    fi

    # backup
    # shellcheck disable=SC2068
    for i in ${backupApp[@]}
    do
        echo "backup" $i
        mv $i backup
    done
}

function status()
{
    appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
        if [ -z $appId ]
        then
            echo -e "\033[31m Not running \033[0m"
        else
            echo -e "\033[32m Running [$appId] \033[0m"
        fi
}


function usage()
{
    echo "Usage: $0 {start|stop|restart|status|stop -f|update}"
    echo "Example: $0 start [profile]"
    exit 1
}

function pull()
{
  git pull
}

function update()
{
  pull
  restart
}

# 将工作目录移动到项目根目录
cd $workspace
profile=$2
case $1 in
        start)
        start;;

        stop)
        stop;;

        restart)
        restart;;

        status)
        status;;

        update)
        update;;

        *)
        usage;;
esac
