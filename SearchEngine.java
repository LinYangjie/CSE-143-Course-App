import java.util.Set;
import java.util.HashSet;

// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// SearchEngine Class
// Act as an searchEngine which handle all kind of the search results.

public class SearchEngine {
    // Stores all the courses that can be searched for.
    public CourseDatabase courseTable;

    // Stores students who are able to take courses.
    public StudentDatabase studentTable;

    // Constructs a search engine with given course table and student table
    public SearchEngine(CourseDatabase courseTable, StudentDatabase studentTable) {
      this.courseTable = courseTable;
      this.studentTable = studentTable;
    }

    // Returns the set of all courses in the course database 
    // that only if the result contains all the terms in the query
    public Set<Course> search(String query) {
        Set<Course> result = new HashSet<>();
        Set<String> terms = split(query);
        boolean isFirst = true;

        for (String term : terms) {
            if (isFirst) {
                result.addAll(courseTable.findCourse(term));
                isFirst = false;
            } else {
                result.retainAll(courseTable.findCourse(term));
            }
        }
        return result;
    }

    // Returns the set of all students who are taking the course represented by the given course id
    public Set<Student> findPartner(String cid) {
        Set<Student> result = new HashSet<>();
        for (Student student : studentTable.getIdMap().values()) {
            if (student.getCourseList().contains(cid)) {
                result.add(student);
            }
        }
        return result;
    }

    // Citation: CSE 143 Assessment 03 : SearchEngine 
    // Return the set of normalized terms split from the given text
    private static Set<String> split(String text) {
        Set<String> result = new HashSet<>();
        for (String term : text.split("\\s+")) {
            term = normalize(term);
            if (!term.isEmpty()) {
                result.add(term);
            }
        }
        return result;
    }

    // Citation: CSE 143 Assessment 03 : SearchEngine
    // Returns a standardized lowercase representation of the given string
    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("(^\\p{P}+\\s*)|(\\s*\\p{P}+$)", "");
    }
}
