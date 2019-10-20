@echo off
echo [INFO] Use maven tomcat6-plugin run the project.

%~d0
cd %~dp0
cd ..

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m
call mvn tomcat6:run

cd bin
pause
