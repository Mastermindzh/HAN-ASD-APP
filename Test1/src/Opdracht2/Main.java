package Opdracht2;

/**
 * Created by mastermindzh on 3/15/17.
 */
public class Main {

    public static void main(String[] args){

        Graph graph = new Graph();
        graph.addAndGetVertex("S0", false);
        graph.addAndGetVertex("S1", true);
        graph.addAndGetVertex("S2", false);
        graph.addAndGetVertex("S3", true);

        graph.addEdge("S0", "S1", "a");
        graph.addEdge("S1", "S2", "b");
        graph.addEdge("S2", "S1", "c");
        graph.addEdge("S1", "S3", "d");


        System.out.println(graph);


        System.out.println(graph.match("a"));
        System.out.println(graph.match("ad"));
        System.out.println(graph.match("abcbcd"));
        System.out.println(graph.match("aaa"));


    }
}
