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



move(A, B, Table) :- clear(B), on(B, Table)


% 6 C. Translate the initial state of the given planning problem into Prolog clauses and add them to the program you wrote in Part B. 
% For this problem, you can also assume that the world only has three blocks in it: 
% a, b, and c (remember object names are represented in lower case in Prolog. Upper case letters are reserved for variables). 
% Add print statements to your program so that the program prints out the plan i.e., 
% the ordered sequence of actions which if executed in this world will result in achievement of the goal state. 
% For instance, the plan for this problem could be printed as

% move(a, b, table) % move a from b to table
% move(c, table, b) % move c from table to b

on(a, b).
on(b, table).
on(c, table).
clear(a).
clear(c).


