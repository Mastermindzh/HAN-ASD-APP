package Opdracht2;

/**
 * Created by mastermindzh on 3/15/17.
 */
public class Edge implements Comparable<Edge> {

    private Vertex start;
    private Vertex dest;
    private String cost;

    public Edge(Vertex dest, String cost, Vertex start) {
        setDest(dest);
        setCost(cost);
        setStart(start);
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getDest() {
        return dest;
    }

    public void setDest(Vertex dest) {
        this.dest = dest;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", dest=" + dest +
                ", cost='" + cost + '\'' +
                '}';
    }

    @Override
    public int compareTo(Edge other) {
        return cost.compareTo(other.cost);
    }
}

