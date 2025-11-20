import java.util.*;

public class Dijkstra {
    static final long INF = 300000;
    static int n, m, s, t;
    static boolean[] mark;
    static long[] dist;
    static List<List<Pair>> adj;

    static class Pair {
        int node;
        long weight;

        Pair(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.weight));
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().node;

            if (mark[u]) continue;
            mark[u] = true;

            for (Pair edge : adj.get(u)) {
                int v = edge.node;
                long w = edge.weight;

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Pair(v, w));
        }

        s = sc.nextInt();
        t = sc.nextInt();

        dist = new long[n + 1];
        mark = new boolean[n + 1];

        dijkstra(s);

        System.out.println(dist[t] == INF ? -1 : dist[t]);

        sc.close();
    }
}
