# Java Template angine Benchmark

This is a benchmark program for a collection of Java template engines:

* Java Native - the baseline. manually created page using StringBuilder
* Freemarker
* Velocity
* Rythm
* Httl
* Beetl
* Jamon
* Thymeleaf
* Mustache.java
* JMustache 
* Handlebars
* Xslt (Saxon)
* Groovy Template
  
The versions of template engines can be deduced by looking in the pom.xml file.
Each template engine should have its version suffixed in the template dir inside final JAR.

Benchmark result can also be available at https://travis-ci.org/jycr/template-engine-benchmarks


You need Maven to run the program, just type `mvn clean verify -Pperf` to compile, build and launch benchmarks. 

Should you have any suggestion or comments, please raise issue via 
https://github.com/jycr/template-engine-benchmarks/issues
