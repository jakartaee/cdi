# CDI Specification document generation

This module contains Asciidoc sources and configuration to generate CDI documentation in HTML and PDF format
for both Apache License 2 and Java Community Process License.

## Generating the documentation

Just enter `mvn` at the command line. Maven will generate final doc in `target/publish/html` and `target/publish/docbook/publish/en-US/pdf` directories

## License in doc

The documentation can be generated for 2 licenses : Apache License v 2.0 (by default) and JCP license.
To generate doc with JCP license just enter `mvn -Pjcp`

## Working on the doc in liverelaod mode

There are helpers to ease the work on the doc. If you have ruby installed you can use bundle to install needed gems:

`bundle install`

After that, just launch `guard.sh` script to have the html version of the spec generated at each change. You may need to install the liverelaod plugin for your browser to have it relaoding automatically the generated page. 

