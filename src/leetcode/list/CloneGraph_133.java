package leetcode.list;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/clone-graph/submissions/
 */
public class CloneGraph_133 {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph0(Node node) {

        HashMap<Node, Node> map = new HashMap<>();
        HashSet<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            map.put(cur, new Node(cur.val));
            queue.addAll(cur.neighbors.stream().filter(e -> set.add(e)).collect(Collectors.toList()));
        }

        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            Node curCopy = map.get(cur);
            for (Node neighbor : cur.neighbors) {
                curCopy.neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    public Node cloneGraph(Node node) {

        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        map.put(node, new Node(node.val, new ArrayList()));

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            for (Node neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    queue.add(neighbor);
                }

                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

}
