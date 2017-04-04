# Produce Batch List (Foreman)
CREATE OR REPLACE VIEW produce_batch_list AS
  SELECT producebatch.rb_id, produce.produce_name, produce.supplier, producebatch.amount FROM producebatch
    INNER JOIN produce WHERE produce.produce_id = producebatch.produce_id;

# produce Overview (Foreman)
CREATE OR REPLACE VIEW produce_overview AS
  SELECT produce.produce_id, produce.produce_name, SUM(producebatch.amount) AS "amount" FROM produce
    NATURAL JOIN producebatch
  GROUP BY produce.produce_name;

# Product Batch List (Foreman)
CREATE OR REPLACE VIEW product_batch_list AS
  SELECT productbatch.recipe_id, productbatch.status, recipe.recipe_name FROM productbatch
    NATURAL JOIN recipe;

# Operator List (Admin)
CREATE OR REPLACE VIEW operator_list AS
  SELECT opr_id, opr_name, cpr, admin, role FROM operator;