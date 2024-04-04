package Assignment4;

import Assignment2.QueueInterface;

import java.util.Queue;


interface WeightedGraphInterface<T> {
    void addVertex(T vertex, String country);
    boolean hasVertex(T vertex);
    void addEdge(T fromVertex, T toVertex, int weight);
    int weightIs(T fromVertex, T toVertex);
}