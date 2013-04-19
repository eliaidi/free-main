#!/bin/bash
isUpdate=$1
deploy=$2

if [ "$deploy" != "test" ];then
   if [ "$deploy" != "product" ];then
        echo "you package choose test or product..."
        exit 1
   fi
fi

projectDir=/opt/project
mkfreeDir=/opt/mkfree


cd $projectDir/free-main

if [ "$isUpdate" = "y" ];then
    git pull
fi

mvn -P$deploy -Dmaven.test.skip=true clean package
rm -rf $mkfreeDir/free-blog/WEB-INF/lib/*
unzip -o $projectDir/free-main/free-blog/target/free-blog-0.0.1-SNAPSHOT.war -d $mkfreeDir/free-blog/

cp free-apithrift/target/free-apithrift-0.0.1-SNAPSHOT.jar $mkfreeDir/free-apiservice/lib
cp free-framework/target/free-framework-0.0.1-SNAPSHOT.jar $mkfreeDir/free-apiservice/lib
cp free-apiservice/target/free-apiservice-0.0.1-SNAPSHOT.jar $mkfreeDir/free-apiservice/

/opt/mkfree/free-apiservice/bin/apiservice restart
/opt/module/jetty/current/bin/jetty.sh restart
