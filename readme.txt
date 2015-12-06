Database: schema-set-up.sql
Initial data: dummy-data.sql


To Compile:
	If Java 1.8: copy mysql jar into src\lib\
	If Java 1.7: copy jfxrt.jar and mysql jar into src\lib\

	jfxrt.jar was located in c:\...\jdk1.8[.0_20]\jre\lib\ext\

	javac -cp lib\* *.java


To Run:

	java -cp lib\*;. Main



MYSQL Username/Password = 'root' / '' (no password)
	if need to change password, set in ConnectionFactory.java

Admin Email/Password = 'admin' / 'admin'

Customer Email/First/Last = 'jon@abc.com' / 'Jon' / 'Nguyen'