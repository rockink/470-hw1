package com.company;

import java.util.*;

public class AStar {

    private final Queue<Edge>[] nodes;

    HashMap<Integer, Integer> edgeTo;
    HashMap<String, Integer> pathCostMap; //from source to target
    List<Integer> list;
    boolean notGoal[];

    public AStar(Queue<Edge>[] nodes) {
        this.nodes = nodes;
        this.pathCostMap = new HashMap<String, Integer>();
    }


    int test = 3;
    private int heuristicFn(int node, int goal){
        return test++;
    }

    
    //given a stack of path, iteratate over each elem to get the cost
    //say stack is 1, 2, 3, cost would be + (1, 2) (2, 3)
    private int pathCostFrom(Stack<Integer> path){
        
        int i = 0;
        int prev = 0;
        int path = 0;
        for(Integer node : path){
            if (i == 0){
                   prev = node;
                    i++;
                continue;
            }
            
            //this guarantees a path from that part!
            path += FGHEdges.get(String.format("%s-%s", prev, node));

            //so that when we do the next one, we are going to look from current node, to
            //the next node from for loop
            prev = node;
            
        }
        
    }
    

    Stack<Integer> stack;
    int cost = Integer.MAX_VALUE;
    
    Stack<Integer> costStack; 
    
    
    public boolean astar(int current, int goal){
        
        int childrenNum = nodes[current].size();
        if (childrenNum == 0 && goal != current) {
            
            return false;
            
        }
        if(current == goal) return true;

        System.out.println("processing " + current + "   ");
        
        costStack.add(current);
        

        //at this moment, pathCostChildren is 0. it is a g function for heuristic function
        int pathCostChildren = 0;
        Map<Integer, Edge> FGHEdges = new TreeMap<>();
        for (Edge e : nodes[current]){ //for every children
            costStack.add(e.target);        
            FGHEdges.put(heuristicFn(e.target, goal) + pathCostFrom(costStack), e);
            costStack.pop();
        }

        outcomes.add(current);

        //this will automatically start from the lowest one
        for (Map.Entry<Integer, Edge> entry : FGHEdges.entrySet()){
                    
            //from the lowest of f, we would select that one
            if(astar(entry.getValue().target, goal, outcomes)){
                Integer target = entry.getValue().target;
                costStack.add(target); //this is a goal state

                //get the cost till here
                pathCostChildren = pathCostChildren + entry.getValue().dist;
                
                //but check if the cost of this one is less than the previously held one.
                if(pathCostChildren < cost){
                    outCome = new Stack();
                    for (Integer node :  costStack){
                        outCome.add(node);   
                    }
                    cost = pathCostChildren;
                }
                
                costStack.pop();
                System.out.println(current +"  " + entry.getValue().target);
            }
        }

        return false;

    }
}
