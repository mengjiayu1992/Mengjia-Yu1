import java.util.*;


class Node {
    String pageName;
    List<Edge> neighbors; 
    int heuristic;        

    public Node(String name, int heuristic) {
        this.pageName = name;
        this.heuristic = heuristic;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node neighbor, int cost) {
        this.neighbors.add(new Edge(neighbor, cost));
    }
}


class Edge {
    Node targetNode;
    int cost;

    public Edge(Node targetNode, int cost) {
        this.targetNode = targetNode;
        this.cost = cost;
    }
}


class AStarSearch {

    public static void aStarSearch(Node start, Node goal) {
        PriorityQueue<NodeRecord> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));

        Map<Node, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        Map<Node, Node> cameFrom = new HashMap<>();

        openList.add(new NodeRecord(start, 0, start.heuristic));

        while (!openList.isEmpty()) {
            NodeRecord currentRecord = openList.poll();
            Node current = currentRecord.node;

            if (current == goal) {
                reconstructPath(cameFrom, current);
                return;
            }

            for (Edge edge : current.neighbors) {
                Node neighbor = edge.targetNode;
                int tentativeGScore = gScore.get(current) + edge.cost;

                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + neighbor.heuristic;
                    openList.add(new NodeRecord(neighbor, tentativeGScore, fScore));
                }
            }
        }

        System.out.println("No path found to the goal!");
    }

    public static void reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<String> totalPath = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            totalPath.add(current.pageName);
            current = cameFrom.get(current);
        }
        totalPath.add(current.pageName); 
        Collections.reverse(totalPath);

        System.out.println("Path found: " + String.join(" -> ", totalPath));
    }

    public static void main(String[] args) {

        Node homePage = new Node("Home Page", 4);
        Node computingPage = new Node("School of Computing", 3);
        Node facultyPage = new Node("Faculty Page", 1);
        Node profVardePage = new Node("Dr. Aparna Varde's Page", 0); 

        homePage.addNeighbor(computingPage, 1); 
        computingPage.addNeighbor(facultyPage, 1); 
        facultyPage.addNeighbor(profVardePage, 1); 

        aStarSearch(homePage, profVardePage);
    }
}

class NodeRecord {
    Node node;
    int gCost;  
    int fCost;  

    public NodeRecord(Node node, int gCost, int fCost) {
        this.node = node;
        this.gCost = gCost;
        this.fCost = fCost;
    }
}
