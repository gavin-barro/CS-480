% 6A:

% clear(x)  -> x is clear
% moveable(x)   -> x is moveable
% table(x)  -> x is on the table
% on(x, y)     -> x is on y

% Predicates:
%   Agent can move if block X is clear: ∀x(clear(x) -> moveable(x))
%   Unclear blocks can't be moved until they are cleared: ∀x∀y(on(y,x) -> ¬moveable(x))
%   The table doesn't have to be clear: ∀x(table(x) -> moveable(y))
%   A block can be moved onto the table even if there are other blocks on the table:  ∀x∀y(table(y) -> moveable(x))


% 6B:

% Rules

% Agent can move if block X is clear
move(X) :- clear(X), moveable(X).

% Unclear blocks can't be moved until they are cleared
move(X) :- \+ (on(Y, X), moveable(X)).

% The table doesn't have to be clear
move(X) :- table(Y).

% A block can be moved onto the table even if there are other blocks on the table
move(X) :- table(Y), moveable(X).


%6C:

% Initial State:          

on(a, b)

on(b, table)

on(c, table)

clear(a)

clear(c)

% Goal State:  on(c, b)