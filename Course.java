// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// Course Class
// A object stores all the necessary information of a course, 
// includes department, courseNumber, courseName, Cid (like SLN code)

public class Course {
    // Fields store course related information
    private final String Department;
    private final String CourseNumber;
    private final String CourseName;
    private final String Cid; // course id field is a unique field.

    //Constructs the course with the given department, course number, course name, course ID
    public Course(String dep, String CourseNumber, String CourseName, String Cid) {
        this.Department = dep;
        this.CourseNumber = CourseNumber;
        this.CourseName = CourseName;
        this.Cid = Cid;
    }

    //Getter: Returns department
    public String getDept() {
        return Department;
    }

    //Getter: Returns course number
    public String getCourseNumber() {
        return CourseNumber;
    }

    //Getter: Returns course name
    public String getCourseName() {
        return CourseName;
    }

    //Getter: Returns course ID
    public String getCid() {
        return Cid;
    }

    //Returns boolean depending if content is found 
    public boolean find(String content) {
        return (Department.toLowerCase().contains(content) 
                || CourseNumber.toLowerCase().contains(content)
                || CourseName.toLowerCase().contains(content)
                || Cid.contains(content));        
    }

    // Returns a string representation of the course consisting of all the course's information
    // on a single line, separated by spaces.
    public String toString() {
        return "[Dept: " + Department 
            + " CourseNumber: " + CourseNumber 
            + " CourseName: " + CourseName 
            + " CourseId: " + Cid + "]";
    }
}
