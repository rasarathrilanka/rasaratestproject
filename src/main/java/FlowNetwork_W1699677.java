/*
    ------ This Java program is for implementation of finding maximum possible flow of a generated network. ------
    ------ Done by Rasara Thrilanka ------
    ------ UoW number - W1699677 ------
    ------ IIt number - 2017814 ------
    ------ Module - Algorithms ------

*/

//importing necessary packages

        queuelist.add(s);
        isVisited[s] = true;
        parentlist[s] = -1;


        while (queuelist.size() != 0) {  // Standard BFS while Loop
            int u = queuelist.poll();

            for (int v = 0; v < V; v++) {
                if (isVisited[v] == false && rGraph[u][v] > 0) {
                    queuelist.add(v);
                    parentlist[v] = u;
                    isVisited[v] = true;
                }
            }
        }
        // isVisited is to check whether there is a path from source to sink. (To iterate the standard BFS while loop)
        // If there is a path to sink in BFS starting from source, then return true (isVisited = true), else false (isVisited = false)
        return (isVisited[t] == true);
    }

    // This method will return tne maximum possible flow from Source to Sink in the given graph
    int fordFulkerson(int graph[][], int V, int s, int t) {
        int u, v;

        /* Create a residual graph and fill the residual graph with given capacities in the original graph as
             residual capacities in residual graph

           Residual graph where rGraph[i][j] indicates residual capacity of edge from i to j (if there
             is an edge. If rGraph[i][j] is 0, then there is not)*/
        int rGraph[][] = new int[V][V];

        for (u = 0; u <= V - 1; u++) {
            for (v = 0; v <= V - 1; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        // This array is filled by BFS and to store path
        int parentlist[] = new int[V];

        int max_flow = 0;  // There is no max flow initially

        // Augment the flow while there is path from source to terminal
        while (bfs(rGraph, V, s, t, parentlist)) {
            /* Find minimum residual capacity of the edges along the path filled by BFS. Or we can say
            find the maximum flow through the path found.*/
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parentlist[v]) {
                u = parentlist[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
                System.out.println("From : " + u + "     To : " + v + "      Flow : " + path_flow);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parentlist[v]) {
                u = parentlist[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall max flow
        return max_flow;
    }

    // Main method
    public static void main(String[] args) throws java.lang.Exception {     // throws an Exception

        Scanner sc = new Scanner(System.in);        // define Scanner class
        System.out.println("This program is to find the Maximum Flow of a Flow Network");
        boolean loop = true;         // create a boolean variable named 'loop' run the do while loop

        do {
            //  To get user preference
            System.out.println("If you want to generate the network manually please enter y or Y ");
            System.out.println("If you want to auto-generate the network please enter any Integer. ");
            String ifYes = sc.next();      // ifYes - variable to get user preference


            if (ifYes.equalsIgnoreCase("y")) {      // use equalsIgnoreCase to ignore the case of the string
                // This true part is to generate the network manually
                System.out.println("Enter number of nodes (between 6 to 12) - ");
                boolean istrue = true;   //istrue - boolean variable to iterate the below while loop
                while (istrue) {
                    int numbOfNodes = sc.nextInt();    //get number of nodes by user

                    if (6 <= numbOfNodes && numbOfNodes <= 12) {   //range of number of nodes with source(s) and sink(t)
                        System.out.println("Your nodes are ; ");

                        // below for loop is to print the nodes
                        for (int i = 1; i <= numbOfNodes; i++) {
                            System.out.println(i);
                        }

                        //create a 2D array named 'usergraph[][]' to store the generated edges and capacities
                        int usergraph[][] = new int[numbOfNodes][numbOfNodes];

                        //following code is to get the user inputs for get edges and capacities
                        System.out.println("Please enter edges and capacities in the following format");
                        System.out.println("Edge_start_from       " + "Edge_end_to       " + "Capacity");
                        System.out.println("All inputs should be integers");
                        System.out.println("-------------------------------");

                        while (true) {
                            // To get the start node of the edge
                            System.out.println("Start node of the edge should not be the sink(no of nodes) ");
                            System.out.println("Enter Edge_start_from node :");
                            int start_from = sc.nextInt();

                            // To get the end node of the edge
                            System.out.println("End node of the edge should not be the source(1) ");
                            System.out.println("Enter Edge_end_to node :");
                            int end_to = sc.nextInt();

                            // To get the capacity of the edge
                            System.out.println("Capacity of the edge should between 5 to 20");
                            System.out.println("Enter Capacity");
                            int capacity = sc.nextInt();

                            // some validations are done here
                            if ((capacity < 5 || capacity > 20) || (start_from == end_to) || end_to == 1 || start_from == numbOfNodes || start_from < 1 || end_to > numbOfNodes) {
                                System.out.println("Invalid Input. Please re-enter.");
                                continue;  //jump to the  begin of the while loop
                            } else {
                                usergraph[start_from - 1][end_to - 1] = capacity;    //there is no errors add to the usergraph[][]
                            }

                            //add more edges and capacities
                            System.out.println("If you want add more edges and capacities please enter (m)");
                            System.out.println("If not, you want to find the maximum possible flow of the network press any other String");
                            String aaa = sc.next();

                            if (!aaa.equalsIgnoreCase("m")) {
                                break;   // break the loop. jump to the end of the loop

                            } else {
                                istrue = false;
                            }

                        }
                        //Display the Maximum possible flow
                        FlowNetwork_W1699677 n = new FlowNetwork_W1699677();
                        System.out.println("The maximum possible flow is " +
                                n.fordFulkerson(usergraph, numbOfNodes, 0, numbOfNodes - 1));

                        //generate the graph
                        GraphGenerator.generateGraph(n.capacityCalculator(usergraph, 0, numbOfNodes - 1), numbOfNodes);


                    } else {
                        System.out.println("Invalid number of nodes. Please enter number between 6 to 12");

                    }
                }
                loop = false;   //break the main Do while loop


            } else {
                //timer on
                double startime = System.nanoTime();
                // This else(false) part is to generate the network randomly

                //generate the number of nodes randomly
                int numbOfNodes = ThreadLocalRandom.current().nextInt(6, 13); //range in 6 to 12 including Source and Sink


                System.out.println("Here is the auto-generated Network ");
                if (StringUtils.isNumeric(ifYes)) {  // ifYes - variable to get user preference - line 138

                    //create a 2D array named 'graph[][]' to store the randomly generated edges and capacities
                    int graph[][] = new int[numbOfNodes][numbOfNodes];

                    //traverse through the number of nodes (0 to number of nodes - 1)
                    for (int startNode = 0; startNode <= numbOfNodes - 1; startNode++) {
                        System.out.print("\n  0");
                        //first column of the 2D array (all zeros)
                        //this shouldn't have edges directed to source

                        if (startNode == numbOfNodes - 1) {
                            //true part of the if condition
                            // To zero the last row
                            for (int t = 1; t <= numbOfNodes - 1; t++) {
                                System.out.printf("%5d", 0);
                            }
                            break;    // break the loop

                        } else {
                            // false/else part of the if condition
                            // To add inner elements to the graph
                            for (int innerNode = 1; innerNode < numbOfNodes; innerNode++) {

                                if (startNode == innerNode) {
                                    // To zero the first column
                                    graph[startNode][innerNode] = 0;
                                    System.out.printf("%5d", 0);
                                } else {
                                    //randomly generate zeros and plus values for inner element(for capacities)
                                    if (ThreadLocalRandom.current().nextBoolean()) {
                                        int ran = ThreadLocalRandom.current().nextInt(5, 21);
                                        graph[startNode][innerNode] = ran;
                                        System.out.printf("%5d", ran);

                                    } else {
                                        graph[startNode][innerNode] = 0;
                                        System.out.printf("%5d", 0);
                                    }
                                }


                            }
                        }

                    }
                    //Display the Maximum possible flow
                    FlowNetwork_W1699677 m = new FlowNetwork_W1699677();
                    System.out.println("\n" + "Number of nodes : " + numbOfNodes);
                    System.out.println("The maximum possible flow is " +
                            m.fordFulkerson(graph, numbOfNodes, 0, numbOfNodes - 1));

                    List<GraphElement> graphElementList = m.capacityCalculator(graph, 0, numbOfNodes - 1);
                    //generate the graph
                    GraphGenerator.generateGraph(graphElementList, numbOfNodes);

                    //timer off
                    double endtime = System.nanoTime();
                    double totaltime = (endtime - startime) / 1000000000.0;
                    System.out.println("Totaltime : "+totaltime +"s"); //display in seconds

                    loop = false;
                } else {
                    //display an error
                    System.out.println("Error !");
                    System.out.println("Your Input is not valid. Please re-enter.");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Start again");
                }
            }

        }
        while (loop);  // this works until loop is false

    }
    // End of main method

    //To calculate edge capacity
    List<GraphElement> capacityCalculator(int graph[][], int s, int t) {
        //Create GraphElement list
        List<GraphElement> element = new ArrayList<GraphElement>();

        System.out.println("\n Edges " + "                 Capacity");

        //Traverse through the graph
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {

                if (graph[i][j] != 0) {
                    String arrivalNode = String.valueOf((i + 1));
                    String destinationNode = String.valueOf((j + 1));
                    int capacity = graph[i][j];
                    //set source node as "S"
                    if (arrivalNode.equals("1")) {
                        arrivalNode = "S";
                    }
                    //set sink node as "T"
                    if (destinationNode.equals(String.valueOf(graph[i].length))) {
                        destinationNode = "T";
                    }

                    System.out.print(arrivalNode + " ---> " + destinationNode + "                 ");
                    System.out.println(capacity);

                    //create GraphElement object
                    GraphElement ge = new GraphElement();
                    ge.setEdgeFrom(i + 1);
                    ge.setEdgeTo(j + 1);
                    ge.setCapacity(graph[i][j]);

                    element.add(ge);

                }
            }

        }
        return element;
    }
}
