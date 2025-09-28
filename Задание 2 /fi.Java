import java.util.ArrayList;  
import java.util.List;  
  
public class FibonacciHeap {  
    private List<Node> nodes = new ArrayList<>();  
    private Node min = null;  
  
    public static class Node {  
        int key;  
        List<Node> children = new ArrayList<>();  
        Node parent = null;  
        boolean marked = false;  
  
        public Node(int key) {  
            this.key = key;  
        }  
  
        @Override  
        public String toString() {  
            return String.valueOf(key);  
        }  
    }  
  
    public void insert(int key) {  
        Node newNode = new Node(key);  
        nodes.add(newNode);  
        if (min == null || newNode.key < min.key) {  
            min = newNode;  
        }  
    }  
  
    public void consolidate() {  
        int[] degree = new int[nodes.size()];  
        for (Node node : nodes) {  
            if (node.parent != null) {  
                degree[node.parent.degree()]++;  
            }  
        }  
  
        List<Node> consolidatedNodes = new ArrayList<>();  
        for (Node node : nodes) {  
            if (node.parent == null) {  
                consolidateNode(node, degree, consolidatedNodes);  
            }  
        }  
  
        nodes = consolidatedNodes;  
        for (Node node : nodes) {  
            if (min == null || node.key < min.key) {  
                min = node;  
            }  
        }  
    }  
  
    private void consolidateNode(Node node, int[] degree, List<Node> consolidatedNodes) {  
        int d = node.degree();  
        while (degree[d] > 0) {  
            Node temp = nodes.get(nodes.size() - 1);  
            nodes.remove(nodes.size() - 1);  
            if (node.key <= temp.key) {  
                link(node, temp);  
                node.marked = true;  
            } else {  
                link(temp, node);  
            }  
            degree[d]--;  
            d++;  
        }  
        consolidatedNodes.add(node);  
    }  
  
    private void link(Node minNode, Node otherNode) {  
        otherNode.parent = minNode;  
        minNode.children.add(otherNode);  
    }  
  
    public int extractMin() {  
        int minKey = min.key;  
        removeMin();  
        return minKey;  
    }  
  
    private void removeMin() {  
        if (min != null) {  
            for (Node node : nodes) {  
                if (node.parent == min) {  
                    node.parent = null;  
                }  
            }  
            min = null;  
            consolidate();  
        }  
    }  
  
    public static void main(String[] args) {  
        FibonacciHeap heap = new FibonacciHeap();  
        heap.insert(1);  
        heap.insert(2);  
        heap.insert(3);  
        heap.insert(4);  
        heap.insert(5);  
  
        System.out.println("Minimum: " + heap.extractMin());  
        System.out.println("Minimum: " + heap.extractMin());  
        System.out.println("Minimum: " + heap.extractMin());  
        System.out.println("Minimum: " + heap.extractMin());  
        System.out.println("Minimum: " + heap.extractMin());  
    }  
}  
