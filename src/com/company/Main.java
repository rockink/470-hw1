package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("pa1.in");
        Scanner fileScanner = new Scanner(file);

        int n = fileScanner.nextInt();
        NodeList nodeList = new NodeList(n);

        while (fileScanner.hasNextInt()){
            int u = fileScanner.nextInt();
            int v = fileScanner.nextInt();
            int dist = fileScanner.nextInt();
            nodeList.addEdge(u, v, dist);
        }

        GreedySearch greedySearch = new GreedySearch(nodeList);
        greedySearch.part2(0, 5);
        System.out.println(greedySearch.trace);

        AStar aStar = new AStar(nodeList);
        aStar.astar(0, 5);
        aStar.stacker();
    }
}
