@ECHO OFF
REM Copyright (c) 2016 Kevin Berendsen
REM Author of Throne

@ECHO Throne Broker
@ECHO Setting paths and files...

REM Setting paths and files
set BIN=%~dp0
set THRONE=%BIN%..\
set LOG_FILE=%THRONE%config\throne-log.properties

@ECHO Setting classpath...

REM Classpaths
set CP=%THRONE%lib\*

@ECHO Setting JVM arguments...

REM JVM Args
set JAVA_ARGS=-XX:+HeapDumpOnOutOfMemoryError -Djava.awt.headless=true
set JAVA_ARGS=%JAVA_ARGS% -Dlog4j.configuration="file:%LOG_FILE%"
set JAVA_ARGS=%JAVA_ARGS% -Dmoquette.path=%THRONE% 
set JAVA_ARGS=%JAVA_ARGS% -Dmoquette.path=%THRONE% 

@ECHO Launching server...

java -server %JAVA_ARGS% -cp %CP% nl.kevinberendsen.throne.EmbeddedServer
PAUSE