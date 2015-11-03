:: Batch file (Windows OS)
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: WARNING!!! Don't run this file under Eclipse! Run this script under command line.
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: this way we turn off all redundant output info
ECHO OFF

:: you can set PATH environment variable this way (no need, if the one was already set)
:: SET PATH=[path to java_home\bin]

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: this way we compile all tne source java files in ua\nure\kolesnikov\Task1
javac ua\nure\kolesnikov\Task1\*.java
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: this way we run all ten subtasks
java ua.nure.kolesnikov.Task1.Demo
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: this way we remove all compiled class files 
:: (you cannot commit class files into the svn repository)
DEL ua\nure\kolesnikov\Task1\*.class
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::