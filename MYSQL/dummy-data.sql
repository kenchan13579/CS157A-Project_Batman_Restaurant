INSERT INTO EMPLOYEE (lastname,firstName,position,lastworked,email) VALUES ("May","b","Server",curdate() , "mayb@batman.com") ;
INSERT INTO EMPLOYEE (lastname,firstName,position,lastworked,email) VALUES ("Paul","W","Supervisor",curdate() ,"paulw@batman.com" );
INSERT INTO EMPLOYEE (lastname,firstName,position,lastworked,email) VALUES ("Ken","C","Busboy",curdate(), "kenc@batman.com");
INSERT INTO EMPLOYEE (lastname,firstName,position,lastworked,email) VALUES ("Phuc","N","Server",curdate() , "phucn@batman.com");
INSERT INTO EMPLOYEE (lastname,firstName,position,lastworked,email) VALUES ("Kate","v","Server",curdate(), "katev@batman.com");

INSERT INTO MENU (itemName, description,price,type) VALUES ("Rice","rice",2,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Noodle","noodle",3,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Coke","coke",2,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Sprite","sprite",2,"beverage");

INSERT INTO CUSTOMER (lastName,firstName,email) VALUES ("David" ,"T","davidt@gmail.com");
INSERT INTO CUSTOMER (lastName,firstName,email) VALUES ("Angelababy" ,"l" , "angelababyl@aol.net");
INSERT INTO CUSTOMER (lastName,firstName,email) VALUES ("Ashley" ,"Z","ashleyz@hotmail.com");

INSERT INTO aTable (seats, available,eID) VALUES (4,true ,(select eID FROM EMPLOYEE WHERE email="mayb@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (1,true, (select eID FROM EMPLOYEE WHERE email="mayb@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (1,true,(select eID FROM EMPLOYEE WHERE email="mayb@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (1,true,(select eID FROM EMPLOYEE WHERE email="mayb@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (2,true,(select eID FROM EMPLOYEE WHERE email="phucn@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (2,true,(select eID FROM EMPLOYEE WHERE email="phucn@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (2,true,(select eID FROM EMPLOYEE WHERE email="phucn@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (2,true,(select eID FROM EMPLOYEE WHERE email="phucn@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="phucn@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="katev@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="katev@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="katev@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="katev@batman.com"));
INSERT INTO aTable (seats, available,eID) VALUES (4,true,(select eID FROM EMPLOYEE WHERE email="katev@batman.com"));
