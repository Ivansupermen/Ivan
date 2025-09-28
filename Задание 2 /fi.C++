// Функция для вычисления n-го числа Фибоначчи с использованием кучи  
long long fibonacci(int n) {  
    // Приоритетная очередь для хранения чисел Фибоначчи  
    priority_queue<long long, vector<long long>, greater<long long>> pq;  
      
    // Первые два числа Фибоначчи  
    long long a = 0, b = 1;  
      
    // Добавляем первые два числа в кучу  
    pq.push(a);  
    pq.push(b);  
      
    // Вычисляем числа Фибоначчи до n-го  
    for(int i = 2; i < n; i++) {  
        long long next = pq.top();  
        pq.pop();  
          
        // Добавляем следующее число в кучу  
        pq.push(next + pq.top());  
    }  
      
    // Возвращаем n-е число Фибоначчи  
    return pq.top();  
}  
  
int main() {  
    int n;  
    cout << "Введите число n: ";  
    cin >> n;  
      
    cout << "n-е число Фибоначчи: " << fibonacci(n) << endl;  
    return 0;  
}  
