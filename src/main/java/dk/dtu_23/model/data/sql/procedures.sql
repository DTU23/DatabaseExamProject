DROP PROCEDURE IF EXISTS update_product_batch_status;
DROP PROCEDURE IF EXISTS get_batch_details_from_id;
DROP PROCEDURE IF EXISTS create_product_batch_from_recipe_id;
DROP PROCEDURE IF EXISTS create_produce_batch_from_produce_id;
DROP PROCEDURE IF EXISTS create_recipe;
DROP PROCEDURE IF EXISTS create_produce;
DROP PROCEDURE IF EXISTS delete_produce_batch_from_produce_id;
DROP PROCEDURE IF EXISTS create_recipe_component;
DROP PROCEDURE IF EXISTS create_operator;
DROP PROCEDURE IF EXISTS update_operator;
DROP PROCEDURE IF EXISTS get_operator_name_from_id;

/**
Foreman Procedures
 */
DELIMITER //
CREATE PROCEDURE update_product_batch_status
(IN input_id INT,
IN input_status INT)
BEGIN
  UPDATE productbatch SET
    status=status
  WHERE productbatch.pb_id = input_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_batch_details_from_id
(IN input INT)
BEGIN
  SELECT productbatchcomponent.rb_id, produce_name, supplier, netto, opr_id
  FROM productbatchcomponent NATURAL JOIN  produce NATURAL JOIN  producebatch
  WHERE productbatchcomponent.pb_id = input;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE create_product_batch_from_recipe_id
(IN recipe_id_input INT)
BEGIN
  INSERT INTO productbatch(status, recipe_id) VALUES(0, recipe_id_input);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE create_produce_batch_from_produce_id
(IN input_amount DOUBLE, IN input_rb_id INT)
BEGIN
  INSERT INTO producebatch(amount, produce_id) VALUES(input_amount, input_rb_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_produce_batch_from_produce_id
(IN input_rb_id INT)
BEGIN
  DELETE FROM producebatch WHERE producebatch.produce_id = input_rb_id;
END //
DELIMITER ;

/**
Pharmacist Procedures
 */
DELIMITER //
CREATE PROCEDURE create_recipe
(IN input_recipe_name TEXT)
BEGIN
  INSERT INTO recipe(recipe_name) VALUES(input_recipe_name);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE create_produce
(IN input_produce_name TEXT, IN input_produce_supplier TEXT)
BEGIN
  INSERT INTO produce(produce_name, supplier) VALUES(input_produce_name, input_produce_supplier);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE create_recipe_component
(
  IN input_recipe_id INT,
  IN input_produce_id INT,
  IN input_netto DOUBLE,
  IN input_tolerance DOUBLE
)
BEGIN
  INSERT INTO recipecomponent(recipe_id, produce_id, nom_netto, tolerance)
  VALUES(input_recipe_id, input_produce_id, input_netto, input_tolerance);
END //
DELIMITER ;

/**
Admin procedures
*/
DELIMITER //
CREATE PROCEDURE create_operator
(
  IN input_id INT,
  IN input_name TEXT,
  IN input_initials TEXT,
  IN input_cpr TEXT,
  IN input_password TEXT
)
BEGIN
  INSERT INTO operator(opr_id, opr_name, ini, cpr, password)
  VALUES(input_id, input_name, input_initials, input_cpr, input_password);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_operator
(
  IN input_id INT,
  IN input_name TEXT,
  IN input_initials TEXT,
  IN input_cpr TEXT,
  IN input_password TEXT
)
BEGIN
  UPDATE operator SET
    opr_name=input_name,
    ini = input_initials,
    cpr = input_cpr,
    password = input_password
  WHERE input_id = opr_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_operator_name_from_id
(
  IN input_id INT
)
BEGIN
  SELECT opr_name FROM operator WHERE opr_id = input_id;
END //
DELIMITER ;