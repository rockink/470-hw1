package com.company;

import javax.xml.soap.Node;
import java.util.*;


public class GreedySearch {

    private final Queue<Edge>[] nodes;
    int[][] pathMap;
    Set<Integer> visited;
    Stack<Integer> nodeStack;
    Stack<Integer> trace;


    public GreedySearch(NodeList nodeList) {
         this.nodes = nodeList.getNodes();
         pathMap = new int[nodes.length][nodes.length];
         visited = new HashSet<>();
         nodeStack = new Stack<>();
         trace = new Stack<>();


    }



    int test = 3;

    /**
     * Heuristic estimates the minimum cost from any vertex x to the goal vertex
     * definition the distance from v to goalVertex, lets say,
     * my heuristic function is, given a node, it points out the minimum path
     * @param current initial vertex
     * @param goalVertex
     */
    private int heuristicFn(int current, int goalVertex){

        if (current == goalVertex) return 0;
        int child = trace.pop();
        int parent = trace.peek();

        //func is the weight from the top,
        int path = pathMap[parent][child];
        trace.add(child);

        //this adds the min children
        parent += nodes[current].peek().dist;
        return path;

    }


    public boolean part2(int current, int goal){

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

            heuristicByEdges.put(heuristicFn(current, goal), e);
            trace.pop();
        }

        for (Map.Entry<Integer, Edge> entry : heuristicByEdges.entrySet()){

            System.out.println("cost"  + entry.getKey());
            System.out.println("for " + entry.getValue().source  + "  " + entry.getValue().target);

            int target = entry.getValue().target;
            if(!visited.contains(target)) {
                nodeStack.add(target);
                if (part2(entry.getValue().target, goal)) {
                    System.out.println(current + "  " + entry.getValue().target);
                    return true;
                }
            }
        }

        nodeStack.pop();
        return false;
    }



    public boolean dfs(int curNode, int goal){

        Queue<Edge> children = nodes[curNode];

        if(children.isEmpty() && curNode != goal){
            notGoal[curNode] = true;
            return false;
        }else if (curNode == goal){
            list.add(curNode);
            System.out.println(curNode);
            return true;
        }

        while (!children.isEmpty()){
            Edge edge = children.poll();

            //if we match
            if (dfs(edge.target, goal)) {
                list.add(edge.source);
                System.out.println(edge.source);
                return true;
            }

        }

        return false;
    }

}
