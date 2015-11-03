#!/bin/sh
###############################################################################
# Unix shell script (Linux OS)
###############################################################################

# you can set PATH environment variable this way (no need, if the one was already set)
# PATH=$PATH:[path to java_home/bin]

###############################################################################
# this way we compile all tne source java files in ua/nure/kolesnikov/Task1
# you must set appropriate encoding if it is different of the default encoding in your OS
javac -encoding cp1251 ua/nure/kolesnikov/Task1/*.java
###############################################################################

###############################################################################
# this way we run all ten subtasks
java ua.nure.kolesnikov.Task1.Demo
###############################################################################

###############################################################################
# this way we remove all compiled class files 
# (you cannot commit class files into the svn repository)
rm -rf ./ua/nure/kolesnikov/Task1/*.class
###############################################################################