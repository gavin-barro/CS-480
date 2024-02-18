% Question 2

% Facts:
have_sugar.
have_flower.
bake_cake :- have_flower ^ have_sugar. % or is it , ???

% Query
bake_cake.

% OUTPUT: true