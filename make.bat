@echo off

set jdk=\Program Files\Java\jdk1.6.0_27\bin

set javac="%jdk%\javac.exe"
set jar="%jdk%\jar.exe"

%javac% -cp compiler.jar vjscomp.java

if %ERRORLEVEL% neq 0 exit /b 1

%jar% cmf MANIFEST.MF vjscomp.jar *.class


