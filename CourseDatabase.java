import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// CourseDatabase Class
// Act as an course database save all the information of all the given courses

public class CourseDatabase {
    // Stores all the available courses in the course database.
    private final List<Course> courseCatalog;

    // Stores unique course identifiers and the courses corresponding to each.
    private final Map<String, Course> courseIdMap; 

    // Constructs the course database.
    public CourseDatabase() {
        this.courseCatalog = new ArrayList<>();
        this.courseIdMap = new HashMap<>();
    }

    // Adds the given course to the course catalog.
    public void addClass(Course course) {
        courseCatalog.add(course);
        courseIdMap.put(String.valueOf(courseIdMap.size()), course);
    }

    // Returns the course stored in the database corresponding to the given course identifier.
    public Course getCourse(String cid) {
        Course c = courseIdMap.get(cid);
        return new Course(c.getDept(), c.getCourseNumber(), c.getCourseName(), c.getCid());
    }

    // Returns whether the course corresponding to the given course identifier is stored in the 
    // database or not.
    public boolean containsCourse(String cid) {
        return this.courseIdMap.containsKey(cid);
    }

    // Returns the set of all courses that are relevant to the given search query.
    public Set<Course> findCourse(String query) {
        Set<Course> result = new HashSet<>();
        for (Course c : courseCatalog) {
            if (c.find(query)) {
                result.add(c);
            }
        }
        return result;
    }

    // Returns a string representation of the course database, consisting of a list of every
    // course stored in the catalog, each separated by a newline.
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Course c : this.courseCatalog) {
            result.append(c.toString()).append("\n");
        }
        return result.toString();
    }
}
