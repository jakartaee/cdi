#!/bin/bash

# Require BASH 3 or newer

REQUIRED_BASH_VERSION=3.0.0

if [[ $BASH_VERSION < $REQUIRED_BASH_VERSION ]]; then
  echo "You must use Bash version 3 or newer to run this script"
  exit
fi

# Canonicalise the source dir, allow this script to be called anywhere
DIR=$(cd -P -- "$(dirname -- "$0")" && pwd -P)

# DEFINE

TARGET=target
MASTER=cdi-spec.asciidoc

OUTPUT_FORMATS=("xml" "epub" "pdf")
OUTPUT_CMDS=("asciidoc -b docbook -o \${output_filename} \$MASTER" "a2x -f epub -D \$dir \$MASTER" "a2x --dblatex-opts \"-s custom-asciidoc-dblatex.sty -P latex.output.revhistory=0\" --backend-opts \"-f docbook45.conf\" -D \$dir \$MASTER")

echo "** Building spec"

echo "**** Cleaning $TARGET"
rm -rf $TARGET
mkdir -p $TARGET

output_format=html
dir=$TARGET/$output_format
mkdir -p $dir

echo "**** Processing $MASTER > $TARGET/cdi-spec.html"
asciidoc -n -b html5 -a toc2 -a pygments -f html5.conf -o $TARGET/cdi-spec.html $MASTER

for ((i=0; i < ${#OUTPUT_FORMATS[@]}; i++))
do
   output_format=${OUTPUT_FORMATS[i]}
   dir=$TARGET
   output_filename=$dir/${MASTER//.asciidoc/.$output_format}
   mkdir -p $dir
   echo "**** Processing $MASTER > ${output_filename}"
   eval ${OUTPUT_CMDS[i]}
done

