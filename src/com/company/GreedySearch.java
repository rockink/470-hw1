package com.company;

import java.util.*;


public class GreedySearch {

    private final Queue<Edge>[] nodes;
    HashMap<Integer, Integer> edgeTo;
    List<Integer> list;
    boolean notGoal[];


    public GreedySearch(Queue<Edge>[] nodes) {
         this.nodes = nodes;
         edgeTo = new HashMap<>();
         list = new ArrayList<Integer>();
         notGoal = new boolean[nodes.length];
    }


    int test = 3;

    /**
     * Heuristic estimates the minimum cost from any vertex x to the goal vertex
     * definition the distance from v to goalVertex, lets say,
     * my heuristic function is, given a node, it points out the minimum path
     * @param v initial vertex
     * @param goalVertex
     */
    private int heuristicFn(int v, int goalVertex){
        return  test++;
    }



    public boolean part2(int current, int goal){

        System.out.println("processing " + current + "   ");
        int childrenNum = nodes[current].size();

        if (childrenNum == 0 && goal != current) return false;
        if(current == goal) return true;

        Map<Integer, Edge> heuristicByEdges = new TreeMap<>();

        for (Edge e : nodes[current]){ //for every children
            heuristicByEdges.put(heuristicFn(current, goal), e);
        }

        for (Map.Entry<Integer, Edge> entry : heuristicByEdges.entrySet()){
            if(part2(entry.getValue().target, goal)){
                System.out.println(current +"  " + entry.getValue().target);
                return true;
            }
        }

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
