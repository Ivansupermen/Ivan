#include <iostream>  
#include <vector>  
  
class BinomialHeap {  
private:  
    std::vector<int> heap;  
    int size;  
  
    void heapify(int i) {  
        int smallest = i;  
        int l = 2 * i + 1;  
        int r = 2 * i + 2;  
  
        if (l < size && heap[l] < heap[smallest])  
            smallest = l;  
        if (r < size && heap[r] < heap[smallest])  
            smallest = r;  
  
        if (smallest != i) {  
            std::swap(heap[i], heap[smallest]);  
            heapify(smallest);  
        }  
    }  
  
public:  
    BinomialHeap(int capacity = 10) : size(0), heap(capacity) {}  
  
    void insert(int key) {  
        heap[size++] = key;  
        heapifyUp(size - 1);  
    }  
  
    void heapifyUp(int i) {  
        while (i != 0 && heap[i] < heap[(i - 1) / 2]) {  
            std::swap(heap[i], heap[(i - 1) / 2]);  
            i = (i - 1) / 2;  
        }  
    }  
  
    int extractMin() {  
        if (size == 0) return -1;  
        int min = heap[0];  
        heap[0] = heap[--size];  
        heapify(0);  
        return min;  
    }  
};  
  
int main() {  
    BinomialHeap bh;  
    bh.insert(5);  
    bh.insert(3);  
    bh.insert(1);  
    std::cout << bh.extractMin() << std::endl; // 1  
    std::cout << bh.extractMin() << std::endl; // 3  
    std::cout << bh.extractMin() << std::endl; // 5  
    return 0;  
} 
