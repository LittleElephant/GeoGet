#!/bin/bash

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

javac -d "$DIR/bin/" -classpath "$DIR/lib/json-simple-1.1.1.jar" -sourcepath "$DIR/src" "$DIR/src/geoget/Main.java"
