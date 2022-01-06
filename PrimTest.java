package com.burk.PrimMST;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PrimTest {
    public static void main(String[] args) throws Exception {

        GraphGenerator graphGenerator = new GraphGenerator(80);

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/burk/PrimMST/graph.txt"));
        int numofVertices = Integer.parseInt(bufferedReader.readLine().trim());
        int numofEdges = Integer.parseInt(bufferedReader.readLine().trim());


        // 构建cost[][]数组
        int[][] cost = new int[numofVertices+1][numofVertices+1];
        for(int i = 1; i<numofVertices+1; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < numofEdges; i++){
            String[] vl = bufferedReader.readLine().trim().split(" ");
            int x = Integer.parseInt(vl[0].trim());
            int y = Integer.parseInt(vl[1].trim());
            int p = Integer.parseInt(vl[2].trim());
            cost[x][y] = p;
            cost[y][x] = p;
        }

       long startTime = System.currentTimeMillis();
       Prim prim = new Prim(cost, numofVertices, Integer.MAX_VALUE);
       long endTime = System.currentTimeMillis();


       System.out.println("\nSum of the MST of this graph is: " + prim.sum);
       System.out.println("This algorithm spend " + (endTime - startTime) + "ms.");
    }
}


