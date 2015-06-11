#!/usr/bin/env bash

asciidoctor -o target/cdi-spec.html src/main/doc/cdi-spec.asciidoc

bundle exec guard
