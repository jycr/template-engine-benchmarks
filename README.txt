==============================================================================
              JAVA TEMPLATE ENGINE BENCHMARKER jdk8
==============================================================================

This is a benchmark program for a collection of Java template engines:
  * stringbuilder - the baseline. manually created page using StringBulder
  * freemarker
  * velocity
  * rythm
  * httl
  * beetl
  * jamon
  * jangod
  * thymeleaf
  * mustache.java
  * jmustache 
  * handlebars
  
The versions of template engines can be deduced by looking in the lib directory.
Each template engine should have its version suffixed in the jar name.

You can use `gradle update` to update the jars to their latest version but beware that beetl and jangod do not 
have any entries in maven central and thus will be deleted.

https://travis-ci.org/agentgt/template-engine-benchmarks

You need apache ant to run the program, just type "ant" and it will start. 
This program accept parameters to configure how to run the benchmark:

 1. out: specify how to output rendering result, could be:
   * os: output to OutputStream (default);
   * w:  output to Writer
   * s:  return String

 2. wtimes: specify number of warm up loops.
    default: 100

 3. ntimes: specify number of benchmark loops.
    default: 10000

 4. buf: specify whether use BufferedWriter or OS
    default: true
    
 5. rythm.new: for rythm engine only. specify whether use new API to
    run rythm benchmark or not. Default: false

The arguments should be passed in using property setting. e.g

   ant -Dout=w \
       -Dwtimes=10 \
       -Dntimes=1000 \
       -Dbuf=false \
       -Drythm.new=true

Should you have any suggestion or comments, please raise issue via 
https://github.com/greenlaw110/template-engine-benchmarks/issues

Note since jangod and thymeleaf is way slow than all others, they are not put into the default target. 
To benchmark jangod and thymeleaf, run `ant all`
