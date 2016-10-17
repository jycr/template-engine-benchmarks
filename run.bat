@echo off

set POM=.\pom.xml


call mvn -f %POM% clean install -o -Dmaven.test.skip=true

call java -jar target\template-engine-benchmarks-0.0.1-SNAPSHOT.jar > run.log
