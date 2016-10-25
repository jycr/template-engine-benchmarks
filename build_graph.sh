#!/bin/sh

#set -o errexit -o nounset
# errexit: stop executing if any errors occur, by default bash will just continue past any errors to run the next command
# nounset: stop executing if an unset variable is encountered, by default bash will use an empty string for the value of such variables.
set -o errexit


TARGET_DIR=benchmark-launcher/target

function buildGraphWithGnuPlot(){
  local filename="$1"
  local title="$2"
  local format="$3"
  local renderer="$4"
  if [ -z "$renderer" ]; then
    renderer="$format"
  fi
gnuplot <<EOF
  # Labels
set title '${title}'
set ylabel 'Template generation time (ns)'
set xlabel 'Template Engine'
set xtics nomirror rotate by -40

# Ranges
set autoscale

# Input
set datafile separator ','

# Output
set terminal $renderer enhanced font "Arial,11"
set output '$TARGET_DIR/${filename}.$format'
set grid
set key off
set boxwidth 0.9 relative

# box style
set style line 1 lc rgb '#1abc9c' lt 1
set style fill solid

# remove top and right borders
set style line 2 lc rgb '#808080' lt 1
set border 3 back ls 2
set tics nomirror

plot '$TARGET_DIR/${filename}.csv' every ::1 using 0:5:xticlabels(stringcolumn(1)) with boxes ls 1,\
    '$TARGET_DIR/${filename}.csv' every ::1 using 0:(\$5):(sprintf("%d",\$5)) with labels
EOF

}

function buildGraph(){
  buildGraphWithGnuPlot "$1" "$2" png pngcairo
  buildGraphWithGnuPlot "$1" "$2" svg
}

function filterData() {
  local templateName="$1"
  local resultFile="jmh-result-benchmark-$templateName"
  local otherEngines='Rythm\|JMustache\|Jmte\|Handlebars\|Xslt\|Jasper\|Thymeleaf\|Jamon\|Trimou'
  
  head -1 $TARGET_DIR/jmh-result.csv > $TARGET_DIR/${resultFile}.csv
  grep "$templateName" $TARGET_DIR/jmh-result.csv | grep 'JavaNative' | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' >> $TARGET_DIR/${resultFile}.csv
  grep "$templateName" $TARGET_DIR/jmh-result.csv | grep -v -e 'JavaNative\|'${otherEngines} | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' >> $TARGET_DIR/${resultFile}.csv
  buildGraph "$resultFile" "Java Template Engine Performance Comparison (template \"${templateName}\")"
  
  head -1 $TARGET_DIR/jmh-result.csv > $TARGET_DIR/${resultFile}-others.csv
  grep "$templateName" $TARGET_DIR/jmh-result.csv | grep 'JavaNative' | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' >> $TARGET_DIR/${resultFile}-others.csv
  grep "$templateName" $TARGET_DIR/jmh-result.csv | grep ${otherEngines} | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' >> $TARGET_DIR/${resultFile}-others.csv
  buildGraph "$resultFile-others" "Java Template Engine Performance Comparison (template \"${templateName}\") ... other engines"
}

filterData "stocks.html"
filterData "response.xml"

pushd "$TARGET_DIR"
	git config --global user.name "Travis"
	git config --global user.email "travis@travis-ci.org"
	git clone --quiet https://$GH_TOKEN@github.com/jycr/template-engine-benchmarks.wiki.git
	pushd "template-engine-benchmarks.wiki"
		mv ../jmh*.svg ../jmh*.png graph/
		git add graph/*
		git push origin
	popd
popd