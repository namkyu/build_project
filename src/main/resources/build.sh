#!/bin/sh

BUILD_HOME="/svc/tad/RELEASE"

LIB_CLASSPATH="${BUILD_HOME}/lib/commons-io-1.4.jar"
LIB_CLASSPATH="${BUILD_HOME}/lib/commons-lang-2.4.jar:${LIB_CLASSPATH}"
LIB_CLASSPATH="${BUILD_HOME}/lib/commons-lang3-3.1.jar:${LIB_CLASSPATH}"
LIB_CLASSPATH="${BUILD_HOME}/lib/mysql-connector-java-5.1.13-bin.jar:${LIB_CLASSPATH}"

#echo "$LIB_CLASSPATH"

java -Xms64m -Xmx64m -classpath ${LIB_CLASSPATH}:. com.release.BuildManager "$1"