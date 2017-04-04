/* must be dropped in this order to avoid constraint violations */
DROP TABLE IF EXISTS productbatchcomponent;
DROP TABLE IF EXISTS productbatch;
DROP TABLE IF EXISTS operator;
DROP TABLE IF EXISTS recipecomponent;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS producebatch;
DROP TABLE IF EXISTS produce;

CREATE TABLE operator(
   opr_id INT PRIMARY KEY,
   opr_name TEXT,
   ini TEXT,
   cpr TEXT,
   password TEXT,
   admin BOOLEAN DEFAULT FALSE,
   role ENUM('Operator', 'Foreman', 'Pharmacist', 'None') DEFAULT 'None'
) ENGINE=innoDB;
 
CREATE TABLE produce(produce_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, produce_name TEXT, supplier TEXT) ENGINE=innoDB;

CREATE TABLE producebatch(rb_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, produce_id INT, amount REAL,
   FOREIGN KEY (produce_id) REFERENCES produce(produce_id)) ENGINE=innoDB;

CREATE TABLE recipe(recipe_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, recipe_name TEXT) ENGINE=innoDB;

CREATE TABLE recipecomponent(recipe_id INT, produce_id INT, nom_netto REAL, tolerance REAL,
   PRIMARY KEY (recipe_id, produce_id),
   FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
   FOREIGN KEY (produce_id) REFERENCES produce(produce_id)) ENGINE=innoDB;

CREATE TABLE productbatch(pb_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, status INT, recipe_id INT,
   FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)) ENGINE=innoDB;

CREATE TABLE productbatchcomponent(pb_id INT, rb_id INT, tara REAL, netto REAL, opr_id INT,
   PRIMARY KEY (pb_id, rb_id),
   FOREIGN KEY (pb_id) REFERENCES productbatch(pb_id),
   FOREIGN KEY (rb_id) REFERENCES producebatch(rb_id),
   FOREIGN KEY (opr_id) REFERENCES operator(opr_id)) ENGINE=innoDB;


INSERT INTO operator(opr_id, opr_name, ini, cpr, password, admin, role) VALUES
(1, 'Angelo A', 'AA', '070770-7007', 'lKje4fa', FALSE, 'Foreman'),
(2, 'Antonella B', 'AB', '080880-8008', 'atoJ21v', FALSE, 'Pharmacist'),
(3, 'Luigi C', 'LC', '090990-9009', 'jEfm5aQ', FALSE, 'Operator'),
(4, 'Super Admin', 'SA', '000000-0000', 'admin', TRUE, 'Pharmacist');

INSERT INTO produce(produce_id, produce_name, supplier) VALUES
(1, 'dej', 'Wawelka'),
(2, 'tomat', 'Knoor'),
(3, 'tomat', 'Veaubais'),
(4, 'tomat', 'Franz'),
(5, 'ost', 'Ost og Skinke A/S'),
(6, 'skinke', 'Ost og Skinke A/S'),
(7, 'champignon', 'Igloo Frostvarer');

INSERT INTO producebatch(rb_id, produce_id, amount) VALUES
(1, 1, 1000),
(2, 2, 300),
(3, 3, 300),
(4, 5, 100),
(5, 5, 100), 
(6, 6, 100),
(7, 7, 100);

INSERT INTO recipe(recipe_id, recipe_name) VALUES
(1, 'margherita'),
(2, 'prosciutto'),
(3, 'capricciosa');


INSERT INTO recipecomponent(recipe_id, produce_id, nom_netto, tolerance) VALUES
(1, 1, 10.0, 0.1),
(1, 2, 2.0, 0.1),
(1, 5, 2.0, 0.1),

(2, 1, 10.0, 0.1),
(2, 3, 2.0, 0.1),  
(2, 5, 1.5, 0.1),
(2, 6, 1.5, 0.1),

(3, 1, 10.0, 0.1),
(3, 4, 1.5, 0.1),
(3, 5, 1.5, 0.1),
(3, 6, 1.0, 0.1),
(3, 7, 1.0, 0.1);

INSERT INTO productbatch(pb_id, recipe_id, status) VALUES
(1, 1, 2), 
(2, 1, 2),
(3, 2, 2),
(4, 3, 1),
(5, 3, 0);


INSERT INTO productbatchcomponent(pb_id, rb_id, tara, netto, opr_id) VALUES
(1, 1, 0.5, 10.05, 1),
(1, 2, 0.5, 2.03, 1),
(1, 4, 0.5, 1.98, 1),

(2, 1, 0.5, 10.01, 2),
(2, 2, 0.5, 1.99, 2),
(2, 5, 0.5, 1.47, 2),

(3, 1, 0.5, 10.07, 1),
(3, 3, 0.5, 2.06, 2),
(3, 4, 0.5, 1.55, 1),
(3, 6, 0.5, 1.53, 2),

(4, 1, 0.5, 10.02, 3),
(4, 5, 0.5, 1.57, 3),
(4, 6, 0.5, 1.03, 3),
(4, 7, 0.5, 0.99, 3);


 