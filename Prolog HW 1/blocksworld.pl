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

% Rules  (replaced Y with _)

% Agent can move if block X is clear
move(X) :- clear(X), moveable(X).

% Unclear blocks can't be moved until they are cleared
move(X) :- \+ (on(_, X), moveable(X)).

% The table doesn't have to be clear
move(X) :- table(_).

% A block can be moved onto the table even if there are other blocks on the table
move(X) :- table(_), moveable(X).   

% 6 C.

% Define dynamic predicates for on/2 and clear/1
:- dynamic on/2, clear/1.
% Additional help from: http://alumni.cs.ucr.edu/~vladimir/cs171/prolog_2.pdf  
% Assert and retract help dynamically update the state of the blocks

:- discontiguous on/2.
% Got a warning and was told to add the above line to get rid of it

on(a, b).
on(b, table).
on(c, table).
clear(a).
clear(c).

% New Move
move(X, Y, Table) :-
    on(X, Y),      % Block X is initially on block Y
    clear(X),      % Block X is clear to be moved
    moveable(X),   % Block X is moveable
    \+ (on(_, X), moveable(X)),  % No other block is on top of X
    \+ table(b),   % Block B is not on the table
    assertz(clear(Y)),   % Update that B is clear after moving A from it
    assertz(on(X, Table)),  % Move X onto the table
    retract(on(X, Y)).  % Update that A is no longer on B

move(a, b, table). % move a from b to table
move(c, table, b). % move c from table to b
move(a, table, b). % move a from table to b (to achieve on(a, table))

% goal state: 
on(c, b).