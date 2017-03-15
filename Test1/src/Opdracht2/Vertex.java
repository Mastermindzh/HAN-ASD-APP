package Opdracht2;

/**
 * Created by mastermindzh on 3/15/17.
 */
import java.util.LinkedList;

public class Vertex {

    private boolean isTerminationPoint;
    private String name;
    private LinkedList<Edge> adj;


    public Vertex(String name, boolean isTerminationPoint) {
        setName(name);
        setAdj(new LinkedList<Edge>());
        setTerminationPoint(isTerminationPoint);
    }

    // check whether vertex is a valid termination point
    public boolean isTerminationPoint() {
        return isTerminationPoint;
    }

    public void setTerminationPoint(boolean terminationPoint) {
        isTerminationPoint = terminationPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Edge> getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Edge> adj) {
        this.adj = adj;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}

