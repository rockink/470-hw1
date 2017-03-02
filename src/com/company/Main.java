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
        formatPriner(greedySearch.search(0, n - 1));

        AStar aStar = new AStar(nodeList);
        formatPriner(aStar.search(0, n - 1));

    }

    private static void formatPriner(Stack<Integer> nodes) {

        StringBuilder stringBuilder = new StringBuilder();
        for(Integer node : nodes)
            stringBuilder.append(node).append(">");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        System.out.println(stringBuilder.toString());

    }
}
