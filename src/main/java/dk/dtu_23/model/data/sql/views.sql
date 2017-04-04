# operator (Admin)
CREATE OR REPLACE VIEW operator_list AS
  SELECT opr_id, opr_name, cpr, admin, role
  FROM operator;

# produce (Foreman)
CREATE OR REPLACE VIEW produce_overview AS
  SELECT produce.produce_id, produce.produce_name, SUM(producebatch.amount) AS "amount"
  FROM produce NATURAL JOIN producebatch
  GROUP BY produce.produce_name;

# produce_batch (Foreman)
CREATE OR REPLACE VIEW produce_batch_list AS
  SELECT producebatch.rb_id, produce.produce_name, produce.supplier, producebatch.amount
  FROM producebatch INNER JOIN produce WHERE produce.produce_id = producebatch.produce_id;

# product_batch (Foreman)
CREATE OR REPLACE VIEW product_batch_list AS
  SELECT productbatch.recipe_id, productbatch.status, recipe.recipe_name
  FROM productbatch NATURAL JOIN recipe;

# product_batch_component (Pharmacist)
CREATE OR REPLACE VIEW product_batch_component_list AS
  SELECT pb_id, recipe_name, productbatch.status, produce_name, netto, opr_id
  FROM recipe NATURAL JOIN productbatch NATURAL JOIN productbatchcomponent
  NATURAL JOIN producebatch NATURAL JOIN produce
  ORDER BY pb_id;

# recipe (Pharmacist)
CREATE OR REPLACE VIEW recipe_list AS
  SELECT recipe_id, recipe_name, produce_name, nom_netto, tolerance
  FROM recipe NATURAL JOIN recipecomponent NATURAL JOIN produce
  ORDER BY recipe_id;