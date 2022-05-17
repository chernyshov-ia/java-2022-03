@echo off
set jf=.\optimized\build\libs\hw04-gc-opt.jar

for /L %%B in (256,10,500) do (
  echo:
  echo heap = %%Bm
  java -Xms%%Bm -Xmx%%Bm -XX:+UseG1GC -jar %jf% | findstr /c:"spend"    
)

for /L %%B in (550,50,2000) do (
  echo:
  echo heap = %%Bm
  java -Xms%%Bm -Xmx%%Bm -XX:+UseG1GC -jar %jf% | findstr /c:"spend"    
)

set jf=