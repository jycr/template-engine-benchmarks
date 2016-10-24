[![GitHub issues](https://img.shields.io/github/issues/jycr/template-engine-benchmarks.svg)](https://github.com/jycr/template-engine-benchmarks/issues)
[![Build Status](https://travis-ci.org/jycr/template-engine-benchmarks.svg?branch=master)](https://travis-ci.org/jycr/template-engine-benchmarks)

template-benchmark
================

JMH benchmark for popular Java template engines:

* Java Native - the baseline. manually created page using byte[] and OutputStream (output Charset : UTF-8)
* [Freemarker](http://freemarker.org/)
* [Groovy Template](http://docs.groovy-lang.org/latest/html/documentation/template-engines.html)
* [Handlebars](https://github.com/jknack/handlebars.java)
* [HTTL](http://httl.github.io/en/) (Hyper-Text Template Language) 
* [Jamon](http://www.jamon.org)
* [Apache Tomcat Jasper](https://tomcat.apache.org/tomcat-8.5-doc/jasper-howto.html) - JSP and JSPX templating
* [Mustache](https://github.com/spullara/mustache.java)
* [Rocker](https://github.com/fizzed/rocker)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Trimou](http://trimou.org/)
* [Twirl](https://github.com/playframework/twirl)
* [Apache Velocity](https://velocity.apache.org/)
* [Saxon](http://saxon.sourceforge.net/) - XSL-T templating


And other engines ... (see branch other-template-engines)

* [Beetl](http://ibeetl.com/guide/beetl.html)
* [JMustache ](https://github.com/samskivert/jmustache)
* [Rythm](http://rythmengine.org/)

NB: The versions of template engines can be deduced by looking in the pom.xml file.
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
