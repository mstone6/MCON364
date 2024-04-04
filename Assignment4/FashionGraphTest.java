package Assignment4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class FashionGraphTest {

    // Test case for finding a valid path between two brands
    @Test
    public void testFindPathWithValidStartAndEndBrand() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices and connections to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");
        graph.addVertex("Brand3", "Country2");
        graph.addConnection("Brand1", "Brand2", 1);
        graph.addConnection("Brand2", "Brand3", 1);

        // Define the expected path
        List<String> expected = Arrays.asList("Brand1", "Brand2", "Brand3");
        // Find the path between two brands
        List<String> result = graph.findPath("Brand1", "Brand3");

        // Assert that the expected path matches the result
        assertEquals(expected, result);
    }

    // Test case for finding a path with an invalid end brand
    @Test
    public void testFindPathWithInvalidEndBrand() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices and connections to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");
        graph.addConnection("Brand1", "Brand2", 1);

        // Find the path between two brands with an invalid end brand
        List<String> result = graph.findPath("Brand1", "Brand3");

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }

    // Test case for finding a path with an invalid start brand
    @Test
    public void testFindPathWithInvalidStartBrand() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices and connections to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");
        graph.addConnection("Brand1", "Brand2", 1);

        // Find the path between two brands with an invalid start brand
        List<String> result = graph.findPath("Brand3", "Brand2");

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }

    // Test case for finding a path with no connection
    @Test
    public void testFindPathWithNoConnection() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");

        // Find the path between two brands with no connection
        List<String> result = graph.findPath("Brand1", "Brand2");

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }

    // Test case for finding a path with multiple possible paths
    @Test
    public void testFindPathWithMultiplePossiblePaths() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices and connections to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");
        graph.addVertex("Brand3", "Country1");
        graph.addVertex("Brand4", "Country2");
        graph.addVertex("Brand5", "Country2");
        graph.addConnection("Brand1", "Brand2", 1);
        graph.addConnection("Brand2", "Brand3", 1);
        graph.addConnection("Brand1", "Brand4", 1);
        graph.addConnection("Brand4", "Brand5", 1);
        graph.addConnection("Brand5", "Brand3", 1);

        // Define the expected path
        List<String> expected = Arrays.asList("Brand1", "Brand2", "Brand3");
        // Find the path between two brands
        List<String> result = graph.findPath("Brand1", "Brand3");

        // Assert that the expected path matches the result
        assertEquals(expected, result);
    }

    // Test case for finding a path in a disconnected graph
    @Test
    public void testFindPathWithDisconnectedGraph() {
        // Create a FashionGraph instance
        FashionGraph<String> graph = new FashionGraph<>(5);
        // Add vertices to the graph
        graph.addVertex("Brand1", "Country1");
        graph.addVertex("Brand2", "Country1");
        graph.addVertex("Brand3", "Country2");
        graph.addVertex("Brand4", "Country2");

        // Find the path between two brands in a disconnected graph
        List<String> result = graph.findPath("Brand1", "Brand4");

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }
}
