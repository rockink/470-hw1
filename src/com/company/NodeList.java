package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class NodeList {


    private final int n;

    private PriorityQueue<Edge>[] nodes; //each node is connected to other node via edge


    public NodeList(int n) {
        System.out.println("Nodes " + n);
        this.n = n;
        nodes= (PriorityQueue<Edge>[]) new PriorityQueue[n];

        for (int i = 0; i < n; i++)
            nodes[i] = new PriorityQueue<>();
    }


    public void addEdge(int source, int target, int dist) {
        Edge edge = new Edge(source, target, dist);
        System.out.println("source : " + source + " target: "+ target );
        nodes[source].add(edge);
    }


    public void runGreedySearch(){
        GreedySearch greedySearch = new GreedySearch(nodes);

    }

    public  Queue<Edge>[] getNodes(){
        return nodes;
    }

}
