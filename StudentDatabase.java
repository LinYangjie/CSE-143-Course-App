import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// StudentDatabase Class
// Act as an student database save all the information of all the given students

public class StudentDatabase {
    private final HashMap<String, Student> idMap; // a student id as key, student as value
    private final Set<String> usedEmail;

    // Instantiate fields
    public StudentDatabase() {
      this.idMap = new HashMap<>();
      this.usedEmail = new HashSet<>();
    }
    
    // Adds a single person to the system given a name, id, phone, email, state, course
    public void addStudent(String firstName, String lastName, String id, String phone, String email, String state, Set<String> set) {
      idMap.put(id, new Student(firstName, lastName, id, phone, email, state, set));
      usedEmail.add(email);
    }

    // Adds course to a given student with a given course ID
    public void addCourse(String id, String cid) {
        Student p = idMap.get(id);
        p.addCoursesToStudent(cid);
    }

    // Returns boolean depending if student already exist 
    public boolean containsStudent(String id) {
      return idMap.containsKey(id);
    }

    // Getter: get id that has already been saved
    public Map<String, Student> getIdMap() {
      return new HashMap<>(this.idMap);
      
    }

    // Getter: get email that has already been saved
    public Set<String> getUsedEmail() {
      if (this.usedEmail.isEmpty()) {
        return new HashSet<>();
      } else {
        return new HashSet<>(this.usedEmail);
      }
    }
        
    // Returns a string representation of the student database consisting of each student's 
    // information, with each set of information separated by a newline.
    public String toString() {
      StringBuilder result = new StringBuilder();
      for (Student p : idMap.values()) {
        result.append(p).append("\n");
      }
      return result.toString();
    }

}
