[![GitHub issues](https://img.shields.io/github/issues/jycr/template-engine-benchmarks.svg)](https://github.com/jycr/template-engine-benchmarks/issues)
[![Build Status](https://travis-ci.org/jycr/template-engine-benchmarks.svg?branch=master)](https://travis-ci.org/jycr/template-engine-benchmarks)

template-benchmark
================

JMH benchmark for popular Java template engines:

* Java Native - the baseline. manually created page using char[] and Writer
* [Freemarker](http://freemarker.org/)
* [Mustache](https://github.com/spullara/mustache.java)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Trimou](http://trimou.org/)
* [Apache Velocity](https://velocity.apache.org/)
* [Saxon](http://saxon.sourceforge.net/) - XSL-T templating
* [Groovy Template](http://docs.groovy-lang.org/latest/html/documentation/template-engines.html)
* [JMustache ](https://github.com/samskivert/jmustache)
* [Handlebars](https://github.com/jknack/handlebars.java)
* [Beetl](http://ibeetl.com/guide/beetl.html)
* [Jamon](http://www.jamon.org)
* [Apache Tomcat Jasper](https://tomcat.apache.org/tomcat-8.5-doc/jasper-howto.html) - JSP and JSPX templating
* [Rythm](http://rythmengine.org/)
* [HTTL](http://httl.github.io/en/) (Hyper-Text Template Language) 
  
The versions of template engines can be deduced by looking in the pom.xml file.
Each template engine should have its version suffixed in the template dir inside final JAR.

Running the benchmark
======================


Download the source code and build it:
`mvn clean verify`

If you want to compile, build and launch benchmarks:
`mvn clean verify -Pperf`

Run the entire benchmark suite:
`java -jar target/template-engine-benchmarks-0.0.1-SNAPSHOT.jar`

To run a single benchmark, such as Mustache, use:
`java -jar target/template-engine-benchmarks-0.0.1-SNAPSHOT.jar MustacheBenchmark`

Example Results
===============

Benchmark result can also be available at https://travis-ci.org/jycr/template-engine-benchmarks

[![JMH Results](https://img.shields.io/github/issues/jycr/template-engine-benchmarks.svg)](https://github.com/jycr/template-engine-benchmarks/issues)
