import java.util.*;


class Graph {
    private Map<String, List<String>> adjacencyList;


    public Graph() {
        adjacencyList = new HashMap<>();
    }


    public void addEdge(String u, String v) {
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.get(u).add(v);
    }


    public void bfs(String start, String goal) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();
        
        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        System.out.println("BFS Traversal:");
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " -> ");
            

            if (current.equals(goal)) {
                System.out.println("Goal Reached: " + goal);
                printPath(parent, start, goal);
                return;
            }


            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("Goal not reachable from " + start);
    }


    public void dfs(String start, String goal) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        stack.push(start);
        visited.add(start);
        parent.put(start, null);

        System.out.println("DFS Traversal:");
        
        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.print(current + " -> ");
            

            if (current.equals(goal)) {
                System.out.println("Goal Reached: " + goal);
                printPath(parent, start, goal);
                return;
            }


            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }
        System.out.println("Goal not reachable from " + start);
    }


    private void printPath(Map<String, String> parent, String start, String goal) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = goal; at != null; at = parent.get(at)) {
            path.addFirst(at);
        }
        System.out.println("Path: " + String.join(" -> ", path));
    }
}

public class Main {
    public static void main(String[] args) {

        Graph townGraph = new Graph();
        

        townGraph.addEdge("Montclair", "Clifton");
        townGraph.addEdge("Montclair", "Bloomfield");
        townGraph.addEdge("Clifton", "Paterson");
        townGraph.addEdge("Paterson", "New York");
        townGraph.addEdge("Bloomfield", "Jersey City");
        townGraph.addEdge("Jersey City", "New York");

        String startLocation = "Montclair";
        String goalLocation = "New York";

 
        System.out.println("Running BFS:");
        townGraph.bfs(startLocation, goalLocation);
        
  
        System.out.println("\nRunning DFS:");
        townGraph.dfs(startLocation, goalLocation);
    }
}
