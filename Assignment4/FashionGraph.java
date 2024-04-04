package Assignment4;

import java.util.*;


class FashionGraph<T> implements WeightedGraphInterface<T> {
    private int maxVertices; // Maximum number of vertices in the graph
    private int numVertices; // Current number of vertices in the graph
    private T[] vertices; // Array to store the vertices
    private int[][] edges; // 2D array to store the weights of edges between vertices
    private Map<T, Integer> vertexIndices; // Map to store the indices of vertices
    private Map<T, String> vertexCountries; // Map to store the countries of vertices
    private boolean[] marks; // Array to mark vertices

    // Constructor
    public FashionGraph(int maxV) {
        maxVertices = maxV;
        numVertices = 0;
        vertices = (T[]) new Object[maxV];
        edges = new int[maxV][maxV];
        vertexIndices = new HashMap<>();
        vertexCountries = new HashMap<>();
        marks = new boolean[maxV];
    }

    // Now to add a connection between two fashion brands with a given weight
    public void addConnection(T brand1, T brand2, int weight) {
        int index1 = getIndex(brand1);
        int index2 = getIndex(brand2);
        if (index1 != -1 && index2 != -1) {
            edges[index1][index2] = weight;
            edges[index2][index1] = weight; // Assuming the graph is undirected
        }
    }

    // Helper method to get the index of a vertex
    private int getIndex(T vertex) {
        return vertexIndices.getOrDefault(vertex, -1);
    }

    // Now lets perform Breadth-First Search (BFS) to find paths between brands
    public List<T> findPath(T startBrand, T endBrand) {
        if (!vertexIndices.containsKey(startBrand) || !vertexIndices.containsKey(endBrand)) {
            return Collections.emptyList(); // One or both brands not found in the graph
        }

        Map<T, T> parentMap = new HashMap<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(startBrand);
        visited.add(startBrand);

        while (!queue.isEmpty()) {
            T currentBrand = queue.poll();
            int currentIndex = getIndex(currentBrand);
            for (int i = 0; i < numVertices; i++) {
                if (edges[currentIndex][i] > 0) { // Check if there's a connection with non-zero weight
                    T neighborBrand = vertices[i];
                    if (!visited.contains(neighborBrand)) {
                        visited.add(neighborBrand);
                        parentMap.put(neighborBrand, currentBrand);
                        queue.add(neighborBrand);
                        if (neighborBrand.equals(endBrand)) {
                            // Reconstruct the path
                            List<T> path = new ArrayList<>();
                            T parent = endBrand;
                            while (parent != null) {
                                path.add(parent);
                                parent = parentMap.get(parent);
                            }
                            Collections.reverse(path);
                            return path;
                        }
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    // Now lets find a path between two brands with the countries included
    public Map<String, List<String>> findPathWithCountries(T startBrand, T endBrand) {
        List<T> path = findPath(startBrand, endBrand);
        Map<String, List<String>> pathWithCountries = new LinkedHashMap<>();
        String currentCountry = null;
        List<String> brandsInCountry = new ArrayList<>();

        for (T brand : path) {
            String country = vertexCountries.get(brand);
            if (currentCountry == null) {
                currentCountry = country;
                brandsInCountry.add(String.valueOf(brand));
            } else if (!country.equals(currentCountry)) {
                pathWithCountries.put(currentCountry, brandsInCountry);
                currentCountry = country;
                brandsInCountry = new ArrayList<>();
                brandsInCountry.add(String.valueOf(brand));
            } else {
                brandsInCountry.add(String.valueOf(brand));
            }
        }

        if (currentCountry != null) {
            pathWithCountries.put(currentCountry, brandsInCountry);
        }

        return pathWithCountries;
    }

    // Now lets add a brand with its country
    public void addBrandWithCountry(T brand, String country) {
        vertexCountries.put(brand, country);
    }

    // This method prints the adjacency table
    public void printAdjacencyTable() {
        System.out.println("\n\n---------------Adjacency Table: ---------------");
        Set<String> printedCountries = new HashSet<>();
        for (int i = 0; i < numVertices; i++) {
            T brand = vertices[i];
            String country = vertexCountries.get(brand);
            if (!printedCountries.contains(country)) {
                System.out.println(country + ":");
                printedCountries.add(country);
            }
            System.out.println("\t\u2022 " + brand);
        }
    }

    // This method checks if the graph is empty
    public boolean isEmpty() {
        return numVertices == 0;
    }

    // This method checks if the graph is full
    public boolean isFull() {
        return numVertices == maxVertices;
    }

    // Now lets add a vertex to the graph
    public void addVertex(T vertex, String country) {
        if (!isFull() && !hasVertex(vertex)) {
            vertices[numVertices] = vertex;
            vertexIndices.put(vertex, numVertices);
            vertexCountries.put(vertex, country);
            numVertices++;
        }
    }

    // Now lets check if a vertex exists in the graph
    public boolean hasVertex(T vertex) {
        return vertexIndices.containsKey(vertex);
    }

    // Lets get the vertices by country
    public List<T> getVerticesByCountry(String country) {
        List<T> brands = new ArrayList<>();
        for (Map.Entry<T, String> entry : vertexCountries.entrySet()) {
            if (entry.getValue().equals(country)) {
                brands.add(entry.getKey());
            }
        }
        return brands;
    }

    // This method adds an edge between two vertices
    public void addEdge(T fromVertex, T toVertex, int weight) {
        //Didnt use this
    }

    // Method to get the weight of an edge between two vertices
    public int weightIs(T fromVertex, T toVertex) {
        int index1 = getIndex(fromVertex);
        int index2 = getIndex(toVertex);
        if (index1 != -1 && index2 != -1) {
            return edges[index1][index2];
        }
        return 0;
    }

    // Now lets get vertices adjacent to a given vertex
    public Queue<T> getToVertices(T vertex) {
        Queue<T> adjVertices = new LinkedList<>();
        int fromIndex = getIndex(vertex);
        for (int toIndex = 0; toIndex < numVertices; toIndex++) {
            if (edges[fromIndex][toIndex] != 0) {
                adjVertices.add(vertices[toIndex]);
            }
        }
        return adjVertices;
    }

    // Now to make clear marks from all vertices
    public void clearMarks() {
        for (int i = 0; i < numVertices; i++) {
            marks[i] = false;
        }
    }

    // Now to mark a specific vertex
    public void markVertex(T vertex) {
        int index = getIndex(vertex);
        marks[index] = true;
    }

    // Now checking if a vertex is marked
    public boolean isMarked(T vertex) {
        int index = getIndex(vertex);
        return marks[index];
    }

    // Now getting the first unmarked vertex
    public T getUnmarked() {
        for (int i = 0; i < numVertices; i++) {
            if (!marks[i]) {
                return vertices[i];
            }
        }
        return null;
    }
}
