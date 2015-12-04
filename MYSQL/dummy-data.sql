# EMPLOYEES
INSERT INTO EMPLOYEE (firstname,lastname,position,lastworked,email) VALUES ("May","b","Server",curdate() , "mayb@batman.com") ;
INSERT INTO EMPLOYEE (firstname,lastname,position,lastworked,email) VALUES ("Paul","W","Supervisor",curdate() ,"paulw@batman.com" );
INSERT INTO EMPLOYEE (firstname,lastname,position,lastworked,email) VALUES ("Ken","C","Busboy",curdate(), "kenc@batman.com");
INSERT INTO EMPLOYEE (firstname,lastname,position,lastworked,email) VALUES ("Phuc","N","Server",curdate() , "phucn@batman.com");
INSERT INTO EMPLOYEE (firstname,lastname,position,lastworked,email) VALUES ("Kate","v","Server",curdate(), "katev@batman.com");

# FOOD

INSERT INTO MENU (itemName, description,price,type) VALUES ("Spring Rolls","A fresh spring rolls with pork and shrimp",6.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Crispy Imperial Rolls","Deep fried cripsy rolls with pork and carrot",6.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Crispy Crepe","Fried and crispy creep, eat with fish sauce",8.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Salt and Pepper Calamari","Stir-fried calarami with salt and pepper",9.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Shrimp Cake","Deep fried shrimp cake with outer tofu layer",7.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Steak Noodle Soup","Traditional Vietnamese soup called Pho with thin layer of steak on top of rice noodle and delicious beef broth",9.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Brisket Noodle Soup","Traditional Vietnamese soup called Pho with thin layer of brisket on top of rice noodle and delicious beef broth",9.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Steak and Meatball Noodle Soup","Traditional Vietnamese soup called Pho with thin layer of steak combined with meatballs on top of rice noodle and delicious beef broth",12.5,"food");

INSERT INTO MENU (itemName, description,price,type) VALUES ("Shaking Beef","Stir fried beef with house special sauce, lettuce and tomato",19.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Basil Eggplant with Chicken","Stir fried basil with eggplant in house special sauce with chicken",13.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("House Curry Chicken","Stir fried chicken and curry flavor deep in coconut milk sauce",12.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("House Special Chicken","Deep fried marinated chicken then get stir fried with house special chili sauce",13.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Spicy Lemongrass Beef","Stir fried beef with lemongrass and chili in house special sauce",14.5,"food");

INSERT INTO MENU (itemName, description,price,type) VALUES ("Tofu Panfried Rice Noodle","Pan fried noodle with tofu and stired fried vegetable",10.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Mixed Vegetable With Tofu","Stir Fried tofu with vegetable",10.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Salt and Pepper Tofu","Deep fried tofu then get stir fried with chili pepper and pepper and salt",9.5,"food");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Crispy Egg Noodle with Vegetable and Tofu","Deep fried egg noodle served with stir fried vegetable and tofu on top",10.5,"food");

# DRINK
INSERT INTO MENU (itemName, description,price,type) VALUES ("Sprite","sprite",2.5,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Coke","Coke",2.5,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Freshly Squeeze Lemonade","Freshly squeezed lemonade",3.9,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Thai ice tea","Black Thai tea served cold with half and half milk",3.95,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Strawberry Lemonade","Freshly squeezed lemonade served cold with blended strawberry",4.95,"beverage");

INSERT INTO MENU (itemName, description,price,type) VALUES ("Saigon Beer","Imported Saigon Beer from Vietnam, strong in flavor and color",3.95,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Corona","Imported Beer from Mexico",3.95,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Affligen Blonde","Yellow beer bottle from Argentina",4.25,"beverage");

INSERT INTO MENU (itemName, description,price,type) VALUES ("Chardonay, J. Lord","Favorite Chardonay wine in the region",10.5,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Sauvingon Blanc","Great white wine",8.95,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Pinor Noir, Educated Guess","Favirote california wine",9.5,"beverage");
INSERT INTO MENU (itemName, description,price,type) VALUES ("Cabernet Sauvingnon","Favorite california wine",9.5,"beverage");

# CUSTOMERS

INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("David" ,"T","davidt@gmail.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Angelababy" ,"l" , "angelababyl@aol.net");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Ashley" ,"Z","ashleyz@hotmail.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Daisy" ,"R","daisyridley@starwars.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Mark" ,"H","markhamill@starwars.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("John" ,"B","johnboyoga@starwars.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("JJ" ,"Abrams","jjabrams@starwars.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Andrew" ,"Kramer","andrewkramer@starwars.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Kim" ,"P","kimp@sjsu.edu");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Sami" ,"Khuri","samikhuri@sjsu.edu");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Steve" ,"Jobs","stevejobs@apple.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Jon" ,"Nguyen","jon@abc.com");
INSERT INTO CUSTOMER (firstname,lastname,email) VALUES ("Phuc" ,"N","phucn@batman.com");



# TABLES
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


# RATING
INSERT INTO RATING (cID, stars, feedback) VALUES (2, 4,  "Great restaurant with good food and good price");
INSERT INTO RATING (cID, stars, feedback) VALUES (5, 4,  "This has been our second date at this restaurant, great experience. Will come back");
INSERT INTO RATING (cID, stars, feedback) VALUES (8, 5,  "Nice atmosphere, friendly service, great food");
INSERT INTO RATING (cID, stars, feedback) VALUES (4, 5,  "Love the soup, great flavor and good price");

# RESERVATIONS
INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (1, 2, 3, curdate());
INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (4, 5, 4, curdate());
INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (5, 7, 1, curdate());
INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (8, 1, 2, curdate());
INSERT INTO RESERVATION (tID, cID, partySize, reservationDate) VALUES (9, 4, 3, curdate());