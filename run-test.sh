#!/bin/sh

set -eu

javac Main.java NFADFA.java
java Main < test-input.txt
