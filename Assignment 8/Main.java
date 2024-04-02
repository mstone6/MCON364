package Assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<ShulMember> shulMembers = new ArrayList<ShulMember>();
        shulMembers.add(new ShulMember("John", "Davidson", LocalDate.of(1990, 5, 15), "Jane", "Davidson",
                new String[]{"Alice", "Crew", "Millie", "Rosalie"}, 7));
        shulMembers.add(new ShulMember("Alice", "Smith", LocalDate.of(1985, 9, 20), "Bob", "Smith",
                new String[]{"Charlie", "David"}, 10));
        shulMembers.add(new ShulMember("Sarah", "Johnson", LocalDate.of(1978, 12, 10), "Michael", "Johnson",
                new String[]{"Emily"}, 5));
        shulMembers.add(new ShulMember("Emily", "Brown", LocalDate.of(1982, 3, 25), "David", "Brown",
                new String[]{"Ethan", "Olivia"}, 3));
        shulMembers.add(new ShulMember("Michael", "Taylor", LocalDate.of(1975, 8, 5), "Sarah", "Taylor",
                new String[]{"Liam", "Emma", "Bobby"}, 8));
        shulMembers.add(new ShulMember("David", "Clark", LocalDate.of(1980, 6, 12), "Emma", "Clark",
                new String[]{"Sophia", "Jacob", "Ava"}, 6));
        shulMembers.add(new ShulMember("Daniel", "Lewis", LocalDate.of(1983, 10, 8), "Olivia", "Lewis",
                new String[]{"Mia", "Benjamin", "Ella", "Elijah"}, 4));
        shulMembers.add(new ShulMember("James", "Anderson", LocalDate.of(1970, 4, 30), "Jennifer", "Anderson",
                new String[]{"Alexander", "Grace", "Isabella", "Lucas"}, 12));
        shulMembers.add(new ShulMember("Matthew", "Harris", LocalDate.of(1988, 8, 15), "Samantha", "Harris",
                new String[]{"Daniel", "Sophie", "Lily"}, 9));
        shulMembers.add(new ShulMember("Andrew", "Moses", LocalDate.of(1995, 12, 5), "Jessica", "Moses",
                new String[]{"Noah", "Chloe"}, 2));

        System.out.println("\n**** Welcome To Beth Israel Synagogue - Where Every Jew Is Welcome ****");
        System.out.println("_____________________________________________________________________________");
        //Now Printing the amount of families that belong to our shul
        long numbOfFamilies = shulMembers.stream()
                .map(shulMember -> shulMember.getLastNameOfMember())
                .distinct()
                .count();
        System.out.println("\n The number of families we have in our Shul is: " + numbOfFamilies);

        System.out.println("\nOur Families Have Been Members For: ");
        shulMembers.stream()
                .sorted(Comparator.comparing(ShulMember::getYearsOfMembership))
                .forEach(shulMember -> {
                    int membershipYears = LocalDate.now().getYear() - (LocalDate.now().getYear() - shulMember.getYearsOfMembership());
                    System.out.println(" The " + shulMember.getLastNameOfMember() + " Family: " + membershipYears + " Years");
                });


        //Now printing out the ages of our members youngest to oldest
        System.out.println("\nOur Members in order of Oldest To Youngest: (Excluding Spouses) ");
        shulMembers.stream()
                .sorted(Comparator.comparing(ShulMember::getBirthDateOfMember))
                .forEach(shulMember -> {
                    int age = LocalDate.now().getYear() - shulMember.getBirthDateOfMember().getYear();
                    System.out.println(shulMember.getFirstNameOfMember() + " " + shulMember.getLastNameOfMember() + ": " + age);
                });

        //Now sorting the names of all the members spouses
        System.out.println("\nMember Spouses: ");
        shulMembers.stream()
                .map(member -> member.getSpouseFirstName() + " ~ " + member.getSpouseLastName())
                .sorted()
                .forEach(System.out::println);

        //Now printing the number of families that have more than three kids
        long moreThanThree = shulMembers.stream()
                .filter(shulMember -> shulMember.getChildrenNames().length >= 4)
                .count();
        System.out.println("\nThe number of families who have more than 3 children: " + moreThanThree);




        System.out.println("\nThe kids whose names start with letters that are bigger than 'C': ");
        // Iterate over each ShulMember, filter, and sort their children's names, then print
        shulMembers.forEach(member -> {
            Arrays.stream(member.getChildrenNames())
                    .filter(childName -> Character.toLowerCase(childName.charAt(0)) > 'c')
                    .sorted() // Sort the children's names alphabetically
                    .forEach(childName -> System.out.println(member.getLastNameOfMember() + ": " + childName));
        });








    }

}
