DROP PROCEDURE IF EXISTS create_operator;
DROP PROCEDURE IF EXISTS update_operator;
DROP PROCEDURE IF EXISTS create_produce_batch_from_produce_id;
DROP PROCEDURE IF EXISTS update_produce_batch_by_id;
DROP PROCEDURE IF EXISTS delete_produce_batch_by_id;
DROP PROCEDURE IF EXISTS create_produce;
DROP PROCEDURE IF EXISTS update_produce_by_id;
DROP PROCEDURE IF EXISTS create_product_batch_component;
DROP PROCEDURE IF EXISTS get_product_batch_component_supplier_details_by_rb_id;
DROP PROCEDURE IF EXISTS create_product_batch_from_recipe_id;
DROP PROCEDURE IF EXISTS update_product_batch_status;
DROP PROCEDURE IF EXISTS create_recipe;
DROP PROCEDURE IF EXISTS create_recipe_component;

/**
Operator
 */
DELIMITER //
CREATE PROCEDURE create_operator
(
  IN input_id INT,
  IN input_name TEXT,
  IN input_initials TEXT,
  IN input_cpr TEXT,
  IN input_password TEXT,
  IN input_admin BOOLEAN,
  IN input_role TEXT
)
BEGIN
  INSERT INTO operator(opr_id, opr_name, ini, cpr, password, admin, role)
  VALUES(input_id,input_name,input_initials,input_cpr,input_password,input_admin,input_role);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_operator
(
  IN input_id INT,
  IN input_name TEXT,
  IN input_initials TEXT,
  IN input_cpr TEXT,
  IN input_password TEXT,
  IN input_admin BOOLEAN,
  IN input_role TEXT
)
BEGIN
  UPDATE operator SET
    opr_name = input_name,
    ini = input_initials,
    cpr = input_cpr,
    password = input_password,
    admin = input_admin,
    role = input_role
  WHERE input_id = opr_id;
END //
DELIMITER ;

/**
Produce_batch
 */
DELIMITER //
CREATE PROCEDURE create_produce_batch_from_produce_id
(IN input_amount DOUBLE, IN input_produce_id INT)
BEGIN
  INSERT INTO producebatch(amount, produce_id) VALUES(input_amount, input_produce_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_produce_batch_by_id
(IN input_rb_id INT, IN input_amount DOUBLE)
BEGIN
  UPDATE producebatch SET
    amount = input_amount
  WHERE input_rb_id = rb_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_produce_batch_by_id
(IN input_rb_id INT)
BEGIN
  DELETE FROM producebatch
  WHERE producebatch.produce_id = input_rb_id;
END //
DELIMITER ;

/**
Produce
 */
DELIMITER //
CREATE PROCEDURE create_produce
(IN input_produce_name TEXT, IN input_supplier TEXT)
BEGIN
  INSERT INTO produce(produce_name, supplier) VALUES(input_produce_name, input_supplier);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_produce_by_id
(IN input_produce_id INT, IN input_produce_name TEXT, IN input_supplier TEXT)
BEGIN
  UPDATE produce SET
    produce_name = input_produce_name,
    supplier = input_supplier
  WHERE input_produce_id = produce_id;
END //
DELIMITER ;

/**
Product_batch_component
 */
DELIMITER //
CREATE PROCEDURE create_product_batch_component
(
  IN input_pb_id INT,
  IN input_rb_id INT,
  IN input_tara DOUBLE,
  IN input_netto DOUBLE,
  IN input_opr_id INT)
BEGIN
  INSERT INTO productbatchcomponent(pb_id,rb_id,tara,netto,opr_id)
  VALUES(input_pb_id,input_rb_id,input_tara,input_netto,input_opr_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_product_batch_component_supplier_details_by_rb_id
(IN input_rb_id INT)
BEGIN
  SELECT productbatchcomponent.rb_id, produce_name, supplier, netto, opr_id
  FROM productbatchcomponent NATURAL JOIN produce NATURAL JOIN producebatch
  WHERE productbatchcomponent.pb_id = input_rb_id;
END //
DELIMITER ;

/**
Product_batch
 */
DELIMITER //
CREATE PROCEDURE create_product_batch_from_recipe_id
(IN recipe_id_input INT)
BEGIN
  INSERT INTO productbatch(status, recipe_id) VALUES(0, recipe_id_input);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_product_batch_status
(IN input_pb_id INT, IN input_status INT)
BEGIN
  UPDATE productbatch SET
    status = input_status
  WHERE productbatch.pb_id = input_pb_id;
END //
DELIMITER ;

/**
Recipe
 */
DELIMITER //
CREATE PROCEDURE create_recipe
(IN input_recipe_name TEXT)
BEGIN
  INSERT INTO recipe(recipe_name) VALUES(input_recipe_name);
END //
DELIMITER ;

/**
Recipe_component
 */
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