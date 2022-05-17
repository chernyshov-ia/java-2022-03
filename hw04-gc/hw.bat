@echo off
echo:
echo heap size = %1
java -Xms%1 -Xmx%1 -XX:+UseG1GC -jar .\homework\build\libs\hw04-gc-homework-0.1.jar | findstr /c:"spend"