package Assignment1;

/* Create a custom class: Create a class Student with two fields: name (String)  and  grade (int). Make sure the
        Student class implements the  Comparable<Student> interface. The compareTo method should compare students based
        on their grades.
*/
public class Student implements Comparable<Student>{

    private String name;
    private int grade;

    public Student(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    public String getName(){
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student student){
        return Integer.compare(this.grade, student.grade);
    }




}

