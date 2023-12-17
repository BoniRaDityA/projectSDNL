package Part2;

public class MainGraph {
    public static void main(String[] args) {
        Path theGraph = new Path(6);
        theGraph.addCity("A");
        theGraph.addCity("B");
        theGraph.addCity("C");
        theGraph.addCity("D");
        theGraph.addCity("E");
        theGraph.addCity("F");
        System.out.println("Daftar Vertex : ");
        for(int i = 0; i < theGraph.getnCity(); i++)
          theGraph.displayVertex(i);
        
        theGraph.addPath("E", "F", 15);
        theGraph.addPath("E", "D", 15);
        theGraph.addPath("F", "D", 10);
        theGraph.addPath("D", "C", 50);
        theGraph.addPath("D", "B", 20);
        theGraph.addPath("A", "F", 10);
        theGraph.addPath("A", "D", 100);
        theGraph.addPath("A", "C", 30);
        theGraph.addPath("A", "B", 50);
        theGraph.addPath("C", "B", 5);
        theGraph.addPath("B", "C", 5);
        
        System.out.println();
        System.out.println("Matrik adjacency : ");
        theGraph.showMatrix();
        System.out.println();
        theGraph.dijkstra("E");
    }
}
