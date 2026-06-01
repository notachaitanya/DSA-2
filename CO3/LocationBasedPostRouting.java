import java.util.*;

public class LocationBasedPostRouting {

    static final int V = 5;

    String[] locations = {
            "User Location",
            "City Park Post",
            "Shopping Mall Post",
            "College Campus Post",
            "Food Street Post"
    };

    int minDistance(int dist[], boolean visited[]) {

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < V; i++) {

            if(!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    void dijkstra(int graph[][], int source) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        for(int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);

            visited[u] = true;

            for(int v = 0; v < V; v++) {

                if(!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("==============================================");
        System.out.println(" LOCATION-BASED POST ROUTING ANALYSIS");
        System.out.println("==============================================");

        System.out.println("\nShortest Distance from User:\n");

        int nearest = 1;

        for(int i = 1; i < V; i++) {

            System.out.printf("%-25s : %d meters\n",
                    locations[i], dist[i]);

            if(dist[i] < dist[nearest])
                nearest = i;
        }

        System.out.println("\n----------------------------------------------");
        System.out.println("Nearest Location-Based Post:");
        System.out.println(locations[nearest]);
        System.out.println("Distance : " + dist[nearest] + " meters");
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args) {

        int graph[][] = {

                {0, 120, 250, 180, 300},
                {120, 0, 90, 60, 200},
                {250, 90, 0, 100, 80},
                {180, 60, 100, 0, 140},
                {300, 200, 80, 140, 0}
        };

        LocationBasedPostRouting routing =
                new LocationBasedPostRouting();

        routing.dijkstra(graph, 0);
    }
}