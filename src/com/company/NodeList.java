package com.company;

import java.util.*;


public class NodeList {


    private final int n;

    private Queue<Edge>[] nodes; //each node is connected to other node via edge
    int E;

    public NodeList(int n) {
        this.n = n;
        nodes= (PriorityQueue<Edge>[]) new PriorityQueue[n];
//        nodes= (LinkedList<Edge>[]) new LinkedList[n];

        for (int i = 0; i < n; i++)
            nodes[i] = new PriorityQueue<>();
//            nodes[i] = new LinkedList<>();
    }


    public void addEdge(int source, int target, int dist) {
        Edge edge = new Edge(source, target, dist);
        nodes[source].add(edge);
        E++;
    }




    public int E(){
        return E;
    }

    public  Queue<Edge>[] getNodes(){
        return nodes;
    }

}
