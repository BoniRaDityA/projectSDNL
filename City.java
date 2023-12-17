package Part2;

public class City {
    String label;
    boolean visited;
    
    public City(String label) {
        this.label = label;
        this.visited = visited;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }    
    
    public String displayCity(){
        return label + " ";
    }
}
