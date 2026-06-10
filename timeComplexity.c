#include <stdio.h>
#include <stdlib.h>
#include <time.h>

long long diff_ns(clock_t a, clock_t b) {
    return (long long)(b - a) * 1000000000LL / CLOCKS_PER_SEC;
}

int binarySearch(int arr[], int low, int high, int x) {
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == x) return mid;
        if (arr[mid] < x) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}

int linearSearch(int arr[], int n, int x) {
    for (int i = 0; i < n; i++)
        if (arr[i] == x) return i;
    return -1;
}

void bubbleSort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++)
        for (int j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1]) {
                int t = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = t;
            }
}

void measure_time(int n) {
    clock_t t1, t2;
    long long auxTime, algoTime;
    int *arr;

    t1 = clock();
    arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++)
        arr[i] = i;
    t2 = clock();
    auxTime = diff_ns(t1, t2);

    printf("\nN = %d\n", n);
    printf("Auxiliary Operations Time: %lld ns\n", auxTime);

    t1 = clock();
    linearSearch(arr, n, n - 1);
    t2 = clock();
    algoTime = diff_ns(t1, t2);
    printf("Linear Search Time: %lld ns\n", algoTime);

    t1 = clock();
    for (int i = 0; i < 1000000; i++)
        binarySearch(arr, 0, n - 1, n - 1);
    t2 = clock();
    algoTime = diff_ns(t1, t2);
    printf("Binary Search Time (1M ops): %lld ns\n", algoTime);

    if (n <= 20000) {
        t1 = clock();
        bubbleSort(arr, n);
        t2 = clock();
        algoTime = diff_ns(t1, t2);
        printf("Bubble Sort Time: %lld ns\n", algoTime);
    } else {
        printf("Bubble Sort Time: skipped\n");
    }

    free(arr);
}

int main() {
    int sizes[] = {1000, 10000, 20000};
    for (int i = 0; i < 3; i++)
        measure_time(sizes[i]);
    return 0;
}