@echo off
echo:
echo heap size = %1
java -Xms%1 -Xmx%1 -XX:+UseG1GC -jar .\optimized\build\libs\hw04-gc-opt.jar | findstr /c:"spend"