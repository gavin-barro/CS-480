% Question 3

% Parent: parent(P, K) which is true if P is a parent
% parent(p, k).

parent(raj, niki).
parent(sarah, niki).
parent(raj, nolan).
parent(sarah, nolan).

parent(nilton, gus).
parent(nilton, russ).
parent(noreen, gus).
parent(noreen, russ).

parent(tammy, raj).
parent(tammy, nitron).
parent(jair, raj).
parent(jair, nitron).

% Sibling: sibling(X, Y) which is true if X is a brother or sister of Y
sibling(X, Y) :- parent(P, X), parent(P, Y), X \= Y.
sibling(nilton, raj).

% Question 4

% Grandparent: grandparent(G, K) that is true if G is a grandparent of K
grandparent(G, K) :- parent(G, P), parent(P, K).

% Output:
% ?- grandparent(G, K).
% G = tammy,
% K = niki ;
% G = tammy,
% K = nolan ;
% G = jair,
% K = niki ;
% G = jair,
% K = nolan ;

% Cousin: cousin(C, S) that is true if C is a cousin of S
cousin(C, S) :- parent(P1, C), parent(P2, S), sibling(P1, P2), C \= S.

% C = gus,
% S = niki ;
% C = gus,
% S = nolan ;
% C = russ,
% S = niki ;
% C = russ,
% S = nolan ;