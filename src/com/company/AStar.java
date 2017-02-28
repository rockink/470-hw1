package com.company;

import java.util.*;

public class AStar {

    private final Queue<Edge>[] nodes;

    HashMap<Integer, Integer> edgeTo;
    List<Integer> list;
    boolean notGoal[];


    public AStar(Queue<Edge>[] nodes) {
        this.nodes = nodes;
    }


    int test = 3;
    private int heuristicFn(int node, int goal){
        return test++;
    }


    public boolean astar(int current, int goal, Stack<Integer> outcomes){

        int childrenNum = nodes[current].size();

        if (childrenNum == 0 && goal != current) return false;

        System.out.println("processing " + current + "   ");

        if(current == goal) return true;

        Map<Integer, Edge> heuristicByEdges = new TreeMap<>();

        for (Edge e : nodes[current]){ //for every children
            heuristicByEdges.put(heuristicFn(current, goal), e);
        }


        outcomes.add(current);

        for (Map.Entry<Integer, Edge> entry : heuristicByEdges.entrySet()){
            if(astar(entry.getValue().target, goal, outcomes)){
                outcomes.add(entry.getValue().target);
                System.out.println(current +"  " + entry.getValue().target);
            }
        }

        return false;

    }


}
