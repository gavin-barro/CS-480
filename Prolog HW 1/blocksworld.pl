% 6 A- Translate the Blocksworld rules specified above into Predicate Logic.

% The agent can move a block as long as that block is clear:
%   clear(X) -> moveable(X)

% Unclear blocks cannot be moved until they are cleared:
%   ¬clear(X) -> ¬moveable(X)

% The table doesn’t have to be clear. 
%    on(X, table)

% A block can always be moved onto the table even when there already are one or more blocks on the table:
%    moveable(X) -> move(X, table)


% -----------------------------------------------------------------------------------------------------------
% 6 B- Translate the predicate logic sentences from Part A into Prolog statements

% Rules

% Agent can move if block X is clear
move(X) :- clear(X), moveable(X).

% Unclear blocks can't be moved until they are cleared
move(X) :- \+ (on(Y, X), moveable(X)).

% The table doesn't have to be clear
move(X) :- table(Y).

% A block can be moved onto the table even if there are other blocks on the table
move(X) :- table(Y), moveable(X)


% 6 C. Translate the initial state of the given planning problem into Prolog clauses and add them to the program you wrote in Part B. 
% For this problem, you can also assume that the world only has three blocks in it: 
% a, b, and c (remember object names are represented in lower case in Prolog. Upper case letters are reserved for variables). 
% Add print statements to your program so that the program prints out the plan i.e., 
% the ordered sequence of actions which if executed in this world will result in achievement of the goal state. 
% For instance, the plan for this problem could be printed as

% move(a, b, table) % move a from b to table
% move(c, table, b) % move c from table to b

% Redefined rules:

on(a, b).
on(b, table).
on(c, table).
clear(a).
clear(c).

% Additional help from: http://alumni.cs.ucr.edu/~vladimir/cs171/prolog_2.pdf  
% Assert and retract help dynamically update the state of the blocks

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

move(a, b, table) % move a from b to table
move(c, table, b) % move c from table to b

% goal state: 
on(c, b)