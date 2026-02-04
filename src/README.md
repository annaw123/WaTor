# Wa-Tor

## Background

Wa-Tor is a predator-prey simulation, initially devised by A. K. Dewdney and described on [page 14 of the December 1984 issue of Scientific American](https://static.scientificamerican.com/sciam/cache/file/7F8FAF26-9FDB-4B8F-86E7F87997E0072E.pdf).

The premise is that sharks and fish are waging an ecological war on the toroidal planet Wa-Tor. The sharks are predatory and eat the fish. Both sharks and fish live, move, reproduce and die in Wa-Tor according to the simple rules defined below.

## Rules
1. Sharks are predatory & eat the fish 
2. Sharks and fish live, move, reproduce and die 
3. If a creature moves past the edge of the grid, it reappears on the opposite side (toroidal world)
4. Time passes in discrete jumps (called chronons)
5. Fish have the following behaviour:
   - At each jump, a fish moves randomly to an adjacent opposite square. If there are no free squares, no movement takes place. 
   - When a fish survives a certain number of jumps, it can reproduce by moving and leaving a new fish in its old position
6. Sharks have the following rules:
   - At each jump, a shark moves to an adjacent square occupied by a fish, or a random adjacent sqaure if there are no fish. If there are no free squares, no movement takes place.
   - At each jump, a shark loses a unit of energy 
   - Upon reaching zero energy, the shark dies 
   - If a shark moves to a square occupied by a fish, it eats the fish and earns a certain amount of energy. 
   - Once a shark has survived a certain number of chronons it may reproduce in exactly the same way as the fish.

