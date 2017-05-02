/**
operator (Admin)
 */
CREATE OR REPLACE VIEW operator_list AS
  SELECT opr_id, ini, opr_name, cpr, admin, role
  FROM operator;

/**
produce (Foreman)
Task 1 - Creates a view that contains all produces, that appears in at least two producebatches.
We assume that the supplier is without significance of the shown produces.
*/
CREATE OR REPLACE VIEW produce_with_at_least_two_occurences_in_producebatch AS
  SELECT produce_name
  FROM produce NATURAL JOIN producebatch
  GROUP BY produce_name
  HAVING count(produce_name) > 1;

/**
produce (Foreman)
Q3 - Shows an overview of how much os each produce type is in stock.
 */
CREATE OR REPLACE VIEW produce_overview AS
  SELECT produce.produce_name, SUM(producebatch.amount) AS "amount"
  FROM produce NATURAL JOIN producebatch
  GROUP BY produce.produce_name;

/**
produce_batch (Foreman)
 */
CREATE OR REPLACE VIEW produce_batch_list AS
  SELECT producebatch.rb_id, produce.produce_name, produce.supplier, producebatch.amount
  FROM producebatch INNER JOIN produce WHERE produce.produce_id = producebatch.produce_id;

/**
product_batch (Foreman)
*/
CREATE OR REPLACE VIEW product_batch_list AS
  SELECT productbatch.recipe_id, productbatch.status, recipe.recipe_name
  FROM productbatch NATURAL JOIN recipe;

/**
product_batch_component (Pharmacist)
*/
CREATE OR REPLACE VIEW product_batch_component_overview AS
  SELECT pb_id, recipe_name, productbatch.status, produce_name, netto, opr_id
  FROM recipe NATURAL JOIN productbatch NATURAL JOIN productbatchcomponent
  NATURAL JOIN producebatch NATURAL JOIN produce
  ORDER BY pb_id;

/**
recipe (Pharmacist)
*/
CREATE OR REPLACE VIEW recipe_list AS
  SELECT recipe_id, recipe_name, produce_name, nom_netto, tolerance
  FROM recipe NATURAL JOIN recipecomponent NATURAL JOIN produce
  ORDER BY recipe_id;

/**
recipe (Pharmacist)
Task 2 - Creates a view from recipecomponent that shows the recipe id, the recipe name and the produce name
 */
CREATE OR REPLACE VIEW recipe_overview AS
  SELECT recipe_id, recipe_name, produce_name
  FROM recipe NATURAL JOIN recipecomponent NATURAL JOIN produce;


/**
 * Recipe name of recipes containing champignon OR skinke
Task 3 - Part 1 of task 3. Creates a view of recipes containing the ingredients 'skinke' OR 'champignon'
*/

CREATE OR REPLACE VIEW recipe_name_of_recipes_containing_champignon_or_skinke AS
SELECT DISTINCT recipe_name
FROM recipe NATURAL JOIN recipecomponent NATURAL JOIN produce
WHERE produce_name = 'champignon' OR produce_name ='skinke';

/**
 * Recipe name of recipes containing champignon AND skinke
#Task 3 - Part 2 of task 3. Creates a view of recipes containing the ingredients 'skinke' AND 'champignon'
*/
CREATE OR REPLACE VIEW recipe_name_of_recipes_containing_champignon_and_skinke AS
SELECT recipe_name
FROM
recipecomponent NATURAL JOIN produce NATURAL JOIN recipe
WHERE produce_name = 'skinke' or produce_name = 'champignon'
GROUP BY recipe_name
HAVING COUNT(recipe_id) = 2;  
  
/**
 * Recipe name of recipes not containing champignon
#Task 4 - Creates a view of recipes not containing the ingredient 'champignon' 
*/
CREATE OR REPLACE VIEW recipe_name_of_recipes_not_containing_champignon AS
SELECT DISTINCT recipe_name
FROM recipe NATURAL JOIN recipecomponent 
WHERE NOT recipe_id = 
(SELECT recipe_id
FROM 
produce NATURAL JOIN recipecomponent
WHERE produce_name = 'champignon');
  
/**
recipe (Pharmacist)
Task 5 - Creates a view, which shows the recipes that contains the largest amount of tomato
 */
CREATE OR REPLACE VIEW recipe_containing_most_tomato AS
  SELECT recipe_name, produce_name, nom_netto
  FROM recipecomponent NATURAL JOIN recipe NATURAL JOIN produce
  WHERE produce_name = 'tomat' AND nom_netto = (
    SELECT MAX(nom_netto)
    FROM recipecomponent NATURAL JOIN produce NATURAL JOIN recipe
    WHERE produce_name = 'tomat');
