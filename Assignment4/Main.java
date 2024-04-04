package Assignment4;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        // Create a FashionGraph instance with a maximum capacity of 20 vertices
        FashionGraph<String> fashionGraph = new FashionGraph<>(20);

        // Making connections between additional brands based on their countries
        fashionGraph.addVertex("Dolce & Gabbana", "Italy");
        fashionGraph.addVertex("Versace", "Italy");
        fashionGraph.addVertex("Gucci", "Italy");
        fashionGraph.addVertex("Armani", "Italy");
        fashionGraph.addVertex("Louis Vuitton", "France");
        fashionGraph.addVertex("Chanel", "France");
        fashionGraph.addVertex("Givenchy", "France");
        fashionGraph.addVertex("Dior", "France");
        fashionGraph.addVertex("Bottega Veneta", "United States");
        fashionGraph.addVertex("Ralph Lauren", "United States");
        fashionGraph.addVertex("Tommy Hilfiger", "United States");
        fashionGraph.addVertex("Calvin Klein", "United States");
        fashionGraph.addVertex("Zimmerman", "Australia");
        fashionGraph.addVertex("Aje", "Australia");
        fashionGraph.addVertex("Camilla and Marc", "Australia");
        fashionGraph.addVertex("Witchery", "Australia");
        fashionGraph.addVertex("Uniqlo", "Japan");
        fashionGraph.addVertex("Comme Des Garçons", "Japan");
        fashionGraph.addVertex("Issey Miyake", "Japan");
        fashionGraph.addVertex("Yohji Yamamoto", "Japan");
        fashionGraph.addVertex("Burberry", "United Kingdom");
        fashionGraph.addVertex("Alexander McQueen", "United Kingdom");
        fashionGraph.addVertex("Vivienne Westwood", "United Kingdom");
        fashionGraph.addVertex("Stella McCartney", "United Kingdom");
        fashionGraph.addVertex("Adidas", "Germany");
        fashionGraph.addVertex("Hugo Boss", "Germany");
        fashionGraph.addVertex("Puma", "Germany");
        fashionGraph.addVertex("Marc O'Polo", "Germany");

        //Now connecting brands from different countries
        fashionGraph.addConnection("Dolce & Gabbana", "Louis Vuitton", 2);
        fashionGraph.addConnection( "Louis Vuitton",  "Versace", 2);
        fashionGraph.addConnection("Zimmerman",  "Uniqlo", 2);



        // Lets make connections between brands based on their countries
        addConnectionsByCountry(fashionGraph, "Italy");
        addConnectionsByCountry(fashionGraph, "France");
        addConnectionsByCountry(fashionGraph, "United States");
        addConnectionsByCountry(fashionGraph, "Australia");
        addConnectionsByCountry(fashionGraph, "Japan");
        addConnectionsByCountry(fashionGraph, "United Kingdom");
        addConnectionsByCountry(fashionGraph, "Germany");

        // Now lets print the adjacency table to show the connections between brands from the same country
        fashionGraph.printAdjacencyTable();


        // Now lets display paths between brands from different countries
        displayCountryPaths(fashionGraph, "Dolce & Gabbana", "Italy", "Louis Vuitton", "France");
        displayCountryPaths(fashionGraph, "Louis Vuitton", "France", "Versace", "Italy");
        displayCountryPaths(fashionGraph, "Zimmerman", "Australia", "Uniqlo", "Japan");


    }

    // This method adds connections between brands from the same country
    private static void addConnectionsByCountry(FashionGraph<String> fashionGraph, String country) {
        List<String> brands = fashionGraph.getVerticesByCountry(country);
        for (int i = 0; i < brands.size() - 1; i++) {
            for (int j = i + 1; j < brands.size(); j++) {
                fashionGraph.addConnection(brands.get(i), brands.get(j), 2);
            }
        }
    }


    // Now lets display a brand and all the brands it is connected to along with paths
    public static void displayBrandConnections(FashionGraph<String> fashionGraph, String brand) {
        System.out.println("\nBrand: " + brand);
        for (String connectedBrand : fashionGraph.getToVertices(brand)) {
            int weight = fashionGraph.weightIs(brand, connectedBrand);
            if (weight > 0) {
                List<String> path = fashionGraph.findPath(brand, connectedBrand);
                if (!path.isEmpty()) {
                    System.out.println("Connected Brand: " + connectedBrand);
                    System.out.print("Path: ");
                    String currentCountry = null;
                    for (String pathBrand : path) {
                        List<String> countries = fashionGraph.getVerticesByCountry(pathBrand);
                        if (!countries.isEmpty()) {
                            String country = countries.get(0); // Assuming a brand belongs to only one country
                            if (!country.equals(currentCountry)) {
                                if (currentCountry != null) {
                                    System.out.print(" -> " + country + ": ");
                                } else {
                                    System.out.print(country + ": ");
                                }
                                currentCountry = country;
                            }
                        }
                        System.out.print(pathBrand + ", ");
                    }
                    System.out.println();
                } else {
                    System.out.println("No path found between " + brand + " and " + connectedBrand);
                }
            }
        }
        System.out.println();
    }



    // This method displays a path between brands from different countries
    private static void displayCountryPaths(FashionGraph<String> fashionGraph, String brand1, String country1, String brand2, String country2) {
        System.out.println("\nPath between " + brand1 + " (" + country1 + ") and " + brand2 + " (" + country2 + "):");

        List<String> path = fashionGraph.findPath(brand1, brand2);
        if (!path.isEmpty()) {
            // Display brands from brand1's country
            System.out.print(brand1 + " -> ");
            List<String> brandsInCountry1 = fashionGraph.getVerticesByCountry(country1);
            for (String brand : brandsInCountry1) {
                if (!brand.equals(brand1)) {
                    System.out.print(brand + ", ");
                }
            }

            // Display intermediate countries without brands
            Set<String> intermediateCountries = new HashSet<>();
            for (String brand : path) {
                List<String> countries = fashionGraph.getVerticesByCountry(brand);
                if (!countries.isEmpty()) {
                    intermediateCountries.add(countries.get(0));
                }
            }
            for (String country : intermediateCountries) {
                System.out.print(" -> " + country + ": ");
            }

            // Now lets display brands from brand2's country
            System.out.print(brand2 + " -> ");
            List<String> brandsInCountry2 = fashionGraph.getVerticesByCountry(country2);
            for (String brand : brandsInCountry2) {
                if (!brand.equals(brand2)) {
                    System.out.print(brand + ", ");
                }
            }

            System.out.println();
        } else {
            System.out.println("No path found between " + brand1 + " and " + brand2);
        }
    }





}
