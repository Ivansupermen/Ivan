class Node {
    int key, degree;
    Node child, sibling;
    Node(int k) { key = k; }
}

public class BinomialHeap {
    private Node head;
    public void insert(int k) {
        BinomialHeap h = new BinomialHeap();
        h.head = new Node(k);
        union(h);
    }
    public int getMin() {
        Node min = head, curr = head;
        while (curr != null) {
            if (curr.key < min.key) min = curr;
            curr = curr.sibling;
        }
        return min.key;
    }
    public int extractMin() {
        Node min = head, prev = null, minPrev = null, curr = head;
        while (curr != null) {
            if (curr.key < min.key) { min = curr; minPrev = prev; }
            prev = curr; curr = curr.sibling;
        }
        if (minPrev == null) head = min.sibling;
        else minPrev.sibling = min.sibling;
        BinomialHeap h = new BinomialHeap();
        Node child = min.child, prevChild = null;
        while (child != null) {
            Node next = child.sibling;
            child.sibling = prevChild;
            prevChild = child;
            child = next;
        }
        h.head = prevChild;
        union(h);
        return min.key;
    }
    private void union(BinomialHeap h) {
        head = merge(head, h.head);
        Node x = head;
        while (x != null && x.sibling != null) {
            if (x.degree == x.sibling.degree) {
                if (x.key > x.sibling.key) {
                    Node temp = x;
                    x = x.sibling;
                    if (temp == head) head = x;
                }
                link(x, x.sibling);
                x.sibling = x.sibling.sibling;
            } else x = x.sibling;
        }
    }
    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.degree > b.degree) return merge(b, a);
        a.sibling = merge(a.sibling, b);
        return a;
    }
    private void link(Node p, Node c) {
        c.sibling = p.child;
        p.child = c;
        p.degree++;
    }
}
