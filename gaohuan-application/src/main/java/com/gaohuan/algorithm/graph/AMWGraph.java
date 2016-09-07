package com.gaohuan.algorithm.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 常用的图存储结构:
 * 1、Adjacency Matrix Model 邻接矩阵
 * 2、Adjacency List 邻接表
 * <p>
 * 比较：
 * 节点数较小，且边较多，采用邻接矩阵效率更高
 * 边远小于相同节点的完全图的边数时，采用邻接表存储结构更有效率。
 * <p>
 * 图的遍历：
 * 深度优先遍历
 * 广度优先遍历
 */
public class AMWGraph {

    private List vertexList;//定点列表

    private int[][] edges;//邻接矩阵,用来存储边

    private int numOfEdges;//边的数目

    public AMWGraph(int n) {
        //初始化
        edges = new int[n][n];
        vertexList = new ArrayList(n);
        numOfEdges = 0;
    }

    /**
     * 得到节点个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 得到边的数目
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点i的数据
     *
     * @param i
     * @return
     */
    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1-v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 插入节点
     *
     * @param vertex
     */
    public void insertVertex(Object vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入节点
     *
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;//设置权重代表插入边
        numOfEdges++;//增加边计数
    }

    /**
     * 删除边
     *
     * @param v1
     * @param v2
     */
    public void deleteEdge(int v1, int v2) {
        edges[v1][v2] = 0;
        numOfEdges--;

    }

    /**
     * 得到第一个邻接点的下标
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接点的下标来取得下一个邻接点
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void print() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                System.out.println("<" + i + "," + j + ">:" + edges[i][j]);
            }
        }
    }

    /**
     * 深度优先遍历
     * 1、优先遍历第一个邻接点，以遍历的那个邻接点为原点，递归遍历它的第一个邻接点
     * 2、在每个节点如果已经访问过，或者邻接点遍历完了，返回
     */
    public void depthFirstSearch() {
        int num = getNumOfVertex();
        boolean[] isVisited = initVisited();
        for (int i = 0; i < num; i++) {
            if (!isVisited[i]) {
                depthFirstSearch(isVisited, i);
            }
        }
    }

    private void depthFirstSearch(boolean[] visited, int i) {
        System.out.println(getValueByIndex(i));//打印
        visited[i] = true;//已访问
        int w = getFirstNeighbor(i);//第一个邻接点
        while (w != -1) {
            if (!visited[w]) {
                depthFirstSearch(visited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    private void broadFirstSearch(boolean[] visited, int i) {
        int u, w;
        LinkedList queue = new LinkedList();
        System.out.println(getValueByIndex(i));//访问节点
        visited[i] = true;//已访问
        queue.addLast(i);//已访问入队列
        while (!queue.isEmpty()) {
            u = ((Integer) queue.removeFirst()).intValue();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!visited[w]) {
                    System.out.println(getValueByIndex(w));
                    visited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }

    }

    /**
     * 广度优先遍历
     * 以一个原点为基础，优先遍历它的邻接点，以及邻接点的邻接点
     * 按照遍历的顺序加入队列
     * 每次从队列里取出一个节点继续遍历它的邻接点
     */
    public void broadFirstSearch() {
        int num = getNumOfVertex();
        boolean[] visited = initVisited();
        for (int i = 0; i < num; i++) {
            if (!visited[i]) {
                broadFirstSearch(visited, i);
            }
        }
    }

    /**
     * 初始化遍历标志
     *
     * @return
     */
    private boolean[] initVisited() {
        int num = getNumOfVertex();
        boolean[] visited = new boolean[num];
        for (int i = 0; i < num; i++) {
            visited[i] = false;
        }
        return visited;
    }

    public static void main(String[] args) {
        int n = 8, e = 9;
        String labels[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        AMWGraph graph = new AMWGraph(n);
        for (String label : labels) {
            graph.insertVertex(label);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(6, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);
        /*
        System.out.println("节点的个数：" + graph.getNumOfVertex());
        System.out.println("边的个数:" + graph.getNumOfEdges());

        //删除边
        graph.deleteEdge(0, 1);
        System.out.println("节点的个数：" + graph.getNumOfVertex());
        System.out.println("边的个数:" + graph.getNumOfEdges());
        */

        graph.depthFirstSearch();
        System.out.println("-------------我是华丽的分割线--------------");
        graph.broadFirstSearch();


    }


}
