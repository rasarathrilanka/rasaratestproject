import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {
    public static void generateGraph(List<GraphElement> graphElementList, int numberOfNodes) {
        List<GraphElement> editedGraphElementList = GraphGenerator.checkMultiDirection(graphElementList);

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        //select MultiGraph to represent the both side directed edges
        MultiGraph graph = new MultiGraph("Network");

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "edge {text-alignment: along;}");

        for (int i = 1; i <= numberOfNodes; i++) { //Change id to s and t
            String nodeId = String.valueOf(i);
            //set source node as "S"
            if (i == 1) {
                nodeId = "S";
            }
            //set sink node as "T"
            if (i == numberOfNodes) {
                nodeId = "T";
            }
            graph.addNode(nodeId);
        }
        int i = 0;
        List<Edge> edgeList = new ArrayList<Edge>();
        for (GraphElement graphElement : editedGraphElementList) {    //change the name of the first node and last node to s ans t in graphelementlist


            if (!graphElement.isDisplayStatus()) {
                //create MultiDirection nodezz
                if (graphElement.isMultiDirection()) {
                    for (GraphElement graphElement2 : editedGraphElementList) {
                        if (graphElement2.isMultiDirection() && graphElement.getEdgeFrom() == graphElement2.getEdgeTo() && graphElement.getEdgeTo() == graphElement2.getEdgeFrom()) {
                            String from1 = String.valueOf(graphElement.getEdgeFrom());
                            String to1 = String.valueOf(graphElement.getEdgeTo());
                            String from2 = String.valueOf(graphElement2.getEdgeFrom());
                            String to2 = String.valueOf(graphElement2.getEdgeTo());

                            //set source node as "S"
                            if (graphElement.getEdgeFrom() == 1) {
                                from1 = "S";
                            }
                            //set sink node as "T"
                            if (graphElement.getEdgeTo() == numberOfNodes) {
                                to1 = "T";
                            }
                            //set source node as "S"
                            if (graphElement2.getEdgeFrom() == 1) {
                                from2 = "S";
                            }
                            //set sink node as "T"
                            if (graphElement2.getEdgeTo() == numberOfNodes) {
                                to2 = "T";
                            }


                            Edge edge1 = graph.addEdge(String.valueOf(i++), from1, to1, true);
                            Edge edge2 = graph.addEdge(String.valueOf(i++), from2, to2, true);
                            //set multi direction node attributes
                            edge1.setAttribute("ui.label", String.valueOf(graphElement.getCapacity()));
                            edge1.setAttribute("ui.style", "text-offset: -5;");
                            graphElement.setDisplayStatus(true);

                            edge2.setAttribute("ui.label", String.valueOf(graphElement2.getCapacity()));
                            edge2.setAttribute("ui.style", "text-offset: 5;");
                            graphElement2.setDisplayStatus(true);

                        }
                    }
                } else {
                    String from = String.valueOf(graphElement.getEdgeFrom());
                    String to = String.valueOf(graphElement.getEdgeTo());

                    //set source node as "S"
                    if (graphElement.getEdgeFrom() == 1) {
                        from = "S";
                    }
                    //set sink node as "T"
                    if (graphElement.getEdgeTo() == numberOfNodes) {
                        to = "T";
                    }

                    Edge edge = graph.addEdge(String.valueOf(i++), from, to, true);
                    edge.setAttribute("ui.label", String.valueOf(graphElement.getCapacity()));
                    edge.setAttribute("ui.style", "text-offset: 5;");
                    graphElement.setDisplayStatus(true);

                }
            }
        }
        for (Node n : graph) {
            n.addAttribute("label", n.getId());
            n.addAttribute("ui.style", "shape:circle;" +
                    "fill-color: yellow;" +
                    "size: 30px; " +
                    "text-alignment: center;");
        }


        graph.display();
    }
    //Check whether the node is multi direction or not
    public static List<GraphElement> checkMultiDirection(List<GraphElement> graphElementList) {
        List<GraphElement> editedGraphElementList = new ArrayList<GraphElement>();
        for (GraphElement graphElement1 : graphElementList) {
            for (GraphElement graphElement2 : graphElementList) {

                if (!graphElement1.isMultiDirection() && graphElement1.getEdgeFrom() == graphElement2.getEdgeTo() && graphElement1.getEdgeTo() == graphElement2.getEdgeFrom()) {
                    graphElement1.setMultiDirection(true);

                }
            }
            editedGraphElementList.add(graphElement1);

        }
        return editedGraphElementList;
    }
}

