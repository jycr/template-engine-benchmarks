#!/bin/sh

function buildGraph(){
	local filename="$1"
	local title="$2"
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
set terminal pngcairo enhanced font "Arial,11"
set output 'target/${filename}.png'
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

plot 'target/${filename}.csv' every ::1 using 0:5:xticlabels(stringcolumn(1)) with boxes ls 1,\
    'target/${filename}.csv' every ::1 using 0:(\$5):(sprintf("%d",\$5)) with labels
EOF

}

function filterData() {
	local templateName="$1"
	local resultFile="jmh-result-benchmark-$templateName"
    grep "$templateName" target/jmh-result.csv | grep -v -e 'Rythm\|JMustache\|Jmte\|Handlebars\|Xslt\|Jasper\|Thymeleaf' | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' > target/${resultFile}.csv
	buildGraph "$resultFile" "Java Template Engine Performance Comparison (template \"${templateName}\")"
    grep "$templateName" target/jmh-result.csv | grep 'JavaNative\|Rocker\|Rythm\|JMustache\|Jmte\|Handlebars\|Xslt\|Jasper\|Thymeleaf' | sed -r 's,^".+?\.([^."]+?)Benchmark[^"]*","\1",' > target/${resultFile}-others.csv
	buildGraph "$resultFile-others" "Java Template Engine Performance Comparison (template \"${templateName}\") ... other engines"
}

filterData "stocks.html"
filterData "response.xml"
