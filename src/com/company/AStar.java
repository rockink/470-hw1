package com.company;

import javax.xml.soap.Node;
import java.util.*;

public class AStar {

    private final Queue<Edge>[] nodes;
    private final int n;
    private Stack<Integer> nodeStack;
    private Set<Integer> visited;
    private int[][] pathMap;
    private Stack<Integer> trace;
    int maxBrach = 2;

    public AStar(NodeList nodeList) {
        this.nodes = nodeList.getNodes();
        this.n = nodes.length;
        nodeStack = new Stack<>();
        visited = new HashSet<>();
        trace = new Stack<>();
        pathMap = new int[n][n];
        //this is important property to look into how to perform A*,
        //for smaller nodes, it does little, for bigger, it goes big
        maxBrach = (nodeList.E() + "").length();
    }



    Set<Integer> cal3MaxBrachset;
    int maxBrachProgress = 0; //goes down to 3 in the end
    //this heuristic function at max goes down to 3 branches
    private int heuristicFn(int node, int goal){

        cal3MaxBrachset = new HashSet<>();
        int cost = calc3MaxBranchFn(node, goal);
        maxBrachProgress = 0;
        return cost;


//        this heuristic function is working, but a more complicated, more senseful looks like the
//        one above, but this below still works!
//        if (node == goal) return 1;
//
//        int childrenNum = nodes[node].size();
//        if (childrenNum == 0)
//            return 2 *n;
//
//
//        return childrenNum;

    }


    private int calc3MaxBranchFn(int node, int goal) {

        if (node == goal && maxBrachProgress < maxBrach) return 0;
        int childrenSize = nodes[node].size();

        //definitely from [0..6], there will be path > n * 2,
        //unless every path is equivalent
        if (childrenSize == 0) return n * 2;
        else if (maxBrachProgress++ > maxBrach) return 0;

        //nextNode is the next node,
        Edge currentEdge = nodes[node].peek();
        int cost = currentEdge.dist;

        //this checks for cyclic, and it increases cost, just so it doesn't get selected
        if (cal3MaxBrachset.contains(currentEdge.target))return +3;
        //if when branching gets to target, certainly cannot be 0, as goal is only zero,
        //so just +1 than goal
        else if (goal == currentEdge.target) return 1;
        //the maxBrach is length,where maxBranch = E,
        //eg: f(323) = 3, f(12345) = 5, so it looks at the edge.. and gets its value
        //on how many brances to look after, more numbers, more branch
        //for more clarity
        else if (maxBrachProgress >= maxBrach) return cost;


        else return cost + calc3MaxBranchFn(currentEdge.target, goal);

    }


    //given a stack of path, iteratate over each elem to get the cost
    //say stack is 1, 2, 3, cost would be + (1, 2) (2, 3)
    private int pathCostFrom(Stack<Integer> trace){

        int i = 0;
        int prev = 0;
        int path = 0;

        for(Integer node : trace){
            if (i++ == 0){
                prev = node;
                continue;
            }

            path += pathMap[prev][node];
            prev = node;
        }

        return path;
    }


    public boolean astar(int current, int goal){

        visited.add(current);

        int childrenNum = nodes[current].size();

        if (childrenNum == 0 && goal != current) {
            nodeStack.pop();
            return false; //node del
        }
        if(current == goal) return true;

        Map<Integer, Edge> heuristicByEdges = new TreeMap<>();

        trace.add(current);

        for (Edge e : nodes[current]){ //for every children
            trace.add(e.target);
            pathMap[current][e.target] = e.dist;

            int pathCostFrom = pathCostFrom(trace);

            System.out.println(String.format("Path Cost: %d to %d is %d", current, e.target, pathCostFrom));
//            System.out.println(String.format("Heuristic Cost: of %d is %d", e.target, heuristicFn(current, goal) ));

            int hrFn = heuristicFn(e.target, goal);
            System.out.println(String.format("herfun from %s is %s ", e.target, hrFn));
            heuristicByEdges.put(hrFn + pathCostFrom, e);
            trace.pop();
        }

        for (Map.Entry<Integer, Edge> entry : heuristicByEdges.entrySet()){

            int target = entry.getValue().target;
            if(!visited.contains(target)) {
                nodeStack.add(target);
                if (astar(entry.getValue().target, goal)) {
                    System.out.println(current + "  " + entry.getValue().target);
                    return true;
                }
            }
        }

        nodeStack.pop();
        return false;
    }


    public void stacker() {
        System.out.println(nodeStack);
    }
}
