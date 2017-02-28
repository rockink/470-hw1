package com.company;



public class Edge implements Comparable<Edge> {
    int source,target;
    int dist;

    Edge(int source, int target, int dist) {
        this.source = source;
        this.target = target;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge edge) {
        return dist - edge.dist;
    }
}
