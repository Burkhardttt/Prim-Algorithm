package com.burk.PrimMST;

public class Prim {
    public int sum = 0;
    public Prim(int[][] cost, int numofVertices, int maxi){
        int lowcost[] = new int[numofVertices+1];
        int closest[] = new int[numofVertices+1];

        for(int i = 2; i<numofVertices+1; i++){
            lowcost[i] = cost[1][i];
            closest[i] = 1;
        }
        int min;
        int m;
        //寻找邻近最小路径
        for(int i = 2; i<numofVertices+1; i++){
            min = maxi;
            m = 1;
            for(int j = 2; j<numofVertices+1; j++){
                if(lowcost[j] < min && lowcost[j] != 0){
                    m = j;
                    min = lowcost[j];
                }
            }
            //输出生成树的边

           System.out.println("closest[" + m + "]=" + closest[m] + ": " + lowcost[m]);
            sum += lowcost[m];
            lowcost[m] = 0;
            closest[m] = 0;
            //调整代价
            for(int j = 2; j<numofVertices+1; j++){
                if(cost[m][j] < lowcost[j] && cost[m][j] != 0){
                    lowcost[j] = cost[m][j];
                    closest[j] = m;
                }
            }
        }
    }
}
