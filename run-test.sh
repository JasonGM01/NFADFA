#!/bin/sh

set -eu

javac *.java
java Main < test-input.txt
