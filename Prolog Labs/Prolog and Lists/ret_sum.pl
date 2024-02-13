% Returning sum:
% add(X, Y, Z) is true when Z = X + Y

% Impure: add x, y, z given z is X + Y
add(x, y, z) :- z is x + y

% Pure Declarative
% Write down all possibilities
% add(0, 1, 1).
% add(1, 0, 1).
% add(1, 1, 2).
% add(2, 1, 3).
% ...

