import java.util.Set;
import java.util.HashSet;

// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// Student Class
// An object stores all the necessary information of a student, 
// includes firstName, lastName, student id, phone number, email and state.

public class Student {
    // Fields store personal information of the student.
    private final String firstName;
    private final String lastName;
    private final String id;
    private final String phone;
    private final String email;
    private final String state;

    // Stores all the courses the student is taking.
    private final Set<String> courseList;

    // Constructs the person given name, id, phone, email, state, courses
    public Student(String firstName, String lastName, String id, String phone, String email, String state, Set<String> courseList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.state = state;
        this.courseList = new HashSet<>(courseList);
    }

    // Getter: Returns first name of student
    public String getFirstName() {
        return firstName;
    }

    // Getter: Returns last name of student
    public String getLastName() {
        return lastName;
    }

    // Getter: Returns the ID of student
    public String getId() {
        return id;
    }

    // Getter: Returns phone of student
    public String getPhone() {
        return phone;
    }

    // Getter: Returns email of student
    public String getEmail() {
        return email;
    }

    //Getter: Returns state of student
    public String getState() {
        return state;
    }

    // Getter: Returns course list of student
    public Set<String> getCourseList() {
        return new HashSet<>(courseList);
    }

    // Adds a given course ID to student
    public void addCoursesToStudent(String cid) {
        this.courseList.add(cid);
    }

    // print out the person object for convenience. including field name, id, phone, email, state, course
    @Override
    public String toString(){
        return "[name:" + this.firstName 
                        + " " 
                        + this.lastName
                        + ", id:" + this.id 
                        + ", phone:" + this.phone 
                        + ", email:" + this.email 
                        + ", state:" + this.state
                        + ", courseList:" + this.courseList + "]"; 
    }

}
