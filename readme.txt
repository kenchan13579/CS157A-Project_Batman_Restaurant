Database: schema-set-up.sql
Initial data: dummy-data.sql

Window
To Compile:
	If Java 1.8: copy mysql jar into src\lib\
	If Java 1.7: copy jfxrt.jar and mysql jar into src\lib\

	jfxrt.jar was located in c:\...\jdk1.8[.0_20]\jre\lib\ext\

	javac -cp lib\* *.java


To Run:

	java -cp lib\*;. Main

MAC OS X
1. Copy mySQL JConnector jar file to src/lib (create 'lib' folder if it has not been created, Google and download the jar file)
2. Compile in terminal: javac -cp lib/* *.java (ignore the warning messages)
3. Run: java -cp lib/*:. Main


MYSQL Username/Password = 'root' / '' (no password)
	if need to change password, set in ConnectionFactory.java

Admin Email/Password = 'admin' / 'admin'

Customer Email/First/Last = 'jon@abc.com' / 'Jon' / 'Nguyen'
