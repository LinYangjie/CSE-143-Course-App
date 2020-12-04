// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// Course Class
// A object stores all the necessary information of a course, 
// includes department, courseNumber, courseName, Cid (like SLN code)

public class Course {
    // Fields store course related information
    private final String department;
    private final String courseNumber;
    private final String courseName;
    private final String cid; // course id field is a unique field.

    //Constructs the course with the given department, course number, course name, course ID
    public Course(String dep, String CourseNumber, String CourseName, String Cid) {
        this.department = dep;
        this.courseNumber = CourseNumber;
        this.courseName = CourseName;
        this.cid = Cid;
    }

    //Getter: Returns department
    public String getDept() {
        return department;
    }

    //Getter: Returns course number
    public String getCourseNumber() {
        return courseNumber;
    }

    //Getter: Returns course name
    public String getCourseName() {
        return courseName;
    }

    //Getter: Returns course ID
    public String getCid() {
        return cid;
    }

    //Returns boolean depending if content is found 
    public boolean find(String content) {
        return (department.toLowerCase().contains(content) 
                || courseNumber.toLowerCase().contains(content)
                || courseName.toLowerCase().contains(content)
                || cid.contains(content));        
    }

    // Returns a string representation of the course consisting of all the course's information
    // on a single line, separated by spaces.
    public String toString() {
        return "[Dept: " + department 
            + " CourseNumber: " + courseNumber 
            + " CourseName: " + courseName 
            + " CourseId: " + cid + "]";
    }
}
