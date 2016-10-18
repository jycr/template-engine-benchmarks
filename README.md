# Java Template angine Benchmark

[![GitHub issues](https://img.shields.io/github/issues/jycr/template-engine-benchmarks.svg)](https://github.com/jycr/template-engine-benchmarks/issues)
[![Build Status](https://travis-ci.org/jycr/template-engine-benchmarks.svg?branch=master)](https://travis-ci.org/jycr/template-engine-benchmarks)

This is a benchmark (OpenJDK: jmh) for a collection of Java template engines:

* Java Native - the baseline. manually created page using StringBuilder
* [Freemarker](http://freemarker.org/)
* [Apache Velocity](https://velocity.apache.org/)
* [Rythm](http://rythmengine.org/)
* [HTTL](http://httl.github.io/en/) (Hyper-Text Template Language) 
* [Beetl](http://ibeetl.com/guide/beetl.html)
* [Jamon](http://www.jamon.org)
* [Apache Tomcat Jasper](https://tomcat.apache.org/tomcat-8.5-doc/jasper-howto.html) - JSP and JSPX templating
* [Thymeleaf](http://www.thymeleaf.org/)
* [Mustache.java](https://github.com/spullara/mustache.java)
* [JMustache ](https://github.com/samskivert/jmustache)
* [Handlebars](https://github.com/jknack/handlebars.java)
* [Saxon](http://saxon.sourceforge.net/) - XSL-T templating
* [Groovy Template](http://docs.groovy-lang.org/latest/html/documentation/template-engines.html)
* [Trimou](http://trimou.org/)
  
The versions of template engines can be deduced by looking in the pom.xml file.
Each template engine should have its version suffixed in the template dir inside final JAR.

Benchmark result can also be available at https://travis-ci.org/jycr/template-engine-benchmarks


You need Maven to run the program, just type `mvn clean verify -Pperf` to compile, build and launch benchmarks. 

Should you have any suggestion or comments, please raise issue via 
https://github.com/jycr/template-engine-benchmarks/issues
