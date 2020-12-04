import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.*; 
import java.util.regex.*;
import java.lang.StringBuilder;

// CSE 143  Final Project : Terminal-based Course App 
// Author : Jason Zheng, Ryan Smit and Yangjie Lin
// A terminal-based interface for student to interact with the database, which includes some useful functions

// javac Main.java && java Main; rm *.class

public class Main {
    // the path for student dataset
    private static final String COURSE_DATASET = "./resource/allCourse.csv";

    // non-white space character at least appear one times
    private static final String COMMAND_PATTERN = "(\\S+)"; 

    // format [alphabet a-z and some special characters at least one times] @ [alphabet a-z or 0-9 or . or - and appears at least one times] 
    // RFC 5322 pattern;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+"; 

    // ID must be 7 digits in length, and only include numbers 0-9
    private static final String ID_PATTERN = "^[0-9]{7}$";

    //State must be 2-3 letters long, and only include letters A-Z
    private static final String STATE_PATTERN = "^[A-Z]{2,3}$"; 

    // Menu functionality
    // provide a interface for users to interact with the database 
    // citation: CSE 414 Introduction to Database System
    private static void menu(StudentDatabase sd, CourseDatabase cd, SearchEngine se) throws IOException {
      while (true) {
        System.out.println();
        System.out.println(" *** Please enter one of the following commands *** ");
        System.out.println("> create <firstName> <lastName> <id> <phoneNumber> <email> <state>");
        System.out.println("> search <course>");
        System.out.println("> addCourse <id> <courseId>");
        System.out.println("> findPartner <courseId>");
        System.out.println("> quit");
        System.out.println(" *** Visualization *** ");
        System.out.println("> printCourse");
        System.out.println("> printStudent");

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        String command = r.readLine();
        
        String response = execute(sd, cd, se, command);
        System.out.println(response);
        if (response.equals("Goodbye\n")) {
          break;
        }
      }
    }

    // Tokenizes a given user input
    // citation: CSE 414 Introduction to Database System
    public static String[] tokenize(String command) {
      Matcher m = Pattern.compile(COMMAND_PATTERN).matcher(command);
      List<String> tokens = new ArrayList<>();
      while (m.find()) {
        if (m.group(1) != null) {
          tokens.add(m.group(1));
        } 
      }
      return tokens.toArray(new String[0]);
    }

    // To execute variety of known commands includes all the command in the menu
    // citation: CSE 414 Introduction to Database System
    public static String execute(StudentDatabase sd, CourseDatabase cd, SearchEngine se, String command) {
      String[] tokens = tokenize(command.trim());
      String response;
      if (tokens.length == 0) {
        response = "Please enter a command";

      } else if (tokens[0].equals("create")) {
        if (tokens.length == 7) {      // valid command form
          String firstName = tokens[1];
          String lastName = tokens[2];
          String id = tokens[3];
          String phoneNumber = tokens[4];
          String email = tokens[5];
          String state = tokens[6];
          String validation = checkValidation(firstName, lastName, id, email, state, sd);
          if (validation.equals("user " + firstName + " " + lastName + " has been created")) {
            sd.addStudent(firstName, lastName, id, phoneNumber, email, state, new HashSet<>());
            writeCSV(firstName, lastName, id, phoneNumber, email, state);
          }
          response = validation;
        } else {
          response = "invalid execution form";
        }

      } else if (tokens[0].equals("search")) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
          sb.append(tokens[i]).append(" ");
        }
        Set<Course> result = se.search(sb.toString());
        response = result.isEmpty() ? "keyword not found" : printCourse(result);

      } else if (tokens[0].equals("addCourse")) {
        if (tokens.length == 3) {
          String id = tokens[1];
          String cid = tokens[2];
          if (idValid(id) && sd.containsStudent(id) && cd.containsCourse(cid)) {
             sd.addCourse(id, cid);
             updateCSV(sd);
             response = "Course:" + cd.getCourse(cid).getDept() + cd.getCourse(cid).getCourseNumber()
                      + " \"" + cd.getCourse(cid).getCourseName() + "\" has been added to your courseList";
          } else if (!idValid(id)) {
            response = "invalid id form";
          } else if (!sd.containsStudent(id)) {
            response = "person not found";            
          } else if (!cd.containsCourse(cid)) {
            response = "course not found";
          } else {
            response = "invalid execution form";
          }
        } else {
          response = "invalid execution form";
        }

      } else if (tokens[0].equals("findPartner")) {
        if (tokens.length == 2) {
          String cid = tokens[1];
          if (cd.containsCourse(cid)) {
            response = "Course information: " + cd.getCourse(cid).toString() + "\t" +
            printStudent(se.findPartner(cid));
          } else {
            response = "course id not found";
          }
        } else {
          response = "invalid excution form";
        }

      } else if (tokens[0].equals("printCourse")) {
        response = cd.toString();

      } else if (tokens[0].equals("printStudent")) {
        response = sd.toString();

      } else if (tokens[0].equals("quit")) {
        response = "Goodbye\n";

      } else {
        response = "unrecognized command";

      }
      return response;
    }
    
    // Checks if given name, id, email, state are all valid
    private static String checkValidation(String firstName, String lastName, String id, String email, String state, StudentDatabase sd) {
      if (sd.containsStudent(id)) {
        return "id: " + id + " has been used";
      }

      if (!idValid(id)) {
        return "id: " + id + " is not a valid id form";
      }

      if (!emailValid(email)) {
        return "email: " + email + " is not a valid email form";
      }

      if (!stateValid(state)) {
        return "state: " + state + " is not a valid email form";
      }
      return "user " + firstName + " " + lastName + " has been created";
    }

    // Checks if the input match the given pattern of the id
    private static boolean idValid(String id) {
      Pattern pattern = Pattern.compile(ID_PATTERN);
      Matcher matcher = pattern.matcher(id);
      return matcher.find();
    }

    // Checks if the input match the given pattern of the email
    private static boolean emailValid(String email) {
      Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(email);
      return matcher.find();
    }

    // Checks if the input match the given pattern of the state
    private static boolean stateValid(String state) {
      Pattern pattern = Pattern.compile(STATE_PATTERN);
      Matcher matcher = pattern.matcher(state);
      return matcher.find();
    }

    // read courses local files into the courseDataBase object
    private static void insertCourseData(CourseDatabase cd) {
      try {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(COURSE_DATASET));
        int count = 0;
        while ((line = br.readLine()) != null) {
          String[] result = line.split(",", 3);
          cd.addClass(new Course(result[0], result[1], result[2], Integer.toString(count)));
          count++;
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // read students local files into the studentDataBase object
    private static void insertStudentData(StudentDatabase sd) {
      try {
        String line;
        BufferedReader br = new BufferedReader(new FileReader("./resource/randomStudent.csv"));
        while ((line = br.readLine()) != null) {
          String[] result = line.split(",");
          String[] name = result[0].split(" ");
          Set<String> set = new HashSet<>();
          if (result.length > 5) {
            for (int i = 5; i < result.length; i++) {
              set.add(result[i]);
            }
          }
          sd.addStudent(name[0], name[1], result[1], result[2], result[3], result[4], set);
          
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    }

    // Creates a file with given name, ID, phone, email, state
    private static void writeCSV(String firstName, String lastName, String id, String phone, String email, String state) {
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./resource/randomStudent.csv", true));
        PrintWriter pw = new PrintWriter(bw);
        pw.println(firstName + " " + lastName + "," + id + "," + phone + "," + email + "," + state);
        pw.flush();
        pw.close();
        
      } catch (Exception E) {
        E.printStackTrace();
      }
    }

    // Updates file with given student info
    private static void updateCSV(StudentDatabase sd) {
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./resource/randomStudent.csv", false));
        PrintWriter pw = new PrintWriter(bw);
        for (Student s : sd.getIdMap().values()) {
          if (!s.getCourseList().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String courseId : s.getCourseList()) {
              sb.append(",").append(courseId);
            }
            pw.println(s.getFirstName() + " " + s.getLastName() + "," + s.getId() + "," + s.getPhone() + "," + s.getEmail() + "," + s.getState() + sb);
          } else {
            pw.println(s.getFirstName() + " " + s.getLastName() + "," + s.getId() + "," + s.getPhone() + "," + s.getEmail() + "," + s.getState());
          }
          
        }
        pw.flush();
        pw.close();
      } catch (Exception E) {
        E.printStackTrace();
      }

    }

    // Returns a String by format a given Course set
    private static String printCourse(Set<Course> set) {
      int count = 1;
      StringBuilder sb = new StringBuilder();
      sb.append("Match Found: ").append(set.size()).append("\n");
      sb.append("**************************************************************")
        .append("**************************************************************\n");
      for (Course c : set) {
        sb.append("result ").append(count).append(": ").append(c).append("\n");
        count++;
      }
      return sb.toString();
    }
    
    // Returns a String by format a given Student set  
    private static String printStudent(Set<Student> set) {
      int count = 1;
      StringBuilder sb = new StringBuilder();
      sb.append("Match Found: ").append(set.size()).append("\n");
      sb.append("**************************************************************")
        .append("**************************************************************\n");
      for (Student s : set) {
        sb.append("result ").append(count).append(": ").append(s).append("\n");
        count++;
      }
      return sb.toString();
    }

    public static void main(String[] args) throws IOException {

      // Initialization
      CourseDatabase cd = new CourseDatabase();
      insertCourseData(cd);
      StudentDatabase sd = new StudentDatabase();
      insertStudentData(sd);
      SearchEngine se = new SearchEngine(cd, sd);
      menu(sd, cd, se);
    }
}







