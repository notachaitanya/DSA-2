import java.util.*;

public class SocialmediaRecommendationNetwork {

    private Map<String, List<String>> network;

    public SocialmediaRecommendationNetwork() {
        network = new HashMap<>();
    }

    void addConnection(String user, String follows) {
        network.putIfAbsent(user, new ArrayList<>());
        network.get(user).add(follows);
    }

    void BFS(String startUser) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startUser);
        queue.add(startUser);

        System.out.println("\nFriend Recommendations using BFS:");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.println("Visited: " + current);

            if(network.containsKey(current)) {

                for(String user : network.get(current)) {

                    if(!visited.contains(user)) {
                        visited.add(user);
                        queue.add(user);
                    }
                }
            }
        }
    }

    void DFS(String user, Set<String> visited) {

        visited.add(user);

        System.out.println("Visited: " + user);

        if(network.containsKey(user)) {

            for(String next : network.get(user)) {

                if(!visited.contains(next))
                    DFS(next, visited);
            }
        }
    }

    public static void main(String[] args) {

        SocialmediaRecommendationNetwork app =
                new SocialmediaRecommendationNetwork();

        app.addConnection("Arjun", "Priya");
        app.addConnection("Arjun", "Rahul");

        app.addConnection("Priya", "Sneha");
        app.addConnection("Rahul", "Vikram");

        app.addConnection("Sneha", "Ananya");

        System.out.println("====================================");
        System.out.println("   SOCIAL MEDIA USER NETWORK ANALYSIS");
        System.out.println("====================================");

        app.BFS("Arjun");

        System.out.println("\nDepth First Exploration (DFS):");

        app.DFS("Arjun", new HashSet<>());
    }
}