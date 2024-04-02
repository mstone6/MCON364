package Assignment8;

import java.time.LocalDate;

public class ShulMember implements Comparable<ShulMember> {
    String firstNameOfMember;
    String lastNameOfMember;
    LocalDate birthDateOfMember;

    String spouseFirstName;
    String spouseLastName;

    String[] childrenNames;
    int yearsOfMembership;

    public ShulMember(String firstNameOfMember, String lastNameOfMember, LocalDate birthDateOfMember, String spouseFirstName,
                      String spouseLastName, String[] childrenNames, int yearsOfMembership) {
        this.firstNameOfMember = firstNameOfMember;
        this.lastNameOfMember = lastNameOfMember;
        this.birthDateOfMember = birthDateOfMember;
        this.spouseFirstName = spouseFirstName;
        this.spouseLastName = spouseLastName;
        this.childrenNames = childrenNames;
        this.yearsOfMembership = yearsOfMembership;
    }


    public String getLastNameOfMember() {
        return lastNameOfMember;
    }


    public void setLastNameOfMember(String lastNameOfMember) {
        this.lastNameOfMember = lastNameOfMember;
    }


    public String getFirstNameOfMember() {
        return firstNameOfMember;
    }


    public void setFirstNameOfMember(String firstNameOfMember) {
        this.firstNameOfMember = firstNameOfMember;
    }


    public LocalDate getBirthDateOfMember() {
        return birthDateOfMember;
    }


    public void setBirthDateOfMember(LocalDate birthDateOfMember) {
        this.birthDateOfMember = birthDateOfMember;
    }


    public String getSpouseFirstName() {
        return spouseFirstName;
    }


    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }


    public String getSpouseLastName() {
        return spouseLastName;
    }


    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }


    public String[] getChildrenNames() {
        return childrenNames;
    }


    public void setChildrenNames(String[] childrenNames) {
        this.childrenNames = childrenNames;
    }


    public int getYearsOfMembership() {
        return yearsOfMembership;
    }


    public void setYearsOfMembership(int yearsOfMembership) {
        this.yearsOfMembership = yearsOfMembership;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + firstNameOfMember + " " + lastNameOfMember + '\'' +
                ", membership years=" + yearsOfMembership +
                '}';
    }


    public int compareTo(ShulMember o) {
        // TODO Auto-generated method stub
        return this.birthDateOfMember.compareTo(o.getBirthDateOfMember());
    }
}






