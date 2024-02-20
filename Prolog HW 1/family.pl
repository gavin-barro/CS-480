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

% Question 4

% Grandparent: grandparent(G, K) that is true if G is a grandparent of K
grandparent(G, K) :- parent(G, P), parent(P, K).

% Cousin: cousin(C, S) that is true if C is a cousin of S
cousin(C, S) :- parent(P1, C), parent(P2, S), sibling(P1, P2), C \= S.