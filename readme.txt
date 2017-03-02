Explanation of how my heuristic function works:

Greedy Search:
h(n) = weight of node from parent to children
Algorithm just calculates the cost to reaching towards the children


AStar Algorithm:
maxBrach = lengthof(String(Number))
maxBrachProgress = 0;
h(n) = until (maxBrachProgress++ < maxBrach)
          cost = path(parent(n), n) + h(minChild(n))

This way, the algorithm 