package Opdracht2;

/**
 * Created by mastermindzh on 3/15/17.
 */

import java.util.*;

public class Graph {

    private Map<String, Vertex> vertices = new HashMap<>();

    boolean match(String s) {
        //always start at S0
        Vertex CurrentVertex = vertices.get("S0");

        // for every character in string
        for (Character c : s.toCharArray()) {
            boolean found = false;
            //for every edge
            for (Edge e : CurrentVertex.getAdj()) {
                //check whether edge cost matches current character
                if (e.getCost().equals(c.toString())) {
                    // it matches, travel along the edge to the next node
                    found = true;
                    CurrentVertex = e.getDest();
                    break;
                }
            }
            // if no path has been found we can return false because the regex doesn't match
            if (!found) {
                return false;
            }
        }

        // String has been checked, did we land at a valid terminationpoint?
        return CurrentVertex.isTerminationPoint();
    }

    // either get the vertex, or add and then get it
    public Vertex addAndGetVertex(String nodename, boolean isTerminationPoint) {
        Vertex vertex = vertices.get(nodename);
        if (vertex == null) {
            vertex = new Vertex(nodename,isTerminationPoint);
            vertices.put(nodename, vertex);
        }
        return vertex;
    }

    // add an edge between two vertices
    public void addEdge(String sourceName, String destName, String cost) {
        Vertex s = addAndGetVertex(sourceName, false);
        Vertex d = addAndGetVertex(destName, false);

        s.getAdj().add(new Edge(d, cost, s));
    }

    @Override
    public String toString() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (Vertex v : vertices.values()) {
            for (Edge e : v.getAdj()) {
                edges.add(e);
            }
        }

        return edges.toString();
    }
}

