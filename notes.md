# Data Structures

# Trees

Used for representing hierarchical relationships between items. Form of a graph.
Used for problems involving Taxonomy, ancestor/descendents, dominance relationships, or
hierarchies in general.

# Graphs

Represents relationships between arbitrary pairs of objects. Graphs are used to make
networks, circuits, webs, or to model relationships in general.

# Points

Represents locations in some geometric space. Points should be used if you need to keep track of
positions, locations, data records, or sites.

# Polygons

Polygons represent some region in some geometric space. Polygons should be used
when working with shapes, regions, configurations, or boundaries.

# Strings

Strings represent a sequence of characters or patterns. Strings should be used when
dealing with text, characters, patterns, or labels.

# RAM model

Compute the number of steps that an algorithm takes to solve the problem. Graph the number of steps against the input size (with varying inputs of the same size) to calculate out worst case, average case, and best case.

# Big O Notation

Big O Notation is an abstraction of time complexity since exact computation of how much time an algorithm takes can require too much detail to specify precisely. Used for looking at the big picture.

Formal Definitions: (WARNING: DISCRETE MATH FLASHBACKS)

• f(n) = O(g(n)) means c · g(n) is an upper bound on f(n). Thus there exists
some constant c such that f(n) is always ≤ c · g(n), for large enough n (i.e. ,
n ≥ n0 for some constant n0).
• f(n) = Ω(g(n)) means c · g(n) is a lower bound on f(n). Thus there exists
some constant c such that f(n) is always ≥ c · g(n), for all n ≥ n0.
• f(n) = Θ(g(n)) means c1 · g(n) is an upper bound on f(n) and c2 · g(n) is
a lower bound on f(n), for all n ≥ n0. Thus there exist constants c1 and c2
such that f(n) ≤ c1 ·g(n) and f(n) ≥ c2 ·g(n). This means that g(n) provides
a nice, tight bound on f(n).

# Practical Numbers

## log(n)

If you have a log(n) algorithm, you are set for doing basically anything. Algorithm efficiency stops being a concern here.

## n

Honestly pretty good. Still maintains 1 second response times at 1 billion inputs. Scales directly with input size vs processing speed, so pretty easy to calculate run time.

## nlog(n)

Still okay. Maintains high responsiveness up till around 10,000,000 inputs. Starts taking noticeable time once you past the 1,000,000,000 point.

## n^2

Pretty reasonable still for lower inputs. It's when you pass 100,000 inputs that this algorithm starts having problems. Doesn't scale very well. 

## 2^n

You... don't want this. still solves problems of input size 30 but... everything past that starts getting really bad.

## n!

Why. just don't. More than 10 inputs and you're done.
