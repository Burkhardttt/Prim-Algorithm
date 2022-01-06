package com.burk.PrimMST;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.String;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;

public class GraphGenerator {
    public int v = 0;
    public int e = 0;
    public String[] edges;
    public ArrayList<Integer> coll = new ArrayList<Integer>();

    public GraphGenerator(int v){
        boolean flag = true;
        Random rand = new Random();
        lag:
        while(flag)
        {
        this.v = v;
        e = ThreadLocalRandom.current().nextInt(v-1, v+9);
//            e = v+8;
        edges = new String[e];
            for (int i = 0; i < e; i++) {
                edges[i] = "";
            }

            label:
            for (int i = 0; i < e; i++) {
                // 节点编号不能为0
                int start = Math.abs(rand.nextInt(v) + 1);
                int end = Math.abs((rand.nextInt(v) + 1));
                if (start == end) {
                    i--;
                } else {
                    String temp = start + " " + end;
                    for (int j = 0; j < e; j++) {
                        if (edges[j].equals(temp) || edges[j].equals(StringUtils.reverse(temp))) {
                            i--;
                            continue label;
                        }
                    }
                    edges[i] = temp;

                }
                this.coll.add(start);
                this.coll.add(end);

            }
            // 去重
            Set<Integer> tempHashSet = new HashSet<Integer>(coll);
            List<Integer> afterHashSetList = new ArrayList<Integer>(tempHashSet);
            this.coll = (ArrayList<Integer>) afterHashSetList;

            // 必须访问到所有节点，保证是connected graph
            for (int i = 1; i < this.v + 1; i++) {
                if(!this.coll.contains((Integer) i)){
                    // 清空之前的无效数据
                    coll.clear();
                    continue lag;

                }
            }
            flag = false;

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/com/burk/PrimMST/graph.txt"));
            out.write(v + "");
            out.write("\n");
            out.write(e + "");
            out.write("\n");

            for(int i = 0; i < e; i++){
                if(i != e - 1){
                    out.write(edges[i]);
                    // weight范围(1-50)不能为0
                    out.write(" " + Math.abs(1 + rand.nextInt(50)));
                    out.write("\n");
                }
                else{
                    out.write(edges[i]);
                    out.write(" " + Math.abs(1 + rand.nextInt(50)));
                }
            }
            out.close();
        } catch (IOException exception) {
        }
    }
  }
}