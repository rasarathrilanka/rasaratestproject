
public class GraphElement {   // This class is to store the graph element objects

    private int edgeFrom;   // edgeFrom is to store the starting node of the edge
    private int edgeTo;     // edgeTo is to store the ending node of the edge
    private int capacity;   // capacity is to store the capacity of the edge
    private boolean isMultiDirection;
    private boolean displayStatus;


    //getters and setters the private variables.

    //getter method for edgeFrom
    public int getEdgeFrom() {
        return edgeFrom;
    }

    //setter method for edgeFrom
    public void setEdgeFrom(int edgeFrom) {
        this.edgeFrom = edgeFrom;
    }

    //getter method for edgeTo
    public int getEdgeTo() {
        return edgeTo;
    }

    //setter method for edgeTo
    public void setEdgeTo(int edgeTo) {
        this.edgeTo = edgeTo;
    }

    //getter method for capacity
    public int getCapacity() {
        return capacity;
    }

    //setter method for capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isMultiDirection() {
        return isMultiDirection;
    }

    public void setMultiDirection(boolean multiDirection) {
        isMultiDirection = multiDirection;
    }

    public boolean isDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(boolean displayStatus) {
        this.displayStatus = displayStatus;
    }
}
