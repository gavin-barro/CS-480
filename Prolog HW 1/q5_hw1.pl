% secondlast(S, L) is true when S is the second last element in the list L. (5 points)
% Examples:

% secondlast(b, [a, b, c]) is true
% secondlast(a, [a, b, c]) is false
% secondlast(c, [a, b, c]) is false
% secondlast(c, [a, b, c, d]) is true.
secondlast(H, [H, _]).
secondlast(H, [_ | T]) :-
    secondlast(H, T).



% member1(E, S) is true when an element E is a member of the set S. (5 points)
% Examples:

% member1(a, [a, b, c]) is true
% member1(b, [a, b, c]) is true
% member1(c, [a, b, c]) is true
% member1(d, [a, b, c]) is false

% element E is a member of the set S if it is the head of the list
member1(E, [E | _]).

% element E is a member of the set S if it is a member of the tail of the list
member1(E, [_ | T]) :-
    member1(E, T).

% intersection1(X, Y, Z) is true when Z is the set that contains elements that are present in both set X and set Y. (5 points)
% Examples:

% intersection1([a, b], [b, c], [b]) is true
% intersection1([a, b], [b, c], [c]) is false
% intersection1([a, b], [b, c], [a]) is false

% This code defines the base case for an empty list, where the intersection with any set is an empty set. 
% The second clause adds an element to the result set if it is a member of both the first set ([H | T]) and the second set (Y).
%  The third clause handles the case when the current element is not a member of the second set.

intersection1([], _, []).
intersection1([H | T], Y, [H | Z]) :-
    member1(H, Y), intersection1(T, Y, Z).
intersection1([_ | T], Y, Z) :-
    intersection1(T, Y, Z).

% difference1(X, Y, Z) is true when Z is the set difference obtained by subtracting all elements of B from the set A i.e., Z = A - Z contains all elements of set A that are not in set B. (5 points)
% Examples: 

% difference1([a, b], [b, c], [a]) is true
% difference1([a, b], [b, c], [a, c]) is false
% difference1([a, b], [b, c], [c]) is false

difference1().

