%   animal(x) is true when there is an animal named X in our world (Use %?)
%   produces-milk(x) is true when animal x produces milk for its offspring
%   flies(x) is true when animal x can fly in the air

animal(cat).
animal(eagle).
animal(dog).
animal(pigeon).
animal(crocodile).
produces-milk(cat).
produces-milk(dog).
flies(eagle).
flies(pigeon).

mamal(X) :- animal(X), produces-milk(X).
% Run:  mamal(X).  then ; to make it false

% Example 2: Animal Size Comparison
bigger(elephant, horse).
bigger(horse, donkey).
bigger(donkey, dog).
bigger(donkey, monkey).
% If X > Y and Y > Z, then X > Z  --- Transitive property

% Recursive
 
% Base Case
is-bigger(X, Y) :- bigger(X, Y)
is-bigger(X, Y) :- bigger(X, Z), is_bigger(Z, Y)