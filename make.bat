@echo off

set jdk=\Program Files\Java\jdk1.7.0_01\bin

set javac="%jdk%\javac.exe"
set jar="%jdk%\jar.exe"

%javac% -cp compiler.jar vjscomp.java

if %ERRORLEVEL% neq 0 exit /b 1

%jar% cmf MANIFEST.MF vjscomp.jar *.class


